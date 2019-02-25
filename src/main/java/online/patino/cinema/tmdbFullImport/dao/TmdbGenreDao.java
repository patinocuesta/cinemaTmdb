package online.patino.cinema.tmdbFullImport.dao;

import online.patino.cinema.tmdbFullImport.model.TmdbGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TmdbGenreDao extends JpaRepository<TmdbGenre, Long> {
}
