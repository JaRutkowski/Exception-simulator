package com.javafee.excsim.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;

import com.javafee.excsim.utils.Constans;
import com.javafee.excsim.utils.Enum;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ExceptionPaneController {

	@FXML
	private TextArea textAreaCode;

	@FXML
	private TextArea textAreaException;

	@FXML
	private TextArea textAreaConsole;

	@FXML
	private ComboBox<String> comboBoxExceptionKind;

	@FXML
	private Button buttonGenerateException;

	@FXML
	private Button buttonHandleException;

	@FXML
	private Text textComment;

	@FXML
	public void initialize() {
		comboBoxExceptionKind.getItems().addAll(Enum.ExceptionKind.NULL_POINTER_EXC.getValue(),
				Enum.ExceptionKind.IO_EXC.getValue(), Enum.ExceptionKind.OUT_OF_BOUNDS.getValue(),
				Enum.ExceptionKind.ARITHMETIC_EXC.getValue(), Enum.ExceptionKind.STR_OUT_OF_BOUNDS.getValue(),
				Enum.ExceptionKind.NUMBER_FORMAT_EXC.getValue(), Enum.ExceptionKind.MY_LOW_BALANCE_EXC.getValue());
	}

	@FXML
	public void onClickButtonGenerateException() {
		if (comboBoxExceptionKind.getSelectionModel().getSelectedItem() != null)
			fillTextAreas(comboBoxExceptionKind.getSelectionModel().getSelectedItem(), false);
	}

	@FXML
	public void onClickButtonHandleException() {
		if (comboBoxExceptionKind.getSelectionModel().getSelectedItem() != null)
			fillTextAreas(comboBoxExceptionKind.getSelectionModel().getSelectedItem(), true);
	}

	@FXML
	public void onClickButtonClear() {
		clearTextAreas();
	}

	private void fillTextAreas(String exceptionKind, boolean handle) {
		String comment;
		switch (Enum.ExceptionKind.getByName(exceptionKind)) {
		case NULL_POINTER_EXC:
			fillControls(handle, "exceptions/nullPointerExCode.txt", null, false);
			break;
		case IO_EXC:
			comment = ResourceBundle.getBundle(Constans.LANGUAGE_RESOURCE_BUNDLE)
					.getString("exceptions.messageThrowsPure");
			fillControls(handle, "exceptions/ioExc.txt", comment, true);
			break;
		case OUT_OF_BOUNDS:
			fillControls(handle, "exceptions/outOfBoundsExc.txt", null, false);
			break;
		case ARITHMETIC_EXC:
			fillControls(handle, "exceptions/arithmeticExc.txt", null, false);
			break;
		case STR_OUT_OF_BOUNDS:
			fillControls(handle, "exceptions/stringIndexOutOfBoundsExc.txt", null, false);
			break;
		case NUMBER_FORMAT_EXC:
			fillControls(handle, "exceptions/numberFormatExc.txt", null, false);
			break;
		case MY_LOW_BALANCE_EXC:
			comment = ResourceBundle.getBundle(Constans.LANGUAGE_RESOURCE_BUNDLE).getString("exceptions.messageOwnClass")
					+ "\n\n"
					+ ResourceBundle.getBundle(Constans.LANGUAGE_RESOURCE_BUNDLE).getString("exceptions.messageThrows");
			fillControls(handle, "exceptions/myLowBalanceExc.txt", comment, true);
			break;
		default:
			break;

		}
	}

	private void fillControls(boolean handle, String excFilePath, String comment, boolean fillEcvForHandle) {
		try {
			textComment.setVisible(comment != null);
			textComment.setText(comment);

			String[] data = FileUtils
					.readFileToString(new File(getClass().getClassLoader().getResource(excFilePath).getFile()),
							Charset.forName(Constans.FILE_ENCODING))
					.split("----");

			clearTextAreas();

			if (!handle) {
				fillTextAreaCode(data[0]);
				if (fillEcvForHandle)
					fillTextAreaException(data[3]);
				fillTextAreaConsole(data[1]);
			} else {
				fillTextAreaCode(data[2]);
				fillTextAreaException(data[3]);
				fillTextAreaConsole(data[4]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void clearTextAreas() {
		textAreaCode.setText("");
		textAreaException.setText("");
		textAreaConsole.setText("");
	}

	private void fillTextAreaCode(String data) {
		Text text = new Text(data);
		text.setFont(Font.font("Consolas", 15));
		textAreaCode.setText(data);
	}

	private void fillTextAreaException(String data) {
		Text text = new Text(data);
		text.setFont(Font.font("Consolas", 15));
		textAreaException.setText(data);
	}

	private void fillTextAreaConsole(String data) {
		Text text = new Text(data);
		text.setFont(Font.font("Consolas", 15));
		textAreaConsole.setText(data);
	}
}
