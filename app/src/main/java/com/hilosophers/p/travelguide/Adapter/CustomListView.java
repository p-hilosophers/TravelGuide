package com.hilosophers.p.travelguide.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hilosophers.p.travelguide.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class CustomListView extends ArrayAdapter<String> {

    private List<String> names;
    private List<String> img;
    private Activity context;

    public CustomListView(Activity context,List<String> names,List<String> img) {
        super(context, R.layout.city_listview,names);

        this.context = context;
        this.names = names;
        this.img = img;
    }


    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent)
    {
        View r = convertView;
        ViewHolder viewHolder = null;
        if(r == null) {

            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.city_listview, null, true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else {

            viewHolder = (ViewHolder) r.getTag();
        }


        Picasso.with(context).load(img.get(position))
                .resize(150,200)
                .centerCrop()
                .into(viewHolder.imageView);
        viewHolder.textView.setText(names.get(position));
        return r;
    }


    class ViewHolder
    {
        TextView textView;
       ImageView imageView;

        ViewHolder(View v)
        {
            textView = (TextView) v.findViewById(R.id.textView);
            imageView = (ImageView) v.findViewById(R.id.imageView);
        }
    }
}
