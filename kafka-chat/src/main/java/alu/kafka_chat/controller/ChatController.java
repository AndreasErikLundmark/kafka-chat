package alu.kafka_chat.controller;

import alu.kafka_chat.Service.TopicService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    //    http://localhost:8081/chat?topic=test-topic&msg=Hello Kafka is working cool!
    @PostMapping
    public ResponseEntity<String> sendMsg(@RequestParam String topic, @RequestParam String msg) {
        template.send(topic, msg);
        return ResponseEntity.status(HttpStatus.OK).body("Sent message: " + msg);
    }

    //    http://localhost:8081/chat/newTopic?topic=newTopicName
    @PostMapping("/newTopic")
    public ResponseEntity<String> newTopic(@RequestParam String topic) {

        if (topicService.createTopic(topic)) {
            return ResponseEntity.status(HttpStatus.OK).body("New topic: " + topic);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create topic: " + topic);
        }
    }

    //  http://localhost:8081/chat/getTopics
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
//
//    @PostMapping("/newSubTopic")
//    public ResponseEntity<String> createSubtopic(@RequestParam String mainTopic, @RequestParam String subTopic) {
//        String fullTopic = mainTopic + "." + subTopic;
//        topicService.createTopic(fullTopic);
//        System.out.println("adding topic: " + fullTopic);
////        kafkaAdmin.createOrModifyTopics(new NewTopic(fullTopic, 3, (short) 1));
//        return ResponseEntity.ok("Created topic: " + fullTopic);
//    }

}

