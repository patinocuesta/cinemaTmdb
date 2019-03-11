package online.patino.cinema.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditsDto implements Serializable {
    private List<CastMemberDto> cast;
    private List<CrewMemberDto> crew;

    @JsonProperty("cast")
    public List<CastMemberDto> getCast() {
        return cast;
    }
    @JsonCreator
    public void setCast(@JsonProperty("cast") List<CastMemberDto> cast) {
        this.cast = cast;
    }

    @JsonProperty("crew")
    public List<CrewMemberDto> getCrew() {
        return crew;
    }
    @JsonCreator
    public void setCrew(@JsonProperty("crew") List<CrewMemberDto> crew) {
        this.crew = crew;
    }

}
