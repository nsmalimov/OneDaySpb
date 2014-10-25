package logic;

import constant.Constants;

public class MovieEvent extends Event {
	public MovieEvent(String name, String image_url,
			String description, String address,
			String date, int eventType){
		super(name, image_url, description,
				address, date);
		this.picture = Constants.moviePicture;
	}

}
