package ai.overmind.model.dto;

import java.util.List;

public class Movie {
	private Integer id;
	private String name;
	private String rate;
	private String comments;
	private String directors;
	private String path;
	private List<Cast> cast;
	
	
	public Movie() {
		super();
	}

	public Movie(Integer id, String name, String rate, String comments, String directors, String path,
			List<Cast> cast) {
		super();
		this.id = id;
		this.name = name;
		this.rate = rate;
		this.comments = comments;
		this.directors = directors;
		this.path = path;
		this.cast = cast;
	}
	

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getRate() {
		return rate;
	}


	public void setRate(String rate) {
		this.rate = rate;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public String getDirectors() {
		return directors;
	}


	public void setDirectors(String directors) {
		this.directors = directors;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public List<Cast> getCast() {
		return cast;
	}


	public void setCast(List<Cast> cast) {
		this.cast = cast;
	}



	
	
	
}
