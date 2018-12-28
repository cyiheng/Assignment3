package com.example.assignment3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    VideoAdapter adapter;

    List<Video> videoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoList = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        videoList.add(
                new Video(
                        1,
                        "Bunny from the demo",
                        "You should watch it",
                        4.3,
                        R.drawable.playbutton,
                        "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"));

        videoList.add(
                new Video(
                        1,
                        "Unavailable",
                        "error",
                        0,
                        R.drawable.playbutton,
                        "https://sample-videos.com/video123/mp4/720/big_buck_bunny_720p_2mb.mp4"));


        adapter = new VideoAdapter(this, videoList);
        recyclerView.setAdapter(adapter);

    }
}
