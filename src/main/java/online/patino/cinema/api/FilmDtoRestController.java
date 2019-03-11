package online.patino.cinema.api;

import online.patino.cinema.dto.FilmDto;
import online.patino.cinema.model.Film;
import online.patino.cinema.service.FilmDtoManager;
import online.patino.cinema.service.FilmManager;
import org.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;

@RestController
@RequestMapping("/api/tmdb/list/film")
public class FilmDtoRestController {

    private FilmDtoManager filmDtoManager;

    public FilmDtoRestController(FilmDtoManager filmDtoManager){
        assert(filmDtoManager != null);
        this.filmDtoManager = filmDtoManager;
    }
    @GetMapping("/{id}")
    public FilmDto getById(@PathVariable("id")long id) throws IOException, JSONException, ParseException {
        return filmDtoManager.tmdbFilmDetail(id);
    }

}
