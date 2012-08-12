package net.insite;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

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
}
