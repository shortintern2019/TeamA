package com.shogun.suzukisan.controller;

import com.shogun.suzukisan.entity.NotifyRoom;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotifyRoomNameController {

    @PostMapping("/notify_room_name")
    public void notifyRoomName(@RequestBody NotifyRoom notifyRoom) {

    }
}