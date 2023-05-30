package com.room10.email.util;

import com.room10.basedomains.dto.Order;
import com.room10.basedomains.dto.OrderEvent;
import org.springframework.stereotype.Service;

@Service
public class EmailProcessUtils {


    public static String processEmailBody(OrderEvent event) {
        Order order = event.getOrder();
        String name = order.getName();
        int qty = order.getQty();
        double price = order.getPrice();
        String extraMessage = processExtraMessage(price);
        return "<html>"
                + "Name : " + name
                + "Quantity : " + qty
                + extraMessage
                + "</html>";
    }

    private static String processExtraMessage(double price) {
        String extraMessage = "";
        if(price > 1_000_000){
            String warningMessage = "<div style='color=red'>Pemesanan diatas 1 juta terdeteksi, harap cek kembali dengan teliti.</div>";
            return extraMessage.concat(warningMessage);
        }
        return extraMessage;
    }
}
