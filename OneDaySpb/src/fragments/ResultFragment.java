package fragments;

import adapter.ResultListAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import graphic.expand_graphic.ExpandingListView;

import com.example.onedayspb.R;


public class ResultFragment extends Fragment {
	
	public static StringBuilder stringBuilder = new StringBuilder();
	
	private ExpandingListView lvResultEvents;
	private ResultListAdapter eventsAdapter;

	private static Activity activity;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    	this.activity = getActivity();
        View  view =  inflater.inflate(R.layout.result_fragment, null);
        
        lvResultEvents = (ExpandingListView) view.findViewById(R.id.lvResultList);
        initResultList();
        
        return view;
    }
    
    private void initResultList(){
    	eventsAdapter = new ResultListAdapter(getActivity());
		lvResultEvents.setAdapter(eventsAdapter);
    }
    
    
}
