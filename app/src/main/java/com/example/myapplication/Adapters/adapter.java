package com.example.myapplication.Adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.myapplication.models.Contacto;
import com.example.myapplication.R;
import java.util.List;


public class adapter extends ArrayAdapter<Contacto> {
    Context context;
    ImageLoader queue;
    private class ViewHolder {
        TextView FirstName;
        TextView LastName;
        TextView Phone;
        NetworkImageView image;
        private ViewHolder() {
        }
    }
    public adapter(Context context, List<Contacto> items, ImageLoader _queue) {
        super(context, 0, items);
        this.context = context;
        this.queue=_queue;
    }
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        final Contacto rowItem = (Contacto) getItem(position);
        LayoutInflater mInflater = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_contacto, null);
            holder = new ViewHolder();
            holder.FirstName = (TextView) convertView.findViewById(R.id.FirstName);
            holder.LastName = (TextView) convertView.findViewById(R.id.LastName);
            holder.Phone=(TextView) convertView.findViewById(R.id.Phone);
            holder.image=(NetworkImageView) convertView.findViewById(R.id.image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.FirstName.setText(rowItem.FirstName);
        holder.LastName.setText(rowItem.LastName);
        holder.Phone.setText(rowItem.Phone);
        holder.image.setImageUrl(rowItem.urlImage, this.queue);
        return convertView;
    }

    private class FirstName {
    }
}