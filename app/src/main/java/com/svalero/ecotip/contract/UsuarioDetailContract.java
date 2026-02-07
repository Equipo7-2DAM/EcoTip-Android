package com.svalero.ecotip.contract;

import com.svalero.ecotip.domain.Animal;
import com.svalero.ecotip.domain.Usuario;

import java.util.List;

public interface UsuarioDetailContract {
    interface Model {
        interface OnLoadListener{
            void onLoadSuccess(Usuario usuario);
            void onLoadError(String message);
        }
        interface OnDeleteListener{
            void onDeleteSuccess(String message);
            void onDeleteError(String message);
        }
        void loadUsuario(long id, UsuarioDetailContract.Model.OnLoadListener listener);
        void deleteUsuario(long id, UsuarioDetailContract.Model.OnDeleteListener listener);
    }

    interface View {
        void showUsuario(Usuario usuario);
        void showError(String message);
        void showMessage(String message);
        void onUsuarioDeleted();

        void showAnimales(List<Animal> ani);
    }
    interface Presenter {
        void loadUsuario(long id);
        void deleteUsuario(long id);
    }
}
