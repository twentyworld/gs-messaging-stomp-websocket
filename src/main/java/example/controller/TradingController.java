package example.controller;


import example.entity.*;
import example.service.OrderService;
import example.service.RecordService;
import example.service.StockDailyRecordService;
import example.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.util.*;

@RestController
public class TradingController {
    @Autowired
    TradeService tradeService;
    @Autowired
    OrderService orderService;
    @Autowired
    RecordService recordService;
    @Autowired
    StockDailyRecordService stockDailyRecordService;
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/addOrder")
    @SendTo({"/topic/addOrder"})
    @ResponseBody
    public Map<String,Object> addOrderMessage(SubmitOrder submitOrder){
        Map<String,Object> map = new HashMap<>();
        long id = submitOrder.getId();
        int isBuy = submitOrder.getIsBuy();
        String symbol = submitOrder.getSymbol();
        double price = submitOrder.getPrice();
        int quantity = submitOrder.getQuantity();
        String strategy = submitOrder.getStrategy();
        System.out.println(isBuy+", "+symbol+", "+price+", "+quantity+", "+strategy);
        RawOrder order = new RawOrder(id,isBuy,symbol,price,quantity);
        System.out.println("zheli1 ");
        //查询点之前。
        List<Record> recordsBefore = recordService.getRecordByName(symbol);
        List<RawOrder> orderBookBefore = orderService.getOrderBookBySymbol(symbol);
        //添加order之后，
        List<Record> recordOfAdd = orderService.addOrder(order,strategy);
        List<RawOrder> orders =orderService.getOrderBookBySymbol(symbol);
        List<Record> records = recordService.getRecordByName(symbol);

        List<RawOrder> ordersByIsBuyOp = new ArrayList<>();


        Collections.sort(orders);
        Collections.sort(records);
        map.put("orderBook",orders);
        map.put("record",records);
        map.put("stockRange",stockDailyRecordService.getStocksFluctuationRange());
        sendUserMessage(recordsBefore.size()==records.size() &&orderBookBefore.size() == orders.size(),id);
//        if(recordsBefore.size()==records.size() &&orderBookBefore.size() == orders.size())
//            simpMessagingTemplate.convertAndSendToUser(id+"","/message",new RejectOrder("true"));
//        else
//            simpMessagingTemplate.convertAndSendToUser(id+"","/message",new RejectOrder("false"));
        return map;
    }
    public void sendUserMessage(boolean flag,long id){
        simpMessagingTemplate.convertAndSendToUser(id+"","/message",new RejectOrder(flag+""));
    }

    @MessageMapping("/message")
    @SendToUser("/message")
    public RejectOrder userMessage(RejectOrder rejectOrder) throws Exception {
        return rejectOrder;
    }

    //here in the place to initial the database.
    @RequestMapping("/initialization")
    public List<RawOrder> initialization() throws FileNotFoundException {
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
        List<RawOrder> lists = orderService.getOrderBookBySymbol(symbol);
        Collections.sort(lists);
        return lists;
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
