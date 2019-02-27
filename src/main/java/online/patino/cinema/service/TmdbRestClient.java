package online.patino.cinema.service;

import online.patino.cinema.dto.FilmDto;
import online.patino.cinema.dto.ListFilmResultatDto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TmdbRestClient {
    @Value("${tmdb.api.key}")
    private String apiKey;
    private static final String TMDB_URL_START = "https://api.themoviedb.org/3/";
    private static final String TMDB_SEARCH = TMDB_URL_START + "movie?api_key={apiKey}&query=";
    private static final String TMDB_FILM = TMDB_URL_START + "movie/{movie_id}?api_key={apiKey}&language={fr-FR}";
    private static final String TMDB_PERSON = TMDB_URL_START + "person/{person_id}?api_key={apiKey}&language={fr-FR}";
    private static final String TMDB_REVIEW = TMDB_URL_START + "review/{review_id}?api_key={apiKey}";
    private static final String TMDB_POPULAR ="https://api.themoviedb.org/3/movie/upcoming?api_key=579e2cef7112c1ad8b0e5909e4becff1&language=fr-FR&page=";

    private RestTemplate restTemplate;


    public TmdbRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public FilmDto[] readSearch(String searchStr) {
        searchStr = searchStr.replace("[^\\wéàèêôêë]", "+");
        return restTemplate.getForObject(TMDB_SEARCH + searchStr, FilmDto[].class);
    }

    public ListFilmResultatDto ListFilmsPopular(int page) throws IOException, JSONException {
        List<FilmDto> filmsList = new ArrayList<FilmDto>();
        String urlResultat = TMDB_POPULAR + page;
        String json = restTemplate.getForObject(urlResultat, String.class);
        JSONObject obj = new JSONObject(json);
        Integer total_pages = (Integer) obj.get("total_pages");
        Integer total_results = (Integer) obj.get("total_results");
        JSONArray results = (JSONArray) obj.get("results");
        for (int i = 0; i < results.length(); i++) {
            JSONObject  result = (JSONObject) results.get(i);
            FilmDto filmDto = new FilmDto();
            filmDto.setTitle(result.getString("title"));
            filmDto.setOriginal_title(result.getString("original_title"));
            filmDto.setPoster_path(result.getString("poster_path"));
            filmDto.setOverview(result.getString("overview"));
            filmDto.setRelease_date(result.getString("release_date"));
            filmDto.setId(result.getString("id"));
            filmDto.setBackdrop_path(result.getString("backdrop_path"));
            filmDto.setPopularity(result.getString("popularity"));
            filmDto.setVote_count(result.getString("vote_count"));
            filmDto.setVideo(result.getString("video"));
            filmDto.setVote_count(result.getString("vote_average"));
            filmsList.add(filmDto);
        }
        return new ListFilmResultatDto (urlResultat, total_results, total_pages, filmsList) ;
        }








}
