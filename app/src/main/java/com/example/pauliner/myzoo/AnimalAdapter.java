package com.example.pauliner.myzoo;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AnimalAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<Animal> animals;

    public AnimalAdapter(Context context, List<Animal> animals) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.animals = animals;
        this.mContext = context;
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

            vh.iv = (ImageView) rowView.findViewById(R.id.iv);

            rowView.setTag(vh);
        }
        else {
            vh = (ViewHolder) rowView.getTag();
        }

        vh.animal = (Animal) getItem(position);

        vh.tv_animal.setText(vh.animal.getName());

        Resources res = mContext.getResources();
        int resourceId = res.getIdentifier(vh.animal.getImg(), "drawable", mContext.getPackageName());
        vh.iv.setImageDrawable(res.getDrawable(resourceId, mContext.getTheme()));

        vh.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AnimalActivity.class);
                intent.putExtra("id", vh.animal.getId());
                mContext.startActivity(intent);
            }
        });

        return rowView;
    }

    public static class ViewHolder {
        public TextView tv_animal;
        public ImageView iv;
        public Animal animal;
    }
}
