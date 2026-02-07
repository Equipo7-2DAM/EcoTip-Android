package com.svalero.ecotip.model;

import com.svalero.ecotip.api.EcoTipApi;
import com.svalero.ecotip.api.EcoTipApiInterface;
import com.svalero.ecotip.contract.DetailAnimalContract;
import com.svalero.ecotip.domain.Animal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailAnimalModel implements DetailAnimalContract.Model {
    @Override
    public void loadAnimal(long id, OnLoadListener listener) {
        EcoTipApiInterface ecoTipApi = EcoTipApi.buildInstance();
        Call<Animal> getAnimalCall = ecoTipApi.getAnimal(id);
        getAnimalCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Animal> call, Response<Animal> response) {
                if (response.code() == 200){
                    Animal animal = response.body();
                    listener.onLoadSuccess(animal);
                } else if (response.code() == 404) {
                    listener.onLoadError("Animal not found");
                } else if(response.code() == 500) {
                    listener.onLoadError("Internal Server error");
                }
            }

            @Override
            public void onFailure(Call<Animal> call, Throwable t) {
                    listener.onLoadError("Couldn't connect with the server");
            }
        });
    }

    @Override
    public void deleteAnimal(long id, OnDeleteListener listener) {
        EcoTipApiInterface ecoTipApi = EcoTipApi.buildInstance();
        Call<Animal> deleteAnimalCall = ecoTipApi.deleteAnimal(id);
        deleteAnimalCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Animal> call, Response<Animal> response) {
                if(response.code() == 204){
                    listener.onDeleteSuccess("Animal deleted");
                } else {
                    listener.onDeleteError("Error deleting ecosystem");
                }
            }

            @Override
            public void onFailure(Call<Animal> call, Throwable t) {
                    listener.onDeleteError("Couldn't connect with the server");
            }
        });
    }
}
