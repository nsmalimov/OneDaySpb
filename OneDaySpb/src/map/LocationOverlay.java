package map;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.IOverlayMenuProvider;
import org.osmdroid.views.overlay.SafeDrawOverlay;
import org.osmdroid.views.safecanvas.ISafeCanvas;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

public class LocationOverlay extends SafeDrawOverlay
{
	
	private GPSTracker gps;
	
	private MapView mapView;
	private GeoPoint meMarkerGpt;
	
	private int myLatitude;
	private int myLongitude;

	public LocationOverlay(Context context,  MapView mapView, GeoPoint meMarkerGpt)
	{
		super(context);
		
		this.mapView = mapView;
		this.meMarkerGpt = meMarkerGpt;
		gps = GPSTracker.getInstance(context);
		gps.getLocation();
		
		myLatitude = (int)(gps.getLatitude()*1e6);
		myLongitude = (int)(gps.getLongitude()*1e6);
		
	}

	@Override
	protected void drawSafe(ISafeCanvas canvas, MapView mapView, boolean shadow)
	{
		// No drawing necessary
	}

	@Override
	public boolean onTouchEvent(MotionEvent event, MapView mapView)
	{
		if (this.isEnabled()) {
			gps.getLocation();
			
			myLatitude = (int)(gps.getLatitude()*1e6);
			myLongitude = (int)(gps.getLongitude()*1e6);
			meMarkerGpt.setLatitudeE6(myLatitude);
			meMarkerGpt.setLongitudeE6(myLongitude);
			
			mapView.invalidate();
		}
		return super.onTouchEvent(event, mapView);
	}
}

