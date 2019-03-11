package online.patino.cinema.dto.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import online.patino.cinema.dto.CrewMemberDto;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CrewDto implements Serializable {
    private List<CrewMemberDto> crewDto;

    @JsonCreator
    public  CrewDto (@JsonProperty("crew")  List<CrewMemberDto> crewDto) {
        this.crewDto = crewDto;
    }

    @JsonProperty("crew")
    public  List<CrewMemberDto> getCrewDto() {
        return crewDto;
    }

}
