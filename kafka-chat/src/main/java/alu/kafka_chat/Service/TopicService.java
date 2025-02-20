package alu.kafka_chat.Service;

import alu.kafka_chat.model.Topic;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@Service
public class TopicService {

    private final AdminClient adminClient;


    public TopicService(AdminClient adminClient) {
        this.adminClient = adminClient;
    }

    public Set<String> getAllTopics() throws ExecutionException, InterruptedException {
        ListTopicsResult topics = adminClient.listTopics();
        return topics.names().get();
    }

    public Topic getTopic(String topicName) {
        return null;
    }

    public boolean createTopic(String topicName) {
        NewTopic newTopic = new NewTopic(topicName, 3, (short) 1); // 3 partitions, 1 replica
        try {
            adminClient.createTopics(Collections.singletonList(newTopic)).all().get();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



}
