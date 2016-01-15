package com.example.pauliner.myzoo;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MapActivity extends AppCompatActivity {

    private Button bt_retour;
    private String theme_map;
    private ImageView img_map;
    public static String loc;
    public static boolean map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        map = true;

        startService(new Intent(this, BackgroundService.class));
        setContentView(R.layout.activity_map);

        theme_map = getIntent().getStringExtra("theme");
        bt_retour = (Button) findViewById(R.id.return_map);
        img_map = (ImageView) findViewById(R.id.img_map);

        bt_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AnimalActivity.class);
                intent.putExtra("id", getIntent().getIntExtra("id", 0));
                finish();
                startActivity(intent);
            }
        });

        Resources res = this.getResources();

        String drawable_img_map = "";

        if (theme_map.equals("Je marche")) {
            drawable_img_map = "plan_felin";
        }
        else if (theme_map.equals("Je vole")) {
            drawable_img_map = "plan_bird";
        }
        else if (theme_map.equals("Je nage")) {
            drawable_img_map = "plan_aqua";
        }
        else if (theme_map.equals("Je rampe")) {
            drawable_img_map = "plan_reptil";
        }

        int resourceId = res.getIdentifier(drawable_img_map, "drawable", this.getPackageName());
        img_map.setImageDrawable(res.getDrawable(resourceId, this.getTheme()));
    }
}
