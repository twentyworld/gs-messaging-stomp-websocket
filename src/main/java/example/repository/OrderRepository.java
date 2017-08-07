package example.repository;

import example.entity.RawOrder;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by temper on 2017/8/7,上午10:52.
 * copy as you like, but with these word.
 * at last, The forza horizon is really fun, buy is made, looking forward to driving together in the hurricane.
 */
public interface OrderRepository extends Repository<RawOrder,Long>{

    RawOrder save(RawOrder bidOrder);
    List<RawOrder> findAll();
    RawOrder deleteByTrader_id(long id);


    @Query(value = "SELECT * FROM Raw_Order WHERE Symbol = ?1 AND isBuy = ?2",nativeQuery = true)
    List<RawOrder> getOrdersBySymbol(String Symbol, int isBuy);






}
