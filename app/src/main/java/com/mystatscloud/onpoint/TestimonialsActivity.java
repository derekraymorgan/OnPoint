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
        String tempDescription = "\"We no longer have to deal with stacks of applications. Now we have a much " +
                "better idea of what we’re getting, and our existing employees know that the " +
                "new team member will be equipped to learn quickly and contribute right away. " +
                "With this program, we’re able to place the right person in the right job.\"";
        String tempAuthor = "- Leon Osborne, Chief Executive Officer, Osborne Wood Products";
        String tempDate = "";

        String tempDescription2 = "\"Taking the assessment validated my skills and showed me areas where " +
                "I could improve or even add additional skills. The certificate showed " +
                "potential employers that I would be a valuable part of their workforce " +
                "with the skills needed to do a good job.\"";
        String tempAuthor2 = "- ACT NCRC recipient, Georgia";

        String tempDescription3 = "\"Employers work to make things predictable, uniform, and certain. " +
                "Predictability,uniformity, and certainty are universal goals of executives. The National " +
                "Career Readiness Certificate does this for the hiring process.\"";
        String tempAuthor3 = "- Mac Macilroy, Past President, Michigan Manufacturers Association";

        int tempImgResource = R.drawable.face;
        int tempImgResource2 = R.drawable.face2;
        int tempImgResource3 = R.drawable.face3;

        setContentView(R.layout.activity_testimonials);  // set the xml file being used to draw the activity

		testimonialView = (ListView) findViewById(R.id.testimonial_layout);  // fetch the list view in the xml file to operate on

        // create and adapter to move data to the list view
        TestimonialsAdapter adapter = new TestimonialsAdapter(getApplicationContext(),R.layout.fragment_testimonials_row);

        // populate the data adapter with the testimonials
        int i;
        int testimonialCount = 1;
        for( i = 0; i < testimonialCount; i++){

            TestimonialClass obj = new TestimonialClass(tempDescription,tempAuthor,tempDate,tempImgResource);
            TestimonialClass obj2 = new TestimonialClass(tempDescription2, tempAuthor2, tempDate, tempImgResource2);
            TestimonialClass obj3 = new TestimonialClass(tempDescription3, tempAuthor3, tempDate, tempImgResource3);
            adapter.add(obj);
            adapter.add(obj2);
            adapter.add(obj3);

        }

        // link the data adapter to the list view for rendering
        testimonialView.setAdapter(adapter);

    }
}
