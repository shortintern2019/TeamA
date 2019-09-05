package com.shogun.suzukisan;

import com.shogun.suzukisan.entity.*;
import com.shogun.suzukisan.repository.GenreRepository;
import com.shogun.suzukisan.repository.UserRepository;
import com.shogun.suzukisan.service.*;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SuzukisanApplicationTests {

    final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    UserRepository userRepository;
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    MentorService mentorService;
    @Autowired
    MentorGenreService mentorGenreService;
    @Autowired
    MenteeService menteeService;
    @Autowired
    MenteeGenreService menteeGenreService;
    @Autowired
    RoomService roomService;
    @Autowired
    ConversationLogService conversationLogService;

    @Test
    public void mentorServiceCrudTest() {
        // CREATE USER
        User test1User = userRepository.save(new User("MentorTest1", "mentorTest1@example.com", "hashedPass"));
        User test2User = userRepository.save(new User("MentorTest2", "mentorTest2@example.com", "hashedPass"));
        User test3User = userRepository.save(new User("MentorTest3", "mentorTest3@example.com", "hashedPass"));
        User test4User = userRepository.save(new User("MentorTest4", "mentorTest4@example.com", "hashedPass"));

        log.info("Mentor Service CRUD Test:");
        // CREATE Mentor
        Mentor mentor1 = mentorService.create(new Mentor(test1User, "mentorRoom1Name"));
        Mentor mentor2 = mentorService.create(new Mentor(test2User, "mentorRoom2Name"));
        Mentor mentor3 = mentorService.create(new Mentor(test3User, "mentorRoom3Name"));
        Mentor mentor4 = mentorService.create(new Mentor(test4User, "mentorRoom4Name"));

        // READ Mentor
        // findAll
        for (Mentor mentor : mentorService.findAll()) {
            log.info(mentor.toString());
        }
        // findById
        log.info(mentorService.findById(mentor1.getId()).toString());
        // findByUser
        log.info(mentorService.findByUserId(test2User).toString());
        // findByRoomName
        log.info(mentorService.findByRoomName("mentorRoom3Name").toString());

        // DELETE Mentor
        // deleteById
        mentorService.deleteById(mentor4.getId());
        // deleteByUser
        mentorService.deleteByUserId(test3User);
        // deleteByRoomName
        mentorService.deleteByRoomName("mentorRoom2Name");

        // FINAL CHECK
        // findAll
        for (Mentor mentor : mentorService.findAll()) {
            log.info(mentor.toString());
        }
        log.info("--------------------------");
    }

    @Test
    public void mentorGenreServiceCrudTest() {
        // CREATE USER
        User test1User = userRepository.save(new User("MentorGenreTest1", "mentorGenreTest1@example.com", "hashedPass"));
        User test2User = userRepository.save(new User("MentorGenreTest2", "mentorGenreTest2@example.com", "hashedPass"));
        User test3User = userRepository.save(new User("MentorGenreTest3", "mentorGenreTest3@example.com", "hashedPass"));
        User test4User = userRepository.save(new User("MentorGenreTest4", "mentorGenreTest4@example.com", "hashedPass"));
        // CREATE GENRE
        Genre genre1 = genreRepository.save(new Genre("MentorGenreTest1"));
        Genre genre2 = genreRepository.save(new Genre("MentorGenreTest2"));
        Genre genre3 = genreRepository.save(new Genre("MentorGenreTest3"));
        // CREATE Mentor
        Mentor mentor1 = mentorService.create(new Mentor(test1User, "mentorGenreRoom1Name"));
        Mentor mentor2 = mentorService.create(new Mentor(test2User, "mentorGenreRoom2Name"));
        Mentor mentor3 = mentorService.create(new Mentor(test3User, "mentorGenreRoom3Name"));
        Mentor mentor4 = mentorService.create(new Mentor(test4User, "mentorGenreRoom4Name"));

        log.info("Mentor Genre Service CRUD Test:");
        // CREATE MentorGenre
        MentorGenre mentorGenre1 = mentorGenreService.create(new MentorGenre(mentor1, genre1));
        MentorGenre mentorGenre2 = mentorGenreService.create(new MentorGenre(mentor1, genre2));
        MentorGenre mentorGenre3 = mentorGenreService.create(new MentorGenre(mentor2, genre1));
        MentorGenre mentorGenre4 = mentorGenreService.create(new MentorGenre(mentor2, genre2));
        MentorGenre mentorGenre5 = mentorGenreService.create(new MentorGenre(mentor2, genre3));
        MentorGenre mentorGenre6 = mentorGenreService.create(new MentorGenre(mentor3, genre3));

        // READ MentorGenre
        // findAll
        for (MentorGenre mentorGenre : mentorGenreService.findAll()) { // 6個
            log.info(mentorGenre.toString());
        }
        // findById
        log.info(mentorGenreService.findById(mentorGenre1.getId()).toString()); // 1個
        // findByMentor
        log.info(mentorGenreService.findByMentorId(mentor4).toString()); // 0個
        log.info(mentorGenreService.findByMentorId(mentor2).toString()); // 3個
        // findByGenre
        log.info(mentorGenreService.findByGenreId(genre3).toString()); // 2個

        // DELETE MentorGenre
        // deleteById
        mentorGenreService.deleteById(mentorGenre1.getId());
        // deleteByMentor
        mentorGenreService.deleteByMentorId(mentor1);
        // deleteByGenre
        mentorGenreService.deleteByGenreId(genre3);

        // FINAL CHECK
        // findAll
        for (MentorGenre mentorGenre : mentorGenreService.findAll()) { // 2個
            log.info(mentorGenre.toString());
        }
        log.info("--------------------------");
    }

//    (参照整合性制約違反)が起こる
//    @Test
//    public void Mentorを消すとMentorGenreも消えるのかTest() {
//        // CREATE USER
//        User test1User = userRepository.save(new User("relationTest1", "relationTest1@example.com", "hashedPass"));
//        User test2User = userRepository.save(new User("relationTest2", "relationTest2@example.com", "hashedPass"));
//        User test3User = userRepository.save(new User("relationTest3", "relationTest3@example.com", "hashedPass"));
//        User test4User = userRepository.save(new User("relationTest4", "relationTest4@example.com", "hashedPass"));
//        // CREATE GENRE
//        Genre genre1 = genreRepository.save(new Genre("relationTest1"));
//        Genre genre2 = genreRepository.save(new Genre("relationTest2"));
//        Genre genre3 = genreRepository.save(new Genre("relationTest3"));
//        // CREATE Mentor
//        Mentor mentor1 = mentorService.create(new Mentor(test1User, "relation1Name"));
//        Mentor mentor2 = mentorService.create(new Mentor(test2User, "relation2Name"));
//        Mentor mentor3 = mentorService.create(new Mentor(test3User, "relationName"));
//        Mentor mentor4 = mentorService.create(new Mentor(test4User, "relation4Name"));
//        // CREATE MentorGenre
//        MentorGenre mentorGenre1 = mentorGenreService.create(new MentorGenre(mentor1, genre1));
//        MentorGenre mentorGenre2 = mentorGenreService.create(new MentorGenre(mentor1, genre2));
//        MentorGenre mentorGenre3 = mentorGenreService.create(new MentorGenre(mentor2, genre1));
//        MentorGenre mentorGenre4 = mentorGenreService.create(new MentorGenre(mentor2, genre2));
//        MentorGenre mentorGenre5 = mentorGenreService.create(new MentorGenre(mentor2, genre3));
//        MentorGenre mentorGenre6 = mentorGenreService.create(new MentorGenre(mentor3, genre3));
//
//        // Mentor1を消す
//        mentorService.deleteById(mentor1.getId());
//
//        // mentorGenreをすべて表示
//        // mentorGenre1, mentorGenre2は消えているはず
//        log.info("relationTest");
//        for (MentorGenre mentorGenre : mentorGenreService.findAll()) { // 2個
//            log.info(mentorGenre.toString());
//        }
//        log.info("--------------------------");
//    }

    @Test
    public void roomCrudTest() {
        // CREATE USER
        User test1User = userRepository.save(new User("RoomTest1", "RoomTest1@example.com", "hashedPass"));
        User test2User = userRepository.save(new User("RoomTest2", "RoomTest2@example.com", "hashedPass"));
        User test3User = userRepository.save(new User("RoomTest3", "RoomTest3@example.com", "hashedPass"));
        User test4User = userRepository.save(new User("RoomTest4", "RoomTest4@example.com", "hashedPass"));
        User test5User = userRepository.save(new User("RoomTest5", "RoomTest5@example.com", "hashedPass"));
        User test6User = userRepository.save(new User("RoomTest6", "RoomTest6@example.com", "hashedPass"));
        User test7User = userRepository.save(new User("RoomTest7", "RoomTest7@example.com", "hashedPass"));
        User test8User = userRepository.save(new User("RoomTest8", "RoomTest8@example.com", "hashedPass"));


        log.info("Room Service CRUD Test:");
        // CREATE ROom
        Room room1 = roomService.create(new Room("testRoom1", test1User, test2User));
        Room room2 = roomService.create(new Room("testRoom2", test3User, test4User));
        Room room3 = roomService.create(new Room("testRoom3", test5User, test6User));
        Room room4 = roomService.create(new Room("testRoom4", test7User, test1User));
        Room room5 = roomService.create(new Room("testRoom5", test8User, test8User));


        // READ MentorGenre
        // findAll
        for (Room room : roomService.findAll()) { // 5個
            log.info(room.toString());
        }
        // findById
        log.info(roomService.findById(room1.getId()).toString()); // 1個
        // findByName
        log.info(roomService.findByName("testRoom1").toString()); // 1個
        // findByMentor
        log.info(roomService.findByMentorId(test1User).toString()); // 1個
        log.info(roomService.findByMentorId(test4User).toString()); // 0個
        // findByMentee
        log.info(roomService.findByMenteeId(test6User).toString()); // 1個

        // DELETE MentorGenre
        // deleteById
        roomService.deleteById(room1.getId());
        // deleteByName
        roomService.deleteByName("testRoom3");
        // deleteByMentor
        roomService.deleteByMentorId(test7User);
        // deleteByMentee
        roomService.deleteByMenteeId(test4User);

        // FINAL CHECK
        // findAll
        for (Room room : roomService.findAll()) { // 1個
            log.info(room.toString());
        }
        log.info("--------------------------");
    }

    @Test
    public void conversationLogCrudTest() {
        // CREATE USER
        User test1User = userRepository.save(new User("ConversationTest1", "ConversationTest1@example.com", "hashedPass"));
        User test2User = userRepository.save(new User("ConversationTest2", "ConversationTest2@example.com", "hashedPass"));
        User test3User = userRepository.save(new User("ConversationTest3", "ConversationTest3@example.com", "hashedPass"));
        User test4User = userRepository.save(new User("ConversationTest4", "ConversationTest4@example.com", "hashedPass"));
        User test5User = userRepository.save(new User("ConversationTest5", "ConversationTest5@example.com", "hashedPass"));


        log.info("Conversation Service CRUD Test:");
        // CREATE Conversation
        ConversationLog conversationLog1 = conversationLogService.create(
                new ConversationLog(test1User, test2User, "ConversationRoom1", 4, 1)
        );
        ConversationLog conversationLog2 = conversationLogService.create(
                new ConversationLog(test3User, test2User, "ConversationRoom2", 1, 1)
        );
        ConversationLog conversationLog3 = conversationLogService.create(
                new ConversationLog(test1User, test3User, "ConversationRoom3", 3, 3)
        );
        ConversationLog conversationLog4 = conversationLogService.create(
                new ConversationLog(test1User, test2User, "ConversationRoom4", 2, 5)
        );
        ConversationLog conversationLog5 = conversationLogService.create(
                new ConversationLog(test5User, test1User, "ConversationRoom1", 4, 4)
        );


        // READ ConversationLog
        // findAll
        for (ConversationLog conversationLog : conversationLogService.findAll()) { // 5個
            log.info(conversationLog.toString());
        }
        // findById
        log.info(conversationLogService.findById(conversationLog1.getId()).toString()); // 1個
        // findByMentorId
        log.info(conversationLogService.findByMentorId(test4User).toString()); // 0個
        // findByRoomName
        log.info(conversationLogService.findByRoomName("ConversationRoom1").toString()); // 2個
        // findByMentorScoreEquals
        log.info(conversationLogService.findByMentorScoreEquals(4).toString()); // 2個
        // findByMentorScoreGraterThanEqual
        log.info(conversationLogService.findByMentorScoreGraterThanEqual(3).toString()); // 3個
        // findByMentorScoreLessThanEqual
        log.info(conversationLogService.findByMenteeScoreLessThanEqual(2).toString()); // 2個

        // DELETE MentorGenre
        // deleteById
        conversationLogService.deleteById(conversationLog1.getId());

        // FINAL CHECK
        // findAll
        for (ConversationLog conversationLog : conversationLogService.findAll()) { // 4個
            log.info(conversationLog.toString());
        }
        log.info("--------------------------");
    }

    @Test
    public void canUserBeMentorTest() {
        Long topMentor = searchBestMentor(Arrays.asList("ッッ"));
        if(topMentor == (long) -1) {
            log.info("no mentor");
//            menteeService.create(new Mentee())
        } else {
            log.info("found: MentorId=" + topMentor.toString());
        }

        // Menteeがいる場合
        // -> MenteeName, MenteeRate, RoomNameを返す
        // -> Roomを作る
        // -> Menteeを削除
        // -> MenteeGenreを

        // Mentorがいない場合
        // -> Menteeを作成
        // -> MenteeGenreを作成
    }

    Long searchBestMentor(List<String> genre) {
        // Menteeを検索
        List<MentorGenre> mentorGenreList = mentorGenreService.findByGenreName(genre);

        // 候補の総合評価を算出
        // 総合評価 += メンタースコア*メンター回数
        Map<Long, Integer> evaluation = new HashMap<>(); // <MentorId, Evaluation>

        for(MentorGenre mgl : mentorGenreList) {
            Integer additionalValue = mgl.getMentorId().getUserId().getMentorScore();
            if(additionalValue == 0) { additionalValue = 1; }
            Integer mentorCount = mgl.getMentorId().getUserId().getMentorCount();
            if(mentorCount == 0) { mentorCount = 1; }

            if(evaluation.containsKey(mgl.getMentorId().getId())) {
                // 2回目以降
                Integer currentValue = evaluation.get(mgl.getMentorId().getId());
                evaluation.put(mgl.getMentorId().getId(), currentValue + additionalValue*mentorCount);
            } else {
                // 初回
                evaluation.put(mgl.getMentorId().getId(), additionalValue*mentorCount);
            }
        }

        // Top Mentorを検索
        Long topMentorId = (long) -1;
        Integer topMentorValue = 0;
        for(Map.Entry<Long, Integer> entry : evaluation.entrySet()) {
            if(entry.getValue() >= topMentorId) {
                topMentorId = entry.getKey();
                topMentorValue = entry.getValue();
            }
        }

        return topMentorId;
    }
}
