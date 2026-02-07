package com.svalero.ecotip.presenter;

import com.svalero.ecotip.contract.DetailEcosistemaContract;
import com.svalero.ecotip.contract.UsuarioDetailContract;
import com.svalero.ecotip.domain.Animal;
import com.svalero.ecotip.domain.Ecosistema;
import com.svalero.ecotip.domain.Usuario;
import com.svalero.ecotip.model.DetailEcosistemaModel;
import com.svalero.ecotip.model.UsuarioDetailModel;

import java.util.List;

public class UsuarioDetailPresenter implements UsuarioDetailContract.Presenter, UsuarioDetailContract.Model.OnDeleteListener, UsuarioDetailContract.Model.OnLoadListener {
    private UsuarioDetailContract.Model model;
    private UsuarioDetailContract.View view;

    public UsuarioDetailPresenter(UsuarioDetailContract.View view){
        this.view = view;
        this.model = new UsuarioDetailModel();
    }

    @Override
    public void onLoadSuccess(Usuario usuario) {
        List<Animal> ani = usuario.getAnimales();
        if(ani != null){
            view.showAnimales(ani);
        }else {
            view.showAnimales(null);
        }
        view.showUsuario(usuario);
        view.showMessage("Loaded.");
    }

    @Override
    public void onLoadError(String message) {
        view.showError(message);
    }

    @Override
    public void onDeleteSuccess(String message) {
        view.showMessage(message);
        view.onUsuarioDeleted();
    }

    @Override
    public void onDeleteError(String message) {
        view.showError(message);
    }



    @Override
    public void loadUsuario(long id) {
        model.loadUsuario(id, this);
    }

    @Override
    public void deleteUsuario(long id) {
        model.deleteUsuario(id, this);
    }
}
