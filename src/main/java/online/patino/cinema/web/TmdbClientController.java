package online.patino.cinema.web;


import net.bytebuddy.implementation.bind.annotation.Default;
import online.patino.cinema.service.FilmDtoManager;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;

@Controller
@RequestMapping("/tmdb")
public class TmdbClientController {
    @Autowired
    private FilmDtoManager filmDtoManager;

    @GetMapping("/list")
    public String list(Model model) throws IOException, JSONException {
     model.addAttribute("films", filmDtoManager.ListFilmsPopular(1));
        return "tmdb/list";
    }


}
