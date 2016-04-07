package com.mystatscloud.onpoint;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

// This class holds all the information and logic for the Testimonial activity
public class TestimonialsActivity extends ActionBarActivity {

	ListView testimonialView;  // the main view for the activity

    // Override the onCreate interface method
	@Override
	protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState); // make sure onCreate is called for parent class

        // temporary place holder testimonial
        String tempDescription = "\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do " +
                "eiusmod tempor incididunt ut labore et dolore magna aliqua.\"";
        String tempAuthor = "- Bob Dylan";
        String tempDate = "4/7/16";
        int tempImgResource = R.drawable.face;

        setContentView(R.layout.activity_testimonials);  // set the xml file being used to draw the activity

		testimonialView = (ListView) findViewById(R.id.testimonial_layout);  // fetch the list view in the xml file to operate on

        // create and adapter to move data to the list view
        TestimonialsAdapter adapter = new TestimonialsAdapter(getApplicationContext(),R.layout.fragment_testimonials_row);

        // populate the data adapter with the testimonials
        int i;
        int testimonialCount = 1;
        for( i = 0; i < testimonialCount; i++){

            TestimonialClass obj = new TestimonialClass(tempDescription,tempAuthor,tempDate,tempImgResource);
            adapter.add(obj);

        }

        // link the data adapter to the list view for rendering
        testimonialView.setAdapter(adapter);

    }
}
