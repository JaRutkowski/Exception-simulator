package com.javafee.excsim.controllers;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;

import com.javafee.excsim.utils.Constans;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.extern.java.Log;

@Log
public class SplashScreenPaneController {

	class SplashScreenSleeper extends Thread {

		@Override
		public void run() {
			try {
				Thread.sleep(2000);

				javafx.application.Platform.runLater(new Runnable() {

					@Override
					public void run() {
						try {
							FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"),
									ResourceBundle.getBundle(Constans.LANGUAGE_RESOURCE_BUNDLE));
							Parent rootNode = null;
							rootNode = fxmlLoader.load();
							Stage stage = new Stage();
							stage.getIcons().add(new Image(getClass().getResourceAsStream(Constans.MAIN_ICON_IMAGE)));
							stage.setTitle(ResourceBundle.getBundle(Constans.LANGUAGE_RESOURCE_BUNDLE).getString("config.appName"));
							stage.setScene(new Scene(rootNode));
							stage.show();

							stackPaneSplashScreenPane.getScene().getWindow().hide();
						} catch (IOException e) {
							log.log(Level.SEVERE, "Error while loading resources Main.fxml", e);
						}
					}
				});

			} catch (InterruptedException e) {
				log.log(Level.SEVERE, "Error while loading Splash Screen", e);
			}
		}

	}

	@FXML
	private StackPane stackPaneSplashScreenPane;

	@FXML
	public void initialize() {
		new SplashScreenSleeper().start();
	}
}
