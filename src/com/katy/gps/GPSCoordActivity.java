package com.katy.gps;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class GPSCoordActivity extends Activity {

	private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 10; // in Meters
	private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000000; // in Milliseconds

	protected LocationManager locationManager;

	protected Button retrieveLocationButton;

	protected String[] allBuilding = {"Alderman Library", "Alderman Library", "Alderman Library", "Alderman Library", "Clemons Library", "Clemons Library", "Clemons Library", "Clemons Library", "Newcomb", "Newcomb", "Newcomb", "Newcomb", "Olsson", "Olsson", "Olsson", "Olsson", "Physics", "Physics", "Physics", "Physics", "Mechanical Engineering", "Mechanical Engineering", "Mechanical Engineering", "Mechanical Engineering", "Wilsdorf", "Wilsdorf", "Wilsdorf", "Wilsdorf", "Chemistry", "Chemistry", "Chemistry", "Chemistry", "Chemistry", "Chemistry", "Chemistry", "Chemistry", "Aquatic Fitness Center", "Aquatic Fitness Center", "Aquatic Fitness Center", "Aquatic Fitness Center", "Aquatic Fitness Center", "Aquatic Fitness Center", "Aquatic Fitness Center", "Aquatic Fitness Center", "Aquatic Fitness Center", "Bookstore", "Bookstore", "Bookstore", "Bookstore", "Cabell", "Cabell", "Cabell", "Cabell", "The Castle", "The Castle", "The Castle", "Halsey Hall", "Halsey Hall", "Halsey Hall", "Halsey Hall", "Bryant Hall", "Bryant Hall", "Bryant Hall", "Bryant Hall", "Cocke Hall", "Cocke Hall", "Cocke Hall", "Cocke Hall", "Chapel", "Chapel", "Chapel", "Chapel", "Emmet/Ivy Garage", "Emmet/Ivy Garage", "Emmet/Ivy Garage", "Emmet/Ivy Garage", "Kerchof", "Kerchof", "Kerchof", "Kerchof", "Kerchof", "Kerchof", "Observatory Hill Dining Hall", "Observatory Hill Dining Hall", "Observatory Hill Dining Hall", "Observatory Hill Dining Hall", "The Lawn", "The Lawn", "The Lawn", "The Lawn", "Monroe Hall", "Monroe Hall", "Monroe Hall", "Monroe Hall", "Small Hall", "Small Hall", "Small Hall", "Small Hall", "Maury", "Maury", "Maury", "Maury", "Amphitheater", "Amphitheater", "Amphitheater", "Amphitheater", "Rouss/Robertson", "Rouss/Robertson", "Rouss/Robertson", "Rouss/Robertson", "University Hall", "University Hall", "Observatory Hill Dining Hall", "Observatory Hill Dining Hall", "Observatory Hill Dining Hall", "Observatory Hill Dining Hall", "Vaughn Hall", "Vaughn Hall", "Clark Hall", "Clark Hall", "Clark Hall", "Clark Hall", "Clark Hall", "Clark Hall", "Clark Hall", "Clark Hall", "Clark Hall", "Clark Hall", "Clark Hall", "Clark Hall", "Clark Hall", "Clark Hall", "Clark Hall", "Clark Hall", "Clark Hall", "Student Health", "Student Health", "Student Health", "Student Health", "Student Activities Building", "Student Activities Building", "Student Activities Building", "Student Activities Building", "Scott Stadium", "Scott Stadium", "Scott Stadium", "Scott Stadium", "Brown Residential College", "Brown Residential College", "Brown Residential College", "Brown Residential College", "Brown Residential College", "Peabody", "Peabody", "Peabody", "Peabody", "Office of African American Affairs", "Office of African American Affairs", "Office of African American Affairs", "Office of African American Affairs", "John Paul Johns Arena", "John Paul Johns Arena", "John Paul Johns Arena", "John Paul Johns Arena", "John Paul Johns Arena", "John Paul Johns Arena", "John Paul Johns Arena", "Chemical Engineering Building", "Chemical Engineering Building", "Chemical Engineering Building", "Chemical Engineering Building", "Brooks Hall", "Brooks Hall", "Brooks Hall", "Brooks Hall", "Brooks Hall", "Minor", "Minor", "Minor", "Minor", "Wilson", "Wilson", "Wilson", "Wilson", "Mechanical Engineering", "Mechanical Engineering", "Mechanical Engineering", "Mechanical Engineering", "Mechanical Engineering", "Mechanical Engineering", "Mechanical Engineering", "Mechanical Engineering", "Memorial Gymnasium", "Memorial Gymnasium", "Memorial Gymnasium", "Memorial Gymnasium", "Cobb Hall", "Cobb Hall", "Booker House", "Booker House", "Runk", "Runk", "Runk", "Runk", "Runk", "Wilson Hall", "Wilson Hall", "Wilson Hall", "Wilson Hall", "Thornton Hall", "Thornton Hall", "Thornton Hall", "Thornton Hall", "The Corner"};
	protected ArrayList <String> buildingsVisited = new ArrayList<String>();
	protected Double[] latitudes = {38.036339, 38.036382, 38.036639, 38.037022, 38.036009, 38.036289, 38.036833, 38.036256, 38.03627, 38.035538, 38.035388, 38.035892, 38.032068, 38.03229, 38.032038, 38.031755, 38.034915, 38.03448, 38.03394, 38.03419, 38.0329, 38.0324, 38.0324, 38.033, 38.033, 38.0329, 38.034, 38.0338, 38.033, 38.0329, 38.034, 38.0338, 38.0341, 38.034, 38.0326, 38.0326, 38.03346265, 38.03313965, 38.03284547, 38.03259308, 38.03248887, 38.03255431, 38.03255296, 38.03255183, 38.03254786, 38.03677, 38.03657, 38.03641, 38.03588, 38.03313, 38.0328, 38.03228, 38.03226, 38.03546, 38.0355, 38.03557, 38.0329, 38.0329, 38.03313, 38.03315, 38.03032, 38.03075, 38.03075, 38.03032, 38.033177, 38.033712, 38.033794, 38.033256, 38.036268002, 38.036242651, 38.036457076, 38.036540522, 38.0412936191, 38.0416020316, 38.0424300916, 38.0420878843, 38.03253, 38.03253, 38.03253, 38.03227, 38.03217, 38.03241, 38.0348178, 38.0349233, 38.0347229, 38.034644, 38.03548, 38.03533213038894, 38.033128, 38.0328, 38.034548, 38.034956, 38.035001, 38.034612, 38.03217, 38.03222, 38.03214, 38.03203, 38.0336009, 38.03349463, 38.03320091, 38.03332544, 38.0335431, 38.03386309, 38.0337681, 38.0331005, 38.03288033, 38.03344961, 38.03312544, 38.0325787, 38.04518, 38.04482, 38.035111, 38.034523, 38.034822, 38.035194, 38.03058, 38.030452, 38.03282, 38.03302, 38.03322, 38.03329, 38.03335, 38.03347, 38.03344, 38.03341, 38.0333, 38.03329, 38.03318, 38.03308, 38.03309, 38.03305, 38.03302, 38.03277, 38.03258, 38.031976, 38.03128, 38.03125, 38.03194, 38.02947, 38.02932, 38.02975, 38.02981, 38.03089, 38.02984763926595, 38.03079, 38.03241, 38.03395, 38.03524, 38.03542, 38.03199, 38.0339, 38.0354417, 38.03569765, 38.03574195, 38.0354637, 38.03294055, 38.0329076, 38.03287915, 38.032824, 38.04575, 38.04651, 38.04612, 38.04532, 38.04532, 38.04592, 38.04594, 38.03338, 38.03328, 38.0332953, 38.03334, 38.0357972, 38.0357972, 38.03567875, 38.038065, 38.03595507, 38.03374, 38.03354, 38.03406, 38.03413, 38.03276, 38.03239, 38.0323, 38.03251, 38.03237, 38.03234, 38.03239, 38.0324, 38.0328, 38.03284, 38.03286, 38.0328, 38.03782042, 38.0377854, 38.03704077, 38.03691249, 38.032615, 38.03241, 38.036489, 38.036715, 38.02876655, 38.02876655, 38.02899391, 38.0285802, 38.028685, 38.0325, 38.0323, 38.0325, 38.0326, 38.03362, 38.03374, 38.03217, 38.03239, 38.0345};
	protected Double[] longitudes = {-78.505897, -78.505797, -78.505166, -78.505179, -78.506022, -78.5059, -78.505823, -78.506644, -78.506753, -78.507003, -78.506705, -78.506161, -78.510969, -78.510833, -78.51007809, -78.510192, -78.510349, -78.5098, -78.50976, -78.51077, -78.5108, -78.511, -78.5112, -78.5113, -78.5113, -78.5116, -78.5109, -78.511, -78.5113, -78.5116, -78.5109, -78.511, -78.5117, -78.5114, -78.5118, -78.5125, -78.51382412, -78.51403882, -78.51414408, -78.51420447, -78.51383867, -78.51378923, -78.51374668, -78.51373371, -78.51364031, -78.50757, -78.50677, -78.5075, -78.50731, -78.50531, -78.5045, -78.50459, -78.50575, -78.510467, -78.51046, -78.51047, -78.50685, -78.50686, -78.50716, -78.50719, -78.51331, -78.51331, -78.51293, -78.51293, -78.505278, -78.504934, -78.5052148, -78.505476, -78.50448668, -78.504360616, -78.50415811, -78.50441292, -78.5069972277, -78.5067719221, -78.5086172819, -78.5087943078, -78.5086, -78.5086, -78.5086, -78.50786, -78.50791, -78.50864, -78.5146177, -78.5147533, -78.514793, -78.51489, -78.50372, -78.50325018167496, -78.505305, -78.5045, -78.506016, -78.505761, -78.506358, -78.503966, -78.51097, -78.51106, -78.51104, -78.51111, -78.5069422, -78.50647994, -78.5065994, -78.50707883, -78.5062061, -78.5059487, -78.50531472, -78.50551167, -78.50438646, -78.5042555, -78.50325118, -78.5035988, -78.50843, -78.50916, -78.515516, -78.515659, -78.514534, -78.514616, -78.518922, -78.518622, -78.50836, -78.50829, -78.50807, -78.50843, -78.5084, -78.508, -78.50799, -78.50795, -78.50752, -78.50751, -78.50726, -78.50742, -78.50744, -78.50746, -78.50748, -78.50763, -78.50791, -78.502936, -78.50322, -78.50333, -78.503636, -78.51514, -78.51535, -78.51576, -78.51575, -78.51204, -78.51350426673889, -78.5152, -78.51458, -78.5084, -78.50768, -78.50716, -78.50682, -78.50784, -78.5063986, -78.5061318, -78.5060427, -78.5061193, -78.5062437, -78.5062193, -78.50620715, -78.5061551, -78.50767, -78.50697, -78.50744, -78.50621, -78.50626, -78.50571, -78.50574, -78.51062425, -78.510645, -78.5106795, -78.5105723, -78.50224155, -78.50224155, -78.50196048, -78.50190143, -78.50226994, -78.50684, -78.5064, -78.50627, -78.50659, -78.50416, -78.50441, -78.50381, -78.50368, -78.51102, -78.5111, -78.51113, -78.51115, -78.51111, -78.51113, -78.51098, -78.51075, -78.5067605, -78.50683251, -78.50774043, -78.50729544, -78.502743, -78.50342, -78.502174, -78.502105, -78.518471, -78.518471, -78.519255, -78.5193065, -78.518374, -78.5038, -78.5038, -78.5044, -78.5043, -78.5092, -78.50976, -78.50982, -78.51073, -78.5001};


	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		retrieveLocationButton = (Button) findViewById(R.id.button1);

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		locationManager.requestLocationUpdates(
				LocationManager.GPS_PROVIDER, 
				MINIMUM_TIME_BETWEEN_UPDATES, 
				MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
				new MyLocationListener()
				);

		retrieveLocationButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//showCurrentLocation();
			}
		});   
		
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);

