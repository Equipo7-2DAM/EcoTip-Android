package com.svalero.ecotip.model;

import com.svalero.ecotip.api.EcoTipApi;
import com.svalero.ecotip.api.EcoTipApiInterface;
import com.svalero.ecotip.contract.AnimalListContract;
import com.svalero.ecotip.contract.EcosistemaListContract;
import com.svalero.ecotip.domain.Animal;
import com.svalero.ecotip.domain.Ecosistema;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EcosistemaListModel implements EcosistemaListContract.Model {
    @Override
    public void loadEcosistemas(OnLoadListener listener) {
        EcoTipApiInterface ecotipApi = EcoTipApi.buildInstance();
        Call<List<Ecosistema>> getEcosistemasCall = ecotipApi.getEcosistemas();

        getEcosistemasCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Ecosistema>> call, Response<List<Ecosistema>> response) {
                if(response.code() == 200) {
                    List<Ecosistema> ecosistemas = response.body();
                    listener.onLoadSuccess(ecosistemas);
                } else if(response.code() == 500) {
                    listener.onLoadError("Internal Server error");
                }
            }
            @Override
            public void onFailure(Call<List<Ecosistema>> call, Throwable t) {
                listener.onLoadError("Internal Server error");
            }
        });
    }

    @Override
    public void deleteEcosistema(long id, OnDeleteListener listener) {
        EcoTipApiInterface ecotipApi = EcoTipApi.buildInstance();
        Call<Ecosistema> deleteEcosistemaCall = ecotipApi.deleteEcosistema(id);
        deleteEcosistemaCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Ecosistema> call, Response<Ecosistema> response) {
                if (response.code() == 204){
                    listener.onDeleteSuccess("Ecosystem deleted succesfully");
                } else {
                    listener.onDeleteError("Could not delete");
                }
            }

            @Override
            public void onFailure(Call<Ecosistema> call, Throwable t) {
                    listener.onDeleteError("Couldn't connect to the server");
            }
        });
    }


}
