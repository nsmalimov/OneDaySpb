package fragments;

import activity.StartActivity;
import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import antistatic.spinnerwheel.AbstractWheel;
import antistatic.spinnerwheel.OnWheelChangedListener;
import antistatic.spinnerwheel.OnWheelScrollListener;
import antistatic.spinnerwheel.adapters.ArrayWheelAdapter;
import antistatic.spinnerwheel.adapters.NumericWheelAdapter;

import com.example.onedayspb.R;

import constant.Constants;

@SuppressLint("ValidFragment")
public class DaychooserFragment extends Fragment implements OnClickListener {


	private int nextPressedCount = 0;

	private Button nextButton;
	private Button resultButton;

	private TextView yourDayText;
	private TextView arriveTimeText;
	private TextView leaveTimeText;

	private ImageView anchorImage;
	private ImageView arriveImage;
	private ImageView leaveImage;
	private ImageView okImage;

	private AbstractWheel dayWheel;
	private AbstractWheel monthWheel;

	private AbstractWheel arriveHourWheel;
	private AbstractWheel arriveMinuteWheel;

	private AbstractWheel leaveHourWheel;
	private AbstractWheel leaveMinuteWheel;

	private String[] months = new String[]{"€нварь","февраль","март","апрель",
			"май","июнь","июль","август",
			"сент€брь","окт€брь","но€брь","декабрь"};

	private StringBuilder data_sb;

	private String arriveMinuteString = null;
	private String leaveMinuteString = null;

	public DaychooserFragment(StringBuilder data_sb){
		this.data_sb = data_sb;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view =  inflater.inflate(R.layout.daychooser_layout, null);
		yourDayText = (TextView) view.findViewById(R.id.yourDayText);
		arriveTimeText = (TextView) view.findViewById(R.id.arriveTimeText);
		leaveTimeText = (TextView) view.findViewById(R.id.leaveTimeText);

		resultButton = (Button) view.findViewById(R.id.resultButton);
		nextButton = (Button) view.findViewById(R.id.nextButton);
		initWheels(view);
		initImages(view);


		Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/CaviarDreams.ttf");
		yourDayText.setTypeface(tf);
		arriveTimeText.setTypeface(tf);
		leaveTimeText.setTypeface(tf);
		resultButton.setTypeface(tf);
		nextButton.setTypeface(tf);

		nextButton.setOnClickListener(this);
		resultButton.setOnClickListener(this);

		return view;
	}

	private void initWheels(View view){
		dayWheel = (AbstractWheel) view.findViewById(R.id.dayWheel);
		dayWheel.setViewAdapter(new NumericWheelAdapter(getActivity(), 1, 30));
		dayWheel.setCurrentItem(1);
		dayWheel.setCyclic(false);

		monthWheel = (AbstractWheel) view.findViewById(R.id.monthWheel);
		ArrayWheelAdapter<String> adapter = new ArrayWheelAdapter<String>(getActivity(), months);
		monthWheel.setViewAdapter(adapter);
		monthWheel.setCurrentItem(1);
		monthWheel.setCyclic(false);

		arriveHourWheel = (AbstractWheel) view.findViewById(R.id.arriveTimeHourWheel);
		arriveHourWheel.setViewAdapter(new NumericWheelAdapter(getActivity(), 0, 23));
		arriveHourWheel.setCurrentItem(1);
		arriveHourWheel.setCyclic(true);

		arriveMinuteWheel = (AbstractWheel) view.findViewById(R.id.arriveTimeMinuteWheel);
		arriveMinuteWheel.setViewAdapter(new NumericWheelAdapter(getActivity(), 0, 59));
		arriveMinuteWheel.setCurrentItem(1);
		arriveMinuteWheel.setCyclic(true);

		leaveHourWheel = (AbstractWheel) view.findViewById(R.id.leaveTimeHourWheel);
		leaveHourWheel.setViewAdapter(new NumericWheelAdapter(getActivity(), 0, 23));
		leaveHourWheel.setCurrentItem(1);
		leaveHourWheel.setCyclic(true);

		leaveMinuteWheel = (AbstractWheel) view.findViewById(R.id.leaveTimeMinuteWheel);
		leaveMinuteWheel.setViewAdapter(new NumericWheelAdapter(getActivity(), 0, 59));
		leaveMinuteWheel.setCurrentItem(1);
		leaveMinuteWheel.setCyclic(true);

	}

