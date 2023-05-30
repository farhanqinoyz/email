package com.room10.email.kafka;

import com.room10.basedomains.dto.OrderEvent;
import com.room10.email.util.EmailProcessUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class KafkaConsumer {

    final
    EmailProcessUtils emailProcessUtils;

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    public KafkaConsumer(EmailProcessUtils emailProcessUtils) {
        this.emailProcessUtils = emailProcessUtils;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}",
    groupId="${spring.kafka.consumer.group-id}")
    public void consume(OrderEvent event){
        if(Objects.isNull(event.getOrder())){
            String errorMessage = "Order Class is null.";
            logger.error(errorMessage);
            throw new NullPointerException(errorMessage);
        }
        this.emailProcessUtils.processEmailBody(event.getOrder());
    }
}
