package com.example.nasa;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView image = findViewById(R.id.detailView);
        TextView title = findViewById(R.id.detailTitle);

        Bundle bundel = getIntent().getExtras();

        String mTitle = bundel.getString("title");
        String Url = bundel.getString("image");

        Glide.with(this).load(Url).into(image);
        title.setText(mTitle);




    }
}
