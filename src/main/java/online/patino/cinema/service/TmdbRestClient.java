package online.patino.cinema.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import online.patino.cinema.model.FilmTmdb;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TmdbRestClient {
    @Value("${tmdb.api.key}")
    private static String APIKEY;
    private static final String TMDB_URL_START = "https://api.themoviedb.org/3/";
    private static final String TMDB_SEARCH = TMDB_URL_START + "movie?api_key=" + APIKEY + "&query=";
    private static final String TMDB_FILM = TMDB_URL_START + "movie/{movie_id}?api_key=" + APIKEY + "&language={fr-FR}";
    private static final String TMDB_PERSON = TMDB_URL_START + "person/{person_id}?api_key=" + APIKEY + "&language={fr-FR}";
    private static final String TMDB_REVIEW = TMDB_URL_START + "review/{review_id}?api_key=" + APIKEY;
    private static final String TMDB_POPULAR = TMDB_URL_START + "movie/upcoming?api_key=579e2cef7112c1ad8b0e5909e4becff1&language=fr-FR&page=1";

    private RestTemplate restTemplate;


    public TmdbRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public FilmTmdb[] readSearch(String searchStr) {
        searchStr = searchStr.replace("[^\\wéàèêôêë]", "+");
        return restTemplate.getForObject(TMDB_SEARCH + searchStr, FilmTmdb[].class);
    }

    public List<FilmTmdb> readPopular() throws IOException, JSONException {
        List<FilmTmdb> filmsList = new ArrayList<FilmTmdb>();
        String json = restTemplate.getForObject(TMDB_POPULAR, String.class);
        JSONObject obj = new JSONObject(json);
        JSONArray results = (JSONArray) obj.get("results");
        for (int i = 0; i < results.length(); i++) {
            JSONObject  result = (JSONObject) results.get(i);
            FilmTmdb filmTmdb = new FilmTmdb();
            filmTmdb.setTitle(result.getString("title"));
            filmTmdb.setOriginal_title(result.getString("original_title"));
            filmTmdb.setPoster_path(result.getString("poster_path"));
            filmTmdb.setOverview(result.getString("overview"));
            filmTmdb.setRelease_date(result.getString("release_date"));
            filmTmdb.setId(result.getString("id"));
            filmTmdb.setBackdrop_path(result.getString("backdrop_path"));
            filmTmdb.setPopularity(result.getString("popularity"));
            filmTmdb.setVote_count(result.getString("vote_count"));
            filmTmdb.setVideo(result.getString("video"));
            filmTmdb.setVote_count(result.getString("vote_average"));
            filmsList.add(filmTmdb);
        }
        return filmsList;
        }

}
