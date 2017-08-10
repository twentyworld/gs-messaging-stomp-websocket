package example.service;

import example.entity.RawOrder;
import example.entity.Record;
import example.repository.OrderBookRepository;
import example.repository.RecordRepository;

import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class OrderType {

    private Record createRecord (RawOrder order1,RawOrder order2,String type,int quantity) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        Record record = new Record();
        if (order1.getIsBuy() == 1) {
            record.setTraderBid(order1.getTraderId());
            record.setTraderOffer(order2.getTraderId());
        } else if (order1.getIsBuy() == 0) {
            record.setTraderOffer(order1.getTraderId());
            record.setTraderBid(order2.getTraderId());
        }
        record.setTimes(time);
        record.setPrice(order1.getPrice());
        record.setQuantity(quantity);

        record.setSymbol(order1.getSymbol());
        record.setType(type);
        return record;
    }

    public boolean useFOK (List<RawOrder> orders, RawOrder order, List <Record> records, OrderBookRepository orderBookRepository, RecordRepository recordRepository) {
        int index = 0;
        for(RawOrder temp : orders){
            if(temp.getIsBuy()!=order.getIsBuy() && Math.abs(temp.getPrice()-order.getPrice())<0.01) {
                if (temp.getQuantity()==order.getQuantity()) {
                    List<RawOrder> delete = new ArrayList<>();
                    delete.add(temp);
                    Record record = createRecord(temp,order,"FOK",temp.getQuantity());
                    recordRepository.save(record);
                    orderBookRepository.delete(temp);
                    //map.put("delete", delete);
                    records.add(record);
                    //map.put("reject", 0);
                    return true;
                } else if (temp.getQuantity()> order.getQuantity()) {
                    Record record = createRecord(temp,order,"FOK",order.getQuantity());
                    recordRepository.save(record);
                    temp.setQuantity(temp.getQuantity()-order.getQuantity());
                    orderBookRepository.save(temp);
                    records.add(record);
                    // List<RawOrder> update = new ArrayList<>();
                    //update.add(temp);
                    //map.put("update", update);
                    //map.put("reject", 0);
                    return true;
                } else if (temp.getQuantity() < order.getQuantity()) records.add(null);//map.put("reject", 0);
                else  records.add(null);//map.put("reject", 0);
                break;
            } else if (temp.getIsBuy()!=order.getIsBuy()&& index == (orders.size()-1)) {
                records.add(null);//map.put("reject", 0);
            }
            index++;
        }
        records.add(null);//map.put("reject", 0);
        return false;
    }


    public boolean useGTC (List<RawOrder> orders,RawOrder order,List <Record> records,OrderBookRepository orderBookRepository,RecordRepository recordRepository) {
        int index = 0;
        for(RawOrder temp : orders){
            if(temp.getIsBuy()!=order.getIsBuy() && Math.abs(temp.getPrice()-order.getPrice())<0.01) {
                if (temp.getQuantity()==order.getQuantity()) {
                    //List<RawOrder> delete = new ArrayList<>();
                    //delete.add(temp);
                    Record record = createRecord(temp,order,"GTC",order.getQuantity());
                    recordRepository.save(record);
                    orderBookRepository.delete(temp);
                    records.add(record);
                    //map.put("delete", delete);
                    //map.put("reject", 0);
                    return true;
                } else if (temp.getQuantity()> order.getQuantity()) {
                    Record record = createRecord(temp,order,"GTC",order.getQuantity());
                    recordRepository.save(record);
                    temp.setQuantity(temp.getQuantity() - order.getQuantity());
                    orderBookRepository.save(temp);
                    //List<RawOrder> update = new ArrayList<>();
                    //update.add(temp);
                    records.add(record);
                    //map.put("update", update);
                    //map.put("reject", 0);
                    return true;
                } else {
                    orderBookRepository.save(order);
                    //List<RawOrder> add = new ArrayList<>();
                    //add.add(temp);
                    records.add(null);
                    //map.put("add", add);
                    //map.put("reject", 0);
                }
                break;
            }
            index++;
        }
        orderBookRepository.save(order);
        // List<RawOrder> add = new ArrayList<>();
        //add.add(order);
        records.add(null);
        // map.put("add", add);
        // map.put("reject", 0);
        return false;
    }

    public boolean useIOC (List<RawOrder> orders,RawOrder order,List <Record> records,OrderBookRepository orderBookRepository,RecordRepository recordRepository) {
        //判断交易是否成交
        int count = 0;
        for(RawOrder temp : orders) {
            if (temp.getIsBuy() != order.getIsBuy()){
                count += temp.getQuantity();
            }
        }
        if (count == 0) {
            records.add(null);
            //map.put("reject", 0);
            return false;
        }

        //更新成功交易的股票量和交易价格
        int index = 0;
        count = 0;
        while (count < order.getQuantity() && index < orders.size() ) {
            RawOrder temp = orders.get(index);
            if(order.getIsBuy() !=orders.get(index).getIsBuy()) {
                //List<RawOrder> delete = new ArrayList<>();
                if (temp.getQuantity() == (order.getQuantity() - count)) {
                    //delete.add(temp);
                    Record record = createRecord(temp,order,"IOC",order.getQuantity());
                    recordRepository.save(record);
                    records.add(record);
                    orderBookRepository.delete(temp);
                    // map.put("delete", delete);
                    // map.put("reject", 0);
                    break;

                } else if (temp.getQuantity() < (order.getQuantity() - count)) {
                    count += temp.getQuantity();
                    //delete.add(temp);
                    Record record = createRecord(temp,order,"IOC",temp.getQuantity());
                    recordRepository.save(record);
                    records.add(record);
                    orderBookRepository.delete(temp);
                    //map.put("delete", delete);
                    //map.put("reject", 0);
                } else if (temp.getQuantity() > (order.getQuantity() - count)) {
                    int quantity = order.getQuantity() - count;
                    Record record = createRecord(temp,order,"IOC",quantity);
                    recordRepository.save(record);
                    records.add(record);
                    temp.setQuantity( temp.getQuantity() - quantity);
                    orderBookRepository.save(temp);
                    //List<RawOrder> update = new ArrayList<>();
                    //update.add(temp);
                    //map.put("update", update);
                    //map.put("reject", 0);
                    break;
                }
            }

            index++;
        }

        //存储trader剩余的未交易数据
        if (count < order.getQuantity() && index >= orders.size()) {
            //RawOrder rest = order;
            //rest.setQuantity(order.getQuantity() - count);
            //int rest = order.getQuantity() - count;
            //  orderBookRepository.save(rest);
            // List<RawOrder> reject = new ArrayList<>();
            // reject.add(rest);
            records.add(null);
            //map.put("reject", rest);
        }

        return true;
    }

    public boolean useMarketOrders (List<RawOrder> orders,RawOrder order,List <Record> records,OrderBookRepository orderBookRepository,RecordRepository recordRepository) {
        //判断交易是否成交
        int count = 0;
        for(RawOrder temp : orders) {
            if (temp.getIsBuy() != order.getIsBuy()){
                count += temp.getQuantity();
            }
        }
        if (count < order.getQuantity()) {
            records.add(null);
            //map.put("reject", 0);
            return false;
        }

        //更新成功交易的股票量和交易价格
        int index = 0;
        count = 0;
        while( count < order.getQuantity()) {
            RawOrder temp = orders.get(index);
            if(order.getIsBuy() !=orders.get(index).getIsBuy()) {
                // List<RawOrder> delete = new ArrayList<>();
                if (temp.getQuantity() == (order.getQuantity() - count)) {
                    //delete.add(temp);
                    Record record = createRecord(temp,order,"MarketOrders",temp.getQuantity());
                    recordRepository.save(record);
                    records.add(record);
                    orderBookRepository.delete(temp);
                    //map.put("delete", delete);
                    //map.put("reject", 0);
                    break;
                } else if (temp.getQuantity() < (order.getQuantity() - count)) {
                    count += temp.getQuantity();
                    //delete.add(temp);
                    Record record = createRecord(temp,order,"MarketOrders",temp.getQuantity());
                    recordRepository.save(record);
                    records.add(record);
                    orderBookRepository.delete(temp);
                    //map.put("delete", delete);
                    //map.put("reject", 0);
                } else if (temp.getQuantity() > (order.getQuantity() - count)) {
                    int quantity = order.getQuantity() - count;
                    Record record = createRecord(temp,order,"MarketOrders",temp.getQuantity());
                    recordRepository.save(record);
                    records.add(record);
                    temp.setQuantity( temp.getQuantity() - quantity);
                    orderBookRepository.save(temp);
                    //List<RawOrder> update = new ArrayList<>();
                    //update.add(temp);
                    //map.put("update", update);
                    //map.put("reject", 0);
                    break;
                }
            }

            index++;
        }
        return true;
    }
}