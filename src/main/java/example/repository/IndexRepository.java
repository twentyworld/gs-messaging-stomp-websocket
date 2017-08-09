package example.repository;

import example.entity.IndexOfBroker;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by teemper on 2017/8/10, 0:33.
 *
 * @auther Zed.
 * copy as you like, but with these words.
 * from win.
 */
public interface IndexRepository extends Repository<IndexOfBroker,Long> {
    IndexOfBroker save(IndexOfBroker index);
    List<IndexOfBroker> findAll();
}
