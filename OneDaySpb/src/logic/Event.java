package logic;

public abstract class Event {
	public Event(String name, String image_url,
			String description, String address,
			String date){
		this.name = name;
		this.image_url = image_url;
		this.description = description;
		this.address = address;
		this.date = date;
	}
	public String name;
	public String image_url;
	public String description;
	public String address;
	public String date;
	public int picture;
}
