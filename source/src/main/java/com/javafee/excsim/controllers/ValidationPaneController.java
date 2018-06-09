package com.javafee.excsim.controllers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.ResourceBundle;

import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import com.javafee.excsim.utils.Constans;
import com.javafee.excsim.utils.Dialog;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class ValidationPaneController {

	@FXML
	private JFXTextField textFieldName;

	@FXML
	private JFXTextField textFieldJob;

	@FXML
	private TextField textFieldCompany;

	@FXML
	private ComboBox<String> comboBoxCity;

	@FXML
	private CheckBox checkBoxAgreement;

	@FXML
	private ColorPicker colorPicker;

	@FXML
	private DatePicker datePicker;

	public void initialize() {
		setupJFoenix();
		setupControlsFx();
	}

	private void setupJFoenix() {
		RequiredFieldValidator validator = new RequiredFieldValidator();
		Image icon = new Image(getClass().getResourceAsStream("/icon/error-icon.png"));
		validator.setIcon(new ImageView(icon));

		NumberValidator numValidator = new NumberValidator();
		numValidator.setIcon(new ImageView(icon));

		textFieldJob.getValidators().add(numValidator);
		numValidator.setMessage(
				ResourceBundle.getBundle(Constans.LANGUAGE_RESOURCE_BUNDLE).getString("validator.onlyNumberSupported"));

		textFieldJob.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue)
					textFieldJob.validate();
			}
		});

		textFieldName.getValidators().add(validator);
		validator.setMessage(ResourceBundle.getBundle(Constans.LANGUAGE_RESOURCE_BUNDLE).getString("validator.noInputGiven"));

		textFieldName.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue)
					textFieldName.validate();
			}
		});
	}

	@FXML
	public void onClickButtonWarningDialog() {
		Dialog.displayWarningDialog(ResourceBundle.getBundle(Constans.LANGUAGE_RESOURCE_BUNDLE).getString("validator.warningHeader"), ResourceBundle.getBundle(Constans.LANGUAGE_RESOURCE_BUNDLE).getString("validator.warningContent"));
	}
	
	@FXML
	public void onClickButtonErrorDialog() {
		Dialog.displayErrorDialog(ResourceBundle.getBundle(Constans.LANGUAGE_RESOURCE_BUNDLE).getString("validator.errorHeader"), ResourceBundle.getBundle(Constans.LANGUAGE_RESOURCE_BUNDLE).getString("validator.errorContent"));
	}
	
	@FXML
	public void onClickButtonSystemErrorDialog() {
		Dialog.displayExceptionDialog(ResourceBundle.getBundle(Constans.LANGUAGE_RESOURCE_BUNDLE).getString("validator.systemErrorHeader"), ResourceBundle.getBundle(Constans.LANGUAGE_RESOURCE_BUNDLE).getString("validator.systemErrorContent"), ResourceBundle.getBundle(Constans.LANGUAGE_RESOURCE_BUNDLE).getString("validator.systemErrorStackTrace"));
	}

	private void setupControlsFx() {
		// FIXME Problem with ValidationSupport

		ValidationSupport validation = new ValidationSupport();
		validation.registerValidator(textFieldCompany, Validator.createEmptyValidator(
				ResourceBundle.getBundle(Constans.LANGUAGE_RESOURCE_BUNDLE).getString("validator.noInputGiven")));

		ValidationSupport validation_cb = new ValidationSupport();
		comboBoxCity.getItems().addAll(ResourceBundle.getBundle(Constans.LANGUAGE_RESOURCE_BUNDLE).getString("city.warsaw"),
				ResourceBundle.getBundle(Constans.LANGUAGE_RESOURCE_BUNDLE).getString("city.katowice"),
				ResourceBundle.getBundle(Constans.LANGUAGE_RESOURCE_BUNDLE).getString("city.london"),
				ResourceBundle.getBundle(Constans.LANGUAGE_RESOURCE_BUNDLE).getString("city.paris"),
				ResourceBundle.getBundle(Constans.LANGUAGE_RESOURCE_BUNDLE).getString("city.barcelona"));
		validation_cb.registerValidator(comboBoxCity, Validator.createEmptyValidator(
				ResourceBundle.getBundle(Constans.LANGUAGE_RESOURCE_BUNDLE).getString("validator.noInputGiven")));

		ValidationSupport validation_cbx = new ValidationSupport();
		validation_cbx
				.registerValidator(checkBoxAgreement,
						(Control c, Boolean newValue) -> ValidationResult.fromErrorIf(c, ResourceBundle
								.getBundle(Constans.LANGUAGE_RESOURCE_BUNDLE).getString("validator.checkBoxShouldBeChecked"),
								!newValue));

		ValidationSupport validation_cp = new ValidationSupport();
		validation_cp.registerValidator(colorPicker,
				Validator.createEqualsValidator(
						ResourceBundle.getBundle(Constans.LANGUAGE_RESOURCE_BUNDLE).getString("validator.colorBlackOrWhite"),
						Arrays.asList(Color.WHITE, Color.BLACK)));

		ValidationSupport validation_dp = new ValidationSupport();
		validation_dp.registerValidator(datePicker, false,
				(Control c, LocalDate newvalue) -> ValidationResult.fromWarningIf(c,
						ResourceBundle.getBundle(Constans.LANGUAGE_RESOURCE_BUNDLE).getString("validator.dateToday"),
						!LocalDate.now().equals(newvalue)));
	}
}
