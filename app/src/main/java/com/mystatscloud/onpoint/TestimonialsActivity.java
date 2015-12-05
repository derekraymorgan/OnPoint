package com.mystatscloud.onpoint;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

public class TestimonialsActivity extends ActionBarActivity {

	ListView testimonialListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_testimonials);

		testimonialListView = (ListView) findViewById(R.id.testimonial_mainList);
        TestimonialsAdapter adapter = new TestimonialsAdapter(getApplicationContext(),R.layout.fragment_testimonials_row);

        int i;
        int testimonialCount = 10;

        String tempDescription = "This is a temp description that will be loaded dynamically";
        String tempAuthor = "Group 17";
        String tempDate = "12/1/15";
        int tempImgResource = R.drawable.gold;

        for( i = 0; i < testimonialCount; i++){

            TestimonialClass obj = new TestimonialClass(tempDescription,tempAuthor,tempDate,tempImgResource);
            adapter.add(obj);

        }



    }

}
