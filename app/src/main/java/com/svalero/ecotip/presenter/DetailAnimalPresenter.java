package com.svalero.ecotip.presenter;

import com.svalero.ecotip.contract.DetailAnimalContract;
import com.svalero.ecotip.domain.Animal;
import com.svalero.ecotip.domain.Ecosistema;
import com.svalero.ecotip.domain.Usuario;
import com.svalero.ecotip.model.DetailAnimalModel;

import java.util.List;

public class DetailAnimalPresenter implements DetailAnimalContract.Presenter, DetailAnimalContract.Model.OnDeleteListener, DetailAnimalContract.Model.OnLoadListener {
    private DetailAnimalContract.Model model;
    private DetailAnimalContract.View view;

    public DetailAnimalPresenter(DetailAnimalContract.View view){
        this.view = view;
        this.model = new DetailAnimalModel();
    }

    @Override
    public void onLoadSuccess(Animal animal) {
        Ecosistema eco = animal.getEcosistema();
        List<Usuario> usu = animal.getUsuarios();
        if(usu != null){
            view.showUsuario(usu);
        }else {
            view.showUsuario(null);
        }
        if (eco != null) {
            view.showEcosistema(eco);
        } else {
            view.showEcosistema(null);
        }
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
