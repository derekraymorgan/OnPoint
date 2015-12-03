package com.mystatscloud.onpoint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

public class MainMenuActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
	}

	/** Called when the user clicks the about menu item button */
	public void showAboutView(View view)
	{
		Intent myIntent = new Intent(MainMenuActivity.this, AboutActivity.class);

		MainMenuActivity.this.startActivity(myIntent);
	}

	/** Called when the user clicks the faq menu item button */
	public void showFaqView(View view)
	{
		Intent myIntent = new Intent(MainMenuActivity.this, FaqActivity.class);

		MainMenuActivity.this.startActivity(myIntent);
	}

	/** Called when the user clicks the testimonials menu item button */
	public void showTestimonialsView(View view)
	{
		Intent myIntent = new Intent(MainMenuActivity.this, TestimonialsActivity.class);

		MainMenuActivity.this.startActivity(myIntent);
	}

	/** Called when the user clicks the test facility locator menu item button */
	public void showTestFacilityLocatorView(View view)
	{
		Intent myIntent = new Intent(MainMenuActivity.this, TestFacilityLocatorActivity.class);

		MainMenuActivity.this.startActivity(myIntent);
	}

	/** Called when the user clicks the practice exam button */
	public void showPracticeExamView(View view)
	{
		Intent myIntent = new Intent(MainMenuActivity.this, PracticeExamMenuActivity.class);

		MainMenuActivity.this.startActivity(myIntent);
	}

	/** Called when the user clicks the resources menu item button */
	public void showScheduleTestView(View view)
	{
		Intent myIntent = new Intent(MainMenuActivity.this, ScheduleTestActivity.class);

		MainMenuActivity.this.startActivity(myIntent);
	}


}
