package com.example.bookseyes.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bookseyes.R;
import com.example.bookseyes.entities.Proverbe;

import java.util.List;

public class ProverbsAdapter extends RecyclerView.Adapter<ProverbsAdapter.MyViewHolder> {

    private List<Proverbe> proverbsList;
    private  OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        listener = onItemClickListener ;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, contenu, page;

        public MyViewHolder(View view, final OnItemClickListener listener) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            contenu = (TextView) view.findViewById(R.id.contenu);
            page = (TextView) view.findViewById(R.id.page);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition() ;
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }


    public ProverbsAdapter(List<Proverbe> proverbsList) {
        this.proverbsList = proverbsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.proverb_list_row, parent, false);

        return new MyViewHolder(itemView,listener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Proverbe movie = proverbsList.get(position);
        holder.title.setText(movie.getTitre());
        holder.contenu.setText(movie.getContenu());
        holder.page.setText(movie.getPage());
    }

    @Override
    public int getItemCount() {
        return proverbsList.size();
    }
}
