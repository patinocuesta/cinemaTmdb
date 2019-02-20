package online.patino.cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GenreTmdb implements Serializable {
private Long id;
private String name;
}
