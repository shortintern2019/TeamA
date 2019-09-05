package com.shogun.suzukisan.controller;

import com.shogun.suzukisan.entity.Mentee;
import com.shogun.suzukisan.entity.NotifyRoom;
import com.shogun.suzukisan.entity.User;
import com.shogun.suzukisan.service.MenteeService;
import com.shogun.suzukisan.service.MentorService;
import com.shogun.suzukisan.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotifyRoomNameController {
    @Autowired
    MentorService mentorService;
    @Autowired
    MenteeService menteeService;

    @PostMapping("/notify_room_name")
    public void notifyRoomName(@RequestBody NotifyRoom notifyRoom) {
        System.out.println("-- Request Data --");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        if(authentication.getPrincipal() instanceof User){
            user = User.class.cast(authentication.getPrincipal());
            System.out.println(user.toString());
        }else{
            // TODO: ログインしていないときの処理
        }

        if(notifyRoom.getRole().equals("mentee")) {
            menteeService.updateRoomName(notifyRoom.getRoom(), user);
        } else if(notifyRoom.getRole().equals("mentor")) {
            mentorService.updateRoomName(notifyRoom.getRoom(), user);
        }

        System.out.println(notifyRoom.getRoom());
        System.out.println(notifyRoom.getRole());
        System.out.println("------------------");

    }
}
