package example.service;

import example.entity.RawOrder;

import example.repository.OrderBookRepository;

import org.springframework.beans.factory.annotation.Autowired;

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
public class FOKOrderService {
    @Autowired
    private OrderBookRepository orderBookRepository;
    public List<RawOrder> getAll(){
        return orderBookRepository.findAll();
    }
    public static String printSelf() {
        return "FOK";
    }


}
