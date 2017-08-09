package example.service;

import example.entity.RawOrder;
import example.repository.OrderBookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderType {


    public boolean useFOK (List<RawOrder> orders,RawOrder order,Map<String,Object> map,OrderBookRepository orderBookRepository) {
        int index = 0;
        for(RawOrder temp : orders){
            if(temp.getIsBuy()!=order.getIsBuy() && temp.getPrice()==order.getPrice()) {
                if (temp.getQuantity()==order.getQuantity()) {
                    List<RawOrder> delete = new ArrayList<>();
                    delete.add(temp);
                    orderBookRepository.delete(temp);
                    map.put("delete", delete);
                    return true;
                } else if (temp.getQuantity()> order.getQuantity()) {
                    temp.setQuantity(temp.getQuantity()-order.getQuantity());
                    orderBookRepository.save(temp);
                    List<RawOrder> update = new ArrayList<>();
                    update.add(temp);
                    map.put("update", update);
                    return true;
                } else if (temp.getQuantity() < order.getQuantity()) map.put("reject", true);
                else  map.put("reject", true);
                break;
            } else if (temp.getIsBuy()!=order.getIsBuy()&& index == (orders.size()-1)) {
                map.put("reject", true);
            }
            index++;
        }
        map.put("reject", true);
        return false;
    }


    public boolean useGTC (List<RawOrder> orders,RawOrder order,Map<String,Object> map,OrderBookRepository orderBookRepository) {
        int index = 0;
        for(RawOrder temp : orders){
            if(temp.getIsBuy()!=order.getIsBuy() && temp.getPrice()==order.getPrice()) {
                if (temp.getQuantity()==order.getQuantity()) {
                    List<RawOrder> delete = new ArrayList<>();
                    delete.add(temp);
                    orderBookRepository.delete(temp);
                    map.put("delete", delete);
                    return true;
                } else if (temp.getQuantity()> order.getQuantity()) {
                    temp.setQuantity(temp.getQuantity() - order.getQuantity());
                    orderBookRepository.save(temp);
                    List<RawOrder> update = new ArrayList<>();
                    update.add(temp);
                    map.put("update", update);
                    return true;
                } else {
                    orderBookRepository.save(order);
                    List<RawOrder> add = new ArrayList<>();
                    add.add(temp);
                    map.put("add", add);
                }
                break;
            }
            index++;
        }
        orderBookRepository.save(order);
        List<RawOrder> add = new ArrayList<>();
        add.add(order);
        map.put("add", add);
        return false;
    }

    public boolean useIOC (List<RawOrder> orders,RawOrder order,Map<String,Object> map,OrderBookRepository orderBookRepository) {
        //判断交易是否成交
        int count = 0;
        for(RawOrder temp : orders) {
            if (temp.getIsBuy() != order.getIsBuy()){
                count += temp.getQuantity();
            }
        }
        if (count == 0) {
            map.put("reject", true);
            return false;
        }

        //更新成功交易的股票量和交易价格
        int index = 0;
        count = 0;
        while (count < order.getQuantity() && index < orders.size() ) {
            RawOrder temp = orders.get(index);
            if(order.getIsBuy() !=orders.get(index).getIsBuy()) {
                List<RawOrder> delete = new ArrayList<>();
                if (temp.getQuantity() == (order.getQuantity() - count)) {
                    delete.add(temp);
                    orderBookRepository.delete(temp);
                    map.put("delete", delete);
                    break;

                } else if (temp.getQuantity() < (order.getQuantity() - count)) {
                    count += temp.getQuantity();
                    delete.add(temp);
                    orderBookRepository.delete(temp);
                    map.put("delete", delete);
                } else if (temp.getQuantity() > (order.getQuantity() - count)) {
                    temp.setQuantity( temp.getQuantity() - (order.getQuantity() - count));
                    orderBookRepository.save(temp);
                    List<RawOrder> update = new ArrayList<>();
                    update.add(temp);
                    map.put("update", update);
                    break;
                }
            }

            index++;
        }

        //存储trader剩余的未交易数据
        if (count < order.getQuantity() && index >= orders.size()) {
           RawOrder rest = order;
           rest.setQuantity(order.getQuantity() - count);
          //  orderBookRepository.save(rest);
            List<RawOrder> reject = new ArrayList<>();
            reject.add(rest);
            map.put("reject", reject);
        }

        return true;
    }

    public boolean useMarketOrders (List<RawOrder> orders,RawOrder order,Map<String,Object> map,OrderBookRepository orderBookRepository) {
        //判断交易是否成交
        int count = 0;
        for(RawOrder temp : orders) {
            if (temp.getIsBuy() != order.getIsBuy()){
                count += temp.getQuantity();
            }
        }
        if (count < order.getQuantity()) {
            map.put("reject", true);
            return false;
        }

        //更新成功交易的股票量和交易价格
        int index = 0;
        count = 0;
        while( count < order.getQuantity()) {
            RawOrder temp = orders.get(index);
            if(order.getIsBuy() !=orders.get(index).getIsBuy()) {
                List<RawOrder> delete = new ArrayList<>();
                if (temp.getQuantity() == (order.getQuantity() - count)) {
                    delete.add(temp);
                    orderBookRepository.delete(temp);
                    map.put("delete", delete);
                    break;
                } else if (temp.getQuantity() < (order.getQuantity() - count)) {
                    count += temp.getQuantity();
                    delete.add(temp);
                    orderBookRepository.delete(temp);
                    map.put("delete", delete);
                } else if (temp.getQuantity() > (order.getQuantity() - count)) {
                    temp.setQuantity( temp.getQuantity() - (order.getQuantity() - count));
                    orderBookRepository.save(temp);
                    List<RawOrder> update = new ArrayList<>();
                    update.add(temp);
                    map.put("update", update);
                    break;
                }
            }

            index++;
        }
        return true;
    }
}
