package com.agrimasthana.fuzz.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.agrimasthana.fuzz.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by agrimasthana on 2/16/16.
 */
public class AllContentAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String> values;

    public AllContentAdapter(Context context,ArrayList<String> values){
        super(context, R.layout.row,values);
        this.context=context;
        this.values=values;
    }

    static class ViewHolder{
        public ImageView image;
        public TextView text;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView= convertView;
        ViewHolder viewHolder;
        if(rowView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.row,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.image=(ImageView) rowView.findViewById(R.id.img_all);
            viewHolder.text=(TextView) rowView.findViewById(R.id.txt_all);
            rowView.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder) rowView.getTag();
        }
        if(values.get(position).matches("(?:([^:/?#]+):)?(?://([^/?#]*))?([^?#]*\\.(?:jpg|gif|png|jpeg))(?:\\?([^#]*))?(?:#(.*))?"))
        {
            String imgurl=values.get(position);
            Picasso.with(context).load(imgurl)
                    .error(android.R.drawable.stat_notify_error)
                    .resize(200,2t00).into(viewHolder.image);
        }
        else {
            viewHolder.text.setText(values.get(position));
        }
            return rowView;
    }
}
