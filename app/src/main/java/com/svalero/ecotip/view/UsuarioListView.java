package com.svalero.ecotip.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.ecotip.R;
import com.svalero.ecotip.adapter.AnimalAdapter;
import com.svalero.ecotip.adapter.UsuarioAdapter;
import com.svalero.ecotip.contract.UsuarioListContract;
import com.svalero.ecotip.domain.Animal;
import com.svalero.ecotip.domain.Usuario;
import com.svalero.ecotip.presenter.AnimalListPresenter;
import com.svalero.ecotip.presenter.UsuarioListPresenter;

import java.util.ArrayList;
import java.util.List;

public class UsuarioListView extends AppCompatActivity implements UsuarioListContract.View {

    List<Usuario> usuarioList;
    private UsuarioAdapter usuarioAdapter;
    private UsuarioListPresenter presenter;
    private OnUsuarioClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_list);
        presenter = new UsuarioListPresenter(this);
        usuarioList = new ArrayList<>();

        RecyclerView usuarioListView = findViewById(R.id.usuario_list);
        usuarioListView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        usuarioListView.setLayoutManager(linearLayoutManager);
        listener = new OnUsuarioClickListener() {
            @Override
            public void onUsuarioClick(Usuario usuario ) {
                onUsuarioItemClick(usuario);
            }

            @Override
            public void onDeleteClick(Usuario usuario) {
                UsuarioListView.this.onDeleteClick(usuario);
            }
        };
        usuarioAdapter = new UsuarioAdapter(usuarioList, listener);
        usuarioListView.setAdapter(usuarioAdapter);
    }

    @Override protected void onResume() {
        super.onResume();
        presenter.loadUsuarios(); }
    @Override
    public void show(List<Usuario> usuarios) {
            usuarioList.clear();
            usuarioList.addAll(usuarios);
            usuarioAdapter.notifyDataSetChanged();
        }



    @Override
    public void showError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }
    @Override
    public void onUsuarioItemClick(Usuario usuario) {
        Intent intent = new Intent(this, UsuarioDetailView.class);
        intent.putExtra(UsuarioDetailView.EXTRA_ID, usuario.getId());
        startActivity(intent);
    }

    @Override
    public void onDeleteClick(Usuario usuario) {
        confirmarBorrado(usuario);
    }

    private void confirmarBorrado(Usuario usuario) {
        new AlertDialog.Builder(this)
                .setTitle("Delete user")
                .setMessage("Are you sure you want to delete " + usuario.getNombre() + " " + usuario.getApellidos() +  "?")
                .setPositiveButton("Delete", (d, w) ->
                        presenter.deleteUsuario(usuario.getId())
                )
                .setNegativeButton("Cancel", null)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenuuser, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_animales_list){
            Intent intent = new Intent(this, EcosistemaListView.class);
            startActivity(intent);
            return  true;
        }
        if (item.getItemId() == R.id.menu_usuario_list){
            Intent intent = new Intent(this, AnimalListView.class);
            startActivity(intent);
            return  true;
        }

        return false;
    }
}
