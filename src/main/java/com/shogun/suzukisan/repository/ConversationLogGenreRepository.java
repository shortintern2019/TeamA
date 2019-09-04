package com.shogun.suzukisan.repository;

import com.shogun.suzukisan.entity.ConversationLog;
import com.shogun.suzukisan.entity.ConversationLogGenre;
import com.shogun.suzukisan.entity.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConversationLogGenreRepository extends CrudRepository<ConversationLogGenre, Long> {
    List<ConversationLogGenre> findByConversationLogId(ConversationLog conversationLogId);
    List<ConversationLogGenre> findByGenreId(Genre genreId);
}
