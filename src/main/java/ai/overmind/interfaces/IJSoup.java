package ai.overmind.interfaces;

import org.jsoup.nodes.Document;

public interface IJSoup<T> {
	public Document initialize(String path, String language);
}
