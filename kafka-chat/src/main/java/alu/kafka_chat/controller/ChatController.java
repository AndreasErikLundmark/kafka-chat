package alu.kafka_chat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;


/**
 * Producer
 */
@RequestMapping("/chat")
@RestController
@CrossOrigin
public class ChatController {

    private final KafkaTemplate<String, String> template;

    public ChatController(KafkaTemplate<String, String> template) {
        this.template = template;
    }



    @PostMapping
    public ResponseEntity<String> sendMsg(@RequestParam String topic, @RequestParam String msg) {
        template.send(topic, msg);

        return ResponseEntity.status(HttpStatus.OK).body("Sent message: " + msg);

    }

}
