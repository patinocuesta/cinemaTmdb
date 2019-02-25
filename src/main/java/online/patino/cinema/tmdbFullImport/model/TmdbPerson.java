package online.patino.cinema.tmdbFullImport.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="tmdb_persons")
public class TmdbPerson {
    @Id
    @Column(name= "id", nullable = false)
    private Long id;
    @Basic
    @Column (name="name")
    private String name;
    @Basic
    @Column (name="popularity")
    private Double popularity;
    @Basic
    @Column (name="adult")
    private Boolean adult;

    public TmdbPerson(){}
    public TmdbPerson(Long id, String name, Double popularity, Boolean adult){
        this.id = id;
        this.name = name;
        this.popularity = popularity;
        this.adult = adult;
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
    public Double getPopularity() {
        return popularity;
    }
    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }
    public Boolean getAdult() {
        return adult;
    }
    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TmdbPerson that = (TmdbPerson) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(popularity, that.popularity) &&
                Objects.equals(adult, that.adult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, popularity, adult);
    }

    @Override
    public String toString() {
        return "TmdbPerson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", popularity=" + popularity +
                ", adult=" + adult +
                '}';
    }
}
