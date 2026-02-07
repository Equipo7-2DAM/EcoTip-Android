package com.svalero.ecotip.view;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.ecotip.contract.DetailEcosistemaContract;
import com.svalero.ecotip.domain.Ecosistema;
import com.svalero.ecotip.presenter.DetailEcosistemaPresenter;
import com.svalero.ecotip.util.DateUtil;

import java.time.LocalDate;

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
        animales.setText(ecosistema.getAnimales().toString());
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
    public void onEcosistemaDeleted() {
        finish();
    }
}
