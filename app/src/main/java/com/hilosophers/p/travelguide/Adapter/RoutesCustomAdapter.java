package com.hilosophers.p.travelguide.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hilosophers.p.travelguide.R;

import java.util.List;

public class RoutesCustomAdapter extends ArrayAdapter<String> {

    private List<String> names;
    private Activity context;

    public RoutesCustomAdapter(Activity context, List<String> names) {
        super(context, R.layout.routes_listview,names);

        this.context = context;
        this.names = names;
    }


    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent)
    {
        View r = convertView;
        RoutesCustomAdapter.ViewHolder viewHolder = null;
        if(r == null) {

            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.routes_listview, null, true);
            viewHolder = new RoutesCustomAdapter.ViewHolder(r);
            r.setTag(viewHolder);
        }
        else {

            viewHolder = (RoutesCustomAdapter.ViewHolder) r.getTag();
        }

        viewHolder.textView.setText(names.get(position));
        return r;
    }


    class ViewHolder
    {
        TextView textView;

        ViewHolder(View v)
        {
            textView =  v.findViewById(R.id.textView);

        }
    }
}
