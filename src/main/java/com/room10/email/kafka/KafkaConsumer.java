package com.room10.email.kafka;

import com.room10.basedomains.dto.Order;
import com.room10.basedomains.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class KafkaConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}",
    groupId="${spring.kafka.consumer.group-id}")
    public void consume(OrderEvent event){
        Order order = event.getOrder();
        String name = order.getName();
        int qty = order.getQty();
        logger.info("Email sent to admininventaris@gmail.com");
        logger.info("Email Body : \n Name " + name + "\nQuantity \n " + qty);
    }
}
