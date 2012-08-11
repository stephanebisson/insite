package net.insite;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

public class TourLoader extends AsyncTaskLoader<Cursor> {

	public TourLoader(Context context) {
		super(context);
		this.forceLoad();
	}

	@Override
	public Cursor loadInBackground() {
		Log.i("TourLoader","loadInBackground");
		MatrixCursor data = new MatrixCursor(new String[]{"_id", "text"});
		data.addRow(new Object[]{1, "text_1"});
		data.addRow(new Object[]{2, "text_2"});
		data.addRow(new Object[]{3, "text_3"});
		return data;
	}
	
}