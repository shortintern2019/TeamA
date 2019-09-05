package com.shogun.suzukisan.controller;

import com.shogun.suzukisan.entity.*;
import com.shogun.suzukisan.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class ConnectController {
    @Autowired
    MenteeService menteeService;
    @Autowired
    MentorService mentorService;
    @Autowired
    MentorGenreService mentorGenreService;
    @Autowired
    MenteeGenreService menteeGenreService;
    @Autowired
    GenreService genreService;
    @Autowired
    RoomService roomService;
    @Autowired
    UserService userService;

    public enum roleEnum {
        mentee,
        mentor
    }

    @RequestMapping(value="/connect", method= RequestMethod.POST)
    public String connect(
            Model model,
            @RequestParam("genre") String[] genres,
            @RequestParam("role") roleEnum role) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        if(authentication.getPrincipal() instanceof User){
            user = User.class.cast(authentication.getPrincipal());
        }else{
            // TODO: ログインしていないときの処理
        }

        // Role
        String roomName = null;
        if(role == roleEnum.mentee) {
            roomName = beMentee(Arrays.asList(genres), user);
        } else if(role == roleEnum.mentor) {
            roomName = beMentor(Arrays.asList(genres), user);
        }

        model.addAttribute("room_name", roomName);

        System.out.println("room Status");
        for (Room room : roomService.findAll()) {
            System.out.println(room.toString());
        }
        System.out.println("------------------");

        return "hello";
    }

    public String beMentee(List<String> genres, User user) {
        Long topMentor = searchBestMentor(genres);
        if(topMentor == (long) -1) {
            System.out.println("no mentor");
            // Menteeを作成
            Mentee newMentee = menteeService.create(new Mentee(user, "newRoom"));
            // MenteeGenreを作成
            for(String genre : genres) {
                // GenreのIdを検索してmenteeに紐付ける
                genreService.findByName(genre).ifPresent(g ->
                        menteeGenreService.create(new MenteeGenre(newMentee, g))
                );
            }

            return "wait";
        } else {
            System.out.println("found: MentorId=" + topMentor.toString());

            // Mentorを検索
            // User
            User topMentorUser = userService.findById(topMentor).get();
            // Mentor
            Mentor topMentorEntity = mentorService.findByUserId(topMentorUser).get();
            // RoomNameを取得
            String tmpRoomName = topMentorEntity.getRoomName();
            // MentorGenre
            List<MentorGenre> topMentorGenreList = mentorGenreService.findByMentorId(topMentorEntity);

            userService.findById(topMentor).ifPresent(u -> {
                        // MentorGenreを削除
                        mentorGenreService.deleteByMentorId(
                                mentorService.findByUserId(u).get()
                        );
                        // Mentorを削除
                        mentorService.deleteByUserId(u);
                    });

            // Roomを作る
            Room newRoom = roomService.create(new Room(tmpRoomName, topMentorUser, user));
            // RoomにGenreを紐付ける
            List<Genre> matchedGenre = null;
            for(MentorGenre mg : topMentorGenreList) {
                for (String g : genres) {
                    if(mg.getGenreId().getName() == g) {
                        matchedGenre.add(mg.getGenreId());
                        mentorGenreService.create(new MentorGenre(topMentorEntity, mg.getGenreId()));
                    }
                }
            }
            return tmpRoomName;
        }
    }

    public String beMentor(List<String> genres, User user) {
        Long topMentee = searchBestMentee(genres);
        if(topMentee == (long) -1) {
            System.out.println("no mentee");
            // Mentorを作成
            Mentor newMentor = mentorService.create(new Mentor(user, "newRoom"));
            // MentorGenreを作成
            for(String genre : genres) {
                // GenreのIdを検索してmentorに紐付ける
                genreService.findByName(genre).ifPresent(g ->
                        mentorGenreService.create(new MentorGenre(newMentor, g))
                );
            }

            return "wait";
        } else {
            System.out.println("found: MenteeId=" + topMentee.toString());

            // Menteeを検索
            // User
            User topMenteeUser = userService.findById(topMentee).get();
            // Mentee
            Mentee topMenteeEntity = menteeService.findByUserId(topMenteeUser).get();
            // RoomNameを取得
            String tmpRoomName = topMenteeEntity.getRoomName();
            // MenteeGenre
            List<MenteeGenre> topMenteeGenreList = menteeGenreService.findByMenteeId(topMenteeEntity);

            userService.findById(topMentee).ifPresent(u -> {
                // MenteeGenreを削除
                menteeGenreService.deleteByMenteeId(
                        menteeService.findByUserId(u).get()
                );
                // Menteeを削除
                menteeService.deleteByUserId(u);
            });

            // Roomを作る
            Room newRoom = roomService.create(new Room(tmpRoomName, topMenteeUser, user));
            // RoomにGenreを紐付ける
            List<Genre> matchedGenre = null;
            for(MenteeGenre mg : topMenteeGenreList) {
                for (String g : genres) {
                    if(mg.getGenreId().getName() == g) {
                        matchedGenre.add(mg.getGenreId());
                        menteeGenreService.create(new MenteeGenre(topMenteeEntity, mg.getGenreId()));
                    }
                }
            }
            return tmpRoomName;
        }
    }

    public Long searchBestMentor(List<String> genre) {
        // Mentorを検索
        List<MentorGenre> mentorGenreList = mentorGenreService.findByGenreName(genre);

        // 候補の総合評価を算出
        // 総合評価 += メンタースコア*メンター回数
        Map<Long, Integer> evaluation = new HashMap<>(); // <UserId, Evaluation>

        for(MentorGenre mgl : mentorGenreList) {
            Integer additionalValue = mgl.getMentorId().getUserId().getMentorScore();
            if(additionalValue == 0) { additionalValue = 1; }
            Integer mentorCount = mgl.getMentorId().getUserId().getMentorCount();
            if(mentorCount == 0) { mentorCount = 1; }

            if(evaluation.containsKey(mgl.getMentorId().getId())) {
                // 2回目以降
                Integer currentValue = evaluation.get(mgl.getMentorId().getId());
                evaluation.put(mgl.getMentorId().getUserId().getId(), currentValue + additionalValue*mentorCount);
            } else {
                // 初回
                evaluation.put(mgl.getMentorId().getUserId().getId(), additionalValue*mentorCount);
            }
        }

        // Top Mentorを検索
        Long topMentorId = (long) -1;
        Integer topMentorValue = 0;
        for(Map.Entry<Long, Integer> entry : evaluation.entrySet()) {
            if(entry.getValue() >= topMentorValue) {
                topMentorId = entry.getKey();
                topMentorValue = entry.getValue();
            }
        }

        return topMentorId;
    }

    public Long searchBestMentee(List<String> genre) {
        // Menteeを検索
        List<MenteeGenre> menteeGenreList = menteeGenreService.findByGenreName(genre);

        // 候補の総合評価を算出
        // 総合評価 += メンタースコア*メンター回数
        Map<Long, Integer> evaluation = new HashMap<>(); // <UserId, Evaluation>

        for(MenteeGenre mgl : menteeGenreList) {
            Integer additionalValue = mgl.getMenteeId().getUserId().getMenteeScore();
            if(additionalValue == 0) { additionalValue = 1; }
            Integer menteeCount = mgl.getMenteeId().getUserId().getMenteeCount();
            if(menteeCount == 0) { menteeCount = 1; }

            if(evaluation.containsKey(mgl.getMenteeId().getId())) {
                // 2回目以降
                Integer currentValue = evaluation.get(mgl.getMenteeId().getId());
                evaluation.put(mgl.getMenteeId().getUserId().getId(), currentValue + additionalValue*menteeCount);
            } else {
                // 初回
                evaluation.put(mgl.getMenteeId().getUserId().getId(), additionalValue*menteeCount);
            }
        }

        // Top Menteeを検索
        Long topMenteeId = (long) -1;
        Integer topMenteeValue = 0;
        for(Map.Entry<Long, Integer> entry : evaluation.entrySet()) {
            if(entry.getValue() >= topMenteeValue) {
                topMenteeId = entry.getKey();
                topMenteeValue = entry.getValue();
            }
        }

        return topMenteeId;
    }

}