package net.insite.activities;

import net.insite.ChaptersAdapter;
import net.insite.R;
import net.insite.domain.Tour;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TourActivity extends Activity implements AdapterView.OnItemClickListener {

    private Tour tour;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);
        
        tour = (Tour)getIntent().getExtras().get("tour");
        
        TextView textBox = (TextView)findViewById(R.id.tourName);
        textBox.setText(tour.getTitle());
        
        ListView list = (ListView)findViewById(R.id.chaptersList);
        
        list.setOnItemClickListener(this);
        
        ChaptersAdapter chaptersAdapter = new ChaptersAdapter(this, R.layout.chapter_list_item);
		list.setAdapter(chaptersAdapter);
		chaptersAdapter.receiveData(tour.getChapters());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_one_tour, menu);
        return true;
    }
    
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
    	// TODO Auto-generated method stub
//    	return super.onMenuItemSelected(featureId, item);
//    	if (item.getItemId() == R.id.About){
    	Toast.makeText(this, "About the app", Toast.LENGTH_LONG).show();
//    	}
    	return false;
    }

	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Log.i("TourActivity", "clicked on " + tour.getChapters().get(position).getName());
		Intent intent = new Intent(this, ChapterActivity.class);
		intent.putExtra("chapter", tour.getChapters().get(position));
		startActivity(intent);
	}
}
