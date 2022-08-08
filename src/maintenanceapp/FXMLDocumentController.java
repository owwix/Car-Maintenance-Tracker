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
import javafx.collections.*;

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
    
    ObservableList<String> types = FXCollections.observableArrayList(datasource.getMaintenanceTypesAsArray());
    
    
    @FXML
    public void onAddButtonClicked(ActionEvent event) {
        String field = addTypeField.getText();
        datasource.updateTypes(field);
        if (field.equals("") || types.contains(field)) {
            return;
        }
        
        types.add(field);
    }
    
    @FXML
    public void onRemoveButtonClicked(ActionEvent event) {
        String field = removeChoiceBox.getValue();
        datasource.removeTypes(field);
        if (field.equals("")) {
            return;
        }
        
        types.remove(field);
    }
    
    private String typeArray[] = datasource.getMaintenanceTypesAsArray();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        typeChoiceBox.setItems(types);
        typeChoiceBox.setValue(typeArray[0]);
        logChoiceBox.setItems(types);
        logChoiceBox.setValue(typeArray[0]);
        removeChoiceBox.setItems(types);
        removeChoiceBox.setValue(typeArray[0]);
    }  
    
    public ObservableList<String> getTypes() {
        return types;
    }
    
    public void setTypes(ObservableList<String> types) {
        this.types=types;
    }
}
