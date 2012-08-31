package net.insite.activities;

import net.insite.ChapterPlayer;
import net.insite.ChapterPlayer.LocalBinder;
import net.insite.R;
import net.insite.domain.Chapter;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChapterActivity extends Activity {

	private Chapter chapter;
	protected ChapterPlayer player;
	protected boolean mBound;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chapter);

		chapter = (Chapter) getIntent().getExtras().get("chapter");

		TextView chapterNameTextBox = (TextView) findViewById(R.id.chapter_details_name);
		chapterNameTextBox.setText(chapter.getName());

		TextView chapterTextTextBox = (TextView) findViewById(R.id.chapter_details_text);
		chapterTextTextBox.setText(chapter.getText());
		
		Intent intent = new Intent(this, ChapterPlayer.class);
		boolean result = bindService(intent, mConnection, 0);
		Log.i("ChapterActivity", "bindService " + result);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_chapter, menu);
		return true;
	}

	private void setButtonText(String text) {
		Button btn = (Button) findViewById(R.id.play_chapter_btn);
		btn.setText(text);
		Log.i("ASDF", "set button text to " + text);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unbindService(mConnection);
	}

	public void play(View view) throws Exception {
		Log.i("ChapterActivity", "play " + (mBound ? "bound" : "unbound"));
		if (isPlayingMyChapter()) {
			player.pause();
		}
		else {
			player.play(chapter);
		}
		updateBtnText();
	}
	
	private boolean isPlayingMyChapter() {
		return player.isPlaying(chapter);
	}
	
	private void updateBtnText() {
		if (isPlayingMyChapter()) {
			setButtonText("PAUSE");
		}
		else {
			setButtonText("PLAY");
		}
	}
	
	private ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName className, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            LocalBinder binder = (LocalBinder) service;
            player = binder.getService();
            mBound = true;
            updateBtnText();
            Log.i("ChapterActivity", "bound");
        }

        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
            Log.i("ChapterActivity", "unbound");
        }
    };
}
