package com.example.jpokebattle.gui;

import com.example.jpokebattle.gui.data.IDynamicViewData;
import com.example.jpokebattle.gui.data.DynamicViewStatus;

public class DynamicViewUIState {
    private final DynamicViewStatus dynamicViewStatus;
    private final IDynamicViewData dynamicViewData;

    public DynamicViewUIState(DynamicViewStatus dynamicViewStatus, IDynamicViewData dynamicViewData) {
        this.dynamicViewStatus = dynamicViewStatus;
        this.dynamicViewData = dynamicViewData;
    }

    public DynamicViewStatus getStatus() { return dynamicViewStatus; }
    public IDynamicViewData getData() { return dynamicViewData; }
}
