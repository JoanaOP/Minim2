package edu.upc.minim2exemple1;

import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Follower> values;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public View layout;
        public ImageView imgAvatar;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            //txtFooter = (TextView) v.findViewById(R.id.secondLine);
            imgAvatar = (ImageView) v.findViewById(R.id.icon);
        }
    }

    public void setData(List<Follower> myDataset) {
        values = myDataset;
        notifyDataSetChanged();
    }

    public void add(int position, Follower item) {
        values.add(position, item);
        notifyItemInserted(position);
    }
/*
    public void sendMessage(int position, View v) {

        Track track = values.get(position);
        Intent intent = new Intent(v.getContext(), TracksActivity.class);
        intent.putExtra("Track title", track.getTitle());
        intent.putExtra("Track singer",track.getSinger());
        intent.putExtra("Track id", track.getId());
        v.getContext().startActivity(intent);
    }

 */

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Follower> myDataset) {
        values = myDataset;
    }

    public MyAdapter() {
        values = new ArrayList<>();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Follower follower = values.get(position);
        String name = follower.getName();
        holder.txtHeader.setText(name);

        Glide.with(holder.imgAvatar.getContext())
                .load(follower.getAvatarUrl())
                .into(holder.imgAvatar);
        /*
        holder.txtHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(holder.getAdapterPosition(),v);
            }
        });

         */

        //holder.txtFooter.setText(track.getSinger());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}
