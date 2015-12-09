package com.mystatscloud.onpoint;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class TestimonialsAdapter extends ArrayAdapter<TestimonialClass>{

    // this static class is used to hold the information for the list-item being rendered
    static class TestimonialBuffer{
        ImageView image;
        TextView description;
        TextView author;
        TextView date;
    }

    // this list holds all list-items in the adapter
    private List<TestimonialClass> testimonials = new ArrayList<>();

    // constructor
    public TestimonialsAdapter(Context context, int resource) {

        super(context,resource);
    }

    // add method used to insert list-items into the adapter's internal list
    public void add(TestimonialClass object){
        testimonials.add(object);
        super.add(object);
    }

    // getCount() interface method used to get the number of list-items in the adapter
    @Override
    public int getCount() {
        return this.testimonials.size();
    }

    // getItem() interface method used to get a particular list-item to work with
    @Override
    public TestimonialClass getItem(int position) {
        return this.testimonials.get(position);
    }

    // getView() interface method detailing how each list-item in the adapter is rendered
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TestimonialBuffer buffer;
        View row = convertView;
        LayoutInflater inflater;

        //  when views are created rapidly it is more efficient to convert a view already in memory rather than create new one
        // if the row for the list-item isn't being converted however then a new one needs to be created
        if( convertView == null ) {
            inflater = LayoutInflater.from(getContext()); // get a reference to the inflater being used to render views
            row = inflater.inflate(R.layout.fragment_testimonials_row, parent, false); // inflate a new row from the xml template
            buffer = new TestimonialBuffer(); // create a buffer to hold a collection of views for the row
            buffer.image = (ImageView) row.findViewById(R.id.testimonial_image); // specify an image view to be part of each row
            buffer.description = (TextView) row.findViewById(R.id.testimonial_description); // specify how the text view looks showing the testimonial
            buffer.author = (TextView) row.findViewById(R.id.testimonial_author); // specify how the text view looks showing the author
            buffer.date = (TextView) row.findViewById(R.id.testimonial_date); // specify how the text view looks showing the date
            row.setTag(buffer); // apply the views to the row
        }
        // otherwise use a set of views already in memory to render the new row
        else {
            buffer = (TestimonialBuffer) row.getTag();
        }

        // regardless of a view being recycled or newly created, update the row for the item getView is being called on
        TestimonialClass obj = getItem(position); // get the item being rendered
        buffer.image.setImageResource(obj.getImgResource()); // set the img resource of the view to the img resource of this list-item
        buffer.description.setText(obj.getDescription()); // set the testimonial in the view to the testimonial of this list-item
        buffer.description.setTextColor(Color.DKGRAY); // set the color for this text
        buffer.author.setText(obj.getAuthor()); // set the author text in the view to the author text of this list-item
        buffer.author.setTextColor(Color.BLACK); // set the color for this text
        buffer.date.setText(obj.getDate()); // set the date in the view to the date of this list-item
        buffer.date.setTextColor(Color.BLACK); // set the color for this text

        // return the row to be rendered
        return row;
    }
}
