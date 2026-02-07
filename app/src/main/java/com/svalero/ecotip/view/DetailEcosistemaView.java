package com.svalero.ecotip.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.svalero.ecotip.R;
import com.svalero.ecotip.contract.DetailEcosistemaContract;
import com.svalero.ecotip.domain.Animal;
import com.svalero.ecotip.domain.Ecosistema;
import com.svalero.ecotip.domain.Usuario;
import com.svalero.ecotip.presenter.DetailEcosistemaPresenter;
import com.svalero.ecotip.util.DateUtil;

import java.time.LocalDate;
import java.util.List;

public class DetailEcosistemaView extends AppCompatActivity implements DetailEcosistemaContract.View {
    private DetailEcosistemaPresenter presenter;
    private Ecosistema ecosistema;
    private TextView nombre, descripcion, temperaturaMedia, animales, createdAt;
    private CheckBox isActive;
    public static final String EXTRA_ID = "ECOSISTEMA_ID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ecosistema);

        nombre = findViewById(R.id.ecosistema_detail_nombre);
        descripcion = findViewById(R.id.ecosistema_detail_descripcion);
        isActive = findViewById(R.id.ecosistema_detail_is_active);
        temperaturaMedia = findViewById(R.id.ecosistema_detail_temperatura_media);
        createdAt = findViewById(R.id.ecosistema_detail_created_at);
        animales = findViewById(R.id.ecosistema_detail_animales);


        long id = getIntent().getLongExtra(EXTRA_ID, -1);
        presenter = new DetailEcosistemaPresenter(this);
        presenter.loadEcosistema(id);
    }
    @Override
    public void showEcosistema(Ecosistema ecosistema) {
        this.ecosistema = ecosistema;
        nombre.setText(ecosistema.getNombre());
        descripcion.setText(ecosistema.getDescripcion());
        isActive.setChecked(ecosistema.isActive());
        temperaturaMedia.setText(String.valueOf(ecosistema.getTemperaturaMedia()));
        LocalDate fecha = ecosistema.getCreatedAt();
        createdAt.setText(
                fecha != null
                        ? DateUtil.formatDate(fecha)
                        : "Fecha no disponible"
        );

    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showAnimales(List<Animal> a) {
        if (a == null || a.isEmpty()) {
            animales.setText("No animals");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Animal ani : a) {
            sb.append(ani.getNombre()).append("\n").append(ani.getEspecie()).append("\n");
        }
        animales.setText(sb.toString());
    }

    @Override
    public void onEcosistemaDeleted() {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.backbutton, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.back_button){
            Intent intent = new Intent(this, EcosistemaListView.class);
            startActivity(intent);
            return  true;
        }

        return false;
    }
}
