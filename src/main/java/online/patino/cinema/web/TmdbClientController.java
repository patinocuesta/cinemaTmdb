package online.patino.cinema.web;


import online.patino.cinema.service.FilmDtoManager;

import online.patino.cinema.service.PersonDtoManager;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.text.ParseException;

@Controller
@RequestMapping("/tmdb")
public class TmdbClientController {
    @Autowired
    private FilmDtoManager filmDtoManager;
    @Autowired
    private PersonDtoManager personDtoManager;


    @GetMapping("/films")
    public String films(Model model) throws IOException, JSONException, ParseException {
     model.addAttribute("films", filmDtoManager.getTmdbMovieListUpcoming(1));
        return "/tmdb/films";
    }
/*
    @GetMapping("/upcoming")
    public String listUpcoming(Model model) throws IOException, JSONException, ParseException {
        model.addAttribute("films", filmDtoManager.getTmdbMovieListUpcoming(1));
        return "/tmdb/films";
    }
    @GetMapping("/latest")
    public String listLatest(Model model) throws IOException, JSONException, ParseException {
        model.addAttribute("films", filmDtoManager.getTmdbMovieListLatest(1));
        return "/tmdb/films";
    }
    @GetMapping("/top")
    public String list(Model model) throws IOException, JSONException, ParseException {
        model.addAttribute("films", filmDtoManager.getTmdbMovieListTop(1));
        return "/tmdb/films";
    }
*/
@GetMapping("/persons")
public String persons(Model model) throws IOException, JSONException, ParseException {
    model.addAttribute("persons", personDtoManager.getTmdbPersonListPopular(1));
    return "/tmdb/persons";
}
}
