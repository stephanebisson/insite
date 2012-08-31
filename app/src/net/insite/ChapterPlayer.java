package net.insite;

import net.insite.activities.ChapterActivity;
import net.insite.domain.Chapter;
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
	private Chapter currentChapter;

	public ChapterPlayer() {
		Log.i("ChapterPlayer", "ctor");
	}

	@Override
	public IBinder onBind(Intent arg0) {
		Log.i("ChapterPlayer", "onBind");
		return mBinder;
	}

	public boolean isPlaying(Chapter chapter) {
		if (currentChapter != null && chapter != null) {
			Log.i("ASDF", "player   :" + currentChapter.getId());
			Log.i("ASDF", "activity :" + chapter.getId());
			Log.i("ASDF", "same?    :" + (chapter.getId() == currentChapter.getId()));
		} else {
			Log.i("ASDF", "nulls");
		}
		return isPlaying() && myChapter(chapter);
	}

	public void play(Chapter chapter) {
		if (mMediaPlayer == null) {
			mMediaPlayer = MediaPlayer.create(getApplicationContext(),
					R.raw.showmetheplace);
		}

		if (myChapter(chapter)) {
			if (!isPlaying()) {
				mMediaPlayer.start();
			}
		} else {
			currentChapter = chapter;
			setupNotification(chapter);
			mMediaPlayer.start();
		}
	}

	public void pause() {
		if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
			mMediaPlayer.pause();
		}
	}

	public void resume() {
		if (mMediaPlayer != null) {
			mMediaPlayer.start();
		}
	}

	private boolean isPlaying() {
		return mMediaPlayer != null && mMediaPlayer.isPlaying();
	}

	private boolean myChapter(Chapter chapter) {
		return currentChapter != null && chapter != null
				&& chapter.getId() == currentChapter.getId();
	}

	private void setupNotification(Chapter chapter) {
		Intent intent = new Intent(getApplicationContext(),
				ChapterActivity.class);
		intent.putExtra("chapter", currentChapter);
		PendingIntent pi = PendingIntent.getActivity(getApplicationContext(),
				0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		Notification notification = new Notification();
		notification.tickerText = currentChapter.getName();
		notification.icon = R.drawable.ic_play;
		notification.flags |= Notification.FLAG_ONGOING_EVENT;
		notification.setLatestEventInfo(getApplicationContext(), "Insite",
				currentChapter.getName(), pi);
		startForeground(1, notification);
	}

	public class LocalBinder extends Binder {
		public ChapterPlayer getService() {
			return ChapterPlayer.this;
		}
	}
}