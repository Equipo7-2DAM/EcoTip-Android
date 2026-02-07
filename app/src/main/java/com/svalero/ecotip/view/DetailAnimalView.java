package com.svalero.ecotip.view;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.svalero.ecotip.contract.DetailAnimalContract;
import com.svalero.ecotip.domain.Animal;
import com.svalero.ecotip.domain.Usuario;
import com.svalero.ecotip.presenter.DetailAnimalPresenter;
import com.svalero.ecotip.util.DateUtil;

import java.time.LocalDate;

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

        nombre = findViewById(R.id.coche_detail_matricula);
        especie = findViewById(R.id.coche_detail_marca);
        peso = findViewById(R.id.coche_detail_modelo);
        ecosistema = findViewById(R.id.coche_detail_tipo_cambio);
        usuarios = findViewById(R.id.coche_detail_kilometraje);
        fechaAvistamiento = findViewById(R.id.coche_detail_fecha_compra);
        EnPeligro = findViewById(R.id.coche_detail_precio_compra);

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
        ecosistema.setText(animal.getEcosistema().toString());
        usuarios.setText(animal.getUsuarios().toString());

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
}
