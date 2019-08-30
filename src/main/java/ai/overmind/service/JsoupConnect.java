package ai.overmind.service;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class JsoupConnect {

	public Document initialize(String path, String language) {
		Document doc=null;
		try {
			doc = Jsoup.connect("https://www.imdb.com/"+path).header("Accept-Language", language).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}
}
