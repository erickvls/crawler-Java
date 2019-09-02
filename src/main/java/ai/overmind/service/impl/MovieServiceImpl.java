package ai.overmind.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.overmind.model.dto.Comments;
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
		return addDetailsToMovie(top10MoviesList);
	}
	
	

	private List<Movie> addDetailsToMovie(List<Movie> top10MoviesList) {
		
		List<Movie> movieListDetails= new ArrayList<>();
		
		for (Movie movie: top10MoviesList) {
			Document dom = jsoupService.initialize(movie.getUrl(), "en");
			Elements directorsMovie = dom.select("div[class='credit_summary_item']");
			movie.setDirectors(directorsMovie.first().getElementsByAttribute("href").html());
			Elements castTable = dom.select("table[class='cast_list']");	
			addCastMovieDto(castTable,movie);
			addCommentToMovie(movie);
			movieListDetails.add(movie);
		}
		
		return movieListDetails;

	}


	private List<Movie> addMovieNamesAndRating(Elements bodyTable) {
		List<Movie> moviesList = new ArrayList<>();
		
		for (Element trElement : bodyTable.select("tr")) {
			Movie movie = new Movie();
			movie.setId(Integer.parseInt(trElement.getElementsByClass("posterColumn").get(0).selectFirst("span[name='rk']").attr("data-value")));
			movie.setUrl(trElement.getElementsByClass("posterColumn").get(0).selectFirst("a").attr("href"));
			movie.setName(trElement.getElementsByClass("titleColumn").get(0).getElementsByAttribute("href").text());
			movie.setRate(trElement.getElementsByClass("ratingColumn imdbRating").text());
			moviesList.add(movie);
		}
		
		return moviesList;
	}
	
	private void addCastMovieDto(Elements castTable,Movie movie) {
		for (Element castElement: castTable.select("tr")) {
			movie.getCast().put(castElement.select("td:nth-child(2)").text(), castElement.getElementsByAttributeValue("class", "character").text());
		}
	}
	
	private void addCommentToMovie(Movie movie) {
		String [] path = movie.getUrl().split("/");
		Document dom = jsoupService.initialize("title/"+path[2]+"/reviews?ref_=tt_urv", "en");
		Elements commentsDiv = dom.select("div[class='lister-list']");
		
		List<Comments> commentsList = new ArrayList<Comments>();
		for(Element element : commentsDiv.select("div[class^='lister-item mode-detail imdb-user-review ']")) {
			Comments comments = new Comments();
			if(checkHasRating(element)) {
				Double rate = convertRateToNumber(element.getElementsByClass("ipl-ratings-bar").text());
				if(checkGreaterEqualThanFive(rate)) {
					comments = createComment(element,rate);
					commentsList.add(comments);
				}
			}
			
		}
		movie.setComments(commentsList);
	}
	
	private Comments createComment(Element element,Double rate) {
		Comments comments = new Comments();
		comments.setTitle(element.getElementsByClass("lister-item-content").get(0).getElementsByClass("title").text());
		comments.setContent(element.getElementsByClass("text show-more__control").text());
		comments.setRate(rate);
		comments.setAuthor(element.getElementsByClass("display-name-link").text());
		return comments;
	}
	
	private Boolean checkHasRating(Element element) {
		return element.getElementsByClass("ipl-ratings-bar").hasText() ? true : false;
	}
	
	private Boolean checkGreaterEqualThanFive(Double number) {
		return number >4 ? true : false;
	}
	
	private Double convertRateToNumber(String rate) {
		String [] arrayValues = rate.split("/");
		Double number = Double.parseDouble(arrayValues[0]);
		return number;
	}
	
	
}
