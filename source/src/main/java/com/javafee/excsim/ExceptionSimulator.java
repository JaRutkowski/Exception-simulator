package com.javafee.excsim;

import java.util.ResourceBundle;

import com.javafee.excsim.utils.Constans;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ExceptionSimulator extends Application {

	public static void main(final String[] args) {
		Application.launch(args);
	}

	private Parent rootNode;

	@Override
	public void init() throws Exception {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Constans.MAIN_SPLASH_SCREEN_IMAGE),
				ResourceBundle.getBundle(Constans.LANGUAGE_RESOURCE_BUNDLE));
		rootNode = fxmlLoader.load();

		FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), rootNode);
		fadeOut.setFromValue(0.5);
		fadeOut.setToValue(1);
		fadeOut.setCycleCount(1);
		fadeOut.play();
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.getIcons().add(new Image(getClass().getResourceAsStream(Constans.MAIN_ICON_IMAGE)));
		stage.setTitle(ResourceBundle.getBundle(Constans.LANGUAGE_RESOURCE_BUNDLE).getString("config.appName"));
		stage.setScene(new Scene(rootNode));
		stage.show();
	}

}