//	    MapView mapView = (MapView) findViewById(R.id.mapview);
//	    mapView.setBuiltInZoomControls(true);
	}

	protected void showCurrentLocation() {

		Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

		if (location != null) {
			String message = String.format(
					"Current Location \n Longitude: %1$s \n Latitude: %2$s",
					location.getLongitude(), location.getLatitude()
					);
			Toast.makeText(GPSCoordActivity.this, message,
					Toast.LENGTH_LONG).show();
		}

	}   

	private class MyLocationListener implements LocationListener {

		public void onLocationChanged(Location location) {
			String message = String.format(
					"New Location \n Longitude: %1$s \n Latitude: %2$s",
					location.getLongitude(), location.getLatitude()
					);
			Toast.makeText(GPSCoordActivity.this, message, Toast.LENGTH_LONG).show();

			for(int i=0; i<latitudes.length; i++){
				if(location.getLatitude() == latitudes[i] && location.getLongitude() == longitudes[1]){
					buildingsVisited.add(allBuilding[i]);
					Toast.makeText(GPSCoordActivity.this, "You're at" +allBuilding[i], Toast.LENGTH_LONG).show();
				}
				else{
					Toast.makeText(GPSCoordActivity.this, "You're not near grounds, please go back to grounds", Toast.LENGTH_LONG).show();
				}
			}
		}

		public void onStatusChanged(String s, int i, Bundle b) {
			Toast.makeText(GPSCoordActivity.this, "Provider status changed",
					Toast.LENGTH_LONG).show();
		}

		public void onProviderDisabled(String s) {
			Toast.makeText(GPSCoordActivity.this,
					"Provider disabled by the user. GPS turned off",
					Toast.LENGTH_LONG).show();
		}

		public void onProviderEnabled(String s) {
			Toast.makeText(GPSCoordActivity.this,
					"Provider enabled by the user. GPS turned on",
					Toast.LENGTH_LONG).show();
		}

	}

//	@Override
//	protected boolean isRouteDisplayed() {
//		// TODO Auto-generated method stub
//		return false;
//	}

}