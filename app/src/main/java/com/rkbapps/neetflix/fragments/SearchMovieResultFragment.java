package com.rkbapps.neetflix.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.adapter.MovieListChildAdapter;
import com.rkbapps.neetflix.db.SharedPreferanceValues;
import com.rkbapps.neetflix.models.movies.MovieListModel;
import com.rkbapps.neetflix.models.movies.MovieResult;
import com.rkbapps.neetflix.services.ApiData;
import com.rkbapps.neetflix.services.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchMovieResultFragment extends Fragment {


    public static List<MovieResult> results = new ArrayList<>();
    static int totalPages;
    private static RecyclerView recyclerView;
    @SuppressLint("StaticFieldLeak")
    private static TextView textNoMovie;
    @SuppressLint("StaticFieldLeak")
    private static MovieListChildAdapter adapter;
    private static MovieListModel movieList = new MovieListModel();
    private static int page;
    private static boolean isLoading = false;
    private static String searchQuery;

    public SearchMovieResultFragment() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    public static void bindData(String query) {
        results.clear();
        if (!query.equals("dummyString") && query != null) {
            page = 1;
            searchQuery = query;
            loadMovieSearchResult(searchQuery, page);
        } else {
            textNoMovie.setText("Try to search something.");
        }
    }

    private static void loadMovieSearchResult(String query, int page) {
        isLoading = true;
        RetrofitInstance.getMovieApi().getMovieSearchResult(ApiData.API_KEY, query, page, SharedPreferanceValues.INSTANCE.readNsfw(recyclerView.getContext()))
                .enqueue(new Callback<MovieListModel>() {
                    @Override
                    public void onResponse(Call<MovieListModel> call, Response<MovieListModel> response) {
                        if (response.isSuccessful()) {

                            if (response.body().getResults().size() == 0 || response.body().getResults() == null) {
                                recyclerView.setVisibility(View.GONE);
                                textNoMovie.setText("No movie found.");
                                textNoMovie.setVisibility(View.VISIBLE);
                            } else {
                                textNoMovie.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                                movieList = response.body();
                                results.addAll(response.body().getResults());
                                adapter.notifyDataSetChanged();
                                isLoading = false;
                            }
                        } else {
                            recyclerView.setVisibility(View.GONE);
                            textNoMovie.setText(response.message());
                            textNoMovie.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieListModel> call, Throwable t) {
                        recyclerView.setVisibility(View.GONE);
                        textNoMovie.setText(t.getMessage());
                    }
                });


    }

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_movie_result, container, false);
        String query = getArguments().getString("query");
        recyclerView = view.findViewById(R.id.recyclerSearchMovies);
        textNoMovie = view.findViewById(R.id.txtSearchNoMovie);
        adapter = new MovieListChildAdapter(results, getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(adapter);


        bindData(query);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();

                if (gridLayoutManager != null && gridLayoutManager.findLastCompletelyVisibleItemPosition() == results.size() - 1) {
                    //bottom of list!
                    if (!isLoading) {
                        page++;
                        if (movieList.getTotalPages() >= page) {
                            loadMovieSearchResult(searchQuery, page);
                        }
                    }
                }
            }
        });
        return view;
    }

}