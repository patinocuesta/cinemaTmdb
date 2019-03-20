package online.patino.cinema.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FilmDto implements Serializable {
    private String poster_path;
    private Boolean adult;
    private String overview;
    private String release_date;
    private List<GenreDto> genres;
    private Long id;
    private String original_title;
    private String original_language;
    private String title;
    private String backdrop_path;
    private Double popularity;
    private Double vote_count;
    private Boolean video;
    private Double vote_average;
    private Integer runtimeFilm;
    private CreditsDto credits;
    private List<CountryDto> production_countries;

    public FilmDto(){}

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
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

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Double getVote_count() {
        return vote_count;
    }

    public void setVote_count(Double vote_count) {
        this.vote_count = vote_count;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public Integer getRuntimeFilm() {
        return runtimeFilm;
    }

    public void setRuntimeFilm(Integer runtimeFilm) {
        this.runtimeFilm = runtimeFilm;
    }

    @JsonProperty("genres")
    public List<GenreDto> getGenres() {
        return genres;
    }
    @JsonCreator
    public void setGenres(@JsonProperty("genres") List<GenreDto> genres) {
        this.genres = genres;
    }

    @JsonProperty("credits")
    public CreditsDto getCredits() {
        return credits;
    }
    @JsonCreator
    public void setCredits(@JsonProperty("credits") CreditsDto credits) {
        this.credits = credits;
    }

    @JsonProperty("production_countries")
    public List<CountryDto> getProduction_countries() {
        return production_countries;
    }
    @JsonCreator
    public void setProduction_countries(@JsonProperty("production_countries") List<CountryDto> production_countries) {
        this.production_countries = production_countries;
    }
}

