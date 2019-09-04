package com.shogun.suzukisan;

import com.shogun.suzukisan.entity.*;
import com.shogun.suzukisan.repository.GenreRepository;
import com.shogun.suzukisan.repository.MentorGenreRepository;
import com.shogun.suzukisan.repository.MentorRepository;
import com.shogun.suzukisan.repository.UserRepository;
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
            repository.save(new User("Jack", "Bauer@example.com", "hashedPass"));
            repository.save(new User("Chloe", "Bauer@example.com", "hashedPass"));
            repository.save(new User("Kim", "Bauer@example.com", "hashedPass"));
            repository.save(new User("David", "Bauer@example.com", "hashedPass"));
            repository.save(new User("Michelle", "Bauer@example.com", "hashedPass"));
            repository.save(new User("Jack", "Bauer@example.com", "hashedPass"));


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
            repository.save(new Genre("恋愛"));
            repository.save(new Genre("勉強"));
            repository.save(new Genre("人間関係"));
            repository.save(new Genre("人生"));
            repository.save(new Genre("将来"));
            repository.save(new Genre("その他"));


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
    public CommandLineRunner addMentorGenre(MentorRepository mentorRepository,
                                            MentorGenreRepository mentorGenreRepository,
                                            GenreRepository genreRepository) {
        return (args) -> {

            // fetch all Genre
            Mentor mt = new Mentor();
            Genre gr = new Genre();
            for (Mentor mentor : mentorRepository.findAll()) { mt = mentor; }
            for (Genre genre: genreRepository.findAll()) { gr = genre; }
            mentorGenreRepository.save(new MentorGenre(mt, gr));

            // fetch all MentorGenre
            log.info("Mentor found with findAll():");
            log.info("-------------------------------");
            for (MentorGenre mentorGenre : mentorGenreRepository.findAll()) {
                log.info(mentorGenre.toString());
            }
            log.info("");
        };
    }

}
