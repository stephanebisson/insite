package net.insite;

import net.insite.activities.ChapterActivity;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class ChapterPlayer extends Service {
	private final IBinder mBinder = new LocalBinder();
	private MediaPlayer mMediaPlayer;

	@Override
	public IBinder onBind(Intent arg0) {
		Log.i("ChapterPlayer", "onBind");
		return mBinder;
	}

	public boolean isPlaying() {
		return mMediaPlayer != null && mMediaPlayer.isPlaying();
	}

	public void play() {
		if (mMediaPlayer == null) {
			mMediaPlayer = MediaPlayer.create(getApplicationContext(),
					R.raw.showmetheplace);

			PendingIntent pi = PendingIntent.getActivity(
					getApplicationContext(), 0, new Intent(
							getApplicationContext(), ChapterActivity.class),
					PendingIntent.FLAG_UPDATE_CURRENT);
			Notification notification = new Notification();
			notification.tickerText = "Show me the place";
			notification.icon = R.drawable.ic_play;
			notification.flags |= Notification.FLAG_ONGOING_EVENT;
			notification.setLatestEventInfo(getApplicationContext(),
					"Insite", "Chapter: " + "the place", pi);
			startForeground(1, notification);

		}
		mMediaPlayer.start();
	}

	public void pause() {
		mMediaPlayer.pause();
	}

	public class LocalBinder extends Binder {
		public ChapterPlayer getService() {
			return ChapterPlayer.this;
		}
	}
}