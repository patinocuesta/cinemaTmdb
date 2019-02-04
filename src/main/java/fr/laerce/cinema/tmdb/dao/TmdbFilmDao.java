package fr.laerce.cinema.tmdb.dao;

import fr.laerce.cinema.tmdb.model.TmdbFilm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TmdbFilmDao extends CrudRepository<TmdbFilm, Long> {
     List<TmdbFilm> findAllByOrderByIdAsc();
     List<TmdbFilm> findAllByOrderByPopularityDesc();
}
