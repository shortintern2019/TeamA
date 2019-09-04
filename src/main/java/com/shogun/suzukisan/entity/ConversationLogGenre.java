package com.shogun.suzukisan.entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class ConversationLogGenre {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotNull
    @ManyToOne
    private ConversationLog conversationLogId;
    @NotNull
    @ManyToOne
    private Genre genreId;

    protected ConversationLogGenre() {}

    @Autowired
    public ConversationLogGenre(ConversationLog conversationLogId, Genre genreId) {
        this.conversationLogId = conversationLogId;
        this.genreId = genreId;
    }

    @Override
    public String toString() {
        return "ConversationLogGenre{" +
                "id=" + id +
                ", conversationLogId=" + conversationLogId +
                ", genreId=" + genreId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConversationLog getConversationLogId() {
        return conversationLogId;
    }

    public void setConversationLogId(ConversationLog conversationLogId) {
        this.conversationLogId = conversationLogId;
    }

    public Genre getGenreId() {
        return genreId;
    }

    public void setGenreId(Genre genreId) {
        this.genreId = genreId;
    }
}
