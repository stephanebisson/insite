package net.insite;

import java.util.List;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import android.widget.ProgressBar;

public class ViewTourActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<List<Tour>> {

	SimpleCursorAdapter mAdapter;
	ToursAdapter toursAdapter;
	
	// These are the Contacts rows that we will retrieve
    static final String[] PROJECTION = new String[] {ContactsContract.Data._ID,
            ContactsContract.Data.DISPLAY_NAME};

    // This is the select criteria
    static final String SELECTION = "((" + 
            ContactsContract.Data.DISPLAY_NAME + " NOTNULL) AND (" +
            ContactsContract.Data.DISPLAY_NAME + " != '' ))";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tour);
        
        setProgressBar();

        // For the cursor adapter, specify which columns go into which views
        String[] fromColumns = {"text"};
        int[] toViews = {R.id.textView1}; // The TextView in simple_list_item_1

        // Create an empty adapter we will use to display the loaded data.
        // We pass null for the cursor, then update it in onLoadFinished()
        mAdapter = new SimpleCursorAdapter(this, 
                R.layout.list_item_1, null,
                fromColumns, toViews, 0);
        
        toursAdapter = new ToursAdapter(this, this.getListView(), R.layout.list_item_1);
        
        getListView().setAdapter(toursAdapter);

        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
        getSupportLoaderManager().initLoader(0, null, this);
    }

	private void setProgressBar() {
		ProgressBar progressBar = new ProgressBar(this);
        progressBar.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
        progressBar.setIndeterminate(true);
        getListView().setEmptyView(progressBar);
        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        root.addView(progressBar);
	}

    private ListView getListView() {
		return (ListView)findViewById(R.id.chaptersList);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_view_tour, menu);
        return true;
    }

	public Loader<List<Tour>> onCreateLoader(int arg0, Bundle arg1) {
		return new TourLoader(this);
	}

	public void onLoaderReset(Loader<List<Tour>> arg0) {
		mAdapter.swapCursor(null);
	}

	public void onLoadFinished(Loader<List<Tour>> arg0, List<Tour> data) {
		// TODO Auto-generated method stub
		toursAdapter.receiveData(data);
	}
}

