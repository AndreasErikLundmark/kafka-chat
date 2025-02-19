package alu.kafka_chat.controller;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/chat")
@RestController
@CrossOrigin
public class ChatController {

    @PostMapping
    public String sendMsg(@RequestParam String topic, @RequestParam String msg) {

        return "Sent message: " + msg;
    }

}
