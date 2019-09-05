package com.shogun.suzukisan;

import com.shogun.suzukisan.entity.*;
import com.shogun.suzukisan.repository.GenreRepository;
import com.shogun.suzukisan.repository.MentorGenreRepository;
import com.shogun.suzukisan.repository.MentorRepository;
import com.shogun.suzukisan.repository.UserRepository;
import com.shogun.suzukisan.service.GenreService;
import com.shogun.suzukisan.service.MentorGenreService;
import com.shogun.suzukisan.service.MentorService;
import com.shogun.suzukisan.service.UserService;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class SuzukisanApplication {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(SuzukisanApplication.class, args);
    }

    @Bean
    public CommandLineRunner addUser(UserRepository repository) {
        return (args) -> {
            // save a couple of User
            repository.save(new User("Jack", "aaaa@example.com", "hashedPass"));
            repository.save(new User("Chloe", "bbb@example.com", "hashedPass"));
            repository.save(new User("Kim", "cccc@example.com", "hashedPass"));
            repository.save(new User("David", "ddd@example.com", "hashedPass"));
            repository.save(new User("Michelle", "eeee@example.com", "hashedPass"));
            repository.save(new User("Jack", "ffff@example.com", "hashedPass"));

            // fetch all User
            log.info("User found with findAll():");
            log.info("-------------------------------");
            for (User user : repository.findAll()) {
                log.info(user.toString());
            }
            log.info("");

            // fetch an individual user by ID
            repository.findById(1L)
                    .ifPresent(user -> {
                        log.info("User found with findById(1L):");
                        log.info("--------------------------------");
                        log.info(user.toString());
                        log.info("");
                    });

            // fetch customers by last name
            log.info("Customer found with findByLastName('Jack'):");
            log.info("--------------------------------------------");
            repository.findByName("Jack").forEach(user -> {
                log.info(user.toString());
            });
            log.info("");
        };
    }

    @Bean
    public CommandLineRunner addGenre(GenreRepository repository) {
        return (args) -> {
            // save a couple of User
            repository.save(new Genre("恋愛", "/images/genre/renai.jpg"));
            repository.save(new Genre("学校", "/images/genre/school.jpg"));
            repository.save(new Genre("就活", "/images/genre/shu-katsu.jpg"));
            repository.save(new Genre("勉強", "/images/genre/study.jpg"));
            repository.save(new Genre("いじめ", "/images/genre/izime.jpg"));

            // fetch all Genre
            log.info("Genre found with findAll():");
            log.info("-------------------------------");
            for (Genre genre : repository.findAll()) {
                log.info(genre.toString());
            }
            log.info("");

            // fetch an individual genre by ID
            repository.findById(1L)
                    .ifPresent(genre -> {
                        log.info("Genre found with findById(1L):");
                        log.info("--------------------------------");
                        log.info(genre.toString());
                        log.info("");
                    });
            log.info("");
        };
    }

    @Bean
    public CommandLineRunner addMentor(MentorRepository mentorRepository, UserRepository userRepository) {
        return (args) -> {
            // save a couple of User
            userRepository.findById(1L)
                    .ifPresent(user -> {
                        mentorRepository.save(new Mentor(user, "weffewe"));
                    });

            // fetch all Genre
            log.info("Mentor found with findAll():");
            log.info("-------------------------------");
            for (Mentor mentor : mentorRepository.findAll()) {
                log.info(mentor.toString());
            }
            log.info("");
        };
    }

    @Bean
    public CommandLineRunner addMentorGenre(UserService userService, GenreService genreService, MentorService mentorService, MentorGenreService mentorGenreService) {
        return (args) -> {
            // CREATE USER
            User test1User = userService.create(new User("MentorGenreTest1", "mentorGenreTest1@example.com", "hashedPass"));
            User test2User = userService.create(new User("MentorGenreTest2", "mentorGenreTest2@example.com", "hashedPass"));
            User test3User = userService.create(new User("MentorGenreTest3", "mentorGenreTest3@example.com", "hashedPass"));
            User test4User = userService.create(new User("MentorGenreTest4", "mentorGenreTest4@example.com", "hashedPass"));
            // CREATE GENRE

            Genre genre1 = genreService.create(new Genre("恋愛", "/images/genre/renai.jpg"));
            Genre genre2 = genreService.create(new Genre("学校", "/images/genre/school.jpg"));
            Genre genre3 = genreService.create(new Genre("就活", "/images/genre/shu-katsu.jpg"));
            Genre genre4 = genreService.create(new Genre("勉強", "/images/genre/study.jpg"));
            Genre genre5 = genreService.create(new Genre("いじめ", "/images/genre/izime.jpg"));
            // CREATE Mentor
            Mentor mentor1 = mentorService.create(new Mentor(test1User, "mentorGenreRoom1Name"));
            Mentor mentor2 = mentorService.create(new Mentor(test2User, "mentorGenreRoom2Name"));
            Mentor mentor3 = mentorService.create(new Mentor(test3User, "mentorGenreRoom3Name"));
            Mentor mentor4 = mentorService.create(new Mentor(test4User, "mentorGenreRoom4Name"));
            // CREATE MentorGenre
            MentorGenre mentorGenre1 = mentorGenreService.create(new MentorGenre(mentor1, genre1));
            MentorGenre mentorGenre2 = mentorGenreService.create(new MentorGenre(mentor1, genre2));
            MentorGenre mentorGenre3 = mentorGenreService.create(new MentorGenre(mentor2, genre1));
            MentorGenre mentorGenre4 = mentorGenreService.create(new MentorGenre(mentor2, genre2));
            MentorGenre mentorGenre5 = mentorGenreService.create(new MentorGenre(mentor2, genre3));
            MentorGenre mentorGenre6 = mentorGenreService.create(new MentorGenre(mentor3, genre3));
            MentorGenre mentorGenre7 = mentorGenreService.create(new MentorGenre(mentor4, genre1));
            MentorGenre mentorGenre8 = mentorGenreService.create(new MentorGenre(mentor4, genre2));
            MentorGenre mentorGenre9 = mentorGenreService.create(new MentorGenre(mentor4, genre3));
            MentorGenre mentorGenre10 = mentorGenreService.create(new MentorGenre(mentor4, genre4));
            MentorGenre mentorGenre11 = mentorGenreService.create(new MentorGenre(mentor4, genre5));

            // fetch all MentorGenre
            log.info("Mentor found with findAll():");
            log.info("-------------------------------");
            for (MentorGenre mentorGenre : mentorGenreService.findAll()) {
                log.info(mentorGenre.toString());
            }
            log.info("");
        };
    }

}
