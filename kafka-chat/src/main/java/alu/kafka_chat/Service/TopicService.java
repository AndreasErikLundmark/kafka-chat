package alu.kafka_chat.Service;

import alu.kafka_chat.model.Topic;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TopicService {

    private final AdminClient adminClient;


    public TopicService(AdminClient adminClient) {
        this.adminClient = adminClient;
    }

    public List<Topic> getAllTopics() {
        return null;
    }

    public Topic getTopic(String topicName) {
        return null;
    }
    public NewTopic createTopic(String topicName) {
        NewTopic newTopic = new NewTopic(topicName, 3, (short) 1); // 3 partitions, 1 replica

        adminClient.createTopics(Collections.singletonList(newTopic));
        return newTopic;
    }


}
