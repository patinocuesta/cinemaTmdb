package online.patino.cinema.dao;

import online.patino.cinema.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonDao extends CrudRepository<Person, Long> {
    public List<Person> findAllByOrderBySurname();
}
