package com.lustre.order.Service;

import com.lustre.order.client.Product;
import com.lustre.order.client.ProductClient;
import com.lustre.order.client.StockClient;
import com.lustre.order.client.StockDTO;
import com.lustre.order.dto.Response;
import com.lustre.order.entity.Order;
import com.lustre.order.event.OrderPlaceEvent;
import com.lustre.order.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private StockClient stockClient;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private KafkaTemplate<String, OrderPlaceEvent> kafkaTemplate;


    public Response orderProduct(StockDTO stockDTO) {

        Response r = new Response();

        boolean isStock = stockClient.isProductInStock(stockDTO);

        if(isStock) {

            Order order = new Order();

            Product product = productClient.getProduct(stockDTO.getProductId());

            order.setProductId(stockDTO.getProductId());
            order.setCreateTime(LocalDateTime.now());
            order.setQuantity(stockDTO.getQuantity());
            order.setSize(stockDTO.getSize());
            order.setStatus("Order Placed");
            order.setPrice(product.getPrice());
            order.setUserId(stockDTO.getUserId());

            orderRepo.save(order);
            r.setMessage("Order Placed");

            OrderPlaceEvent o = new OrderPlaceEvent();
            o.setProductId(stockDTO.getProductId());
            o.setEmail("naseefrahman90@gmail.com");

            kafkaTemplate.send("order-placed",o);

            r.setCode(200);
            return r;

        }
        else{
            r.setCode(404);
            r.setMessage("Out of stock");
            return r;
        }

    }

    public Response cancelOrder(String orderId) {

        Response r = new Response();

        try{

            if(orderId!=null){
                Order order = orderRepo.findById(orderId).orElseThrow(()->new RuntimeException("Order Not Found"));

                StockDTO stockDTO = new StockDTO();
                stockDTO.setProductId(order.getProductId());
                stockDTO.setQuantity(order.getQuantity());
                stockDTO.setSize(order.getSize());

                if(stockClient.updateStock(stockDTO)) {

                    orderRepo.deleteById(orderId);

                    r.setMessage("Order Canceled");
                    r.setCode(200);
                }
                else{
                    r.setCode(404);
                    r.setMessage("Something went wrong");
                }
                return r;

            }
            else{
                r.setCode(404);
                r.setMessage("Oder Not Found");
                return r;
            }
        }
        catch(Exception e){
            r.setCode(500);
            r.setMessage(e.getMessage());
            return r;
        }

    }

}
