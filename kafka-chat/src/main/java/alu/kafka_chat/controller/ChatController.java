package alu.kafka_chat.controller;

import alu.kafka_chat.Service.TopicService;
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
    private final TopicService topicService;

    public ChatController(KafkaTemplate<String, String> template, TopicService topicService) {
        this.template = template;
        this.topicService = topicService;
    }


//    http://localhost:8081/chat?topic=test-topic&msg=Hello Kafka is working cool!
    @PostMapping
    public ResponseEntity<String> sendMsg(@RequestParam String topic, @RequestParam String msg) {
        template.send(topic, msg);
        return ResponseEntity.status(HttpStatus.OK).body("Sent message: " + msg);
    }

    @PostMapping("/newTopic")
    public ResponseEntity<String> newTopic(@RequestParam String topic) {
        if(topicService.createTopic(topic)!=null) {
            return ResponseEntity.status(HttpStatus.OK).body("New topic: " + topic);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create topic: " + topic);

    }

}
