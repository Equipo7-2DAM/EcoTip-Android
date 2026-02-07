package com.svalero.ecotip.contract;

import com.svalero.ecotip.domain.Animal;

import java.util.List;

public interface AnimalListContract {
    interface Model{
        interface OnLoadListener{
            void onLoadSuccess(List<Animal> animales);
            void onLoadError(String message);
        }
        interface OnDeleteListener{
            void onDeleteSuccess(String message);
            void onDeleteError(String message);
        }
        void loadAnimales(OnLoadListener listener);
        void deleteAnimal(long id, OnDeleteListener listener);

    }

    interface View{
        void show(List<Animal> animales);
        void showError(String message);
        void showMessage(String message);

        void onAnimalItemClick(Animal animal);

        void onDeleteClick(Animal animal);
    }

    interface Presenter{
        void loadAnimales();
        void deleteAnimal(long id);

    }
}
