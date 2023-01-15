package maintenanceapp.controller;

import maintenanceapp.model.Datasource;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import javafx.collections.*;
import javafx.beans.value.ObservableValue;
import maintenanceapp.model.MaintenanceType;
import maintenanceapp.model.MaintenanceLog;

public class FXMLDocumentController implements Initializable {
    
    Datasource datasource = new Datasource();
    private String typeArray[] = datasource.getMaintenanceTypesAsArray();
    MaintenanceLog logArray[];
    
    @FXML
    private TextField addTypeField;
    
    @FXML
    private TextField milesPerformedAtField;
    
    @FXML
    private TextField datePerformedOnField;
    
    @FXML
    private ChoiceBox<String> typeChoiceBox;
    
    @FXML
    private TableView<MaintenanceLog> logtable;

    @FXML
    TableColumn<MaintenanceLog,Integer> miles = new TableColumn<MaintenanceLog,Integer>("miles");
    
    @FXML
    TableColumn<MaintenanceLog,Integer> date = new TableColumn<MaintenanceLog,Integer>("date");
    
    @FXML
    private ChoiceBox<String> logChoiceBox;
    
    @FXML
    private ChoiceBox<String> removeChoiceBox;
    
    private String typeChoiceBoxState;
    
    ObservableList<String> types = FXCollections.observableArrayList(datasource.getMaintenanceTypesAsArray());
    
    ObservableList<MaintenanceLog> logbox;

    
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
    public void onViewButtonClicked(ActionEvent event) {
        typeChoiceBoxState = typeChoiceBox.getValue();
        refreshTable();
        logtable.setItems(logbox);
        
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
        
        typeChoiceBoxState = typeChoiceBox.getValue();
        refreshTable();
        logtable.setItems(logbox);


    }
    
    public void loadtable(){
        refreshTable();
        miles.setCellValueFactory(new PropertyValueFactory<MaintenanceLog,Integer>("mileage"));
        date.setCellValueFactory(new PropertyValueFactory<MaintenanceLog,Integer>("date"));
        logtable.setItems(logbox);
    }
    
    @FXML
    private void refreshTable(){
        typeChoiceBoxState = typeChoiceBox.getValue();
        try {
            logbox.clear();
        } catch(NullPointerException e){
            System.out.println("Observable List is empty");
        }

        logbox = FXCollections.observableArrayList(datasource.getLogAsArray(typeChoiceBoxState));
        
    }
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typeChoiceBox.setItems(types);
        typeChoiceBox.setValue(typeArray[0]);
        logChoiceBox.setItems(types);
        logChoiceBox.setValue(typeArray[0]);
        removeChoiceBox.setItems(types);
        removeChoiceBox.setValue(typeArray[0]);
        
        loadtable();
            
    }  
    
    public ObservableList<String> getTypes() {
        return types;
    }
    
    public void setTypes(ObservableList<String> types) {
        this.types=types;
    }
}

