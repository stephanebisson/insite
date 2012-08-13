package net.insite;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class OneTourActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_tour);
        
        Tour tour = (Tour)getIntent().getExtras().get("tour");
        
        TextView textBox = (TextView)findViewById(R.id.chapterTitle);
        textBox.setText(tour.getTitle());
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
}
