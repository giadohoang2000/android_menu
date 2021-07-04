package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.menu.MainActivity;
import com.example.menu.R;
import com.example.model.Model;

import java.util.List;

public class Adapter extends BaseAdapter {
    private MainActivity context;
    private int layout;
    private List<Model> modelList;

    public Adapter(MainActivity context, int layout, List<Model> modelList) {
        this.context = context;
        this.layout = layout;
        this.modelList = modelList;
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.nameProduct = convertView.findViewById(R.id.nameProduct);
            holder.priceProduct = convertView.findViewById(R.id.priceProduct);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }


        final Model m = modelList.get(position);
        holder.nameProduct.setText(m.getName());
        holder.priceProduct.setText(m.getPrice());
        return convertView;
    }

    private static class ViewHolder{
        TextView nameProduct, priceProduct;

    }
}
