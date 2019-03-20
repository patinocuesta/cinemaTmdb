package online.patino.cinema.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import online.patino.cinema.dto.GenreDto;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GenresListDto {
    private  List<GenreDto> genresListDto;

    @JsonCreator
    public GenresListDto(@JsonProperty("genres")  List<GenreDto> genresListDto) {
        this.genresListDto = genresListDto;
    }

    @JsonProperty("genres")
    public List<GenreDto> getListGenresDto() {
        return genresListDto;
    }

}
