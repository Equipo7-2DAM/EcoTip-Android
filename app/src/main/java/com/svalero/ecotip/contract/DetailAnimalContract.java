package com.svalero.ecotip.contract;

import com.svalero.ecotip.domain.Animal;

public interface DetailAnimalContract {

    interface Model {
        interface OnLoadListener{
            void onLoadSuccess(Animal animal);
            void onLoadError(String message);
        }
        interface OnDeleteListener{
            void onDeleteSuccess(String message);
            void onDeleteError(String message);
        }
        void loadAnimal(long id, DetailAnimalContract.Model.OnLoadListener listener);
        void deleteAnimal(long id, DetailAnimalContract.Model.OnDeleteListener listener);
    }

    interface View {
        void showAnimal(Animal animal);
        void showError(String message);
        void showMessage(String message);
        void onAnimalDeleted();
    }
    interface Presenter {
        void loadAnimal(long id);
        void deleteAnimal(long id);
    }

}
