package net.insite.activities;

import net.insite.ChapterPlayer;
import net.insite.R;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Intent intent = new Intent(this, ChapterPlayer.class);
		boolean result = bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
		Log.i("ChapterActivity", "bindService " + result);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void viewTour1(View view) {
		Intent intent = new Intent(this, ToursActivity.class);
		startActivity(intent);
	}
	
	private ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName className, IBinder service) {
            Log.i("MainActivity", "bound");
        }

        public void onServiceDisconnected(ComponentName arg0) {
            Log.i("MainActivity", "unbound");
        }
    };
}
