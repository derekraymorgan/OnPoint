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
        String tempDescription = "\"We no longer have to deal with stacks of applications. Now we have a much\n" +
                "better idea of what we’re getting, and our existing employees know that the\n" +
                "new team member will be equipped to learn quickly and contribute right away.\n" +
                "With this program, we’re able to place the right person in the right job.\"";
        String tempAuthor = "- Leon Osborne, chief executive officer, Osborne Wood Products";
        String tempDate = "";
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
