package adapter;

import graphic.expand_graphic.ExpandableListItem;
import graphic.expand_graphic.ExpandingLayout;
import graphic.expand_graphic.ViewHolder;

import java.util.ArrayList;
import java.util.HashSet;

import logic.Event;
import logic.MuseumEvent;
import logic.TheaterEvent;

import com.example.onedayspb.R;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;



public class ResultListAdapter extends BaseAdapter {
	private Context context;

	private final static int mCollapsedHeight = 80;
	private static ArrayList<Event> events;

	static{	
		events = new ArrayList<Event>();
		MuseumEvent museum1 = new MuseumEvent("Русский музей","feferece","Самый лучший музей",
											  "Невский проспект","10:00-12:00");
		MuseumEvent museum2 = new MuseumEvent("Русский музей","feferece","Самый лучший музей",
				  "Невский проспект","10:00-12:00");
		TheaterEvent museum3 = new TheaterEvent("Русский музей","feferece","Самый лучший музей",
				  "Невский проспект","10:00-12:00");
		MuseumEvent museum4 = new MuseumEvent("Русский музей","feferece","Самый лучший музей",
				  "Невский проспект","10:00-12:00");
		MuseumEvent museum5 = new MuseumEvent("Русский музей","feferece","Самый лучший музей",
				  "Невский проспект","10:00-12:00");
		MuseumEvent museum6 = new MuseumEvent("Русский музей","feferece","Самый лучший музей",
				  "Невский проспект","10:00-12:00");
		events.add(museum1);
		events.add(museum2);
		events.add(museum3);
		events.add(museum4);
		events.add(museum5);
		events.add(museum6);
	}

	public ResultListAdapter(Context context){
		super();
		this.context = context;
	}

	@Override
	public int getCount() {
		return events.size();
	}

	@Override
	public Object getItem(int position) {
		return events.get(position);
	}

	@Override
	public long getItemId(int position) {
		//Unimplemented, because we aren't using Sqlite.
		return position;
	}

	public void changeData(ArrayList<Event> newDesireList){
		events = newDesireList;
		this.notifyDataSetChanged();
	}

	public ArrayList<Event> getData(){
		return events;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Event event = events.get(position);

		final ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.result_list_row, parent, false);

			holder.timeInfoView = (TextView) convertView.findViewById(R.id.resultlist_ElementTime);
			holder.eventImageView = (ImageView) convertView.findViewById(R.id.resultlist_ElementImage);
			
			holder.viewObject = new ExpandableListItem(mCollapsedHeight);
			holder.expandingView = (ExpandingLayout) convertView.findViewById(R.id.expanding_layout);
			holder.convertViewLayoutParams = new ListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,
					AbsListView.LayoutParams.WRAP_CONTENT);
			holder.fullEventInfoView = (TextView) convertView.findViewById(R.id.resultlist_fullEventInfoView);

			convertView.setTag(holder);
		}
		else holder = (ViewHolder) convertView.getTag();
		
		holder.timeInfoView.setText(event.date);
		holder.fullEventInfoView.setText(event.description);
		holder.eventImageView.setBackgroundResource(event.picture);
		
		convertView.setLayoutParams(holder.convertViewLayoutParams);

		holder.expandingView.setExpandedHeight(holder.viewObject.mExpandedHeight);
		holder.expandingView.setSizeChangedListener(holder.viewObject);
		holder.expandingView.setVisibility(View.GONE);

		return convertView;
	}

}
