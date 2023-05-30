package com.room10.email.kafka;

import com.room10.basedomains.dto.OrderEvent;
import com.room10.email.util.EmailProcessUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


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
        String emailTarget = "admininventaris@gmail.com";
        String emailBody = EmailProcessUtils.processEmailBody(event);
        logger.info("Email sent to " + emailTarget);
        logger.info("Email Body : " + emailBody);
    }
}
