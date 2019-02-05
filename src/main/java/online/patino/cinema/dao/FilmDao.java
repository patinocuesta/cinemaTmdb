package online.patino.cinema.dao;

import online.patino.cinema.model.Film;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmDao extends CrudRepository<Film, Long> {
    public List<Film> findAllByOrderByTitle();
}
