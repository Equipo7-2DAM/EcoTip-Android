package com.svalero.ecotip.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.svalero.ecotip.R;
import com.svalero.ecotip.domain.Usuario;
import com.svalero.ecotip.view.OnUsuarioClickListener;

import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioHolder> {
    private List<Usuario> usuarioList;
    private OnUsuarioClickListener listener;

    public UsuarioAdapter(List<Usuario> usuarioList, OnUsuarioClickListener listener) {
        this.usuarioList = usuarioList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UsuarioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuario, parent, false);
        return new UsuarioAdapter.UsuarioHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioAdapter.UsuarioHolder holder, int position) {
        Usuario usuario = usuarioList.get(position);

        holder.usuario_nombre.setText(usuario.getNombre());
        holder.usuario_apellidos.setText(usuario.getApellidos());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onUsuarioClick(usuario);
            }
        });
        holder.deleteButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDeleteClick(usuario);
            }
        });
    }

    @Override
    public int getItemCount() {
        return usuarioList.size();
    }

    public class UsuarioHolder extends RecyclerView.ViewHolder {

        private TextView usuario_nombre;
        private TextView usuario_apellidos;
        private MaterialButton deleteButton;

        public UsuarioHolder(@NonNull View itemView) {
            super(itemView);
            usuario_nombre = itemView.findViewById(R.id.usuario_nombre);
            usuario_apellidos = itemView.findViewById(R.id.usuario_apellidos);
            deleteButton = itemView.findViewById(R.id.action_delete_usuario);
        }
    }
}
