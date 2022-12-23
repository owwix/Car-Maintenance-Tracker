/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package maintenanceapp.controller;

import maintenanceapp.model.Datasource;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.collections.*;
import javafx.beans.value.ObservableValue;
import maintenanceapp.model.MaintenanceType;
import maintenanceapp.model.MaintenanceLog;

/**
 *
 * @author alexanderokonkwo
 */
public class FXMLDocumentController implements Initializable {
    
    Datasource datasource = new Datasource();
    
    @FXML
    private TextField addTypeField;
    
    @FXML
    private TextField milesPerformedAtField;
    
    @FXML
    private TextField datePerformedOnField;
    
    @FXML
    private ChoiceBox<String> typeChoiceBox;
    
    @FXML
    private TableView<?> logtable;

    @FXML
    private TableColumn<?, ?> miles;

    @FXML
    private TableColumn<?, ?> date;
    
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
    
    @FXML
    public void onLogButtonClicked(ActionEvent event) {
        String milesField = milesPerformedAtField.getText();
        String dateField = datePerformedOnField.getText();
        String selectedID = logChoiceBox.getValue();
        MaintenanceLog log = new MaintenanceLog();
        log.setId(selectedID);
        log.setDate(Integer.parseInt(dateField));
        log.setMileage(Integer.parseInt(milesField));
        datasource.addToLog(log);
        


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
        typeChoiceBox.getSelectionModel().selectedIndexProperty().addListener(
            (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                System.out.println(old_val);
      });
    }  
    
    public ObservableList<String> getTypes() {
        return types;
    }
    
    public void setTypes(ObservableList<String> types) {
        this.types=types;
    }
}
