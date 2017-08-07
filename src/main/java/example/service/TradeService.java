package example.service;

import example.entity.RawOrder;
import example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

/**
 * Created by temper on 2017/8/7,上午11:15.
 * copy as you like, but with these word.
 * at last, The forza horizon is really fun, buy is made, looking forward to driving together in the hurricane.
 */
@Component
public class TradeService {

    @Autowired
    private OrderRepository orderRepository;


    public List<RawOrder> BidSort(RawOrder order){
        List<RawOrder> bids = orderRepository.findBySymbol("order");
        return bids;
    }
//
//    public List<RawOrder> init(){
//        //BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/Users/temperlee/Desktop")));
//        File file = new File("/Users/temperlee/Desktop/tradedata.csv");
//        try {
//            FileReader fileReader = new FileReader(file);
//
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//            String line = null;
//            while((line = bufferedReader.readLine())!= null){
//                String[] lines = line.split(",");
//                int isBuy = lines[1].equals("B")?1:0;
//                double price = Double.parseDouble(lines[2].trim());
//                int quantity = Integer.parseInt(lines[3].trim());
//                RawOrder order = new RawOrder(isBuy,lines[0],price,quantity);
//
//                orderRepository.save(order);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//       return orderRepository.findAll();
//    }
//

}
