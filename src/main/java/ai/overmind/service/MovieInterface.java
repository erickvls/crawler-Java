package ai.overmind.service;

import java.util.List;

import org.jsoup.nodes.Document;

import ai.overmind.model.dto.Movie;

public interface MovieInterface<T> {
	public List<Movie> listMovies(String path,String language);
	
	public List<Movie> viewDetails(List<Movie> list);
}
