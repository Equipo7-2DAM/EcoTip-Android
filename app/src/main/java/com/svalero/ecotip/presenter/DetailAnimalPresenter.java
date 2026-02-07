package com.svalero.ecotip.presenter;

import com.svalero.ecotip.contract.DetailAnimalContract;
import com.svalero.ecotip.domain.Animal;
import com.svalero.ecotip.model.DetailAnimalModel;

public class DetailAnimalPresenter implements DetailAnimalContract.Presenter, DetailAnimalContract.Model.OnDeleteListener, DetailAnimalContract.Model.OnLoadListener {
    private DetailAnimalContract.Model model;
    private DetailAnimalContract.View view;

    public DetailAnimalPresenter(DetailAnimalContract.View view){
        this.view = view;
        this.model = new DetailAnimalModel();
    }

    @Override
    public void onLoadSuccess(Animal animal) {
        view.showAnimal(animal);
        view.showMessage("Loaded.");
    }

    @Override
    public void onLoadError(String message) {
        view.showError(message);
    }

    @Override
    public void onDeleteSuccess(String message) {
        view.showMessage(message);
        view.onAnimalDeleted();
    }

    @Override
    public void onDeleteError(String message) {
        view.showError(message);
    }

    @Override
    public void loadAnimal(long id) {
        model.loadAnimal(id,this);
    }

    @Override
    public void deleteAnimal(long id) {
        model.deleteAnimal(id,this);
    }
}
