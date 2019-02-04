package fr.laerce.cinema.tmdb.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="tmdb_films")
public class TmdbFilm {
    @Id
    @Column (name = "id", nullable = false)
        private Long id;
    @Basic
    @Column (name="original_title")
        private String original_title;
    @Basic
    @Column (name="popularity")
        private double popularity;

    public TmdbFilm (){}
    public TmdbFilm (Long id,String original_title,double popularity){
        this.id = id;
        this.original_title= original_title;
        this.popularity= popularity;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getOriginal_title() {
        return original_title;
    }
    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }
    public double getPopularity() {
        return popularity;
    }
    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TmdbFilm tmdbFilm = (TmdbFilm) o;
        return Double.compare(tmdbFilm.popularity, popularity) == 0 &&
                Objects.equals(id, tmdbFilm.id) &&
                Objects.equals(original_title, tmdbFilm.original_title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, original_title, popularity);
    }

    @Override
    public String toString() {
        return "TmdbFilm{" +
                "id=" + id +
                ", original_title='" + original_title + '\'' +
                ", popularity=" + popularity +
                '}';
    }
}
