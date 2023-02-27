package com.example.tiktoklike;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private Window window;
private ViewPager2 viewPager2;
private VideoAdapter videoAdapter;
private  DatabaseReference dataVideo ;
private List<Video> listVideo;
private boolean flag = true;
private int index ;
private UserAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(Build.VERSION.SDK_INT>=21){
            window = MainActivity.this.getWindow();
            window.setStatusBarColor(MainActivity.this.getResources().getColor(R.color.black));
            window.setNavigationBarColor(MainActivity.this.getResources().getColor(R.color.black));
        }

        viewPager2 = findViewById(R.id.viewpager2);


        listVideo = new ArrayList<>();
        dataVideo = FirebaseDatabase.getInstance().getReference("VideoShort");

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                dataVideo.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        listVideo.clear();
//                        for(DataSnapshot ds : snapshot.getChildren()){
//                            Video video = ds.getValue(Video.class);
//                            listVideo.add(video);
//                        }
//                        videoAdapter.notifyDataSetChanged();
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//            }
//        }).start();
        listVideo.add(new Video(1,"https://www.tiktok.com/@nuglog/video/7165156672971754778?is_from_webapp=1&sender_device=pc&web_id=7130644955869529602",1));

        videoAdapter = new VideoAdapter(MainActivity.this, listVideo, new VideoAdapter.ClickLikeVideo() {
            @Override
            public void Click(Video video, ImageView img_like) {

            }

            @Override
            public void ClickVideoView(Video video) {

            }
        });
        viewPager2.setAdapter(videoAdapter);
    }




}