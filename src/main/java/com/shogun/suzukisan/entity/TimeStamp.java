package com.shogun.suzukisan.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class TimeStamp {
    @Column(name = "created_datetime")
    private Date createdDatetime;

    @Column(name = "updated_datetime")
    private Date updatedDatetime;

    @PrePersist
    public void onPrePersist() {
        setCreatedDatetime(new Date());
        setCreatedDatetime(new Date());
    }

    @PreUpdate
    public void onPreUpdate() {
        setUpdatedDatetime(new Date());
    }
}
