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
import com.svalero.ecotip.domain.Ecosistema;
import com.svalero.ecotip.view.OnAnimalClickListener;
import com.svalero.ecotip.view.OnEcosistemaClickListener;

import java.util.List;

public class EcosistemaAdapter extends RecyclerView.Adapter<EcosistemaAdapter.EcosistemaHolder>{
    private List<Ecosistema> ecosistemaList;
    private OnEcosistemaClickListener listener;

    public EcosistemaAdapter(List<Ecosistema> ecosistemaList,OnEcosistemaClickListener listener) {
        this.ecosistemaList = ecosistemaList;
        this.listener = listener;
    }


    @NonNull
    @Override
    public EcosistemaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ecosistema, parent, false);
        return new EcosistemaAdapter.EcosistemaHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EcosistemaAdapter.EcosistemaHolder holder, int position) {
        Ecosistema ecosistema = ecosistemaList.get(position);

        holder.ecosistema_nombre.setText(ecosistema.getNombre());
        holder.ecosistema_descripcion.setText(ecosistema.getDescripcion());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEcosistemaClick(ecosistema);
            }
        });
        holder.deleteButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDeleteClick(ecosistema);
            }
        });
    }



    @Override
    public int getItemCount() {
        return ecosistemaList.size();
    }

    public class EcosistemaHolder extends RecyclerView.ViewHolder {

        private TextView ecosistema_nombre;
        private TextView ecosistema_descripcion;
        private MaterialButton deleteButton;

        public EcosistemaHolder(@NonNull View itemView) {
            super(itemView);
            ecosistema_nombre = itemView.findViewById(R.id.ecosistema_nombre);
            ecosistema_descripcion = itemView.findViewById(R.id.ecosistema_descripcion);
            deleteButton = itemView.findViewById(R.id.action_delete_ecosistema);
        }
    }
}
