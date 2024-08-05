package com.exagonal.tasks.application.services;

import com.exagonal.tasks.domain.model.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TaskKafkaListener {

    private final TaskService taskService;

    private final ObjectMapper objectMapper;


    public TaskKafkaListener( TaskService taskService, ObjectMapper objectMapper) {
        this.taskService = taskService;
        this.objectMapper = objectMapper;
    }

//
//    @KafkaListener(id ="taskListner", topics ="mercadona-prueba", groupId = "group_id")
//    public void listen(Task task) {
//
//        try {
//            String jsonString = objectMapper.writeValueAsString(task);
//            log.info("Received message: {}", jsonString);
//        } catch (Exception e) {
//            log.error("Error processing message", e);        }
//    }
//

    @KafkaListener(id ="taskListner", topics ="mercadona-prueba", groupId = "group_id")
    public void listen(String task) {
            log.info("Received message: {}", task);
        }



//    @KafkaListener(id ="taskListner",topics = "mercadona-prueba")
//    void listener(@Payload Task data,
//                  @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
//                  @Header(KafkaHeaders.OFFSET) int offset) {
//        log.info("Received message [{}] from group1, partition-{} with offset-{}",
//                data,
//                partition,
//                offset);
//    }
}
