package alu.kafka_chat.model;

import org.springframework.stereotype.Component;


public class Topic {

    public String name;

    public Topic(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
