package online.patino.cinema.api;

import online.patino.cinema.dto.FilmListResultatDto;
import online.patino.cinema.service.FilmDtoManager;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;

@RestController
@RequestMapping("/api/films")
public class FilmListDtoRestController {
    @Autowired
    private FilmDtoManager filmDtoManager;

    public FilmListDtoRestController(FilmDtoManager filmDtoManager){
        assert(filmDtoManager != null);
        this.filmDtoManager = filmDtoManager;
    }

    @GetMapping("/popular/{page}")
    public FilmListResultatDto getPopularByPage (@PathVariable("page") int page) throws IOException, JSONException, ParseException {
      return filmDtoManager.getTmdbMovieListPopular(page);
    }
    @GetMapping("/latest/{page}")
    public FilmListResultatDto getLatestByPage (@PathVariable("page") int page) throws IOException, JSONException, ParseException {
        return filmDtoManager.getTmdbMovieListLatest(page);
    }
    @GetMapping("/upcoming/{page}")
    public FilmListResultatDto getUpcomingByPage (@PathVariable("page") int page) throws IOException, JSONException, ParseException {
        return filmDtoManager.getTmdbMovieListUpcoming(page);
    }
    @GetMapping("/top/{page}")
    public FilmListResultatDto getTopRatedByPage (@PathVariable("page") int page) throws IOException, JSONException, ParseException {
        return filmDtoManager.getTmdbMovieListTop(page);
    }
}
