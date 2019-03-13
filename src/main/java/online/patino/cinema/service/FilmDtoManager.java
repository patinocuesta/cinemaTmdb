package online.patino.cinema.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import online.patino.cinema.dto.*;
import online.patino.cinema.dto.rest.CastDto;
import online.patino.cinema.dto.rest.CreditDto;
import online.patino.cinema.dto.rest.CrewDto;
import online.patino.cinema.dto.ListFilmResultatDto;
import online.patino.cinema.dto.rest.ListGenresDto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class FilmDtoManager {

    private final static String API_KEY = "579e2cef7112c1ad8b0e5909e4becff1";
    private static final String TMDB_HOST = "https://api.themoviedb.org/3/";
    private static final String TMDB_SEARCH = TMDB_HOST + "movie?api_key={apiKey}&query=";
    private static final String TMDB_PERSON = TMDB_HOST + "person/{person_id}?api_key="+API_KEY+"&language={fr-FR}";
    private static final String TMDB_REVIEW = TMDB_HOST + "review/{review_id}?api_key="+API_KEY;
    private static final String TMDB_POPULAR = TMDB_HOST + "movie/upcoming?api_key="+API_KEY+"&language=fr-FR&page=";

    private RestTemplate restTemplate;


    public FilmDtoManager(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public FilmDto[] readSearch(String searchStr) {
        searchStr = searchStr.replace("[^\\wéàèêôêë]", "+");
        return restTemplate.getForObject(TMDB_SEARCH + searchStr, FilmDto[].class);
    }

    public ListFilmResultatDto ListFilmsPopular(int page) throws IOException, JSONException, ParseException {
        List<FilmDto> filmsList = new ArrayList<FilmDto>();
        String urlResultat = TMDB_POPULAR + page;
        String json = restTemplate.getForObject(urlResultat, String.class);
        JSONObject obj = new JSONObject(json);
        Integer total_pages = (Integer) obj.get("total_pages");
        Integer current_page = (Integer) obj.get("page");
        Integer total_results = (Integer) obj.get("total_results");
        JSONArray results = (JSONArray) obj.get("results");
        for (int i = 0; i < results.length(); i++) {
            JSONObject  result = (JSONObject) results.get(i);
            FilmDto filmDto = new FilmDto();
            filmDto.setTitle(result.getString("title"));
            filmDto.setOriginal_title(result.getString("original_title"));
            filmDto.setPoster_path(result.getString("poster_path"));
            filmDto.setOverview(result.getString("overview"));
            filmDto.setRelease_date(new SimpleDateFormat("yyyy-MM-dd").parse(result.getString("release_date")));
            filmDto.setId(result.getLong("id"));
            filmDto.setBackdrop_path(result.getString("backdrop_path"));
            filmDto.setPopularity(result.getDouble("popularity"));
            filmDto.setVote_count(result.getDouble("vote_count"));
            filmDto.setVideo(result.getBoolean("video"));
            filmDto.setVote_count(result.getDouble("vote_average"));
            filmsList.add(filmDto);
        }

        return new ListFilmResultatDto (total_results, current_page, total_pages, filmsList) ;
        }

    public FilmDto tmdbFilmDetail(Long id) throws IOException, JSONException, ParseException {
        String urlResultat = TMDB_HOST + "movie/"+id+"?api_key="+API_KEY+"&language=fr-FR&append_to_response=credits,images";
        String json = restTemplate.getForObject(urlResultat, String.class);
        JSONObject obj = new JSONObject(json);

// Unwrap the root value para sacar el array nested
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS, true);
        ListGenresDto listGenresDto = mapper.readValue(json, ListGenresDto.class);
        CreditDto credits = mapper.readValue(json, CreditDto.class);
        FilmDto filmDto = new FilmDto();
        filmDto.setTitle(obj.getString("title"));
        filmDto.setOriginal_title(obj.getString("original_title"));
        filmDto.setPoster_path(obj.getString("poster_path"));
        filmDto.setOverview(obj.getString("overview"));
        filmDto.setRelease_date(new SimpleDateFormat("yyyy-MM-dd").parse(obj.getString("release_date")));
        filmDto.setId(obj.getLong("id"));
        filmDto.setBackdrop_path(obj.getString("backdrop_path"));
        filmDto.setPopularity(obj.getDouble("popularity"));
        filmDto.setVote_count(obj.getDouble("vote_count"));
        filmDto.setVideo(obj.getBoolean("video"));
        filmDto.setVote_count(obj.getDouble("vote_average"));
        filmDto.setGenres(listGenresDto.getListGenresDto());
        filmDto.setCredits( credits.getCreditsDto());
        return filmDto;
    }






}
