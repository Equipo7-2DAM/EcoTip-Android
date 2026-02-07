package com.svalero.ecotip.model;

import com.svalero.ecotip.api.EcoTipApi;
import com.svalero.ecotip.api.EcoTipApiInterface;
import com.svalero.ecotip.contract.DetailEcosistemaContract;
import com.svalero.ecotip.domain.Ecosistema;
import com.svalero.ecotip.domain.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailEcosistemaModel implements DetailEcosistemaContract.Model {
    @Override
    public void loadEcosistema(long id, OnLoadListener listener) {
        EcoTipApiInterface ecoTipApi = EcoTipApi.buildInstance();
        Call<Ecosistema> getEcosistemaCall = ecoTipApi.getEcosistema(id);
        getEcosistemaCall.enqueue(new Callback<Ecosistema>() {
            @Override
            public void onResponse(Call<Ecosistema> call, Response<Ecosistema> response) {
                if (response.code() == 200){
                    Ecosistema ecosistema = response.body();
                    listener.onLoadSuccess(ecosistema);
                } else if (response.code() == 404) {
                    listener.onLoadError("Ecosistema not found");
                } else if(response.code() == 500) {
                    listener.onLoadError("Internal Server error");
                }
            }

            @Override
            public void onFailure(Call<Ecosistema> call, Throwable t) {
                listener.onLoadError("Couldn't connect with the server");
            }
        });
    }

    @Override
    public void deleteEcosistema(long id, OnDeleteListener listener) {
        EcoTipApiInterface ecoTipApi = EcoTipApi.buildInstance();
        Call<Ecosistema> deleteEcosistemaCall = ecoTipApi.deleteEcosistema(id);
        deleteEcosistemaCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Ecosistema> call, Response<Ecosistema> response) {
                if(response.code() == 204){
                    listener.onDeleteSuccess("Ecosystem deleted");
                } else {
                    listener.onDeleteError("Error deleting ecosystem");
                }
            }

            @Override
            public void onFailure(Call<Ecosistema> call, Throwable t) {
                listener.onDeleteError("Couldn't connect with the server");
            }
        });
    }
}
