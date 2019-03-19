package online.patino.cinema.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import online.patino.cinema.dto.CreditsDto;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditDto  {
    private CreditsDto creditsDto;

    @JsonCreator
    public CreditDto(@JsonProperty("credits")  CreditsDto creditsDto) {
        this.creditsDto = creditsDto;
    }

    @JsonProperty("credits")
    public CreditsDto getCreditsDto() {return creditsDto;}

}
