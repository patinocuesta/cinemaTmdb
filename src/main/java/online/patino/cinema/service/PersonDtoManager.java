package online.patino.cinema.service;

import org.springframework.stereotype.Component;

@Component
public class PersonDtoManager {
    private final static String API_KEY = "579e2cef7112c1ad8b0e5909e4becff1";
    private static final String TMDB_HOST = "https://api.themoviedb.org/3/";
    private static final String TMDB_PERSON = TMDB_HOST + "person/{person_id}?api_key="+API_KEY+"&language={fr-FR}";
    private static final String TMDB_POPULAR = TMDB_HOST + "movie/upcoming?api_key="+API_KEY+"&language=fr-FR&page=";
}
