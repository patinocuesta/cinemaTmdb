package online.patino.cinema.api;

import online.patino.cinema.dto.PersonListResultatDto;
import online.patino.cinema.service.PersonDtoManager;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;

@RestController
@RequestMapping("/api/persons")
public class PersonListDtoRestController {
    @Autowired
    private PersonDtoManager personDtoManager;

    public PersonListDtoRestController(PersonDtoManager personDtoManager){
        assert(personDtoManager != null);
        this.personDtoManager = personDtoManager;
    }

    @GetMapping("/popular/{page}")
    public PersonListResultatDto getPopularPersonByPage (@PathVariable("page") int page) throws IOException, JSONException, ParseException {
        return personDtoManager.getTmdbPersonListPopular(page);
    }
}
