package net.insite;

import net.insite.activities.ChapterActivity;
import net.insite.domain.Chapter;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class ChapterPlayer extends Service implements OnCompletionListener {
	private static final int CHAPTER_PLAYING_NOTIFICATION = 2112;
	public static final String REFRESH_PLAY_BUTTON = "REFRESH_PLAY_BUTTON";
	private final IBinder mBinder = new LocalBinder();
	private MediaPlayer mMediaPlayer;
	private Chapter currentChapter;
	private Notification notification;
	private PendingIntent pendingIntent;

	public ChapterPlayer() {
		Log.i("ChapterPlayer", "ctor");
	}

	@Override
	public IBinder onBind(Intent arg0) {
		Log.i("ChapterPlayer", "onBind");
		return mBinder;
	}

	public boolean isPlaying(Chapter chapter) {
		return isPlaying() && myChapter(chapter);
	}

	public void play(Chapter chapter) {
		currentChapter = chapter;
		eliminate();
		init();
		setupNotification();
		startForeground(CHAPTER_PLAYING_NOTIFICATION, notification);
		mMediaPlayer.start();
	}

	private void init() {
		mMediaPlayer = MediaPlayer.create(getApplicationContext(), currentChapter.getAudio());
		mMediaPlayer.setOnCompletionListener(this);
	}

	private void eliminate() {
		if (mMediaPlayer != null) {
			if (mMediaPlayer.isPlaying()) {
				mMediaPlayer.stop();
			}
			mMediaPlayer.release();
			mMediaPlayer = null;
		}
	}

	public void pause() {
		if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
			mMediaPlayer.pause();
			stopForeground(true);
		}
	}

	public void resume() {
		if (mMediaPlayer != null) {
			setupNotification();
			startForeground(CHAPTER_PLAYING_NOTIFICATION, notification);
			mMediaPlayer.start();
		}
	}

	public boolean isPlaying() {
		return mMediaPlayer != null && mMediaPlayer.isPlaying();
	}

	public boolean myChapter(Chapter chapter) {
		return currentChapter != null && chapter != null
				&& chapter.equals(currentChapter);
	}

	private void setupNotification() {
		Intent intent = new Intent(getApplicationContext(),
				ChapterActivity.class);
		intent.putExtra("chapter", currentChapter);
		pendingIntent = PendingIntent.getActivity(getApplicationContext(),
				0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		notification = new Notification();
		notification.tickerText = currentChapter.getName();
		notification.icon = R.drawable.ic_play;
		notification.flags |= Notification.FLAG_FOREGROUND_SERVICE;
		notification.setLatestEventInfo(getApplicationContext(), "Insite",
				currentChapter.getName(), pendingIntent);
	}

	public class LocalBinder extends Binder {
		public ChapterPlayer getService() {
			return ChapterPlayer.this;
		}
	}

	public void onCompletion(MediaPlayer arg0) {
		
		stopForeground(false);
		Intent i = new Intent();
		i.setAction(REFRESH_PLAY_BUTTON);
		Log.i("ChapterPlayer", "broadcasting " + i.getAction());
		sendBroadcast(i);
		
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		eliminate();
		NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		nm.cancel(CHAPTER_PLAYING_NOTIFICATION);
	}
}