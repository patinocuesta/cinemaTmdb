package online.patino.cinema.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import online.patino.cinema.dto.*;
import online.patino.cinema.dto.FilmListResultatDto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FilmDtoManager {

    private final static String API_KEY = "579e2cef7112c1ad8b0e5909e4becff1";
    private static final String TMDB_HOST = "https://api.themoviedb.org/3/movie/";
    private static final String TMDB_SEARCH = TMDB_HOST + "movie?api_key={apiKey}&query=";
    private static final String TMDB_POPULAR = TMDB_HOST + "upcoming?api_key="+API_KEY+"&language=fr-FR&page=";
    private static final String TMDB_LATEST = TMDB_HOST + "latest?api_key="+API_KEY+"&language=fr-FR&page=";
    private static final String TMDB_UPCOMING = TMDB_HOST + "upcoming?api_key="+API_KEY+"&language=fr-FR&page=";
    private static final String TMDB_TOP_RATED = TMDB_HOST + "top_rated?api_key="+API_KEY+"&language=fr-FR&page=";


    private RestTemplate restTemplate;


    public FilmDtoManager(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public FilmDto[] readSearch(String searchStr) {
        searchStr = searchStr.replace("[^\\wéàèêôêë]", "+");
        return restTemplate.getForObject(TMDB_SEARCH + searchStr, FilmDto[].class);
    }

    public FilmDto parserFilmDto (JSONObject  result) throws JSONException {
        FilmDto filmDto = new FilmDto();
        filmDto.setTitle(result.getString("title"));
        filmDto.setOriginal_title(result.getString("original_title"));
        filmDto.setOriginal_language(result.getString("original_language"));
        filmDto.setPoster_path(result.getString("poster_path"));
        filmDto.setOverview(result.getString("overview"));
        filmDto.setRelease_date(result.getString("release_date"));
        filmDto.setId(result.getLong("id"));
        filmDto.setBackdrop_path(result.getString("backdrop_path"));
        filmDto.setPopularity(result.getDouble("popularity"));
        filmDto.setVote_count(result.getDouble("vote_count"));
        filmDto.setVideo(result.getBoolean("video"));
        filmDto.setVote_count(result.getDouble("vote_average"));
        return filmDto;
    }

    public FilmListResultatDto getTmdbMovieListPopular (int page) throws JSONException, ParseException, IOException {
        return tmdbMovieList (page,TMDB_POPULAR );
    }

    public FilmListResultatDto getTmdbMovieListLatest (int page) throws JSONException, ParseException, IOException {
        return tmdbMovieList (page,TMDB_LATEST );
    }

    public FilmListResultatDto getTmdbMovieListUpcoming (int page) throws JSONException, ParseException, IOException {
        return tmdbMovieList (page,TMDB_UPCOMING );
    }

    public FilmListResultatDto getTmdbMovieListTop (int page) throws JSONException, ParseException, IOException {
        return tmdbMovieList (page,TMDB_TOP_RATED );
    }

    public JSONObject jSONObjectBuilder (String urlResultat) throws JSONException {
        String json = restTemplate.getForObject(urlResultat, String.class);
        JSONObject obj = new JSONObject(json);
        return obj;
    }

    public FilmListResultatDto tmdbMovieList(int page, String url) throws IOException, JSONException, ParseException {
        List<FilmDto> filmsList = new ArrayList<FilmDto>();
        Integer total_pages = (Integer) jSONObjectBuilder (url + page).get("total_pages");
        Integer current_page = (Integer) jSONObjectBuilder (url + page).get("page");
        Integer total_results = (Integer) jSONObjectBuilder (url + page).get("total_results");
        JSONArray results = (JSONArray) jSONObjectBuilder (url + page).get("results");
        for (int i = 0; i < results.length(); i++) {
            JSONObject  result = (JSONObject) results.get(i);
             filmsList.add(parserFilmDto(result));
        }
        return new FilmListResultatDto(total_results, current_page, total_pages, filmsList) ;
        }

    public FilmDto tmdbFilmDetail(Long id) throws IOException, JSONException, ParseException {
        jSONObjectBuilder(TMDB_HOST + id+"?api_key="+API_KEY+"&language=fr-FR&append_to_response=credits,images");
        FilmDto filmDto = parserFilmDto(jSONObjectBuilder(TMDB_HOST + id+"?api_key="+API_KEY+"&language=fr-FR&append_to_response=credits,images"));
        filmDto.setRuntimeFilm(jSONObjectBuilder(TMDB_HOST + id+"?api_key="+API_KEY+"&language=fr-FR&append_to_response=credits,images").getInt("runtime"));
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS, true);
        GenresListDto genresListDto = mapper.readValue(restTemplate.getForObject(TMDB_HOST + id+"?api_key="+API_KEY+"&language=fr-FR&append_to_response=credits,images", String.class), GenresListDto.class);
        CountriesListDto production_countriesDto = mapper.readValue(restTemplate.getForObject(TMDB_HOST + id+"?api_key="+API_KEY+"&language=fr-FR&append_to_response=credits,images", String.class), CountriesListDto.class);
        CreditDto credits = mapper.readValue(restTemplate.getForObject(TMDB_HOST + id+"?api_key="+API_KEY+"&language=fr-FR&append_to_response=credits,images", String.class), CreditDto.class);
        filmDto.setGenres(genresListDto.getListGenresDto());
        filmDto.setCredits( credits.getCreditsDto());
        filmDto.setProduction_countries(production_countriesDto.getListCountriesDto());
        return filmDto;
    }






}
