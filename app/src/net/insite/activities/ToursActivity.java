package net.insite.activities;

import java.util.List;

import net.insite.R;
import net.insite.TourLoader;
import net.insite.ToursAdapter;
import net.insite.domain.Tour;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

public class ToursActivity extends FragmentActivity 
	implements LoaderManager.LoaderCallbacks<List<Tour>>, 
		AdapterView.OnItemClickListener {

	ToursAdapter toursAdapter;
	
    // This is the select criteria
	private List<Tour> data;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tours);
        
        setProgressBar();

        toursAdapter = new ToursAdapter(this, R.layout.tour_list_item);
        getListView().setAdapter(toursAdapter);

		getListView().setOnItemClickListener(this);
        
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
		return (ListView)findViewById(R.id.toursList);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_view_tour, menu);
        return true;
    }

	public Loader<List<Tour>> onCreateLoader(int arg0, Bundle arg1) {
		return new TourLoader(this);
	}

	public void onLoadFinished(Loader<List<Tour>> arg0, List<Tour> data) {
		this.data = data;
		toursAdapter.receiveData(data);
	}
	
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Log.i("nav", "item clicked " + data.get(position).getTitle());
		Intent intent = new Intent(this, TourActivity.class);
		intent.putExtra("tour", data.get(position));
		startActivity(intent);
   }

	public void onLoaderReset(Loader<List<Tour>> loader) {
		this.toursAdapter.receiveData(null);
	}

}

