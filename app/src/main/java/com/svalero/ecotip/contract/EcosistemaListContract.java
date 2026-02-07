package com.svalero.ecotip.contract;


import com.svalero.ecotip.domain.Animal;
import com.svalero.ecotip.domain.Ecosistema;

import java.util.List;

public interface EcosistemaListContract {
    interface Model{
        interface OnLoadListener{
            void onLoadSuccess(List<Ecosistema> ecosistemas);
            void onLoadError(String message);
        }
        interface OnDeleteListener{
            void onDeleteSuccess(String message);
            void onDeleteError(String message);
        }
        void loadEcosistemas(OnLoadListener listener);
        void deleteEcosistema(long id, OnDeleteListener listener);

    }

    interface View{
        void show(List<Ecosistema> ecosistemas);
        void showError(String message);
        void showMessage(String message);

        void onEcosistemaItemClick(Ecosistema ecosistema);

        void onDeleteClick(Ecosistema ecosistema);
    }

    interface Presenter{
        void loadEcosistemas();
        void deleteEcosistema(long id);

    }
}
