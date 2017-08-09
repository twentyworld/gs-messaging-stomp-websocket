package example.controller;

/**
 * Created by teemper on 2017/8/9, 22:37.
 *
 * @auther Zed.
 * copy as you like, but with these words.
 * from win.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HTMLController {
    @RequestMapping("/trade")
    public String trade(){
        return "trade";
    }
}
