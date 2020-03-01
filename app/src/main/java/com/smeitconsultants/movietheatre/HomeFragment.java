package com.smeitconsultants.movietheatre;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayList<Movie> list = new ArrayList<>();
    private NowShowingAdapter nowShowingAdapter;


    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container
                , false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView nowShowingRecyclerView = view
                .findViewById(R.id.nowShowingList);
        nowShowingAdapter = new NowShowingAdapter(list);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);
        nowShowingRecyclerView.setLayoutManager(manager);
        nowShowingRecyclerView.setItemAnimator(new DefaultItemAnimator());
        nowShowingRecyclerView.setAdapter(nowShowingAdapter);

        loadMovies();

    }

    public void testMovies() {

        Movie m1 = new Movie(
                "Birds of Prey: And the Fantabulous " +
                        "Emancipation of One Harley Quinn",
                "2020",
                "R",
                "07 Feb 2020",
                "109 min",
                "After splitting with the Joker, Harley " +
                        "Quinn joins superheroes Black Canary, " +
                        "Huntress and Renee Montoya to save a " +
                        "young girl from an evil crime lord.",
                "https://m.media-amazon.com/images/M/" +
                        "MV5BMzQ3NTQxMjItODBjYi00YzUzLWE1NzQtZT" +
                        "BlY2Y2NjZlNzkyXkEyXkFqcGdeQXVyMTkxNjU" +
                        "yNQ@@._V1_SX300.jpg",
                "6.6",
                "tt7713068",
                true
        );

        Movie m2 = new Movie(
                "Birds of Prey: And the Fantabulous " +
                        "Emancipation of One Harley Quinn",
                "2020",
                "R",
                "07 Feb 2020",
                "109 min",
                "After splitting with the Joker, Harley " +
                        "Quinn joins superheroes Black Canary, " +
                        "Huntress and Renee Montoya to save a " +
                        "young girl from an evil crime lord.",
                "https://m.media-amazon.com/images/M/" +
                        "MV5BMzQ3NTQxMjItODBjYi00YzUzLWE1NzQtZT" +
                        "BlY2Y2NjZlNzkyXkEyXkFqcGdeQXVyMTkxNjU" +
                        "yNQ@@._V1_SX300.jpg",
                "6.6",
                "tt7713068",
                true
        );

        Movie m3 = new Movie(
                "Birds of Prey: And the Fantabulous " +
                        "Emancipation of One Harley Quinn",
                "2020",
                "R",
                "07 Feb 2020",
                "109 min",
                "After splitting with the Joker, Harley " +
                        "Quinn joins superheroes Black Canary, " +
                        "Huntress and Renee Montoya to save a " +
                        "young girl from an evil crime lord.",
                "https://m.media-amazon.com/images/M/" +
                        "MV5BMzQ3NTQxMjItODBjYi00YzUzLWE1NzQtZT" +
                        "BlY2Y2NjZlNzkyXkEyXkFqcGdeQXVyMTkxNjU" +
                        "yNQ@@._V1_SX300.jpg",
                "6.6",
                "tt7713068",
                true
        );

        list.add(m1);
        list.add(m2);
        list.add(m3);
        nowShowingAdapter.notifyDataSetChanged();
    }

    public void testMovie2() {

        getMovie("tt7713068", true);
        getMovie("tt9285882", true);
        getMovie("tt3794354", false);
    }

    private void loadMovies() {

        OnCompleteListener<QuerySnapshot> listener = new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (!task.isSuccessful()) { // not successfull
                    Toast.makeText(getContext(),
                            "Error retrieving records",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                for (QueryDocumentSnapshot doc : task.getResult()) {
                    Toast.makeText(getContext(),
                            "Records loaded",
                            Toast.LENGTH_SHORT).show();

                    Movie movie = Movie.fromMap(doc.getData());

                    Log.i("movie", Boolean.toString(movie.isShowing()));

                    if (movie.isShowing()) {
                        list.add(movie);
                        nowShowingAdapter.notifyDataSetChanged();
                    }
                }
            }
        };

        db.collection("movies")
                .get()
                .addOnCompleteListener(listener);

    }

    private void getMovie(String IMDBID, final boolean showing) {

        AndroidNetworking.get("https://www.omdbapi.com")
                .addQueryParameter("apikey", "b54e863")
                .addQueryParameter("i", IMDBID)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Movie movie = Movie.fromJSON(response, showing);

                        if (movie.isShowing()) {
                            list.add(movie);
                            nowShowingAdapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("error", anError.getErrorDetail());
                        Log.d("error", anError.getErrorCode() + "");
                        anError.printStackTrace();
                    }
                });

    }
}
