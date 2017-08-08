package example.service;

import example.entity.RawOrder;
import example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by temper on 2017/8/7,下午3:16.
 * copy as you like, but with these word.
 * at last, The forza horizon is really fun, buy is made, looking forward to driving together in the hurricane.
 */
@Service
public class FOKOrderService implements IOrderService{
    @Autowired
    private OrderRepository orderRepository;

    public List<RawOrder> getAll(){
        return orderRepository.findAll();
    }



    //当我们增加一个order时，调用此方法。
    public Map<String,Object> addOrder(RawOrder order){
        System.out.println("zheli ");
        Map<String,Object> map = new HashMap<>();
        //取出所有同一symbol下的所有的order book
        System.out.println(order.getSymbol());
       // System.out.println(orderRepository.findByTraderId(1).getPrice());
        List<RawOrder> orders = orderRepository.findBySymbol(order.getSymbol());
        if(orders==null) {
            map.put("reject", true);
            return map;
        }
        for(RawOrder temp : orders){
            if(temp.getIsBuy()!=order.getIsBuy()&& temp.getPrice()==order.getPrice()) {
                if (temp.getQuantity()==order.getQuantity()) {
                    orderRepository.delete(temp);
                    map.put("delete", temp);
                } else if (temp.getQuantity() > order.getQuantity()) {
                    temp.setQuantity(temp.getQuantity() - order.getQuantity());
                    orderRepository.save(temp);
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
