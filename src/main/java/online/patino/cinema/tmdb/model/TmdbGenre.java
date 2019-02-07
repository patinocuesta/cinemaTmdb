package online.patino.cinema.tmdb.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="tmdb_genre")
public class TmdbGenre {
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column (name="name")
    private String name;

    public TmdbGenre(){}
    public TmdbGenre(Long id, String name){
        this.id = id;
        this.name= name;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TmdbGenre tmdbGenre = (TmdbGenre) o;
        return Objects.equals(id, tmdbGenre.id) &&
                Objects.equals(name, tmdbGenre.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
    @Override
    public String toString() {
        return "TmdbGenre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
