package online.patino.cinema.tmdb.web;

import online.patino.cinema.tmdb.service.TmdbFilmManager;
import online.patino.cinema.tmdb.service.TmdbGenreManager;
import online.patino.cinema.tmdb.service.TmdbPersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/tmdb")
public class TmdbImportController {
    @Autowired
    TmdbFilmManager tmdbFilmManager;
    @Autowired
    TmdbPersonManager tmdbPersonManager;
    @Autowired
    TmdbGenreManager tmdbGenreManager;

    @GetMapping("/import")
    public String tmdbImportController(){
        return "tmdb/import";
    }

    @RequestMapping(value = "/import",  params = "FilmsURL", method=POST)
    public String importOnlineFilms(){
        tmdbFilmManager.importOnlineFullMovie_ids();
        return "redirect:../";
    }
    @RequestMapping(value = "/import",  params = "FilmsLocal", method=POST)
    public String importLocalFilms(){
        tmdbFilmManager.importLocallyFullMovie_ids();
        return "redirect:../";
    }
    @RequestMapping(value = "/import",  params = "PersonsURL", method=POST)
    public String importOnlinePersons(){
        tmdbPersonManager.importOnlineFullPerson_ids();
        return "redirect:../";
    }
    @RequestMapping(value = "/import",  params = "PersonsLocal", method=POST)
    public String importLocalPersons(){
        tmdbPersonManager.importLocallyFullPerson_ids();
        return "redirect:../";
    }
    @RequestMapping(value = "/import",  params = "GenresURL", method=POST)
    public String importOnlineGenres(){
        tmdbGenreManager.importOnlineFullGenre_ids();
        return "redirect:../";
    }










}


