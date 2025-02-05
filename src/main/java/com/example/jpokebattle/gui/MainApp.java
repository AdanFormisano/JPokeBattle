package com.example.jpokebattle.gui;

import com.example.jpokebattle.service.session.GuiSession;
import com.example.jpokebattle.service.session.NoGuiSession;
import com.example.jpokebattle.service.session.SessionData;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        GuiSession guiSession = new GuiSession(primaryStage);
        guiSession.startSession();
    }
}