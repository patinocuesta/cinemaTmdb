package fr.laerce.cinema.tmdb.web;

import fr.laerce.cinema.model.Film;
import fr.laerce.cinema.tmdb.model.TmdbFilm;
import fr.laerce.cinema.tmdb.service.TmdbFilmManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("tmdb")
public class TmdbFilmController {
    @Autowired
    TmdbFilmManager tmdbFilmManager;
    @GetMapping("/import")
    public String importFile(){
        return "tmdb/import";
    }
    @RequestMapping(value = "/import",  params = "FilmsURL", method=POST)
    public String importOnlinePost(){
        tmdbFilmManager.importOnlineFullMovie_ids();
        return "index";
    }
    @RequestMapping(value = "/import",  params = "FilmsLocal", method=POST)
    public String importLocalPost(){
        tmdbFilmManager.importLocallyFullMovie_ids();
        return "index";
    }
}


