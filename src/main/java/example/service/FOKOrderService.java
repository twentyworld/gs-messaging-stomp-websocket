package example.service;

import example.entity.RawOrder;
import example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by temper on 2017/8/7,下午3:16.
 * copy as you like, but with these word.
 * at last, The forza horizon is really fun, buy is made, looking forward to driving together in the hurricane.
 */
@Component
public class FOKOrderService implements IOrderService{
    @Autowired
    OrderRepository orderRepository;

    //当我们增加一个order时，调用此方法。
    public Map<String,Object> addOrder(RawOrder order){
        Map<String,Object> map = new HashMap<>();
        //取出所有同一symbol下的所有的order book，而且是同一买卖的
        List<RawOrder> orders = orderRepository.getOrdersBySymbol(order.getSymbol(),order.getIsBuy());
        for(RawOrder temp : orders){
            if(temp.getIsBuy()==order.getIsBuy()&& temp.getPrice()==order.getPrice()) {
                if (temp.getQuantity()==order.getQuantity()) {
                    orderRepository.deleteByTrader_id(temp.getTrader_id());
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
