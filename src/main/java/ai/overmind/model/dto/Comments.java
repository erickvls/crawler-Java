package ai.overmind.model.dto;

public class Comments {
	private String title;
	private Double rate;
	private String content;
	private String author;
	
	public Comments() {}
	
	public Comments(String title, Double rate, String content, String author) {
		super();
		this.title = title;
		this.rate = rate;
		this.content = content;
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	
}
