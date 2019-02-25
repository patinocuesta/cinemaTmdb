package online.patino.cinema.tmdbFullImport.dao;

import online.patino.cinema.tmdbFullImport.model.TmdbPerson;
import org.springframework.data.repository.CrudRepository;

public interface TmdbPersonDao  extends CrudRepository<TmdbPerson, Long> {
}
