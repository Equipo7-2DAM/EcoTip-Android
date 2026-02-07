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
import com.svalero.ecotip.adapter.EcosistemaAdapter;
import com.svalero.ecotip.contract.EcosistemaListContract;
import com.svalero.ecotip.domain.Ecosistema;
import com.svalero.ecotip.presenter.EcosistemaListPresenter;

import java.util.ArrayList;
import java.util.List;

public class EcosistemaListView extends AppCompatActivity implements EcosistemaListContract.View {
    List<Ecosistema> ecosistemaList;
    private EcosistemaAdapter ecosistemaAdapter;
    private EcosistemaListPresenter presenter;
    private OnEcosistemaClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecosystem_list);
        presenter = new EcosistemaListPresenter(this);
        ecosistemaList = new ArrayList<>();

        RecyclerView ecosistemaListView = findViewById(R.id.ecosistema_list);
        ecosistemaListView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        ecosistemaListView.setLayoutManager(linearLayoutManager);
        listener = new OnEcosistemaClickListener() {
            @Override
            public void onEcosistemaClick(Ecosistema ecosistema ) {
                onEcosistemaItemClick(ecosistema);
            }

            @Override
            public void onDeleteClick(Ecosistema ecosistema) {
                EcosistemaListView.this.onDeleteClick(ecosistema);
            }
        };
        ecosistemaAdapter = new EcosistemaAdapter(ecosistemaList, listener);
        ecosistemaListView.setAdapter(ecosistemaAdapter);
    }
    @Override protected void onResume() {
        super.onResume();
        presenter.loadEcosistemas(); }
    @Override
    public void show(List<Ecosistema> ecosistemas) {

            ecosistemaList.clear();
            ecosistemaList.addAll(ecosistemas);
            ecosistemaAdapter.notifyDataSetChanged();


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
    public void onEcosistemaItemClick(Ecosistema ecosistema) {
        Intent intent = new Intent(this, DetailEcosistemaView.class);
        intent.putExtra(DetailEcosistemaView.EXTRA_ID, ecosistema.getId());
        startActivity(intent);
    }

    @Override
    public void onDeleteClick(Ecosistema ecosistema) {
        confirmarBorrado(ecosistema);
    }

    private void confirmarBorrado(Ecosistema ecosistema) {
        new AlertDialog.Builder(this)
                .setTitle("Delete ecosystem")
                .setMessage("Are you sure you want to delete " + ecosistema.getNombre() +  "?")
                .setPositiveButton("Delete", (d, w) ->
                        presenter.deleteEcosistema(ecosistema.getId())
                )
                .setNegativeButton("Cancel", null)
                .show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_animales_list){
            Intent intent = new Intent(this, AnimalListView.class);
            startActivity(intent);
            return  true;
        }
        if (item.getItemId() == R.id.menu_usuario_list){
            Intent intent = new Intent(this, UsuarioListView.class);
            startActivity(intent);
            return  true;
        }

        return false;
    }
}
