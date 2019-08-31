package ai.overmind.service;

import java.util.List;

import org.jsoup.nodes.Document;

import ai.overmind.model.dto.Movie;

public interface MovieInterface<T> {
	public List<T> listMovies(String path,String language);
	
	public List<T> viewDetails(List<Movie> list);
}
