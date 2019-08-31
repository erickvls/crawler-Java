package ai.overmind.model.dto;

import java.util.HashMap;

public class Cast {
	private HashMap<String,String> name;
	
	public Cast() {}
	
	public Cast(HashMap<String, String> name) {
		super();
		this.name = name;
	}

	public HashMap<String, String> getName() {
		return name;
	}

	public void setName(HashMap<String, String> name) {
		this.name = name;
	}

	
}
