package example.controller;

import example.entity.IndexOfBroker;
import example.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by teemper on 2017/8/10, 1:01.
 *
 * @auther Zed.
 * copy as you like, but with these words.
 * from win.
 */
@Controller
public class IndexController {
    @Autowired
    private IndexService indexService;

    @RequestMapping("/getAllIndex")
    public @ResponseBody
    List<IndexOfBroker> getAllIndex(){
        return indexService.getAllIndex();
    }
}
