package online.patino.cinema.api;

import online.patino.cinema.dto.FilmDto;
import online.patino.cinema.dto.ListFilmResultatDto;
import online.patino.cinema.service.FilmDtoManager;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/tmdb/list/")
public class FilmListDtoRestController {
    @Autowired
    private FilmDtoManager filmDtoManager;

    public FilmListDtoRestController(FilmDtoManager filmDtoManager){
        assert(filmDtoManager != null);
        this.filmDtoManager = filmDtoManager;
    }

    @GetMapping("/{page}")
    public ListFilmResultatDto getByPage (@PathVariable("page") int page) throws IOException, JSONException {
      return filmDtoManager.ListFilmsPopular(page);
    }
}
