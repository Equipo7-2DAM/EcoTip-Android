package com.svalero.ecotip.presenter;

import com.svalero.ecotip.contract.UsuarioListContract;
import com.svalero.ecotip.domain.Ecosistema;
import com.svalero.ecotip.domain.Usuario;
import com.svalero.ecotip.model.UsuarioListModel;

import java.util.List;

public class UsuarioListPresenter implements UsuarioListContract.Presenter, UsuarioListContract.Model.OnLoadListener, UsuarioListContract.Model.OnDeleteListener {
    private UsuarioListContract.Model model;
    private UsuarioListContract.View view;

    public UsuarioListPresenter(UsuarioListContract.View view) {
        this.model = new UsuarioListModel();
        this.view = view;

    }

    @Override
    public void onDeleteSuccess(String message) {
        view.showMessage(message);
        loadUsuarios();
    }

    @Override
    public void onDeleteError(String message) {
        view.showError(message);
    }

    @Override
    public void onLoadSuccess(List<Usuario> usuarios) {
        view.show(usuarios);
        view.showMessage("Loaded.");
    }

    @Override
    public void onLoadError(String message) {
        view.showError(message);
    }



    @Override
    public void loadUsuarios() {
        model.loadUsuarios(this);
    }

    @Override
    public void deleteUsuario(long id) {
        model.deleteUsuario(id, this);

    }
}
