package com.svalero.ecotip.presenter;

import com.svalero.ecotip.contract.AnimalListContract;
import com.svalero.ecotip.domain.Animal;
import com.svalero.ecotip.model.AnimalListModel;

import java.util.List;

public class AnimalListPresenter implements AnimalListContract.Presenter, AnimalListContract.Model.OnLoadListener, AnimalListContract.Model.OnDeleteListener {

    private AnimalListContract.Model model;
    private AnimalListContract.View view;
    public AnimalListPresenter(AnimalListContract.View view){
        this.model = new AnimalListModel();
        this.view = view;

    }

    @Override
    public void onDeleteSuccess(String message) {
        view.showMessage(message);
        loadAnimales();
    }

    @Override
    public void onDeleteError(String message) {
        view.showError(message);
    }

    @Override
    public void onLoadSuccess(List<Animal> animales) {
        view.show(animales);
        view.showMessage("Loaded.");
    }

    @Override
    public void onLoadError(String message) {
        view.showError(message);
    }


    @Override
    public void loadAnimales() {
        model.loadAnimales(this);
    }

    @Override
    public void deleteAnimal(long id) {
        model.deleteAnimal(id, this);
    }
}
