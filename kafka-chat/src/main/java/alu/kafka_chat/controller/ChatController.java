package alu.kafka_chat.controller;

import alu.kafka_chat.Service.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.Set;
import java.util.concurrent.ExecutionException;


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

    //    base_url/chat?topic=test-topic&msg=Hello Kafka is working cool!
    @PostMapping
    public ResponseEntity<String> sendMsg(@RequestParam String topic, @RequestParam String msg) {
        template.send(topic, msg);
        return ResponseEntity.status(HttpStatus.OK).body("Sent message: " + msg);
    }

    //    base_url/chat/newTopic?topic=newTopicName
    @PostMapping("/newTopic")
    public ResponseEntity<String> newTopic(@RequestParam String topic) {

        if (topicService.createTopic(topic)) {
            return ResponseEntity.status(HttpStatus.OK).body("New topic: " + topic);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create topic: " + topic);
        }
    }

    //  base_url/chat/getTopics
    @GetMapping("/getTopics")
    public ResponseEntity<Set<String>> getTopics() {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(topicService.getAllTopics());
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

