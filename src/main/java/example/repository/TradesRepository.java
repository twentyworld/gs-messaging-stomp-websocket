package example.repository;

import example.entity.Trades;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by temper on 2017/8/6,下午4:30.
 * copy as you like, but with these word.
 * at last, The forza horizon is really fun, buy is made, looking forward to driving together in the hurricane.
 */

public interface TradesRepository extends CrudRepository<Trades, Long> {
    List<Trades> findAll();

}
