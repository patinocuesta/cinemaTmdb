package online.patino.cinema.service;

import online.patino.cinema.model.FilmTmdb;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class TmdbRestClient {
    @Value("${tmdb.api.key}")
    private static  String APIKEY;
    private static final String TMDB_URL_START = "https://api.themoviedb.org/3/";
    private static final String TMDB_SEARCH =TMDB_URL_START+"movie?api_key="+APIKEY+"&query=";
    private static final String TMDB_FILM =TMDB_URL_START+"movie/{movie_id}?api_key="+APIKEY+"&language={fr-FR}";
    private static final String TMDB_PERSON =TMDB_URL_START+"person/{person_id}?api_key="+APIKEY+"&language={fr-FR}";
    private static final String TMDB_REVIEW =TMDB_URL_START+"review/{review_id}?api_key="+APIKEY;
    private static final String TMDB_POPULAR = TMDB_URL_START+"movie/upcoming?api_key="+APIKEY+"&language=fr-FR&page=1";

    private RestTemplate restTemplate;
    public TmdbRestClient (RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }
    public FilmTmdb[] readSearch(String searchStr){
        searchStr = searchStr.replace("[^\\wéàèêôêë]", "+");
        return restTemplate.getForObject(TMDB_SEARCH+searchStr, FilmTmdb[].class);
    }
    public FilmTmdb[] readPopular(){

        return restTemplate.getForObject(TMDB_POPULAR, FilmTmdb[].class);
    }
}
