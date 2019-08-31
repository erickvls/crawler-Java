package ai.overmind.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.overmind.model.dto.Movie;
import ai.overmind.service.JSoupService;
import ai.overmind.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	JSoupService jsoupService;

	@Override
	public List<Movie> listMovies(String path,String language) {
		
		Document dom = jsoupService.initialize(path, language);
		
		Elements bodyTable = dom.select("tbody[class='lister-list']");

		List<Movie> moviesList = addMovieNamesAndRating(bodyTable);
		
		Collections.reverse(moviesList);
		
		List <Movie> top10MoviesList =  moviesList.subList(0, 10);
		
		return viewDetails(top10MoviesList);
	}
	
	

	@Override
	public List<Movie> viewDetails(List<Movie> top10MoviesList) {
		
		List<Movie> movieListDetails= new ArrayList<>();
		
		for (Movie movie: top10MoviesList) {
			Document dom = jsoupService.initialize(movie.getPath(), "en");
	
			Elements directorsMovie = dom.select("div[class='credit_summary_item']");
			movie.setDirectors(directorsMovie.first().getElementsByAttribute("href").html());
			
			Elements castTable = dom.select("table[class='cast_list']");
			
			addCastMovie(castTable,movie);
			movieListDetails.add(movie);
		}
		return movieListDetails;

	}

	@Override
	public List<Movie> viewComments() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public List<Movie> addMovieNamesAndRating(Elements bodyTable) {
		List<Movie> moviesList = new ArrayList<>();
		
		for (Element trElement : bodyTable.select("tr")) {
			Movie movie = new Movie();
			movie.setId(Integer.parseInt(trElement.getElementsByClass("posterColumn").get(0).selectFirst("span[name='rk']").attr("data-value")));
			movie.setPath(trElement.getElementsByClass("posterColumn").get(0).selectFirst("a").attr("href"));
			movie.setName(trElement.getElementsByClass("titleColumn").get(0).getElementsByAttribute("href").text());
			movie.setRate(trElement.getElementsByClass("ratingColumn imdbRating").text());
			moviesList.add(movie);
		}
		
		return moviesList;
	}
	
	public void addCastMovie(Elements castTable,Movie movie) {
		for (Element castElement: castTable.select("tr")) {
			movie.getCast().put(castElement.select("td:nth-child(2)").text(), castElement.getElementsByAttributeValue("class", "character").text());
		}
	}
	
	
}
