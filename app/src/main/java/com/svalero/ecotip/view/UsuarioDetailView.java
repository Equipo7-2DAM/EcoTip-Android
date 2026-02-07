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

import com.svalero.ecotip.contract.UsuarioDetailContract;
import com.svalero.ecotip.domain.Animal;
import com.svalero.ecotip.domain.Usuario;
import com.svalero.ecotip.presenter.UsuarioDetailPresenter;
import com.svalero.ecotip.util.DateUtil;
import com.svalero.ecotip.R;

import java.time.LocalDate;
import java.util.List;

public class UsuarioDetailView extends AppCompatActivity implements UsuarioDetailContract.View {
    private UsuarioDetailPresenter presenter;
    private Usuario usuario;
    private TextView nombre, apellidos, dni, email, fechaNacimiento, donativo, animales ;
    private CheckBox colaborador;
    public static final String EXTRA_ID = "USUARIO_ID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_usuario);

        nombre = findViewById(R.id.usuario_detail_nombre);
        apellidos = findViewById(R.id.usuario_detail_apellidos);
        dni = findViewById(R.id.usuario_detail_dni);
        email = findViewById(R.id.usuario_detail_email);
        fechaNacimiento = findViewById(R.id.usuario_detail_fecha_nacimiento);
        donativo = findViewById(R.id.usuario_detail_donativo);
        animales = findViewById(R.id.usuario_detail_animales);
        colaborador = findViewById(R.id.usuario_detail_colaborador);


        long id = getIntent().getLongExtra(EXTRA_ID, -1);
        presenter = new UsuarioDetailPresenter(this);
        presenter.loadUsuario(id);
    }


    @Override
    public void showUsuario(Usuario usuario) {
        this.usuario = usuario;
        nombre.setText(usuario.getNombre());
        apellidos.setText(usuario.getApellidos());
        dni.setText(usuario.getDni());
        email.setText(usuario.getEmail());
        colaborador.setChecked(usuario.isColaborador());
        donativo.setText(String.valueOf(usuario.getDonativo()));
        LocalDate fecha = usuario.getFechaNacimiento();
        fechaNacimiento.setText(
                fecha != null
                        ? DateUtil.formatDate(fecha)
                        : "Fecha no disponible"
        );

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
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUsuarioDeleted() {
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
            Intent intent = new Intent(this, UsuarioListView.class);
            startActivity(intent);
            return  true;
        }

        return false;
    }
}
