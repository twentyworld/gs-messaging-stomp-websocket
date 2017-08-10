package example.service;

import example.entity.RawOrder;
import example.entity.Record;
import example.repository.OrderBookRepository;
import example.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by teemper on 2017/8/8, 20:24.
 *
 * @auther Zed.
 * copy as you like, but with these words.
 * from win.
 */
@Service
public class OrderService {

    @Autowired
    private OrderBookRepository orderBookRepository;
    @Autowired
    private RecordRepository recordRepository;

    public List<RawOrder> getAll(){
        return orderBookRepository.findAll();
    }


    public List<RawOrder> getOrderBookBySymbol(String symbol){
        return orderBookRepository.findBySymbol(symbol);
    }

    //当我们增加一个order时，调用此方法。
    public List <Record> addOrder(RawOrder order,String type){
        System.out.println("zheli");
        // Map<String,Object> map = new HashMap<>();
        List <Record> records = new ArrayList<>();

        //如果有需要add的Order Book，put通过“add”,覆盖掉null.同样适用于delete,update,reject
        // map.put("add",null);
        //  map.put("delete",null);
        // map.put("update",null);
        //  map.put("reject",-1);
        //取出所有同一symbol下的所有的order book
        //取出所有同一symbol下的所有的order book
        System.out.println(order.getSymbol());
        // System.out.println(orderRepository.findByTraderId(1).getPrice());
        List<RawOrder> orders = orderBookRepository.findBySymbol(order.getSymbol());
        if(orders==null) {
            // map.put("reject", true);
            records.add(null);
            return records;
        }
/************change begin*****************/ //change
        //选择策略的种类
        OrderType ordertype = new OrderType();
        if (type.equals("FOK")) {
            ordertype.useFOK(orders,order,records,orderBookRepository,recordRepository);
        } else if (type.equals("GTC")) {
            ordertype.useGTC(orders,order,records,orderBookRepository,recordRepository);
        } else if (type.equals("IOC")) {
            ordertype.useIOC(orders,order,records,orderBookRepository,recordRepository);
        } else if (type.equals("MarketOrders")) {
            ordertype.useMarketOrders(orders,order,records,orderBookRepository,recordRepository);
        }

        /************change end***********************/
        return records;
    }

}
