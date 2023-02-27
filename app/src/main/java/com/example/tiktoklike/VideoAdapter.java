package com.example.tiktoklike;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder>{
    private Activity activity;
    private Context context;
    private List<Video> listVideo;
    private ClickLikeVideo clickLikeVideo;
    public interface ClickLikeVideo{
        void Click(Video video, ImageView img_like);
        void ClickVideoView(Video video);
    }

    public VideoAdapter(Context context, List<Video> listVideo, ClickLikeVideo clickLikeVideo) {
        this.context = context;
        this.listVideo = listVideo;
        this.clickLikeVideo = clickLikeVideo;
    }

    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_tik_tok,parent,false);
        return new VideoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.ViewHolder holder, int position) {
        Video video = this.listVideo.get(position);
      //  holder.videoView.setVideoPath(video.getURL());
//        Uri uri = Uri.parse("android.resource://"+context.getPackageName()+"/"+R.raw.video1);
//        holder.videoView.setVideoURI(uri);
        Uri uri = Uri.parse(video.getURL());
        holder.videoView.setVideoURI(uri);
        holder.videoView.requestFocus();
        holder.videoView.setMediaController(new MediaController(context));
        holder.txt_music.setSelected(true);

        Picasso.get().load("https://scontent.xx.fbcdn.net/v/t1.15752-9/313099311_865380471153517_8436922236801161324_n.png?stp=dst-png_p168x128&_nc_cat=101&ccb=1-7&_nc_sid=aee45a&_nc_ohc=dHsElwlLGCQAX8scJlJ&_nc_ad=z-m&_nc_cid=0&_nc_ht=scontent.xx&oh=03_AdR6OOYwLdiYaAwLqffmVabky4ZW0eCuC7rZhFY3KF7F4w&oe=63AADF23").into(holder.img_avatar);
        Picasso.get().load("https://www.incimages.com/uploaded_files/image/1920x1080/getty_626660256_2000108620009280158_388846.jpg").into(holder.img_music_tiktok);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                holder.img_music_tiktok.animate().rotationBy(360).withEndAction(this).setDuration(10000)
                        .setInterpolator(new LinearInterpolator()).start();
            }
        };
        holder.img_music_tiktok.animate().rotationBy(360).withEndAction(runnable).setDuration(10000)
                .setInterpolator(new LinearInterpolator()).start();

        holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                holder.txt_view_tiktok.setVisibility(View.GONE);
                holder.progressBar.setVisibility(View.GONE);
                holder.videoView.requestFocus();
                holder.videoView.start();

                holder.txt_view_tiktok.setVisibility(View.VISIBLE);
            }
        });


        holder.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                holder.videoView.start();
            }
        });
        holder.videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(holder.videoView.isPlaying()){
//                    holder.videoView.pause();
//                }else {
//                    holder.videoView.start();
//                }
            }
        });



        holder.rcv_user.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        holder.rcv_user.setHasFixedSize(true);
        List<String> list = new ArrayList<>();
        list.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRLe5PABjXc17cjIMOibECLM7ppDwMmiDg6Dw&usqp=CAU");
        list.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNPEigtC8FVHM17qS_y7d6wIsOH3QdKbD1LQ&usqp=CAU");
        list.add("https://image-us.24h.com.vn/upload/4-2022/images/2022-11-06/doi-hinh-mu-2020-2-1667730924-130-width500height300.jpg");
        list.add("https://static.bongda24h.vn/medias/standard/2022/06/17/bellingham_1706112044.jpg");
        list.add("https://i.ytimg.com/vi/YSe-0uLRKGg/mqdefault.jpg");
        holder.rcv_user.setAdapter(new UserAdapter(list));

    };

    @Override
    public int getItemCount() {
        if(listVideo.size()!=0){
            return listVideo.size();
        }
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private VideoView videoView;
        private ProgressBar progressBar;
        private ImageView imageView_like,imageView_comment,imageView_share;
        private CircleImageView img_avatar,img_music_tiktok;
        private TextView txt_like,txt_music,txt_view_tiktok;
        private RecyclerView rcv_user;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView_tiktok);
            progressBar = itemView.findViewById(R.id.progressBar2);
            img_avatar = itemView.findViewById(R.id.img_avatar_tiktok);
            txt_music = itemView.findViewById(R.id.txt_music_tiktok);
            img_music_tiktok = itemView.findViewById(R.id.img_music_tiktok);
            rcv_user  = itemView.findViewById(R.id.rcv_user);
            txt_view_tiktok = itemView.findViewById(R.id.txt_view_tiktok);
        }


    }


}
