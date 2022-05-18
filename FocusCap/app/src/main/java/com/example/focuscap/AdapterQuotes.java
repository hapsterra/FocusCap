package com.example.focuscap;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.focuscap.Model.Quote;

import java.util.ArrayList;

public class AdapterQuotes extends RecyclerView.Adapter<AdapterQuotes.ViewHolderQuotes>{
    ArrayList<Quote> quotes;

    public AdapterQuotes(ArrayList<Quote> quotes) {
        this.quotes = quotes;
    }

    @NonNull
    @Override

    public ViewHolderQuotes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);
        return new ViewHolderQuotes(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderQuotes holder, int position) {
        holder.user.setText("@"+quotes.get(position).getUser());
        holder.date.setText(quotes.get(position).getDate());
        holder.quote.setText(quotes.get(position).getQuote());
        holder.foto.setBackgroundResource(R.drawable.perfil);
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }

    public class ViewHolderQuotes extends RecyclerView.ViewHolder {
        TextView user,date,quote;
        ImageView foto;

        public ViewHolderQuotes(@NonNull View itemView) {
            super(itemView);
            user=itemView.findViewById(R.id.tv_user);
            date=itemView.findViewById(R.id.tv_date);
            quote=itemView.findViewById(R.id.tv_quote);
            foto=itemView.findViewById(R.id.foto);
        }
    }
}
