package com.shogun.suzukisan.repository;

import com.shogun.suzukisan.entity.ConversationLog;
import com.shogun.suzukisan.entity.ConversationLogGenre;
import com.shogun.suzukisan.entity.Genre;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ConversationLogGenreRepository extends CrudRepository<ConversationLogGenre, Long> {
    List<ConversationLogGenre> findByConversationLogId(ConversationLog conversationLogId);
    List<ConversationLogGenre> findByGenreId(Genre genreId);
    @Modifying
    @Transactional
    void deleteByConversationLogId(ConversationLog conversationLog);
    @Modifying
    @Transactional
    void deleteByGenreId(Genre genre);
}
