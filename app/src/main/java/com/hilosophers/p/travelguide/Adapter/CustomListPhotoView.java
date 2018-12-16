package com.hilosophers.p.travelguide.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hilosophers.p.travelguide.Activity.DisplayPhoto;
import com.hilosophers.p.travelguide.Activity.SightsPhotosActivity;
import com.hilosophers.p.travelguide.Model.Sight;
import com.hilosophers.p.travelguide.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomListPhotoView extends ArrayAdapter<String>{


    private List<String> img;
    private Activity context;

    public CustomListPhotoView(Activity context,  List<String> img) {

        super(context, R.layout.photo_listview,img);
        this.context = context;
        this.img = img;
    }


    @NonNull
    @Override
    public View getView(final int position, @NonNull View convertView, @NonNull ViewGroup parent)
    {
        View r = convertView;
        ViewHolder viewHolderPhoto = null;
        if(r == null) {

            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.photo_listview, null, true);
            viewHolderPhoto = new ViewHolder(r);
            r.setTag(viewHolderPhoto);
        }
        else {

            viewHolderPhoto = (ViewHolder) r.getTag();
        }


        Picasso.with(context).load(img.get(position))
                .resize(150,200)
                .centerCrop()
                .into(viewHolderPhoto.imageView);

       viewHolderPhoto.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DisplayPhoto.class);
                intent.putExtra("image_url", img.get(position));
                context.startActivity(intent);

            }
        });
        return r;
    }
    class ViewHolder
    {

        ImageView imageView;


        ViewHolder(View v)
        {

            imageView =  v.findViewById(R.id.imageView);

        }
    }
}
