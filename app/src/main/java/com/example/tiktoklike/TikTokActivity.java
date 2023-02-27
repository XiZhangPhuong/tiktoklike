package com.example.tiktoklike;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.PictureInPictureParams;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class TikTokActivity extends AppCompatActivity {
private VideoView videoView_tiktok;
private Window window;
private CircleImageView img_avatar_tiktok,img_music_tiktok;
private TextView txt_music_tiktok;
private String url = "";
private ImageView imageView_like;
private boolean flag = true;
private ProgressBar progressBar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tik_tok);
        videoView_tiktok = findViewById(R.id.videoView_tiktok);
        img_avatar_tiktok = findViewById(R.id.img_avatar_tiktok);
        img_music_tiktok = findViewById(R.id.img_music_tiktok);
        txt_music_tiktok = findViewById(R.id.txt_music_tiktok);
        imageView_like = findViewById(R.id.imageView_like);
        progressBar2 = findViewById(R.id.progressBar2);

        if(Build.VERSION.SDK_INT>=21){
            window = TikTokActivity.this.getWindow();
            window.setStatusBarColor(TikTokActivity.this.getResources().getColor(R.color.black));
            window.setNavigationBarColor(TikTokActivity.this.getResources().getColor(R.color.black));
        }
        txt_music_tiktok.setSelected(true);
        Picasso.get().load("https://scontent.xx.fbcdn.net/v/t1.15752-9/313099311_865380471153517_8436922236801161324_n.png?stp=dst-png_p168x128&_nc_cat=101&ccb=1-7&_nc_sid=aee45a&_nc_ohc=dHsElwlLGCQAX8scJlJ&_nc_ad=z-m&_nc_cid=0&_nc_ht=scontent.xx&oh=03_AdR6OOYwLdiYaAwLqffmVabky4ZW0eCuC7rZhFY3KF7F4w&oe=63AADF23").into(img_avatar_tiktok);
        animationCircleImageView(img_music_tiktok);
        ClickImageViewLike(imageView_like);
      //  videoView_tiktok.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.titkok));


        final DatabaseReference link = FirebaseDatabase.getInstance().getReference("StringVideo");
        new Thread(new Runnable() {
            @Override
            public void run() {
                link.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds : snapshot.getChildren()){
                             url = ds.getValue(String.class);
                        }
                        videoView_tiktok.setVideoPath(url);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }).start();
        videoView_tiktok.setMediaController(new MediaController(TikTokActivity.this));
        videoView_tiktok.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                videoView_tiktok.requestFocus();
                videoView_tiktok.start();
                progressBar2.setVisibility(View.GONE);

            }
        });
        videoView_tiktok.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoView_tiktok.start();
            }
        });
//        videoView_tiktok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(videoView_tiktok.isPlaying()){
//                    videoView_tiktok.pause();
//                }else{
//                    videoView_tiktok.start();
//                }
//            }
//        });

    }

    private void animationCircleImageView(CircleImageView imageView){
        Picasso.get().load("https://www.incimages.com/uploaded_files/image/1920x1080/getty_626660256_2000108620009280158_388846.jpg").into(imageView);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
               imageView.animate().rotationBy(360).withEndAction(this).setDuration(10000)
                       .setInterpolator(new LinearInterpolator()).start();
            }
        };
        imageView.animate().rotationBy(360).withEndAction(runnable).setDuration(10000)
                .setInterpolator(new LinearInterpolator()).start();
    }

    private void ClickImageViewLike(ImageView imageView){
         imageView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(flag==true){
                     imageView.setImageResource(R.drawable.ic_favorite_white_50);
                     flag  = false;
                 }else{
                     imageView.setImageResource(R.drawable.ic_favorite_red_50);
                     flag = true;
                 }
             }
         });
    }

    private void pictureIn_pictureMode(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.ECLAIR_0_1){

        }
    }
}