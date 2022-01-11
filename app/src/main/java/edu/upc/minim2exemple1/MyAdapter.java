package edu.upc.minim2exemple1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Repository> values;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtRepoName;
        public TextView txtLanguage;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtRepoName = (TextView) v.findViewById(R.id.repoText);
            txtLanguage = (TextView) v.findViewById(R.id.languageText);
        }
    }

    public void setData(List<Repository> myDataset) {
        values = myDataset;
        notifyDataSetChanged();
    }

    public void add(int position, Repository item) {
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
    public MyAdapter(List<Repository> myDataset) {
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
        Repository repository = values.get(position);
        String name = repository.getName();
        holder.txtRepoName.setText(name);
        if(repository.getLanguage() == null){
            holder.txtLanguage.setText("Not defined");
        }
        else {
            holder.txtLanguage.setText(repository.getLanguage());
        }

        /*
        Glide.with(holder.imgAvatar.getContext())
                .load(follower.getAvatarUrl())
                .into(holder.imgAvatar);

         */
        /*
        holder.txtHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(holder.getAdapterPosition(),v);
            }
        });

         */


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}
