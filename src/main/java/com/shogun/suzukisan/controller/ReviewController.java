package com.shogun.suzukisan.controller;

import com.shogun.suzukisan.entity.ConversationLog;
import com.shogun.suzukisan.entity.ConversationLogGenre;
import com.shogun.suzukisan.entity.Room;
import com.shogun.suzukisan.entity.RoomGenre;
import com.shogun.suzukisan.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ReviewController {
    @Autowired
    RoomService roomService;
    @Autowired
    RoomGenreService roomGenreService;
    @Autowired
    ConversationLogService conversationLogService;
    @Autowired
    ConversationLogGenreService conversationLogGenreService;
    @Autowired
    UserService userService;


    @RequestMapping(value="/review", method= RequestMethod.POST)
    public String review(
            Model model,
            @RequestParam("roomName") String roomName,
            @RequestParam("ratingScore") Integer ratingScore,
            @RequestParam("role") String role
    ) {
        model.addAttribute("name", "review");

        System.out.println(ratingScore);
        System.out.println(roomName);
        System.out.println(role);

        Integer mentorScoreTmp = 0;
        Integer menteeScoreTmp = 0;

        if(role.equals("mentee")) {
            mentorScoreTmp = ratingScore;
        } else if(role.equals("mentor")) {
            menteeScoreTmp = ratingScore;
        }

        // roomNameを使ってRoomを検索
        if(roomService.findByName(roomName).isPresent()) {
            // -> roomが存在する場合
            Room roomTmp = roomService.findByName(roomName).get();
            List<RoomGenre> roomGenreTmp = roomGenreService.findByRoomId(roomTmp);
            // roomGenreを削除
            roomGenreService.deleteByRoomId(roomTmp);
            // roomを削除
            roomService.deleteByName(roomName);
            // conversationLogを作成
            ConversationLog clTmp = conversationLogService.create(
                    new ConversationLog(roomTmp.getMentorId(), roomTmp.getMenteeId(), roomName, mentorScoreTmp, menteeScoreTmp)
            );
            // conversationLogGenreを作成
            for(RoomGenre rg : roomGenreTmp) {
                conversationLogGenreService.create(new ConversationLogGenre(clTmp, rg.getGenreId()));
            }
            // 相手のUser評価をUpdate
            if(role.equals("mentee")) {
                System.out.println("first-mentee called");
                Long counterPart = roomTmp.getMentorId().getId();
                Integer counterPartCount = roomTmp.getMentorId().getMentorCount()+1;
                Integer counterPartScore = roomTmp.getMentorId().getMentorScore();
                System.out.println(counterPartCount);
                userService.updateMentorCount(counterPartCount, counterPart);
                userService.updateMentorScore(counterPartScore+ratingScore, counterPart);
            } else if(role.equals("mentor")) {
                System.out.println("first-mentor called");
                Long counterPart = roomTmp.getMenteeId().getId();
                Integer counterPartCount = roomTmp.getMenteeId().getMenteeCount()+1;
                Integer counterPartScore = roomTmp.getMenteeId().getMenteeScore();
                userService.updateMenteeCount(counterPartCount, counterPart);
                userService.updateMenteeScore(counterPartScore+ratingScore, counterPart);
            }
        } else {
            // -> roomが存在しない場合
            // conversationLogを検索
            ConversationLog cl = conversationLogService.findByRoomName(roomName).get(0);
            if(role.equals("mentee")) {
                System.out.println("second-mentee called");
                // conversationLogのUser評価をUpdate
                conversationLogService.updateMentorScore(ratingScore, cl.getId());
                // 相手のUser評価をUpdate
                Long counterPart = cl.getMentorId().getId();
                Integer counterPartCount = cl.getMentorId().getMentorCount()+1;
                Integer counterPartScore = cl.getMentorId().getMentorScore();
                userService.updateMentorCount(counterPartCount, counterPart);
                userService.updateMentorScore(counterPartScore+ratingScore, counterPart);
            } else if(role.equals("mentor")) {
                System.out.println("second-mentor called");
                // conversationLogのUser評価をUpdate
                conversationLogService.updateMenteeScore(ratingScore, cl.getId());
                // 相手のUser評価をUpdate
                Long counterPart = cl.getMenteeId().getId();
                Integer counterPartCount = cl.getMenteeId().getMenteeCount()+1;
                Integer counterPartScore = cl.getMenteeId().getMenteeScore();
                userService.updateMenteeCount(counterPartCount, counterPart);
                userService.updateMenteeScore(counterPartScore+ratingScore, counterPart);
            }
        }

        return "root";
    }

}