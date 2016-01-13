package com.example.pauliner.myzoo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Stéphane.e on 13/01/2016.
 */
public class AnimalAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Animal> animals;

    public AnimalAdapter(Context context, List<Animal> animals) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.animals = animals;
    }

    @Override
    public int getCount() {
        return animals.size();
    }

    @Override
    public Object getItem(int position) {
        return animals.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View rowView, ViewGroup parent) {
        final ViewHolder vh;
        if (rowView == null) {
            rowView = mInflater.inflate(R.layout.animal_cellule, null);

            vh = new ViewHolder();

            vh.tv_animal = (TextView) rowView.findViewById(R.id.tv_animal);

            rowView.setTag(vh);
        }
        else {
            vh = (ViewHolder) rowView.getTag();
        }

        vh.animal = (Animal) getItem(position);
        vh.tv_animal.setText(vh.animal.getName());

        return rowView;
    }

    public static class ViewHolder {
        public TextView tv_animal;
        public Animal animal;
    }
}
