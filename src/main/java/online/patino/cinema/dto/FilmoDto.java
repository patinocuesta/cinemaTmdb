package online.patino.cinema.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FilmoDto implements Serializable {
    private List<FilmDto> filmo;
    @JsonProperty("known_for")
    public List<FilmDto> getFilmo() {
        return filmo;
    }
    @JsonCreator
    public void setFilmo(@JsonProperty("known_for") List<FilmDto> filmo) {
        this.filmo = filmo;
    }
}
