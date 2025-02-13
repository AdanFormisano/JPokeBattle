package com.example.jpokebattle.gui.data;

import com.example.jpokebattle.gui.DynamicViewUIState;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class DynamicViewModel {
    private final ObjectProperty<DynamicViewUIState> currentViewUIState =
            new SimpleObjectProperty<>(new DynamicViewUIState(DynamicViewStatus.BATTLE, null)) ;

    public ObjectProperty<DynamicViewUIState> currentViewUIStateProperty() { return currentViewUIState; }
    public DynamicViewUIState getUIState() { return currentViewUIState.get(); }
    public void setUIState(DynamicViewUIState viewState) { currentViewUIState.set(viewState); }
}
