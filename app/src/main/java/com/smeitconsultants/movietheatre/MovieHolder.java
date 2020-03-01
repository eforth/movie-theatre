package com.smeitconsultants.movietheatre;

import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.widget.ANImageView;

class MovieHolder extends RecyclerView.ViewHolder {

    TextView title;
    ANImageView poster;
    ConstraintLayout listItem;

    public MovieHolder(View view) {
        super(view);
        title = view.findViewById(R.id.title);
        poster = view.findViewById(R.id.poster);
        listItem = view.findViewById(R.id.item);
    }
}
