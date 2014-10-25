package fragments;

import com.example.onedayspb.R;

import constant.Constants;
import activity.StartActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


@SuppressLint("ValidFragment")
public class StartFragment extends Fragment {

	private TextView logoText;
	private TextView logoDescription;
	private Button startTestButton;

	private Context context;

	
	public StartFragment(Context context){
		this.context = context;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		
		View view =  inflater.inflate(R.layout.startup_layout, null);
		logoText = (TextView) view.findViewById(R.id.logoText);
		logoDescription = (TextView) view.findViewById(R.id.logoDescript);
		startTestButton = (Button) view.findViewById(R.id.startTestButton);
		setFonts();
		

		startTestButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				switchFragment();
			}});

		return view;
	}
	

	private void setFonts(){
		// Loading Font Face
		Typeface logoTf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/jandles.ttf");
		// Applying font
		logoText.setTypeface(logoTf);

		Typeface textTf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/CaviarDreams.ttf");
		logoDescription.setTypeface(textTf);
		startTestButton.setTypeface(textTf);

	}


	private void switchFragment(){
		StartActivity ma = (StartActivity) getActivity();
		ma.switchFragment(Constants.startFragment);
	}
}