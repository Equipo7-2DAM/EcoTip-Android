package com.svalero.ecotip.model;

import com.svalero.ecotip.api.EcoTipApi;
import com.svalero.ecotip.api.EcoTipApiInterface;
import com.svalero.ecotip.contract.UsuarioDetailContract;
import com.svalero.ecotip.domain.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioDetailModel implements UsuarioDetailContract.Model {
    @Override
    public void loadUsuario(long id, OnLoadListener listener) {
        EcoTipApiInterface ecoTipApi = EcoTipApi.buildInstance();
        Call<Usuario> getUsuarioCall = ecoTipApi.getUsuario(id);
        getUsuarioCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.code() == 200){
                    Usuario usuario = response.body();
                    listener.onLoadSuccess(usuario);
                } else if (response.code() == 404) {
                    listener.onLoadError("User not found");
                } else if(response.code() == 500) {
                    listener.onLoadError("Internal Server error");
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                    listener.onLoadError("Couldn't connect with the server");
            }
        });
    }

    @Override
    public void deleteUsuario(long id, OnDeleteListener listener) {
        EcoTipApiInterface ecoTipApi = EcoTipApi.buildInstance();
        Call<Usuario> deleteUsuarioCall = ecoTipApi.deleteUsuario(id);
        deleteUsuarioCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.code() == 204){
                    listener.onDeleteSuccess("User deleted");
                } else {
                    listener.onDeleteError("Error deleting user");
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                    listener.onDeleteError("Couldn't connect with the server");
            }
        });
    }
}
