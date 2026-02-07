package com.svalero.ecotip.model;

import com.svalero.ecotip.api.EcoTipApi;
import com.svalero.ecotip.api.EcoTipApiInterface;
import com.svalero.ecotip.contract.UsuarioListContract;
import com.svalero.ecotip.domain.Ecosistema;
import com.svalero.ecotip.domain.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioListModel implements UsuarioListContract.Model {
    @Override
    public void loadUsuarios(OnLoadListener listener) {
        EcoTipApiInterface ecotipApi = EcoTipApi.buildInstance();
        Call<List<Usuario>> getUsuariosCall = ecotipApi.getUsuarios();

        getUsuariosCall.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if(response.code() == 200) {
                    List<Usuario> usuarios = response.body();
                    listener.onLoadSuccess(usuarios);
                } else if(response.code() == 500) {
                    listener.onLoadError("Internal Server error");
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                    listener.onLoadError("Internal Server error");
            }
        });
    }

    @Override
    public void deleteUsuario(long id, OnDeleteListener listener) {
        EcoTipApiInterface ecotipApi = EcoTipApi.buildInstance();
        Call<Usuario> deleteUsuarioCall = ecotipApi.deleteUsuario(id);
        deleteUsuarioCall.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.code() == 204){
                    listener.onDeleteSuccess("User deleted succesfully");
                } else {
                    listener.onDeleteError("Could not delete");
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                    listener.onDeleteError("Couldn't connect to the server");
            }
        });
    }
}
