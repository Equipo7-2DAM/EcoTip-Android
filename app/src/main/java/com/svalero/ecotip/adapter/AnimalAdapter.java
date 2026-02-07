package com.svalero.ecotip.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.svalero.ecotip.R;
import com.svalero.ecotip.domain.Animal;
import com.svalero.ecotip.view.OnAnimalClickListener;

import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalHolder>{

    private List<Animal> animalList;
    private OnAnimalClickListener listener;

    public AnimalAdapter(List<Animal> animalList,OnAnimalClickListener listener) {
        this.animalList = animalList;
        this.listener = listener;
    }
    @NonNull
    @Override
    public AnimalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_animal, parent, false);
        return new AnimalHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalHolder holder, int position) {

        Animal animal = animalList.get(position);

        holder.animal_nombre.setText(animal.getNombre());
        holder.animal_especie.setText(animal.getEspecie());
        holder.animal_peso.setText(String.valueOf(animal.getPeso()));

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onAnimalClick(animal);
            }
        });
        holder.deleteButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDeleteClick(animal);
            }
        });
        }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

    public class AnimalHolder extends RecyclerView.ViewHolder {

        private TextView animal_nombre;
        private TextView animal_especie;
        private TextView animal_peso;
        private MaterialButton deleteButton;

        public AnimalHolder(@NonNull View itemView) {
            super(itemView);
            animal_nombre = itemView.findViewById(R.id.animal_nombre);
            animal_especie = itemView.findViewById(R.id.animal_especie);
            animal_peso = itemView.findViewById(R.id.animal_peso);
            deleteButton = itemView.findViewById(R.id.action_delete_animal);
        }
    }
}

