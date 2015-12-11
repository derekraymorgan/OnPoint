package com.mystatscloud.onpoint;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ViewFlipper;

public class AboutActivity extends ActionBarActivity {
	float x1, x2; // x1: The point of first contact during touch event, x2: point of release
	static float  MINIMUM_X = 150; // the minimum amount of distance needed to travel to register a touch event

	ViewFlipper flipper; // Android view used for displaying multiple 'pages' on the same activity.

	/**
	 * Called on creation of the activity
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about); // Sets the layout file for this activity

		LayoutInflater inflater = LayoutInflater.from(this);
		flipper = (ViewFlipper) findViewById(R.id.viewFlipper); // Initialize flipper

		/* Add the required layout files to the flipper */
		flipper.addView(inflater.inflate(R.layout.fragment_about_ncrc, null));
		flipper.addView(inflater.inflate(R.layout.fragment_about_workkeys, null));
		flipper.addView(inflater.inflate(R.layout.fragment_about_cert_level, null));

		/* Add the required animation files to the flipper */
		flipper.setInAnimation(this, android.R.anim.fade_in); // animation for the layout entering the activity
		flipper.setOutAnimation(this, android.R.anim.fade_out); // animation for the layout leaving the activity
	}

	/**
	 * Handles touch events, such as swiping left/right
	 * @return Returns true if motion event occurs.
	 */
	@Override
	public boolean onTouchEvent(MotionEvent e) {

		/* calculates the direction of the finger swipe and flips to the next or previous layout accordingly */
		switch (e.getAction()) {
			case MotionEvent.ACTION_UP: // When releasing finger from screen
				x2 = e.getX();
				if (Math.abs(x1 - x2) > MINIMUM_X) {
					if (x1 < x2) {
						flipper.showPrevious ();
					} else if (x1 > x2) {
						flipper.showNext ();
					}
				}
				break;
			case MotionEvent.ACTION_DOWN: // When pressing finger on screen
				x1 = e.getX();
				break;
			default:
				break;
		}

		return true;
	}
}
