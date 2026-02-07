package com.svalero.ecotip.view;

import com.svalero.ecotip.domain.Animal;
import com.svalero.ecotip.domain.Usuario;

public interface OnUsuarioClickListener {
    void onUsuarioClick(Usuario usuario);
    void onDeleteClick(Usuario usuario);
}
