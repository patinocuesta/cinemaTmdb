package online.patino.cinema.tmdb.service;

import online.patino.cinema.tmdb.dao.TmdbGenreDao;
import online.patino.cinema.tmdb.model.TmdbGenre;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Component
public class TmdbGenreManager {

    private String apiKey = "579e2cef7112c1ad8b0e5909e4becff1";

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
        public void importOnlineFullGenre_ids(){
            try {
                InputStream httpIS = new URL(getUrl()).openStream();
                InputStream bufferedIS = new BufferedInputStream(httpIS);
                BufferedReader br = new BufferedReader(new InputStreamReader(bufferedIS, StandardCharsets.UTF_8));
                String line;
                while ((line = br.readLine()) != null) {
                    JSONObject json = new JSONObject(line);
                    Long id = json.getLong("id");
                    String name= json.getString("name");
                    TmdbGenre genre = new TmdbGenre(id,name);
                    tmdbGenreDao.save(genre);
                }
            } catch (IOException | JSONException e) {e.printStackTrace();}
        }
    }

