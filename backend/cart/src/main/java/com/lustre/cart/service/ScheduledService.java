package com.lustre.cart.service;

import com.lustre.cart.entity.Cart;
import com.lustre.cart.event.CartExistEvent;
import com.lustre.cart.repo.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ScheduledService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private KafkaTemplate<String, CartExistEvent> kafkaTemplate;

    @Scheduled(fixedRate = 50000)
    public void mailAboutCart(){

        List<Cart> carts = cartRepo.findAllByDateMore(LocalDateTime.now().minusSeconds(50));

        if(carts!=null) {
            carts.forEach(f -> {

                CartExistEvent event = new CartExistEvent();
                event.setEmail("nas@gmail.com");
                event.setUserId(f.getUserId());
                event.setProductName(f.getProductId());
                event.setSize(f.getSize());
                event.setQuantity(f.getQuantity());

                kafkaTemplate.send("cart-exist", event);
            });
        }
        else{

            return;
        }

    }

}
