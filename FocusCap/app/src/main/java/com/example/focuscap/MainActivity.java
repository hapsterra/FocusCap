package com.example.focuscap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewPager2 videosViewPager=findViewById(R.id.videosViewPager);

        List<VideoItem> videoItems= new ArrayList<>();

        VideoItem videoItemFocused= new VideoItem();
        videoItemFocused.videoURL = "android.resource://"+getPackageName()+"/"+R.raw.video1;
        videoItemFocused.videoTitle="It's time to stay focused";
        videoItemFocused.videoDescription="Focus on your goals if not you will repent all your life";
        videoItems.add(videoItemFocused);

        VideoItem videoItemAlone= new VideoItem();
        videoItemAlone.videoURL = "android.resource://"+getPackageName()+"/"+R.raw.video2;
        videoItemAlone.videoTitle="I enjoy being alone";
        videoItemAlone.videoDescription="I enjoy being alone so I can focus in myself";
        videoItems.add(videoItemAlone);

        VideoItem videoItemBalance= new VideoItem();
        videoItemBalance.videoURL = "android.resource://"+getPackageName()+"/"+R.raw.video3;
        videoItemBalance.videoTitle="50/50";
        videoItemBalance.videoDescription="Focus on your goals but always with a balance";
        videoItems.add(videoItemBalance);

        VideoItem videoItemPainful= new VideoItem();
        videoItemPainful.videoURL = "android.resource://"+getPackageName()+"/"+R.raw.video4;
        videoItemPainful.videoTitle="Growth is painful";
        videoItemPainful.videoDescription="Growth is painful but nothing is more painful than staying in the same place";
        videoItems.add(videoItemPainful);

        VideoItem videoItemDream= new VideoItem();
        videoItemDream.videoURL = "android.resource://"+getPackageName()+"/"+R.raw.video5;
        videoItemDream.videoTitle="It's not the destination is the journey";
        videoItemDream.videoDescription="Those times when you push yourself to do anything even you don't feel like doing it";
        videoItems.add(videoItemDream);

        videosViewPager.setAdapter(new VideosAdapter(videoItems));
    }


    public void goForum(View view){
        Intent i = new Intent(this,Forum.class);
        startActivity(i);
    }
    public void goContact(View view){
        Intent i = new Intent(this,Contact.class);
        startActivity(i);
    }
    public void goFocused(View view){
        Intent i = new Intent(this,Focused.class);
        startActivity(i);}


    public void logOut(View view){
        auth.signOut();
        if(auth.getCurrentUser()==null){
            Toast.makeText(getApplicationContext(), "Logged out", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,Login.class);
            startActivity(i);
        }

    }
}