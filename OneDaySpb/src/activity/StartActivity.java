package activity;

import java.util.List;
import java.util.Random;

import logic.Attraction;
import logic.ServerConnection;
import constant.Constants;

import adapter.ResultAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.onedayspb.R;

import fragments.DaychooserFragment;
import fragments.ResultFragment;
import fragments.StartFragment;
import fragments.TestingFragment;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;


public class StartActivity extends FragmentActivity {

	private Fragment currentFragment;
	private Fragment startFragment;
	private Fragment testingFragment;
	private Fragment resultFragment;
	private Fragment daychooserFragment;

	private Handler handler;


	private StringBuilder resultTest_sb = new StringBuilder();
	private StringBuilder data_sb = new StringBuilder();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		handler = new Handler();
		startFragment = new StartFragment(this);
		testingFragment = new TestingFragment(resultTest_sb);
		daychooserFragment = new DaychooserFragment(data_sb);
		resultFragment = new ResultFragment();

		currentFragment = resultFragment;

		getSupportFragmentManager()
		.beginTransaction()
//		.add(R.id.content_frame, startFragment)
//		.add(R.id.content_frame, testingFragment)
//		.add(R.id.content_frame, daychooserFragment)
		.add(R.id.content_frame, resultFragment)
//		.hide(testingFragment)
//		.hide(daychooserFragment)
//		.hide(resultFragment)
		.commit();
		
	}
	

	public void switchFragment(final int currentPosition){
		handler.post(new Runnable(){

			@Override
			public void run() {	
				Fragment nextFragment = null;
				switch(currentPosition){
				case Constants.startFragment:
					nextFragment = testingFragment;
					break;
				case Constants.testingFragment:
					nextFragment = daychooserFragment;
					break;
				case Constants.daychooserFragment:
					nextFragment = resultFragment;
					break;
				}

				Log.d("error",Integer.toString(currentPosition));

				FragmentManager manager = getSupportFragmentManager();
				FragmentTransaction ft = manager.beginTransaction();
				ft.setCustomAnimations(R.animator.slide_in, R.animator.slide_out);

				nextFragment.getView().bringToFront();
				currentFragment.getView().bringToFront();
				ft.hide(currentFragment);				

				ft.show(nextFragment);
				currentFragment = nextFragment;

				ft.commit();


			}
		});

	}

	public void sendToServer(){
		final FragmentActivity activity = this;
		new AsyncTask<Void, Void, String>() {
			@Override
			protected String doInBackground(Void... params) {
				try {
					String id = generateRandomString();
					String result = "data="+resultTest_sb.toString()+id+";"+data_sb.toString();
					ServerConnection.post(id, result);
					ServerConnection.getJSON(0, id);
					return null;
				} catch (Exception e) {
					Log.e("exep", "exep", e);
					return null;
				}
			}

			@Override
			protected void onPostExecute(String result){
				Log.d("error3","here!");
//				((ResultFragment)resultFragment).parseDataFromServer();
				switchFragment(Constants.daychooserFragment);
			}

			private String generateRandomString(){
				Random generator = new Random();
				int i = generator.nextInt(900) + 100;
				return Integer.toString(i);
			}

		}.execute();
	}
}
