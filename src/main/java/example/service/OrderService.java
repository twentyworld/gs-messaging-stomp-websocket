package example.service;

import example.entity.RawOrder;
import example.repository.OrderBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<RawOrder> getAll(){
        return orderBookRepository.findAll();
    }


    public List<RawOrder> getOrderBookBySymbol(String symbol){
        return orderBookRepository.findBySymbol(symbol);
    }

    //当我们增加一个order时，调用此方法。
    public Map<String,Object> addOrder(RawOrder order){
        System.out.println("zheli");
        Map<String,Object> map = new HashMap<>();


        //如果有需要add的Order Book，put通过“add”,覆盖掉null.同样适用于delete,update,reject

        map.put("add",null);
        map.put("delete",null);
        map.put("update",null);

        map.put("reject",-1);
        //取出所有同一symbol下的所有的order book
        //取出所有同一symbol下的所有的order book
        System.out.println(order.getSymbol());
        // System.out.println(orderRepository.findByTraderId(1).getPrice());
        List<RawOrder> orders = orderBookRepository.findBySymbol(order.getSymbol());
        if(orders==null) {
            map.put("reject", true);
            return map;
        }
        for(RawOrder temp : orders){
            if(temp.getIsBuy()!=order.getIsBuy() && temp.getPrice()==order.getPrice()) {
                if (temp.getQuantity()==order.getQuantity()) {
                    orderBookRepository.delete(temp);
                    map.put("delete", temp);
                } else if (temp.getQuantity()> order.getQuantity()) {
                    temp.setQuantity(temp.getQuantity() - order.getQuantity());
                    orderBookRepository.save(temp);
                    map.put("update", temp);
                } else if (temp.getQuantity() < order.getQuantity()) map.put("reject", true);
                else
                    map.put("reject", true);
                break;
            }
        }
        return map;
    }

    public static String printSelf() {
        return "FOK";
    }


}
