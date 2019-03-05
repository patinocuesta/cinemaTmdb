package online.patino.cinema.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {
    @Autowired
   // private TmdbRestClient tmdbRestClient;

    @GetMapping("/")
    public String home(){
        return "index";
    }
}
