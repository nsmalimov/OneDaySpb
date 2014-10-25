package logic;

import constant.Constants;

public class ExhibitionEvent extends Event {
	public ExhibitionEvent(String name, String image_url,
			String description, String address,
			String date, int eventType){
		super(name, image_url, description,
				address, date);
		this.picture = Constants.exhibitionPicture;
	}

}
