package com.example.nasa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder> {


    // Variables
    Context context;
    List<Image> imageList;

    // Constructor
    public ImageAdapter(Context context, List<Image> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(context).inflate(R.layout.singleimageview, parent, false);
        return new ImageHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {
        Image image = imageList.get(position);
        holder.setImageView(image.getImageUrl());
        holder.setTitle(image.getTitle());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);

                Bundle bundel = new Bundle();
                bundel.putString("title", image.getTitle());
                bundel.putString("image", image.getImageUrl());

                intent.putExtras(bundel);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class ImageHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView imgTitle;
        View view;
        ConstraintLayout constraintLayout;

        public ImageHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            constraintLayout = itemView.findViewById(R.id.main_layout);
        }

        // Set Image
        public void setImageView(String url) {
            imageView = view.findViewById(R.id.imageview);
            Glide.with(context).load(url).into(imageView);
        }

        // Set Title to the Image
        public void setTitle(String title) {
            imgTitle = view.findViewById(R.id.title);
            imgTitle.setText(title);
        }
    }
}
