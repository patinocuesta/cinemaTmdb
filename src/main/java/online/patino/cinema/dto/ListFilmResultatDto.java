package online.patino.cinema.dto;

import java.util.List;

public class ListFilmResultatDto {
    private Integer total_results;
    private Integer total_pages;
    private List<FilmDto> listFilmDto;
    private String urlResultat;

    public ListFilmResultatDto(){}

    public ListFilmResultatDto(String urlResultat, Integer total_results, Integer total_pages, List<FilmDto> listFilmDto){
        this.urlResultat=urlResultat;
        this.total_results=total_results;
        this.total_pages=total_pages;
        this.listFilmDto = listFilmDto;
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

    public List<FilmDto> getListFilmDto() {
        return listFilmDto;
    }

    public void setListFilmDto(List<FilmDto> listFilmDto) {
        this.listFilmDto = listFilmDto;
    }

    public String getUrlResultat() {
        return urlResultat;
    }

    public void setUrlResultat(String urlResultat) {
        this.urlResultat = urlResultat;
    }

    @Override
    public String toString() {
        return "ListFilmResultatDto{" +
                "total_results=" + total_results +
                ", total_pages=" + total_pages +
                ", listFilmDto=" + listFilmDto +
                ", urlResultat='" + urlResultat + '\'' +
                '}';
    }
}
