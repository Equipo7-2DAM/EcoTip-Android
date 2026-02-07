package com.svalero.ecotip.model;

import com.svalero.ecotip.api.EcoTipApi;
import com.svalero.ecotip.api.EcoTipApiInterface;
import com.svalero.ecotip.contract.AnimalListContract;
import com.svalero.ecotip.domain.Animal;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnimalListModel implements AnimalListContract.Model {
    @Override
    public void loadAnimales(OnLoadListener listener) {
        EcoTipApiInterface ecotipApi = EcoTipApi.buildInstance();
        Call<List<Animal>> getAnimalesCall = ecotipApi.getAnimales();

        getAnimalesCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Animal>> call, Response<List<Animal>> response) {
                if(response.code() == 200) {
                    List<Animal> animales = response.body();
                    listener.onLoadSuccess(animales);
                } else if(response.code() == 500) {
                    listener.onLoadError("Internal Server error");
                }
            }

            @Override
            public void onFailure(Call<List<Animal>> call, Throwable t) {
                listener.onLoadError("Couldn't connect to the server");
            }
        });
    }

    @Override
    public void deleteAnimal(long id, OnDeleteListener listener) {
        EcoTipApiInterface ecotipApi = EcoTipApi.buildInstance();
        Call<Animal> deleteAnimalCall = ecotipApi.deleteAnimal(id);
        deleteAnimalCall.enqueue(new Callback<Animal>() {
            @Override
            public void onResponse(Call<Animal> call, Response<Animal> response) {
                if (response.code() == 204){
                    listener.onDeleteSuccess("Animal deleted succesfully");
                } else {
                    listener.onDeleteError("Could not delete");
                }
            }

            @Override
            public void onFailure(Call<Animal> call, Throwable t) {
                listener.onDeleteError("Couldn't connect to the server");
            }
        });
    }
}
