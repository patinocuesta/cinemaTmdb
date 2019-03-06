package online.patino.cinema.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListGenresDto {
    private  List<GenreDto> genresListDto;

    @JsonCreator
    public ListGenresDto(@JsonProperty("genres")  List<GenreDto> genresListDto) {
        this.genresListDto = genresListDto;
    }

    @JsonProperty("genres")
    public List<GenreDto> getListGenresDto() {
        return genresListDto;
    }

}
