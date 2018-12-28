package com.example.assignment3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    Context mCtx;
    List<Video> videoList;

    public VideoAdapter(Context mCtx, List<Video> videoList) {
        this.mCtx = mCtx;
        this.videoList = videoList;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       LayoutInflater inflater = LayoutInflater.from(mCtx);
       View view = inflater.inflate(R.layout.item_list,null );
       VideoViewHolder holder = new VideoViewHolder(view);
       return holder;
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, final int position) {
        final Video video = videoList.get(position);
        holder.textName.setText(video.getTextName());
        holder.textShortDesc.setText(video.getTextShortDesc());
        holder.textViewRating.setText(String.valueOf(video.getRating()));
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(video.getImage()));

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vIntent = new Intent(mCtx,PlayVideo.class);
                vIntent.putExtra("url",video.getLink());
                mCtx.startActivity(vIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    class VideoViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textName, textShortDesc, textViewRating;
        RelativeLayout relativeLayout;
        public VideoViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageVideo);
            textName = itemView.findViewById(R.id.textName);
            textShortDesc = itemView.findViewById(R.id.textShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
        }
    }
}
