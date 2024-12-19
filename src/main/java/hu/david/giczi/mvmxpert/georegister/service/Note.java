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
		String[] contentParts = content.split("<br>");
		StringBuilder sb = new StringBuilder();
		String contentSytle = "style=color:";
		
		for (String contentPart : contentParts) {	
		
		if( contentPart.contains(contentSytle) ) {
			sb.append(contentPart)
			.append("<br>");
		}
		else {	
			
				if( contentPart.contains("<") && contentPart.contains(">") ) {
					sb.append(contentPart.substring(0, contentPart.indexOf('>')))
					.append(" ")
					.append(contentSytle)
					.append(color)
					.append(contentPart.substring(contentPart.indexOf('>')) )
					.append("<br>");
				}
				else if( !contentPart.contains("<") && !contentPart.contains(">") ) {
					sb.append("<b")
					.append(" ")
					.append(contentSytle)
					.append(color)
					.append(">")
					.append(contentPart)
					.append("<b>")
					.append("<br>");
				}
			}
			this.content = sb.toString();
		}
	}

	@Override
	public String toString() {
		return "Note [id=" + id + ", color=" + color + ", content=" + content + "]";
	}
	
}
