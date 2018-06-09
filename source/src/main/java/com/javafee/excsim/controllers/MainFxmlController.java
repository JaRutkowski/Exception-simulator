package com.javafee.excsim.controllers;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;

import com.javafee.excsim.utils.Constans;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lombok.extern.java.Log;

@Log
public class MainFxmlController {

	@FXML
	private TabPane tabPane;
	
	@FXML
	private JFXDrawer jfxDrawerMenu;

	@FXML
	private JFXHamburger jfxHamburger;
	
	@FXML
	public void initialize() {
        try {
            VBox box = FXMLLoader.load(getClass().getResource("/fxml/SideDrawerPane.fxml"),
    				ResourceBundle.getBundle(Constans.LANGUAGE_RESOURCE_BUNDLE));
            jfxDrawerMenu.setSidePane(box);
        } catch (IOException e) {
            log.log(Level.SEVERE, "Error while loading side drawer pane",  e);
        }	
		
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(jfxHamburger);
        transition.setRate(-1);
        jfxHamburger.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{
            transition.setRate(transition.getRate()*-1);
            transition.play();
            
            if(jfxDrawerMenu.isOpened())
            {
            	jfxDrawerMenu.close();
            }else {
            	jfxDrawerMenu.open();
            	tabPane.toBack();
            }
        });
	}
	
}
