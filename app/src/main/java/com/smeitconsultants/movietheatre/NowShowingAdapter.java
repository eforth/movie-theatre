package com.smeitconsultants.movietheatre;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NowShowingAdapter extends
        RecyclerView.Adapter<MovieHolder> {

    private ArrayList<Movie> movies;

    public NowShowingAdapter(ArrayList<Movie> movies) {
        Log.d("movies", movies.size() + "");
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                          int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.layout_now_showing, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder,
                                 int position) {

        final Movie movie = movies.get(position);

        Log.d("movie", Boolean.toString(movie.isShowing()));

        if (!movie.isShowing()) return;

        Log.d("movie", movie.getTitle());
        Log.d("uri", Uri.parse(movie.getPoster()).toString());

        holder.title.setText(movie.getTitle());
        holder.poster.setImageUrl(movie.getPoster());
        //holder.poster.setImageDrawable(movie.getDrawable());
        holder.listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if (listener == null) return;
                //listener.onItemSelected(movie, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.movies.size();
    }
}
