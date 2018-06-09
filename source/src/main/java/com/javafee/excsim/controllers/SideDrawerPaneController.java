package com.javafee.excsim.controllers;

import java.io.IOException;
import java.util.ResourceBundle;

import com.javafee.excsim.utils.Constans;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SideDrawerPaneController {

	@FXML
	public void onClickButtonLexicon() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LexiconPane.fxml"),
				ResourceBundle.getBundle(Constans.LANGUAGE_RESOURCE_BUNDLE));
		Stage stage = new Stage();
		stage.getIcons().add(new Image(getClass().getResourceAsStream(Constans.MAIN_ICON_IMAGE)));
		stage.setTitle(ResourceBundle.getBundle(Constans.LANGUAGE_RESOURCE_BUNDLE).getString("config.appName"));
		stage.setResizable(false);
		try {
			stage.setScene(new Scene((Parent) fxmlLoader.load()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		stage.show();
	}
	
	@FXML
	public void onClickButtonAbout() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AuthorPane.fxml"),
				ResourceBundle.getBundle(Constans.LANGUAGE_RESOURCE_BUNDLE));
		Stage stage = new Stage();
		stage.getIcons().add(new Image(getClass().getResourceAsStream(Constans.MAIN_ICON_IMAGE)));
		stage.setTitle(ResourceBundle.getBundle(Constans.LANGUAGE_RESOURCE_BUNDLE).getString("config.appName"));
		stage.setResizable(false);
		try {
			stage.setScene(new Scene((Parent) fxmlLoader.load()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		stage.show();
	}

	@FXML
	public void onClickButtonExit() {
		Platform.exit();
		System.exit(0);
	}
	
}
