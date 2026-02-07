package com.svalero.ecotip.presenter;

import com.svalero.ecotip.contract.DetailAnimalContract;
import com.svalero.ecotip.contract.DetailEcosistemaContract;
import com.svalero.ecotip.domain.Animal;
import com.svalero.ecotip.domain.Ecosistema;
import com.svalero.ecotip.model.DetailAnimalModel;
import com.svalero.ecotip.model.DetailEcosistemaModel;

public class DetailEcosistemaPresenter implements DetailEcosistemaContract.Presenter, DetailEcosistemaContract.Model.OnDeleteListener, DetailEcosistemaContract.Model.OnLoadListener {
    private DetailEcosistemaContract.Model model;
    private DetailEcosistemaContract.View view;

    public DetailEcosistemaPresenter(DetailEcosistemaContract.View view){
        this.view = view;
        this.model = new DetailEcosistemaModel();
    }

    @Override
    public void onLoadSuccess(Ecosistema ecosistema) {
        view.showEcosistema(ecosistema);
        view.showMessage("Loaded.");
    }

    @Override
    public void onLoadError(String message) {
        view.showError(message);
    }

    @Override
    public void onDeleteSuccess(String message) {
        view.showMessage(message);
        view.onEcosistemaDeleted();
    }

    @Override
    public void onDeleteError(String message) {
        view.showError(message);
    }


    @Override
    public void loadEcosistema(long id) {
        model.loadEcosistema(id, this);
    }

    @Override
    public void deleteEcosistema(long id) {
        model.deleteEcosistema(id, this);
    }
}
