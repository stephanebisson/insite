package net.insite;

import net.insite.ChapterPlayer.LocalBinder;
import net.insite.domain.Chapter;
import android.content.Intent;
import android.test.ServiceTestCase;

public class ChapterPlayerTest extends ServiceTestCase<ChapterPlayer> {

	private ChapterPlayer service;

	public ChapterPlayerTest() {
		super(ChapterPlayer.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		LocalBinder binder = (LocalBinder) bindService(new Intent(getContext(), ChapterPlayer.class));
		service = binder.getService();
	}
	
	public void testServiceState() throws Exception {
		// given
		Chapter chapter1 = new Chapter("1", "name1", "text1", 0);
		Chapter chapter2 = new Chapter("2", "name2", "text2", 0);
		
		// when
		service.play(chapter1);
		
		// then
		assertTrue(service.isPlaying(chapter1));
		assertFalse(service.isPlaying(chapter2));
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		//service.pause();
	}

}
