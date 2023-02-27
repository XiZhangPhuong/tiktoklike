package com.example.tiktoklike;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    List<String> listString;

    public UserAdapter(List<String> listString) {
        this.listString = listString;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_view,parent,false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        String url = listString.get(position);
        Picasso.get().load(url).into(holder.imageview_user);
    }

    @Override
    public int getItemCount() {
        if(listString.size()!=0){
            return listString.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView imageview_user;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageview_user = itemView.findViewById(R.id.imageview_user);
        }
    }
}
