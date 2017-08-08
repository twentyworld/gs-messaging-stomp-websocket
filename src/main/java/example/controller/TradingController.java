package example.controller;


import example.entity.RawOrder;
import example.service.FOKOrderService;
import example.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TradingController {



    @Autowired
    TradeService tradeService;
    @Autowired
    FOKOrderService fokOrderService;
    @Autowired
    SimpMessageSendingOperations simpMessageSendingOperations;

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


    @RequestMapping(value ="/getAllOrders")
    public @ResponseBody List<RawOrder> getAllOrders(){
        return fokOrderService.getAll();
    }


    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> addOrder(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();


        int isBuy = Boolean.parseBoolean(request.getParameter("isBuy"))==true?1:0;
        String symbol = request.getParameter("symbol");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String strategy = request.getParameter("strategy");
        //RawOrder order = new RawOrder(1234566778,0,"ABT",48.5,19);

        System.out.println(isBuy+", "+symbol+", "+price+", "+quantity+", "+strategy);
        RawOrder order = new RawOrder(1234567,isBuy,symbol,price,quantity);
        //String strategy = "FOK";
        //orderService = IOrderServiceFactory.getOrderService(strategy);
        System.out.println("zheli1 ");
        map = fokOrderService.addOrder(order);
        return map;
    }
}
