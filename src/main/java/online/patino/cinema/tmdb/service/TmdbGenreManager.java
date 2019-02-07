package online.patino.cinema.tmdb.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import online.patino.cinema.tmdb.dao.TmdbGenreDao;
import online.patino.cinema.tmdb.model.TmdbGenre;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.*;


@Component
public class TmdbGenreManager {
    @Value("${tmdb.api.key}")
    private String apiKey;

    @Autowired
    private TmdbGenreDao tmdbGenreDao;

    public TmdbGenre getById(Long id) {
        return tmdbGenreDao.findById(id).get();
    }

    public Iterable<TmdbGenre> getAll() {return tmdbGenreDao.findAll();}
    public String getUrl() {
        String url = "https://api.themoviedb.org/3/genre/movie/list?api_key=" + apiKey;
        return url;
    }

        public void importOnlineFullGenre_ids() throws IOException, JSONException {
            RestTemplate template = new RestTemplate();
            ResponseEntity<String> response;
            String resourceUrl =getUrl();
            response = template.getForEntity(resourceUrl, String.class);
            JSONObject genresJsonObj = new JSONObject(response.getBody());
            JSONArray genresJsonArray = (JSONArray) genresJsonObj.get("genres");;

            for (int i = 0; i < genresJsonArray.length(); i++) {
                JSONObject json = genresJsonArray.getJSONObject(i);
                Long id = json.getLong("id");
                String name= json.getString("name");
                TmdbGenre genre = new TmdbGenre(id, name);
                tmdbGenreDao.save(genre);

            }
        }


    }

