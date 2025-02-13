package com.example.jpokebattle.gui;

import com.example.jpokebattle.gui.data.DynamicViewData;
import com.example.jpokebattle.gui.data.DynamicViewStatus;

public class DynamicViewUIState {
    private final DynamicViewStatus dynamicViewStatus;
    private final DynamicViewData dynamicViewData;

    public DynamicViewUIState(DynamicViewStatus dynamicViewStatus, DynamicViewData dynamicViewData) {
        this.dynamicViewStatus = dynamicViewStatus;
        this.dynamicViewData = dynamicViewData;
    }

    public DynamicViewStatus getStatus() { return dynamicViewStatus; }
    public DynamicViewData getData() { return dynamicViewData; }
}
