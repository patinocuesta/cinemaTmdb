package online.patino.cinema.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CountriesListDto implements Serializable {
    private List<CountryDto> listCountriesDto;

    @JsonCreator
    public CountriesListDto(@JsonProperty("production_countries")  List<CountryDto> listCountriesDto) {
        this.listCountriesDto = listCountriesDto;
    }

    @JsonProperty("production_countries")
    public List<CountryDto> getListCountriesDto() {
        return listCountriesDto;
    }

}
