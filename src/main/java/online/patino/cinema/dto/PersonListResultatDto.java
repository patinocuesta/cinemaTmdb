package online.patino.cinema.dto;

import java.util.List;

public class PersonListResultatDto {
    private Integer total_results;
    private Integer total_pages;
    private List<PersonDto> listPersonDto;
    private Integer current_page;

    public PersonListResultatDto(){}

    public PersonListResultatDto(Integer total_results, Integer current_page, Integer total_pages, List<PersonDto> listPersonDto){
        this.total_results=total_results;
        this.current_page=current_page;
        this.total_pages=total_pages;
        this.listPersonDto=listPersonDto;
    }

    public Integer getTotal_results() {
        return total_results;
    }

    public void setTotal_results(Integer total_results) {
        this.total_results = total_results;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    public List<PersonDto> getListPersonDto() {
        return listPersonDto;
    }

    public void setListPersonDto(List<PersonDto> listPersonDto) {
        this.listPersonDto = listPersonDto;
    }

    public Integer getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(Integer current_page) {
        this.current_page = current_page;
    }
}
