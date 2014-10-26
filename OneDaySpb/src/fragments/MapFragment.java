package fragments;

import java.util.ArrayList;


import map.LocationOverlay;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.onedayspb.R;


public class MapFragment extends Fragment {

	private Context context;
	private TextView nameEventTextView;
	private MapView mapView;
	private MapController mapController;
	
	private GeoPoint meMarkerGpt;
	private GeoPoint eventGpt;

	ArrayList<OverlayItem> overlayItemArray;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		this.context = getActivity();
		View  view =  inflater.inflate(R.layout.map_fragment, null);

		//init text view with description
		nameEventTextView = (TextView) view.findViewById(R.id.map_event_name);
		nameEventTextView.setText("Русский музей");
		
		mapView = (MapView) view.findViewById(R.id.mapview);
		mapView.setTileSource(TileSourceFactory.MAPNIK);
		mapView.setMultiTouchControls(true);
	
		//		mapView.getOverlays().add(new RotationGestureOverlay(getActivity(), mapView));
		mapController = (MapController) mapView.getController();
		mapController.setZoom(15);
		
		//setting markers
		overlayItemArray = new ArrayList<OverlayItem>();
		//set marker for event
		Drawable eventMarker = context.getResources().getDrawable(R.drawable.marker64x64);
		eventGpt = new GeoPoint(51500000, -150000);
		OverlayItem eventItem = new OverlayItem("Event", "Description", eventGpt);
		mapController.setCenter(eventGpt);
		eventItem.setMarker(eventMarker);
		overlayItemArray.add(eventItem);
		
		
		//set marker for me
		Drawable meMarker = context.getResources().getDrawable(R.drawable.me64x64);
		meMarkerGpt = new GeoPoint(0, 0);
		OverlayItem meItem = new OverlayItem("Me", "Description", meMarkerGpt);
		meItem.setMarker(meMarker);
		overlayItemArray.add(meItem);
		
		//setting overlay for location listener
		LocationOverlay locOverlay = new LocationOverlay(context,mapView,meMarkerGpt);
		mapView.getOverlays().add(locOverlay);
		
		ItemizedOverlayWithFocus<OverlayItem> markersOverlay = new ItemizedOverlayWithFocus<OverlayItem>(getActivity(), overlayItemArray, new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>(){

			@Override
			public boolean onItemLongPress(int arg0, OverlayItem arg1) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean onItemSingleTapUp(int arg0, OverlayItem item) {
				if(item.getTitle().equals("Me")){
					//show me text
					nameEventTextView.setText("Я");
				}else{
					//show event text
					nameEventTextView.setText("Русский музей");
				}
//				Toast.makeText(context, arg1.getSnippet(), Toast.LENGTH_SHORT).show();
				return false;
			}});
		mapView.getOverlays().add(markersOverlay);
	
		return view;
	}

}
