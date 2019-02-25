package online.patino.cinema.tmdbFullImport.dao;

import online.patino.cinema.tmdbFullImport.model.TmdbFilm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TmdbFilmDao extends CrudRepository<TmdbFilm, Long> {

}
