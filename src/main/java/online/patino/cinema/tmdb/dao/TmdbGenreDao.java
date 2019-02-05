package online.patino.cinema.tmdb.dao;

import online.patino.cinema.tmdb.model.TmdbGenre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TmdbGenreDao extends CrudRepository<TmdbGenre, Long> {
}
