package com.shogun.suzukisan.service;

import com.shogun.suzukisan.entity.ConversationLog;
import com.shogun.suzukisan.entity.ConversationLogGenre;
import com.shogun.suzukisan.entity.Genre;
import com.shogun.suzukisan.repository.ConversationLogGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConversationLogGenreService {
    @Autowired
    ConversationLogGenreRepository conversationLogGenreRepository;

    // CREATE
    public ConversationLogGenre create(ConversationLogGenre conversationLogGenre) {
        return conversationLogGenreRepository.save(conversationLogGenre);
    }

    // READ
    public Iterable<ConversationLogGenre> findAll() {
        return conversationLogGenreRepository.findAll();
    }
    public Optional<ConversationLogGenre> findById(Long id) {
        return conversationLogGenreRepository.findById(id);
    }
    public List<ConversationLogGenre> findByConversationLog(ConversationLog conversationLog) {
        return  conversationLogGenreRepository.findByConversationLogId(conversationLog);
    }
    public List<ConversationLogGenre> findByGenreId(Genre genre) {
        return conversationLogGenreRepository.findByGenreId(genre);
    }

    // DELETE
    public void deleteById(Long id) {
        conversationLogGenreRepository.deleteById(id);
    }
    public void deleteByConversationLogId(ConversationLog conversationLog) {
        conversationLogGenreRepository.deleteByConversationLogId(conversationLog);
    }
    public void deleteByGenreId(Genre genre) {
        conversationLogGenreRepository.deleteByGenreId(genre);
    }
}
