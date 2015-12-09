package com.mystatscloud.onpoint;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ViewFlipper;

public class AboutActivity extends ActionBarActivity {

	float x1, x2;

	static float  MINIMUM_X = 150;

	ViewFlipper flipper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);

		LayoutInflater inflater = LayoutInflater.from(this);

		flipper = (ViewFlipper) findViewById(R.id.viewFlipper);

		flipper.addView(inflater.inflate(R.layout.fragment_about_ncrc, null));
		flipper.addView(inflater.inflate(R.layout.fragment_about_workkeys, null));
		flipper.addView(inflater.inflate(R.layout.fragment_about_cert_level, null));

		flipper.setInAnimation(this, android.R.anim.fade_in);
		flipper.setOutAnimation(this, android.R.anim.fade_out);
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {

		switch (e.getAction()) {
			case MotionEvent.ACTION_UP:
				x2 = e.getX();
				if (Math.abs(x1 - x2) > MINIMUM_X) {
					if (x1 < x2) {
						flipper.showPrevious ();
					} else if (x1 > x2) {
						flipper.showNext ();
					}
				}
				break;
			case MotionEvent.ACTION_DOWN:
				x1 = e.getX();
				break;
			default:
				break;
		}

		return true;
	}
}
