package com.javafee.excsim.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class Dialog {

	public static void displayWarningDialog(String header, String content) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(SystemProperties.getResourceBundle().getString("dialog.warningDialogHeader"));
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}

	public static void displayErrorDialog(String header, String content) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(SystemProperties.getResourceBundle().getString("dialog.errorDialogTitle"));
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}

	public static void displayExceptionDialog(String header, String content, String exceptionText) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(SystemProperties.getResourceBundle().getString("dialog.exceptionDialogTitle"));
		alert.setHeaderText(header);
		alert.setContentText(content);

		Label label = new Label(SystemProperties.getResourceBundle().getString("dialog.exceptionDialogLabel"));

		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		alert.getDialogPane().setExpandableContent(expContent);
		alert.showAndWait();
	}

}
