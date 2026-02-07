package com.svalero.ecotip.contract;


import com.svalero.ecotip.domain.Usuario;

import java.util.List;

public interface UsuarioListContract {
    interface Model{
        interface OnLoadListener{
            void onLoadSuccess(List<Usuario> usuarios);
            void onLoadError(String message);
        }
        interface OnDeleteListener{
            void onDeleteSuccess(String message);
            void onDeleteError(String message);
        }
        void loadUsuarios(OnLoadListener listener);
        void deleteUsuario(long id, OnDeleteListener listener);

    }

    interface View{
        void show(List<Usuario> usuarios);
        void showError(String message);
        void showMessage(String message);

        void onUsuarioItemClick(Usuario usuario);

        void onDeleteClick(Usuario usuario);
    }

    interface Presenter{
        void loadUsuarios();
        void deleteUsuario(long id);

    }
}