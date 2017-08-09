package example.service;

import example.entity.IndexOfBroker;
import example.repository.IndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by teemper on 2017/8/10, 1:00.
 *
 * @auther Zed.
 * copy as you like, but with these words.
 * from win.
 */
@Service
public class IndexService {
    @Autowired
    private IndexRepository indexRepository;
    public List<IndexOfBroker> getAllIndex(){
        return indexRepository.findAll();
    }
}
