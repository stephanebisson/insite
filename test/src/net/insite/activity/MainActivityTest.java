package net.insite.activity;

import net.insite.activities.MainActivity;


import android.test.ActivityInstrumentationTestCase2;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private MainActivity activity;

	public MainActivityTest() {
		super("net.insite.activities", MainActivity.class);
	}

	public void setUp() throws Exception {
		super.setUp();
		setActivityInitialTouchMode(false);
		activity = getActivity();
	}

	public void testPreconditions() {
		assertTrue(activity != null);
	}

}
