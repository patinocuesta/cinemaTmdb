package online.patino.cinema.tmdb.web;

import online.patino.cinema.tmdb.service.TmdbFilmManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/tmdb")
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
        return "redirect:../";
    }
    @RequestMapping(value = "/import",  params = "FilmsLocal", method=POST)
    public String importLocalPost(){
        tmdbFilmManager.importLocallyFullMovie_ids();
        return "redirect:../";
    }
}


