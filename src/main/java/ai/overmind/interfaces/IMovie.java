package ai.overmind.interfaces;

import java.util.List;

import org.jsoup.nodes.Document;

import ai.overmind.model.dto.Movie;

public interface IMovie<T> {
	public List<T> listMovies(String path,String language);
	
	public List<T> viewDetails(List<Movie> list);
	
	public List<T> viewComments();
}
