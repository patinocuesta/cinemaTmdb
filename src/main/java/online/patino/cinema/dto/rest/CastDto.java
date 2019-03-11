package online.patino.cinema.dto.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import online.patino.cinema.dto.CastMemberDto;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CastDto implements Serializable {
    private List<CastMemberDto> castDto;

    @JsonCreator
    public CastDto (@JsonProperty("cast") List<CastMemberDto> castDto) {
        this.castDto = castDto;
    }

    @JsonProperty("cast")
    public List<CastMemberDto> getCastDto() {
        return castDto;
    }
}
