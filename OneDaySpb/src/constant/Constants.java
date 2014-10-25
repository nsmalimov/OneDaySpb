package constant;

import java.util.ArrayList;
import java.util.Arrays;

import com.example.onedayspb.R;

public class Constants {
	public final static int startFragment = 0;
	public final static int testingFragment = 1;
	public final static int daychooserFragment = 2;
	public final static int resultFragment = 3;
	private final static String[] answerStringArray = {"Ќравитс€ ли вам театр?",
														"Ќравитс€ ли вам ходить в музеи?",
														"Ќравитс€ ли вам посещать зоопарки?",
														"Ќравитс€ ли вам гул€ть по паркам?",
														"Ќравитс€ ли вам смотреть кино?",
														"Ќравитс€ ли вам посещать галереи?"
														};
	public final static ArrayList<String> answerArray = new ArrayList<String>(Arrays.asList(answerStringArray));
	
	public final static int museumType = 0;
	public final static int exhibitionType = 1;
	public final static int theaterType = 2;
	public final static int movieType = 3;
	public final static int parkType = 4;
	
	public final static int museumPicture = R.drawable.museum48x48;
	public final static int exhibitionPicture = R.drawable.exhibition48x48;
	public final static int theaterPicture = R.drawable.theater48x48;
	public final static int moviePicture = R.drawable.movie48x48;
}
