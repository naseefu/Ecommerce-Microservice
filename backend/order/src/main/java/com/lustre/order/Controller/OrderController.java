package com.lustre.order.Controller;

import com.lustre.order.Service.OrderService;
import com.lustre.order.client.CartClient;
import com.lustre.order.client.StockDTO;
import com.lustre.order.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartClient cartClient;

    @PostMapping("/product/{id}")
    public ResponseEntity<Response> orderProduct(@PathVariable String id) {

        List<StockDTO> stockDTO = cartClient.getCartOrders(id);

        List<StockDTO> stockOutProd = new ArrayList<>();
        AtomicBoolean flag = new AtomicBoolean(true);
        Response res = new Response();

        stockDTO.forEach(s->{
            if(orderService.orderProduct(s).getCode()!=200){
                stockOutProd.add(s);
                flag.set(false);
            }
            else{
                cartClient.deleteCart(s.getUserId(),s.getProductId());
            }
        });

        if(!flag.get()){
            res.setStock(stockOutProd);
            res.setMessage("Order for following products are out of stock");
            res.setCode(400);
            return ResponseEntity.status(200).body(res);
        }
        else{
            res.setMessage("Order placed successfully");
            res.setCode(200);
            return ResponseEntity.status(200).body(res);
        }

    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<Response> orderCancel(@PathVariable String id) {

        Response r = orderService.cancelOrder(id);
        return ResponseEntity.status(r.getCode()).body(r);

    }


}
