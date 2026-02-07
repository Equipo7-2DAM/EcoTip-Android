package com.svalero.ecotip.presenter;

import com.svalero.ecotip.contract.EcosistemaListContract;
import com.svalero.ecotip.domain.Animal;
import com.svalero.ecotip.domain.Ecosistema;
import com.svalero.ecotip.model.EcosistemaListModel;

import java.util.List;

public class EcosistemaListPresenter implements EcosistemaListContract.Presenter, EcosistemaListContract.Model.OnLoadListener, EcosistemaListContract.Model.OnDeleteListener {

    private EcosistemaListContract.Model model;
    private EcosistemaListContract.View view;

    public EcosistemaListPresenter(EcosistemaListContract.View view) {
        this.model = new EcosistemaListModel();
        this.view = view;

    }

    @Override
    public void onDeleteSuccess(String message) {
        view.showMessage(message);
        loadEcosistemas();
    }

    @Override
    public void onDeleteError(String message) {
        view.showError(message);
    }

    @Override
    public void onLoadSuccess(List<Ecosistema> ecosistemas) {
        view.show(ecosistemas);
        view.showMessage("Loaded.");
    }

    @Override
    public void onLoadError(String message) {
        view.showError(message);
    }


    @Override
    public void loadEcosistemas() {
        model.loadEcosistemas(this);

    }

    @Override
    public void deleteEcosistema(long id) {
        model.deleteEcosistema(id, this);
    }
}
