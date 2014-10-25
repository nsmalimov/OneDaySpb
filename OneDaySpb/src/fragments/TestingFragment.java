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
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import constant.Constants;

import com.example.onedayspb.R;

@SuppressLint("ValidFragment")
public class TestingFragment extends Fragment implements OnClickListener {

	private RelativeLayout testingBackgroundLayout;
	
	private Button goodButton;
	private Button maybeButton;
	private Button badButton;
	private TextView answerText;

	private int answerNumber = 1;
	private int numberOfQuestions = Constants.answerArray.size();
	
	private StringBuilder resultTest_sb;
	
	public TestingFragment(StringBuilder resultTest_sb){
		this.resultTest_sb = resultTest_sb;
	}
	 

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view =  inflater.inflate(R.layout.testing_layout, null);

		testingBackgroundLayout = (RelativeLayout) view.findViewById(R.id.testBackgroundLayout);
		answerText = (TextView) view.findViewById(R.id.answerTextView);
		goodButton = (Button) view.findViewById(R.id.buttonGood);
		maybeButton = (Button) view.findViewById(R.id.buttonMaybe);
		badButton = (Button) view.findViewById(R.id.buttonBad);
		
		goodButton.setOnClickListener(this);
		maybeButton.setOnClickListener(this);
		badButton.setOnClickListener(this);
		
		
		setBackground(1);
		answerText.setText(Constants.answerArray.get(0));
		setFont();
		return view;
	}

	@Override
	public void onClick(View v) {
		answerNumber++;
		setBackground(answerNumber);
		switch(v.getId()){
		case R.id.buttonGood:
			resultTest_sb.append("0;");
			break;
		case R.id.buttonMaybe:
			resultTest_sb.append("1;");
			break;
		case R.id.buttonBad:
			resultTest_sb.append("2;");
			break;
		}
		

		if(answerNumber >= numberOfQuestions+1){
			//			send to server
			switchFragment();
		}else{
			answerText.setText(Constants.answerArray.get(answerNumber-1));
		}
	}
	
	private void setBackground(int answerNumber){
		switch(answerNumber){
		case 1:
			testingBackgroundLayout.setBackgroundResource(R.drawable.theatre2);
			break;
		case 2:
			testingBackgroundLayout.setBackgroundResource(R.drawable.museum);
			break;
		case 3:
			testingBackgroundLayout.setBackgroundResource(R.drawable.zoo);
			break;
		case 4:
			testingBackgroundLayout.setBackgroundResource(R.drawable.park);
			break;
		case 5:
			testingBackgroundLayout.setBackgroundResource(R.drawable.cinema);
			break;
		case 6:
			testingBackgroundLayout.setBackgroundResource(R.drawable.gallery);
			break;
		}
	}
	
	private void setFont(){
		Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/CaviarDreams.ttf");
		answerText.setTypeface(tf);
		goodButton.setTypeface(tf);
		maybeButton.setTypeface(tf);
		badButton.setTypeface(tf);
	}
	
	private void switchFragment(){
		StartActivity ma = (StartActivity) getActivity();
		ma.switchFragment(Constants.testingFragment);
	}

}

