package com.svalero.ecotip.contract;

import com.svalero.ecotip.domain.Ecosistema;

public interface DetailEcosistemaContract {

    interface Model {
        interface OnLoadListener{
            void onLoadSuccess(Ecosistema ecosistema);
            void onLoadError(String message);
        }
        interface OnDeleteListener{
            void onDeleteSuccess(String message);
            void onDeleteError(String message);
        }
        void loadEcosistema(long id, DetailEcosistemaContract.Model.OnLoadListener listener);
        void deleteEcosistema(long id, DetailEcosistemaContract.Model.OnDeleteListener listener);
    }

    interface View {
        void showEcosistema(Ecosistema ecosistema);
        void showError(String message);
        void showMessage(String message);
        void onEcosistemaDeleted();
    }
    interface Presenter {
        void loadEcosistema(long id);
        void deleteEcosistema(long id);
    }

}
