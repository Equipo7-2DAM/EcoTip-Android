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
import com.svalero.ecotip.contract.AnimalListContract;
import com.svalero.ecotip.domain.Animal;
import com.svalero.ecotip.presenter.AnimalListPresenter;

import java.util.ArrayList;
import java.util.List;

public class AnimalListView extends AppCompatActivity implements AnimalListContract.View {

    List<Animal> animalList;
    private AnimalAdapter animalAdapter;
    private AnimalListPresenter presenter;
    private OnAnimalClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_list);
        presenter = new AnimalListPresenter(this);
        animalList = new ArrayList<>();

        RecyclerView animalListView = findViewById(R.id.animal_list);
        animalListView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        animalListView.setLayoutManager(linearLayoutManager);
        listener = new OnAnimalClickListener() {
            @Override
            public void onAnimalClick(Animal animal ) {
                onAnimalItemClick(animal);
            }

            @Override
            public void onDeleteClick(Animal animal) {
                AnimalListView.this.onDeleteClick(animal);
            }
        };
        animalAdapter = new AnimalAdapter(animalList, listener);
        animalListView.setAdapter(animalAdapter);
    }

    @Override protected void onResume() {
        super.onResume();
        presenter.loadAnimales(); }
    @Override
    public void show(List<Animal> animales) {
        animalList.clear();
        animalList.addAll(animales);
        animalAdapter.notifyDataSetChanged();
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
    public void onAnimalItemClick(Animal animal) {
        Intent intent = new Intent(this, DetailAnimalView.class);
        intent.putExtra(DetailAnimalView.EXTRA_ID, animal.getId());
        startActivity(intent);
    }

    @Override
    public void onDeleteClick(Animal animal) {
        confirmarBorrado(animal);
    }

    private void confirmarBorrado(Animal animal) {
        new AlertDialog.Builder(this)
                .setTitle("Delete animal")
                .setMessage("Are you sure you want to delete " + animal.getNombre() + " " + animal.getEspecie() +  "?")
                .setPositiveButton("Delete", (d, w) ->
                        presenter.deleteAnimal(animal.getId())
                )
                .setNegativeButton("Cancel", null)
                .show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenuanimal, menu);
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
            Intent intent = new Intent(this, UsuarioListView.class);
            startActivity(intent);
            return  true;
        }

        return false;
    }
}
