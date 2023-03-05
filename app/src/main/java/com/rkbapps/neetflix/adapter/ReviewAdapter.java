package com.rkbapps.neetflix.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.models.Review.Result;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private final Context context;
    private final List<Result> resultList;

    public ReviewAdapter(Context context, List<Result> resultList) {
        this.context = context;
        this.resultList = resultList;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReviewViewHolder(LayoutInflater.from(context).inflate(R.layout.review_item, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        holder.authorName.setText(resultList.get(position).getAuthor());
        if (resultList.get(position).getAuthorDetails().getRating() != null)
            holder.authorRating.setText("" + resultList.get(position).getAuthorDetails().getRating());
        else
            holder.authorRating.setText("" + 0);

        holder.review.setText(resultList.get(position).getContent());

        String date = "";
        char[] ch = resultList.get(position).getCreatedAt().toCharArray();
        for (int i = 0; i < 10; i++) {
            date += ch[i];
        }

        holder.lastUpdateTime.setText(date);

    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {

        TextView authorName, authorRating, review, lastUpdateTime;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);

            authorName = itemView.findViewById(R.id.txtAuthorName);
            authorRating = itemView.findViewById(R.id.txtAuthorRating);
            review = itemView.findViewById(R.id.txtReview);
            lastUpdateTime = itemView.findViewById(R.id.txtPostDate);


        }
    }
}
