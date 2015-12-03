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
						flipper.showNext ();
					} else if (x1 > x2) {
						flipper.showPrevious ();
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
