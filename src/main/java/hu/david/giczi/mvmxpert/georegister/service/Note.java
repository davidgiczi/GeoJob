package hu.david.giczi.mvmxpert.georegister.service;

public class Note {

	
	private String id;
	private String color;
	private String content;
	
	
	public String getId() {
		return id;
	}
	
	public String getColor() {
		return color;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setContent(String content) {
		StringBuilder sb = new StringBuilder();
		String contentSytle = "style=color:";
		if( !content.contains("<") && !content.contains(">") ) {
			this.content = content;
			return;
		}
		else if( content.startsWith("<b") && content.contains(contentSytle) ) {
			this.content = content;
			return;
		}
		else if( content.startsWith("<b") && !content.contains(contentSytle) ) {
			String[] parts = content.split("<b>|</b>");
			this.content = "<b " + contentSytle + color + ">" + parts[1] + "</b>";
			return;
		}
		String[] contentParts = content.split("</p>|</h[1-6]>");
		String preTag;
		String postTag;
		
		for (String part : contentParts) {
			
			if( part.startsWith("<br>") ) {
				
				if( !Character.isDigit(part.charAt(6)) && !part.contains(contentSytle) ){
                    preTag = part.substring(4, 6) + " " + contentSytle + color + ">";
                    String[] info = part.split("<p>|<h[1-6]>");
                    postTag =  "</" + part.substring(5, 7);
                    sb.append(preTag + info[1] + postTag);
                }
                else if( !Character.isDigit(part.charAt(6)) && part.contains(contentSytle) ){
                	sb.append(part + "</" + part.charAt(5) + ">");
                }
                else if( Character.isDigit(part.charAt(6)) && part.contains(contentSytle) ){
                	sb.append(part + "</" + part.substring(5,7) + ">");
                }
                else if( Character.isDigit(part.charAt(6)) && !part.contains(contentSytle) ){
                    preTag = part.substring(4, 7) + " " + contentSytle + color + ">";
                    String[] info = part.split("<p>|<h[1-6]>");
                    postTag = "</" + part.substring(5, 7) + ">";
                    sb.append(preTag + info[1] + postTag);
                }
				
			}
			else {
				
				if( Character.isDigit(part.charAt(2)) && part.contains(contentSytle) ){
					sb.append(part + "</" + part.substring(1,3) + ">");
                }
                else if( Character.isDigit(part.charAt(2)) && !part.contains(contentSytle) ){
                    preTag = part.substring(0, 3) + " " + contentSytle + color + ">";
                    String[] info = part.split("<p>|<h[1-6]>");
                    postTag = "</" + part.substring(1, 3) + ">";
                    sb.append(preTag + info[1] + postTag);
                }
                else if( !Character.isDigit(part.charAt(2)) && !part.contains(contentSytle) ){
                    preTag = part.substring(0, 2) + " " + contentSytle + color + ">";
                    String[] info = part.split("<p>|<h[1-6]>");
                    postTag =  "</" + part.substring(1, 3);
                    sb.append(preTag + info[1] + postTag);
                }
                else if( !Character.isDigit(part.charAt(2)) && part.contains(contentSytle) ){
                	sb.append(part + "</" + part.charAt(1) + ">");
                }			
			}
		}
		this.content = sb.toString();
	}

	@Override
	public String toString() {
		return "Note [id=" + id + ", color=" + color + ", content=" + content + "]";
	}
	
}
