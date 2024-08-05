package exagonal.kafkademo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exagonal.kafkademo.domain.Task;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class TaskInitialize {

    private static final Logger logger = LoggerFactory.getLogger(TaskInitialize.class);

    private static final String TOPIC = "mercadona-prueba";

    private KafkaTemplate<String, Object> kafkaTemplate;

    private ObjectMapper objectMapper;

    public TaskInitialize(KafkaTemplate<String, Object> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void init() throws JsonProcessingException {
        Task task1 = new Task(1L, "Task 1", "la primera", LocalDateTime.now(), false);
        Task task2 = new Task(2L, "Task 2", "la segunda", LocalDateTime.now(), false);
        Task task3 = new Task(3L, "Task 3", "la tercera", LocalDateTime.now(), false);

        logger.info("Sending message to topic {}: {}", TOPIC, objectMapper.writeValueAsString(task1));
        logger.info("Sending message to topic {}: {}", TOPIC, objectMapper.writeValueAsString(task2));
        logger.info("Sending message to topic {}: {}", TOPIC, objectMapper.writeValueAsString(task3));


        kafkaTemplate.send(TOPIC, task1.toString());
        kafkaTemplate.send(TOPIC, task2.toString());
        kafkaTemplate.send(TOPIC, task3.toString());
    }
}
