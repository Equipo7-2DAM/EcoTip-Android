package com.svalero.ecotip.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.svalero.ecotip.R;
import com.svalero.ecotip.contract.DetailAnimalContract;
import com.svalero.ecotip.domain.Animal;
import com.svalero.ecotip.domain.Ecosistema;
import com.svalero.ecotip.domain.Usuario;
import com.svalero.ecotip.presenter.DetailAnimalPresenter;
import com.svalero.ecotip.util.DateUtil;

import java.time.LocalDate;
import java.util.List;

public class DetailAnimalView extends AppCompatActivity implements DetailAnimalContract.View {
    public static final String EXTRA_ID = "ANIMAL_ID";
    private DetailAnimalPresenter presenter;
    private Animal animal;
    private TextView nombre, especie, peso, ecosistema, usuarios, fechaAvistamiento;
    private CheckBox EnPeligro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_animal);

        nombre = findViewById(R.id.animal_detail_nombre);
        especie = findViewById(R.id.animal_detail_especie);
        peso = findViewById(R.id.animal_detail_peso);
        ecosistema = findViewById(R.id.animal_detail_ecosistema);
        usuarios = findViewById(R.id.animal_detail_usuarios);
        fechaAvistamiento = findViewById(R.id.animal_detail_fecha_avistamiento);
        EnPeligro = findViewById(R.id.anima_detail_en_peligro);

        long id = getIntent().getLongExtra(EXTRA_ID, -1);
        presenter = new DetailAnimalPresenter(this);
        presenter.loadAnimal(id);
    }
    @Override
    public void showAnimal(Animal animal) {
        this.animal = animal;
        nombre.setText(animal.getNombre());
        especie.setText(animal.getEspecie());
        EnPeligro.setChecked(animal.isEnPeligro());
        peso.setText(String.valueOf(animal.getPeso()));
        LocalDate fecha = animal.getFechaAvistamiento();
        fechaAvistamiento.setText(
                fecha != null
                        ? DateUtil.formatDate(fecha)
                        : "Fecha no disponible"
        );


    }

    public void showUsuario(List<Usuario> u) {
        if (u == null || u.isEmpty()) {
            usuarios.setText("No users");
            return;
        }
        Usuario usuario = u.get(0);
        usuarios.setText(usuario.getNombre());
    }

    public void showEcosistema(Ecosistema e) {
        if (e != null) {
            ecosistema.setText(e.getNombre());
        } else {
            ecosistema.setText("Without ecosystem");
        }
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAnimalDeleted() {
        finish();
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
        getMenuInflater().inflate(R.menu.backbutton, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.back_button){
            Intent intent = new Intent(this, AnimalListView.class);
            startActivity(intent);
            return  true;
        }

        return false;
    }
}
