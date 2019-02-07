package online.patino.cinema.tmdb.dao;

import online.patino.cinema.tmdb.model.TmdbGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface TmdbGenreDao extends JpaRepository<TmdbGenre, Long> {
}
