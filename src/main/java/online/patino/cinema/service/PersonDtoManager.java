package online.patino.cinema.service;

import online.patino.cinema.dto.FilmDto;
import online.patino.cinema.dto.FilmListResultatDto;
import online.patino.cinema.dto.PersonDto;
import online.patino.cinema.dto.PersonListResultatDto;
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
public class PersonDtoManager {
    private final static String API_KEY = "579e2cef7112c1ad8b0e5909e4becff1";
    private static final String TMDB_HOST = "https://api.themoviedb.org/3/person/";
    private static final String TMDB_PERSON = TMDB_HOST + "person/{person_id}?api_key="+API_KEY;
    private static final String TMDB_PERSON_POPULAR = TMDB_HOST + "popular?api_key="+API_KEY+"&page=";

    private RestTemplate restTemplate;

    public PersonDtoManager(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public JSONObject jSONObjectBuilder (String urlResultat) throws JSONException {
        String json = restTemplate.getForObject(urlResultat, String.class);
        JSONObject obj = new JSONObject(json);
        return obj;
    }

    public PersonDto parserPersonDto (JSONObject  result) throws JSONException {
        PersonDto personDto = new PersonDto();
        personDto.setId(result.getLong("id"));
        personDto.setProfile_path(result.getString("profile_path"));
        personDto.setName(result.getString("name"));
        return personDto;
    }

    public PersonListResultatDto tmdbPersonList(int page, String url) throws IOException, JSONException, ParseException {
        List<PersonDto> personList = new ArrayList<PersonDto>();
        Integer total_pages = (Integer) jSONObjectBuilder (url + page).get("total_pages");
        Integer current_page = (Integer) jSONObjectBuilder (url + page).get("page");
        Integer total_results = (Integer) jSONObjectBuilder (url + page).get("total_results");
        JSONArray results = (JSONArray) jSONObjectBuilder (url + page).get("results");
        for (int i = 0; i < results.length(); i++) {
            JSONObject  result = (JSONObject) results.get(i);
            personList.add(parserPersonDto(result));
        }
        return new PersonListResultatDto(total_results, current_page, total_pages, personList) ;
    }
    public PersonListResultatDto getTmdbPersonListPopular (int page) throws JSONException, ParseException, IOException {
        return tmdbPersonList (page,TMDB_PERSON_POPULAR );
    }

}
