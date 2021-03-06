package com.LovellStudio.Flexogram;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.GridLayoutManager;

import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;

import android.widget.ImageView;



import com.bumptech.glide.Glide;



import java.io.File;

import java.util.ArrayList;

public class SelectResultActivity extends AppCompatActivity {

    public static final String EXTRA_IMAGES = "extraImages";
    private ArrayList<String> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        initView();
    }



    public void initView() {
        images = (ArrayList<String>) getIntent().getSerializableExtra(EXTRA_IMAGES);
        ImageView singleImageView = (ImageView) findViewById(R.id.single_image);
        RecyclerView resultRecyclerView = (RecyclerView) findViewById(R.id.result_recycler);
        resultRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        if (images.size() == 1) {
            resultRecyclerView.setVisibility(View.GONE);
            Glide.with(SelectResultActivity.this)
                    .load(new File(images.get(0)))
                    .into(singleImageView);
        } else {
            singleImageView.setVisibility(View.GONE);
            resultRecyclerView.setAdapter(new GridAdapter());
        }
    }

    private class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

        @Override
        public GridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(GridAdapter.ViewHolder holder, int position) {
            Glide.with(SelectResultActivity.this)
                    .load(new File(images.get(position)))
                    .centerCrop()
                    .into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return images.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            public ViewHolder(View itemView) {
                super(itemView);
                imageView = (ImageView) itemView.findViewById(R.id.image);
            }
        }
    }
}