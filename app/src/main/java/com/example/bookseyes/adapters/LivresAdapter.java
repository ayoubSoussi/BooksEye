package com.example.bookseyes.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bookseyes.R;
import com.example.bookseyes.entities.Livre;


import java.util.List;

    public class LivresAdapter extends RecyclerView.Adapter<LivresAdapter.MyViewHolder> {

        private List<Livre> livresList;
        private OnItemClickListener listener;

        public interface OnItemClickListener {
            void onItemClick(int position);
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener){
            listener = onItemClickListener ;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView title, auteur;

            public MyViewHolder(View view, final OnItemClickListener listener) {
                super(view);
                title = (TextView) view.findViewById(R.id.titleL);
                auteur = (TextView) view.findViewById(R.id.auteur);

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


        public LivresAdapter(List<Livre> livresList) {
            this.livresList = livresList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.livre_list_row, parent, false);

            return new MyViewHolder(itemView,listener);
        }


        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Livre movie = livresList.get(position);
            holder.title.setText(movie.getTitre());
            holder.auteur.setText(movie.getAuteur()) ;
        }

        @Override
        public int getItemCount() {
            return livresList.size();
        }}
