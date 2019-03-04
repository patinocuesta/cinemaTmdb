package online.patino.cinema.tmdbFullImport.service;

import online.patino.cinema.tmdbFullImport.dao.TmdbGenreDao;
import online.patino.cinema.tmdbFullImport.model.TmdbGenre;
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


    @Autowired
    private TmdbGenreDao tmdbGenreDao;

    public TmdbGenre getById(Long id) {
        return tmdbGenreDao.findById(id).get();
    }

    public Iterable<TmdbGenre> getAll() {return tmdbGenreDao.findAll();}
    public String getUrl() {
        String url = "";
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

