package example.controller;


import example.entity.Content;
import example.entity.Message;
import example.entity.RawOrder;
import example.entity.StocksFluctuationRange;
import example.service.OrderService;
import example.service.RecordService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TradingController {
    @Autowired
    TradeService tradeService;
    @Autowired
    OrderService orderService;
    @Autowired
    RecordService recordService;
    @Autowired
    SimpMessageSendingOperations simpMessageSendingOperations;

    @MessageMapping("/hello")
    @SendTo(value = "/topic/greetings")
    public @ResponseBody Map<String,Object> greeting(Message message) throws Exception {

        System.out.println(message.getName());
        Map<String,Object> map = new HashMap<>();
        map.put("record",recordService.getAllRecord());

        return map;
    }


    @MessageMapping("/addOrder")
    @SendTo("/topic/addOrder")
    public @ResponseBody Map<String, Object> addOrderMessage(HttpServletRequest request){

        Map<String,Object> map = new HashMap<>();
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
        System.out.println("zheli1 ");
        map = orderService.addOrder(order,strategy);
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

    @RequestMapping("/getStockUpsAndDowns")
    public @ResponseBody List<StocksFluctuationRange> getStockUpsAndDowns(){
        List<StocksFluctuationRange> lists = new ArrayList<>();
        lists.add(new StocksFluctuationRange("ABT",1.2,123.7,50));
        lists.add(new StocksFluctuationRange("ABBV",-0.2,126.7,50));
        lists.add(new StocksFluctuationRange("ACN",3.4,123.7,50));
        lists.add(new StocksFluctuationRange("ADBE",-2.3,123.7,50));

        lists.add(new StocksFluctuationRange("AES",1.7,128.7,50));
        lists.add(new StocksFluctuationRange("AET",-0.8,123.7,50));
        lists.add(new StocksFluctuationRange("AFL",6.4,103.7,50));
        lists.add(new StocksFluctuationRange("AMG",-0.3,1123.7,50));


        return lists;
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
        map = orderService.addOrder(order,strategy);
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
