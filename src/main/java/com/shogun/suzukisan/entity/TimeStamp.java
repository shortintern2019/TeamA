package com.shogun.suzukisan.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@MappedSuperclass
@NoArgsConstructor
@Data
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


    public Date getCreatedDatetime() { return createdDatetime; }
    public void setCreatedDatetime(Date createdDatetime) { this.createdDatetime = createdDatetime; }
    public Date getUpdatedDatetime() { return updatedDatetime; }
    public void setUpdatedDatetime(Date updatedDatetime) { this.updatedDatetime = updatedDatetime; }
}
