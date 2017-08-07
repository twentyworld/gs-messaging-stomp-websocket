package example.controller;



import example.entity.RawOrder;
import example.service.IOrderService;
import example.service.IOrderServiceFactory;
import example.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TradingController {


    @Autowired
    TradeService tradeService;

    private IOrderService orderService;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String greeting() throws Exception {
        Thread.sleep(1000); // simulated delay
        return new String("this");
    }

    //here in the place to initial the database.
    @RequestMapping("/initialization")
    public List<RawOrder> initialization(){
        return tradeService.init();
        //return orderBookRepository.findAll();
    }


    @RequestMapping("/addOrder")
    public @ResponseBody Map<String, Object> addOrder(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        int isBuy = Boolean.parseBoolean(request.getParameter(""))==true?1:0;
        String Symbol = request.getParameter("");
        double price = Double.parseDouble(request.getParameter(""));
        int quantity = Integer.parseInt(request.getParameter(""));
        String strategy = request.getParameter("");
        RawOrder order = new RawOrder(isBuy,Symbol,price,quantity);

        orderService = IOrderServiceFactory.getOrderService(strategy);
        map = orderService.addOrder(order);
        return map;
    }



}
