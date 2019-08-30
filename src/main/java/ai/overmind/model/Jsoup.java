package ai.overmind.model;

import org.jsoup.nodes.Document;

public class Jsoup {
	private String url;
	private String language;
	private Document doc;
//	String url = "https://www.imdb.com/";
//	String language = "en";
//	
//	Document doc = Jsoup.connect(url+"chart/bottom").header("Accept-Language", language).get();
	
	public  Jsoup(String url, String language) {
		this.url = url;
		this.language = language;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	
}
