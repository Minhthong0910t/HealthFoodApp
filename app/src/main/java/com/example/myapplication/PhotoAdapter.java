package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {
    private final List<Photo> mlistPhoto;

    public PhotoAdapter(List<Photo> mlistPhoto) {
        this.mlistPhoto = mlistPhoto;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider,parent,false);

        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        Photo photo=mlistPhoto.get(position);
        if(photo==null){
            return;
        }
        holder.imgPhoto.setImageResource(photo.getResourceID());
    }

    @Override
    public int getItemCount() {
        if(mlistPhoto!=null){
            return mlistPhoto.size();
        }
        return 0;
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder{
        private final ImageView imgPhoto;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto=itemView.findViewById(R.id.imgPhoto);

        }
    }
}