	private void initImages(View view){
		anchorImage = (ImageView) view.findViewById(R.id.spb_logo);
		arriveImage = (ImageView) view.findViewById(R.id.clock_logo);
		leaveImage = (ImageView) view.findViewById(R.id.leave_logo);
		okImage = (ImageView) view.findViewById(R.id.ok_logo);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.nextButton:
			//animate widgets
			switch(nextPressedCount){
			case 0:
				//arrive time anim
				data_sb.append(Integer.toString(dayWheel.getCurrentItem()+1)+"."+Integer.toString(monthWheel.getCurrentItem()+1)+".2014;");
				hideWidgets(yourDayText, anchorImage,  dayWheel, monthWheel);
				showWidgets(arriveTimeText, arriveImage, arriveHourWheel, arriveMinuteWheel);
				nextPressedCount++;
				break;
			case 1:
				//leave time anim
				int arriveHour = arriveHourWheel.getCurrentItem()+1;
				int arriveMinute = arriveMinuteWheel.getCurrentItem();
				if(arriveMinute < 10){
					arriveMinuteString = "0"+Integer.toString(arriveMinute);
				} else{
					arriveMinuteString = Integer.toString(arriveMinute);
				}

				data_sb.append(Integer.toString(arriveHourWheel.getCurrentItem())+":"+arriveMinuteString+";");
				hideWidgets(arriveTimeText, arriveImage, arriveHourWheel, arriveMinuteWheel);
				showWidgets(leaveTimeText, leaveImage, leaveHourWheel, leaveMinuteWheel);
				nextPressedCount++;
				break;
			case 2:
				//show ok
				int leaveMinute = leaveMinuteWheel.getCurrentItem();
				if(leaveMinute < 10){
					leaveMinuteString = "0"+Integer.toString(leaveMinute);
				} else{
					leaveMinuteString = Integer.toString(leaveMinute);
				}
				data_sb.append(Integer.toString(leaveHourWheel.getCurrentItem())+":"+leaveMinuteString);
				hideWidgets(leaveTimeText, leaveImage, leaveHourWheel, leaveMinuteWheel);
				showWidgets(okImage,null,null,null);
				nextButton.setVisibility(View.GONE);
				resultButton.setVisibility(View.VISIBLE);

			}
			break;
		case R.id.resultButton:
			sendToServer();
			break;
		}
	}

	private void hideWidgets(View title, View image, View wheel1, View wheel2){
		Animation fadeOut = AnimationUtils.loadAnimation(getActivity(),
				R.animator.panel_out);

		if(title != null){
			title.setAnimation(fadeOut);
			title.setVisibility(View.GONE);
		}

		if(image != null){
			image.setAnimation(fadeOut);
			image.setVisibility(View.GONE);
		}

		if(wheel1 != null){
			wheel1.setAnimation(fadeOut);
			wheel1.setVisibility(View.GONE);
		}

		if(wheel2 != null){
			wheel2.setAnimation(fadeOut);
			wheel2.setVisibility(View.GONE);
		}
	}

	private void showWidgets(View title, View image, View wheel1, View wheel2){
		Animation fadeIn = AnimationUtils.loadAnimation(getActivity(),
				R.animator.panel_in);

		if(title != null){
			title.setAnimation(fadeIn);
			title.setVisibility(View.VISIBLE);
		}

		if(image != null){
			image.setAnimation(fadeIn);
			image.setVisibility(View.VISIBLE);
		}

		if(wheel1 != null){
			wheel1.setAnimation(fadeIn);
			wheel1.setVisibility(View.VISIBLE);
		}

		if(wheel2 != null){
			wheel2.setAnimation(fadeIn);
			wheel2.setVisibility(View.VISIBLE);
		}
	}

	private void switchFragment(){
		StartActivity ma = (StartActivity) getActivity();
		ma.switchFragment(Constants.daychooserFragment);
	}

	private void sendToServer(){
		StartActivity ma = (StartActivity) getActivity();
		ma.sendToServer();
	}

}
