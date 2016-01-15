package com.example.pauliner.myzoo;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AnimalActivity extends AppCompatActivity {

    private Button bt_activity;
    private ImageView iv_detail;
    private TextView theme;
    private TextView animal_name;
    private TextView description_animal;
    private Button animal_sound;
    private Button bt_animal_map;
    private ImageView animal_favoris;
    private Context mContext;
    private Animal animal;

    public AnimalActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);

        mContext = this;

        //Todo:ecup variables
        bt_activity = (Button) findViewById(R.id.bt_activity);
        iv_detail = (ImageView) findViewById(R.id.iv_detail);
        theme = (TextView) findViewById(R.id.theme);
        animal_name = (TextView) findViewById(R.id.animal_name);
        description_animal = (TextView) findViewById(R.id.description_animal);
        animal_sound = (Button) findViewById(R.id.animal_sound);
        bt_animal_map = (Button) findViewById(R.id.bt_animal_map);
        animal_favoris = (ImageView) findViewById(R.id.animal_favoris);

        animal = ZooBDD.getAnimalById(getIntent().getIntExtra("id", 0));

        // Todo:if the animal is in my favories
        if (ZooBDD.isFavoris(getIntent().getIntExtra("id", 0))) {
            Resources res = this.getResources();
            int resourceId = res.getIdentifier("ic_star_full", "drawable", this.getPackageName());
            animal_favoris.setImageDrawable(res.getDrawable(resourceId, this.getTheme()));
        }
        else {
            Resources res = this.getResources();
            int resourceId = res.getIdentifier("ic_star", "drawable", this.getPackageName());
            animal_favoris.setImageDrawable(res.getDrawable(resourceId, this.getTheme()));
        }

        Resources res = this.getResources();
        int resourceId = res.getIdentifier(animal.getImg(), "drawable", this.getPackageName());
        iv_detail.setImageDrawable(res.getDrawable(resourceId, this.getTheme()));

        theme.setText(animal.getThm().getName());
        animal_name.setText(animal.getName());
        description_animal.setText(animal.getDesc());

        // Todo:click return
        bt_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(intent);
            }
        });

        // Todo:click animal's map
        bt_animal_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                intent.putExtra("theme", animal.getThm().getName());
                intent.putExtra("id", animal.getId());
                finish();
                startActivity(intent);
            }
        });

        // Todo:update favoris
        animal_favoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ZooBDD.trtFavoris(getIntent().getIntExtra("id", 0)) == "add") {
                    Resources res = mContext.getResources();
                    int resourceId = res.getIdentifier("ic_star_full", "drawable", mContext.getPackageName());
                    animal_favoris.setImageDrawable(res.getDrawable(resourceId, mContext.getTheme()));
                    Toast.makeText(getApplicationContext(), "Ajouté aux favoris", Toast.LENGTH_SHORT).show();
                }
                else {
                    Resources res = mContext.getResources();
                    int resourceId = res.getIdentifier("ic_star", "drawable", mContext.getPackageName());
                    animal_favoris.setImageDrawable(res.getDrawable(resourceId, mContext.getTheme()));
                    Toast.makeText(getApplicationContext(), "Supprimé des favoris", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
