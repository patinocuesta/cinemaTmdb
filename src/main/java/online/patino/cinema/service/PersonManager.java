package online.patino.cinema.service;

import online.patino.cinema.dao.PersonDao;
import online.patino.cinema.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonManager {
    @Autowired
    PersonDao personDao;

    public List<Person> getAll(){
        return personDao.findAllByOrderBySurname();
    }
}
