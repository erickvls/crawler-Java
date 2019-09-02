package ai.overmind.model.dto;

import java.util.HashMap;
import java.util.List;

public class Movie {
	private Integer id;
	private String name;
	private String rate;
	private List<Comments> comments;
	private String directors;
	private String url;
	
	private HashMap<String,String> cast = new HashMap<String,String>();

	public Movie() {
		super();
	}

	public Movie(Integer id, String name, String rate, List<Comments> comments, String directors, String url,
			HashMap<String, String> cast) {
		super();
		this.id = id;
		this.name = name;
		this.rate = rate;
		this.comments = comments;
		this.directors = directors;
		this.url = url;
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

	public List<Comments> getComments() {
		return comments;
	}
	
	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	public String getDirectors() {
		return directors;
	}

	public void setDirectors(String directors) {
		this.directors = directors;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public HashMap<String, String> getCast() {
		return cast;
	}

	public void setCast(HashMap<String, String> cast) {
		this.cast = cast;
	}

}
