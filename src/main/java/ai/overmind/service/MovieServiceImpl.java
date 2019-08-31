package ai.overmind.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.overmind.model.dto.Cast;
import ai.overmind.model.dto.Movie;

@Service
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	JsoupConnect jsoupConnect;

	@Override
	public List<Movie> listMovies(String path,String language) {
		
		Document dom = jsoupConnect.initialize(path, language);
		
		List<Movie> movies = new ArrayList<>();
		
		Elements bodyTable = dom.select("tbody[class='lister-list']");

		for (Element element2 : bodyTable.select("tr")) {
			Movie movie = new Movie();
			movie.setId(Integer.parseInt(element2.getElementsByClass("posterColumn").get(0).selectFirst("span[name='rk']").attr("data-value")));
			movie.setPath(element2.getElementsByClass("posterColumn").get(0).selectFirst("a").attr("href"));
			movie.setName(element2.getElementsByClass("titleColumn").get(0).getElementsByAttribute("href").text());
			movie.setRate(element2.getElementsByClass("ratingColumn imdbRating").text());
			movies.add(movie);
		}
		
		Collections.reverse(movies);
		
		List <Movie> top10Movies =  movies.subList(0, 10);
		
		return viewDetails(top10Movies);
	}

	@Override
	public List<Movie> viewDetails(List<Movie> list) {
		List<Movie> movieDetails= new ArrayList<>();
		
		for (Movie movie: list) {
			Document dom = jsoupConnect.initialize(movie.getPath(), "en");
			
			
			Elements directors = dom.select("div[class='credit_summary_item']");
			movie.setDirectors(directors.first().getElementsByAttribute("href").html());
			
			Elements el = dom.select("table[class='cast_list']");
			for (Element e: el.select("tr")) {
				HashMap<String,String> hash = new HashMap<String,String>();
//				hash.put(e.select("td:nth-child(2)").text(), e.getElementsByAttributeValue("class", "character").text());
				movie.getCast().put(e.select("td:nth-child(2)").text(), e.getElementsByAttributeValue("class", "character").text());
//				movie.setCast(hash);
			}
			
			
			
			movieDetails.add(movie);
			
		}
		
		
		return movieDetails;
	

	}

}
