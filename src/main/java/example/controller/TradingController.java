package example.controller;



import example.entity.OrderBook;
import example.repository.OrderBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TradingController {

    @Autowired
    OrderBookRepository orderBookRepository;
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String greeting() throws Exception {
        Thread.sleep(1000); // simulated delay
        return new String("this");
    }


    @RequestMapping("/initialization")
    public List<OrderBook> initialization(){
        OrderBook orderBook = new OrderBook("ysc",12.3,300,23.2,400);
        orderBookRepository.save(orderBook);
        return orderBookRepository.findAll();
    }

}
