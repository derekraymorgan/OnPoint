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

    static class TestimonialBuffer{
        ImageView image;
        TextView description;
        TextView author;
        TextView date;
    }

    private List<TestimonialClass> testimonials = new ArrayList<>();

    public TestimonialsAdapter(Context context, int resource) {

        super(context,resource);
    }

    public void add(TestimonialClass object){
        testimonials.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.testimonials.size();
    }

    @Override
    public TestimonialClass getItem(int position) {
        return this.testimonials.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TestimonialBuffer buffer;
        View row = convertView;
        LayoutInflater inflater;

        if( convertView == null ) {
            inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(R.layout.fragment_testimonials_row, parent, false);
            buffer = new TestimonialBuffer();
            buffer.image = (ImageView) row.findViewById(R.id.testimonial_image);
            buffer.description = (TextView) row.findViewById(R.id.testimonial_description);
            buffer.author = (TextView) row.findViewById(R.id.testimonial_author);
            buffer.date = (TextView) row.findViewById(R.id.testimonial_date);
            row.setTag(buffer);
        }
        else {
            buffer = (TestimonialBuffer) row.getTag();
        }

        TestimonialClass obj = getItem(position);
        buffer.image.setImageResource(obj.getImgResource());
        buffer.description.setText(obj.getDescription());
        buffer.description.setTextColor(Color.DKGRAY);
        buffer.author.setText(obj.getAuthor());
        buffer.author.setTextColor(Color.BLACK);
        buffer.date.setText(obj.getDate());
        buffer.date.setTextColor(Color.BLACK);

        return row;
    }
}
