package net.insite;

import java.util.ArrayList;
import java.util.List;

import net.insite.domain.Chapter;
import net.insite.domain.Tour;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

public class TourLoader extends AsyncTaskLoader<List<Tour>> {

	private static final String LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

	public TourLoader(Context context) {
		super(context);
		this.forceLoad();
	}

	@Override
	public List<Tour> loadInBackground() {
		Log.i("TourLoader","loadInBackground");
		List<Tour> tours = new ArrayList<Tour>();
		tours.add(addChapters(new Tour("Visite du Louvres")));
		tours.add(addChapters(new Tour("La tour Effel")));
		tours.add(addChapters(new Tour("Les Jardins de Brel")));
		return tours;
	}
	
	private Tour addChapters(Tour t) {
		List<Chapter> chapters = new ArrayList<Chapter>();
		chapters.add(new Chapter("Intro", LOREM_IPSUM));
		chapters.add(new Chapter("second part", LOREM_IPSUM));
		chapters.add(new Chapter("the big thing", LOREM_IPSUM));
		t.setChapters(chapters);
		return t;
	}
	
}