package net.insite;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoURI;

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
//		tours.add(addChapters(new Tour("t1", "Visite du Louvres")));
//		tours.add(addChapters(new Tour("t2", "La tour Effel")));
//		tours.add(addChapters(new Tour("t3", "Les Jardins de Brel")));
		
		try {
			Mongo m = new Mongo("localhost", 27017);
			DB db = m.getDB("insite");
			DBCollection col = db.getCollection("tour");
			DBCursor cur = col.find();
			for (DBObject o : cur) {
				Tour t = new Tour(o.get("_id").toString(), o.get("title").toString());
				tours.add(t);
			}
		} catch (Exception e) {
			Log.e("TourLoader", e.toString());
		}
		
		return tours;
	}
	
	private Tour addChapters(Tour t) {
		List<Chapter> chapters = new ArrayList<Chapter>();
		chapters.add(new Chapter(newId(t, 1), "Intro", LOREM_IPSUM, R.raw.first));
		chapters.add(new Chapter(newId(t, 2), "second part", LOREM_IPSUM, R.raw.second));
		chapters.add(new Chapter(newId(t, 3), "the big thing", LOREM_IPSUM, R.raw.third));
		t.setChapters(chapters);
		return t;
	}
	
	private String newId(Tour tour, int i) {
		return tour.getId() + "c" + i;
	}
	
}