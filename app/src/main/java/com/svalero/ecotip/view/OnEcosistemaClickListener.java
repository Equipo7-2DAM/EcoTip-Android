package com.svalero.ecotip.view;

import com.svalero.ecotip.domain.Animal;
import com.svalero.ecotip.domain.Ecosistema;

public interface OnEcosistemaClickListener {
    void onEcosistemaClick(Ecosistema ecosistema);
    void onDeleteClick(Ecosistema ecosistema);
}
