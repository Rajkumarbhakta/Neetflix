package com.rkbapps.neetflix.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.models.movies.ProductionCompany;

import java.util.List;

public class ProductionCompanyAdapter extends RecyclerView.Adapter<ProductionCompanyAdapter.ProductionCompanyViewHolder> {

    private Context context;
    private List<ProductionCompany> productionCompanyList;

    public ProductionCompanyAdapter(Context context, List<ProductionCompany> productionCompanyList) {
        this.context = context;
        this.productionCompanyList = productionCompanyList;
    }

    @NonNull
    @Override
    public ProductionCompanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductionCompanyViewHolder(LayoutInflater.from(context).inflate(R.layout.cast_item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductionCompanyViewHolder holder, int position) {

        holder.companyName.setText(productionCompanyList.get(position).getName());

        if(productionCompanyList.get(position).getLogoPath()!=null)
            Glide.with(context).load("https://image.tmdb.org/t/p/w500/"+productionCompanyList.get(position).getLogoPath()).into(holder.logo);
        else
            Glide.with(context).load("").into(holder.logo);


    }

    @Override
    public int getItemCount() {
        return productionCompanyList.size();
    }

    public class ProductionCompanyViewHolder extends RecyclerView.ViewHolder {

        ImageView logo;
        TextView companyName;
        public ProductionCompanyViewHolder(@NonNull View itemView) {
            super(itemView);
            logo = itemView.findViewById(R.id.imgCast);
            companyName = itemView.findViewById(R.id.txtCastName);
        }
    }
}
