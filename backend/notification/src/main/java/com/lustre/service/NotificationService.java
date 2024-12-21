package com.lustre.service;

import com.lustre.cart.event.CartExistEvent;
import com.lustre.order.event.OrderPlaceEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private JavaMailSender javaMailSender;

    @KafkaListener(topics = "order-placed")
    public void listenForOrderPlace(OrderPlaceEvent orderPlaceEvent) {

        MimeMessagePreparator prep = mime->{
            MimeMessageHelper message = new MimeMessageHelper(mime);
            message.setTo(orderPlaceEvent.getEmail());
            message.setSubject("Order placed");
            message.setFrom("lustre@gmail.com");
            message.setText(String.format("""
                    
                    Hi,
                    
                    Your order with order id %s is placed.
                    
                    Best regards,
                    Lustre Team
                    
                    """,orderPlaceEvent.getProductId()));
        };

        try{

            javaMailSender.send(prep);

        }
        catch(Exception e){
            throw new RuntimeException(e);
        }

    }

    @KafkaListener(topics = "cart-exist")
    public void listenForCartExist(CartExistEvent orderPlaceEvent) {

        MimeMessagePreparator prep = mime->{
            MimeMessageHelper message = new MimeMessageHelper(mime);
            message.setTo(orderPlaceEvent.getEmail());
            message.setSubject("Cart Details");
            message.setFrom("lustre@gmail.com");
            message.setText(String.format("""
                    
                    Hi,
                    
                    Order you're product as fast
                    
                    product-name : %s
                    size : %s
                    quantity : %s
                     
                    Best regards,
                    Lustre Team
                    
                    """,orderPlaceEvent.getProductName(),orderPlaceEvent.getSize(),orderPlaceEvent.getQuantity()));
        };

        try{

            javaMailSender.send(prep);

        }
        catch(Exception e){
            throw new RuntimeException(e);
        }

    }

}
