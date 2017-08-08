package example.controller;


import example.entity.Content;
import example.entity.Message;
import example.entity.RawOrder;
import example.service.FOKOrderService;
import example.service.OrderService;
import example.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TradingController {
    @Autowired
    TradeService tradeService;
    @Autowired
    OrderService orderService;
    @Autowired
    SimpMessageSendingOperations simpMessageSendingOperations;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public @ResponseBody Content greeting(Message message) throws Exception {
        Thread.sleep(1000); // simulated delay
        System.out.println(message.getName());
        return new Content(message.getName()+"this");
    }

    @MessageMapping("/addOrders")
    @SendTo("/topic/greetings")
    public @ResponseBody Map<String, Object> addOrderMessage(HttpServletRequest request){

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
        map = orderService.addOrder(order);
        return map;
    }


    //here in the place to initial the database.
    @RequestMapping("/initialization")
    public List<RawOrder> initialization(){
        return tradeService.init();
        //return orderBookRepository.findAll();
    }


    @RequestMapping(value ="/getAllOrderBook")
    public @ResponseBody List<RawOrder> getAllOrders(){
        return orderService.getAll();
    }

    @RequestMapping(value = "/getOrderBookBySymbol")
    public @ResponseBody List<RawOrder> getOrderBookBySymbol(HttpServletRequest request){
        String symbol = request.getParameter("symbol");
        return orderService.getOrderBookBySymbol(symbol);
    }


    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> addOrder(HttpServletRequest request){
        Map<String,Object> map;
        int isBuy = request.getParameter("isBuy").equals("1")?1:0;
        String symbol = request.getParameter("symbol");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String strategy = request.getParameter("strategy");
        //RawOrder order = new RawOrder(1234566778,0,"ABT",48.5,19);

        System.out.println(isBuy+", "+symbol+", "+price+", "+quantity+", "+strategy);
        RawOrder order = new RawOrder(1234567,isBuy,symbol,price,quantity);
        //String strategy = "FOK";
        //orderService = IOrderServiceFactory.getOrderService(strategy);
        System.out.println("zheli1");
        map = orderService.addOrder(order);
        return map;
    }

    @RequestMapping("/show")
    public @ResponseBody Map<String, Object> show(){
        Map<String,Object> map = new HashMap<>();
        RawOrder order = new RawOrder(123456789,1,"SYN",224.2,34);
        List<RawOrder> add = new ArrayList<RawOrder>();
        add.add(order);
        add.add(order);
        List<RawOrder> delete = new ArrayList<RawOrder>();
        delete.add(order);
        delete.add(order);
        List<RawOrder> update = new ArrayList<RawOrder>();
        update.add(order);
        update.add(order);

        map.put("add",add);
        map.put("delete",delete);
        map.put("update",update);
        map.put("reject",2);

        return map;
    }
}
