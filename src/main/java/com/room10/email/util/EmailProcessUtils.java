package com.room10.email.util;

import com.room10.basedomains.dto.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailProcessUtils {

    private static final Logger logger = LoggerFactory.getLogger(EmailProcessUtils.class);

    public void processEmailBody(Order order) {
        String emailTarget = "admininventaris@gmail.com";
        String name = order.getName();
        int qty = order.getQty();
        String extraMessage = processExtraMessage(order.getPrice(), order.getOrderId());
        String emailBody = "<html>"
                + "Name : " + name
                + "Quantity : " + qty
                + extraMessage
                + "</html>";

        logger.info("Email Body : " + emailBody);
        logger.info("Email sent to " + emailTarget);
        // Let's say after this it will send the email haha
    }

    private static String processExtraMessage(double price, String orderId) {
        String extraMessage = "";
        if(price > 1_000_000){
            logger.warn(String.format("Order ID %s is more than one million rupiah(s).", orderId));
            String warningMessage = "<div style='color=red'>Pemesanan diatas 1 juta terdeteksi, harap cek kembali dengan teliti.</div>";
            return extraMessage.concat(warningMessage);
        }
        return extraMessage;
    }
}
