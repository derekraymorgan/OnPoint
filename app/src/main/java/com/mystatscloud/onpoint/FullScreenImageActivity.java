package com.mystatscloud.onpoint;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Activity that implements the actions needed to take the practice test
 */
public class FullScreenImageActivity extends ActionBarActivity
{

	private ZoomInZoomOut zoomer;
	/**
	 * Connect the activity to the correct view and methods
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);

		// set the view for the exam questions
		setContentView(R.layout.full_screen_image);

		ImageView questionImageView = (ImageView) findViewById(R.id.mainImage);

		// grab some saved data from the last activity
		Bundle savedValues = getIntent().getExtras();

		// get the translate the image file path to the internal resource identifier for this image
		int imageResource = getResources().getIdentifier(savedValues.getString("currentQuestionImageID"), null, getPackageName());

		// get the image using the internal resource identifier
		Drawable questionResource = getResources().getDrawable(imageResource);

		// put the image inside of the image view on the screen
		questionImageView.setImageDrawable(questionResource);

		zoomer = new ZoomInZoomOut();

		questionImageView.setOnTouchListener(new View.OnTouchListener() {


			@Override
			public boolean onTouch(View v, MotionEvent event)
			{

				zoomer.onTouch(v, event);

				return true;
			}

		});

		// grab some saved data from the last activity
		//Bundle selectedValues = getIntent().getExtras();

	}

	public void backButton(View view)
	{
		finish();
	}

}
