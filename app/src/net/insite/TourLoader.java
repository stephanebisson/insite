package net.insite;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

public class TourLoader extends AsyncTaskLoader<List<Tour>> {

	public TourLoader(Context context) {
		super(context);
		this.forceLoad();
	}

	@Override
	public List<Tour> loadInBackground() {
		Log.i("TourLoader","loadInBackground");
		List<Tour> tours = new ArrayList<Tour>();
		tours.add(new Tour("Visite du Louvres"));
		tours.add(new Tour("La tour Effel"));
		tours.add(new Tour("Les Jardins de Brel"));
		return tours;
	}
	
}