package logic;

import constant.Constants;

public class TheaterEvent extends Event {
	public TheaterEvent(String name, String image_url,
			String description, String address,
			String date){
		super(name, image_url, description,
				address, date);
		this.picture = Constants.theaterPicture;
	}

}
