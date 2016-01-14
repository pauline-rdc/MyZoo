package com.example.pauliner.myzoo;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AnimalActivity extends AppCompatActivity {

    private Button bt_activity;
    private ImageView iv_detail;
    private TextView theme;
    private TextView animal_name;
    private TextView description_animal;
    private Button animal_sound;
    private Button bt_animal_map;

    public AnimalActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);

        bt_activity = (Button) findViewById(R.id.bt_activity);
        iv_detail = (ImageView) findViewById(R.id.iv_detail);
        theme = (TextView) findViewById(R.id.theme);
        animal_name = (TextView) findViewById(R.id.animal_name);
        description_animal = (TextView) findViewById(R.id.description_animal);
        animal_sound = (Button) findViewById(R.id.animal_sound);
        bt_animal_map = (Button) findViewById(R.id.bt_animal_map);

        Animal animal = ZooBDD.getAnimalById(getIntent().getIntExtra("id", 0));

        Resources res = this.getResources();
        int resourceId = res.getIdentifier(animal.getImg(), "drawable", this.getPackageName());
        iv_detail.setImageDrawable(res.getDrawable(resourceId, this.getTheme()));

        theme.setText(animal.getThm().getName());

        animal_name.setText(animal.getName());

        description_animal.setText(animal.getDesc());

        bt_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
