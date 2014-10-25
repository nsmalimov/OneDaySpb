package logic;

import constant.Constants;

public class MuseumEvent extends Event {
	public MuseumEvent(String name, String image_url,
			String description, String address,
			String date){
		super(name, image_url, description,
				address, date);
		this.picture = Constants.museumPicture;
	}
}
