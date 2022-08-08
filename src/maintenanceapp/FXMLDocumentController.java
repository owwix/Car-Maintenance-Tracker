/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package maintenanceapp;

import maintenanceapp.model.Datasource;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;

/**
 *
 * @author alexanderokonkwo
 */
public class FXMLDocumentController implements Initializable {
    
    Datasource datasource = new Datasource();
    
    @FXML
    private TextField addTypeField;
    
    @FXML
    private ChoiceBox<String> typeChoiceBox;
    
    @FXML
    private ChoiceBox<String> logChoiceBox;
    
    @FXML
    private ChoiceBox<String> removeChoiceBox;
    
    @FXML
    public void onAddButtonClicked(ActionEvent event) {
        String field = addTypeField.getText();
        datasource.updateTypes(field);
    }
    
    @FXML
    public void onRemoveButtonClicked(ActionEvent event) {
        String field = removeChoiceBox.getValue();
        datasource.removeTypes(field);
        removeChoiceBox.getItems().removeAll();
        removeChoiceBox.getItems().addAll(typeArray);
    }
    
    private String typeArray[] = datasource.getMaintenanceTypesAsArray();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        typeChoiceBox.getItems().addAll(typeArray);
        typeChoiceBox.setValue(typeArray[0]);
        logChoiceBox.getItems().addAll(typeArray);
        logChoiceBox.setValue(typeArray[0]);
        removeChoiceBox.getItems().addAll(typeArray);
        removeChoiceBox.setValue(typeArray[0]);
    }    
    
}
