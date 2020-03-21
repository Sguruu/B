package com.example.bullsandows.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bullsandows.R;
import com.example.bullsandows.The_Game;


import java.util.ArrayList;
import java.util.List;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.HistoryViewHolder> {
    private List<The_Game.GameModel> gameModels = new ArrayList<>();

    public AdapterHistory(List<The_Game.GameModel> gameModels) {
        this.gameModels = gameModels;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model_layout, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.bind(gameModels.get(position), position);

    }

    @Override
    public int getItemCount() {
        return gameModels.size();
    }

    public void clearItems() {
        gameModels.clear();
        notifyDataSetChanged();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewHistory;
        private TextView resultHistory;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewHistory = itemView.findViewById(R.id.textEmailModel);
            resultHistory = itemView.findViewById(R.id.textResulModel);
        }

        public void bind(The_Game.GameModel gameModel, int position) {
            textViewHistory.setText(position + 1 + " Попытка : " + gameModel.getHistory());
            resultHistory.setText("Результат " + gameModel.getResultUser());
        }
    }

}