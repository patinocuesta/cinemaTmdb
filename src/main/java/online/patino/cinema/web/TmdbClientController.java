package online.patino.cinema.web;

import online.patino.cinema.model.Film;
import online.patino.cinema.service.TmdbRestClient;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/tmdb")
public class TmdbClientController {
    @Autowired
    private TmdbRestClient tmdbRestClient;

    @GetMapping("/list")
    public String list(Model model) throws IOException, JSONException {
     model.addAttribute("films", tmdbRestClient.readPopular());
        return "tmdb/list";
    }

}
