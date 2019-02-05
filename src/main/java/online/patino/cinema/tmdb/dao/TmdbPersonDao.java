package online.patino.cinema.tmdb.dao;

import online.patino.cinema.tmdb.model.TmdbPerson;
import org.springframework.data.repository.CrudRepository;

public interface TmdbPersonDao  extends CrudRepository<TmdbPerson, Long> {
}
