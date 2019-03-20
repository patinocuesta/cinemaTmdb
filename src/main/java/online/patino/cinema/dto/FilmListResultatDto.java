package online.patino.cinema.dto;


import java.util.List;

public class FilmListResultatDto {
    private Integer total_results;
    private Integer total_pages;
    private List<FilmDto> listFilmDto;
    private Integer current_page;

    public FilmListResultatDto(){}

    public FilmListResultatDto(Integer total_results, Integer current_page, Integer total_pages, List<FilmDto> listFilmDto){

        this.total_results=total_results;
        this.current_page=current_page;
        this.total_pages=total_pages;
        this.listFilmDto = listFilmDto;
    }

    public Integer getTotal_results() {
        return total_results;
    }

    public void setTotal_results(Integer total_results) {
        this.total_results = total_results;
    }

    public Integer getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(Integer current_page) {
        this.current_page = current_page;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    public List<FilmDto> getListFilmDto() {
        return listFilmDto;
    }

    public void setListFilmDto(List<FilmDto> listFilmDto) {
        this.listFilmDto = listFilmDto;
    }


}
