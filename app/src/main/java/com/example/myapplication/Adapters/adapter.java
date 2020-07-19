package com.example.myapplication.Adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapplication.models.Contacto;
import com.example.myapplication.R;
import java.util.List;


public class adapter extends ArrayAdapter<Contacto> {
    Context context;
    private class ViewHolder {
        TextView FirstName;
        TextView LastName;
        TextView Phone;
        private ViewHolder() {
        }
    }
    public adapter(Context context, List<Contacto> items) {
        super(context, 0, items);
        this.context = context;
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
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.FirstName.setText(rowItem.FirstName);
        holder.LastName.setText(rowItem.LastName);
        holder.Phone.setText(rowItem.Phone);
        return convertView;
    }

    private class FirstName {
    }
}