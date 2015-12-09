package com.mystatscloud.onpoint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Handler;

public class MainActivity extends ActionBarActivity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);

		// load the splash screen
		setContentView(R.layout.splash);

		int secondsDelayed = 4;

		// create a new runnable so that the splash screen is not included in the 'back button'
		new Handler().postDelayed(new Runnable()
		{
			public void run()
			{
				startActivity(new Intent(MainActivity.this, MainMenuActivity.class));
				finish();
			}

		}, secondsDelayed * 1000);

	}
}
