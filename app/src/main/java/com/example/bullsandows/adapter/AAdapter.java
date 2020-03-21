package com.example.bullsandows.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bullsandows.FireBase;
import com.example.bullsandows.Model;
import com.example.bullsandows.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AAdapter extends RecyclerView.Adapter<AAdapter.VViewHolder> {
    private List<Model> models = new ArrayList<>();

    public AAdapter(List<Model> models) {
        this.models = models;
    }


    // Предоставляет прямую ссылку на каждый View-компонент
    // Используется для кэширования View-компонентов и последующего быстрого доступа к ним
    public class VViewHolder extends RecyclerView.ViewHolder {

        // Ваш ViewHolder должен содержать переменные для всех
        // View-компонентов, которым вы хотите задавать какие-либо свойства
        // в процессе работы пользователя со списком
        private TextView emailUser;
        private TextView ratingUser;


        // Мы также создали конструктор, который принимает на вход View-компонент строкИ
        // и ищет все дочерние компоненты
        public VViewHolder(@NonNull View itemView) {
            super(itemView);
            emailUser = itemView.findViewById(R.id.textEmailModel);
            ratingUser = itemView.findViewById(R.id.textResulModel);


        }

        //position для распределения рейтинга
        public void bind(Model model, int position) {
            emailUser.setText(position + 1 + " Место: " + model.getId());
            ratingUser.setText("Среднее число попыток: " + model.getStringAverageResult());
        }
    }

    //метод вызывается для создания объекта ViewHolder,метод вызывается без нашего вмешательства
    @NonNull
    @Override
    public VViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model_layout, parent, false);
        return new VViewHolder(view);
    }

    //метод отвечает за связь java объекта и View. Вызывается без нашего участия
    @Override
    public void onBindViewHolder(VViewHolder holder, int position) {
        // holder.bind(Data.modelList.get(position));
        holder.bind(models.get(position), position);

    }


    //сообщает количество элементов в списке
    @Override
    public int getItemCount() {

        return models.size();

    }

    //метод для наполнения коллекции
    public void setItems(Collection<Model> modelCollection) {
        models.addAll(modelCollection);
        //дает адаптеру знать что список эллементов изменился
        notifyDataSetChanged();

    }

    //метод для очистки коллекции
    public void clearItems() {
        models.clear();
        FireBase.showDate();
        notifyDataSetChanged();
    }
}
