package online.patino.cinema.web;

import online.patino.cinema.model.Film;
import online.patino.cinema.service.TmdbRestClient;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequestMapping("/tmdb")
public class TmdbClientController {
    @Autowired
    private TmdbRestClient tmdbRestClient;

    @GetMapping("/list/{page}")
    public String list(@PathVariable("page") int page, Model model) throws IOException, JSONException {
     model.addAttribute("films", tmdbRestClient.ListFilmsPopular(page));
        return "tmdb/list";
    }

}
