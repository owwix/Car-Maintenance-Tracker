package maintenanceapp.controller;

import maintenanceapp.model.Datasource;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.*;
import maintenanceapp.model.MaintenanceLog;

public class FXMLDocumentController implements Initializable {
    
    Datasource datasource = new Datasource();
    final private String typeArray[] = datasource.getMaintenanceTypesAsArray();
    
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
    TableColumn<MaintenanceLog,String> date = new TableColumn<MaintenanceLog,String>("date");
    
    @FXML
    private ChoiceBox<String> logChoiceBox;
    
    @FXML
    private ChoiceBox<String> removeChoiceBox;
    
    private String typeChoiceBoxState;
    
    ObservableList<String> types = FXCollections.observableArrayList(datasource.getMaintenanceTypesAsArray());
    
    ObservableList<MaintenanceLog> logbox;
    
//    @FXML
//    void confirmLogDialog(ActionEvent event) {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Confirmation");
//        alert.setContentText("Are you sure you want to log this?");
//        Optional<ButtonType> result = alert.showAndWait();
//        
//    }

    
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
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Are you sure you want to log this?");
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK) {
            
            String milesField = milesPerformedAtField.getText();
            String dateField = datePerformedOnField.getText();
            String selectedID = logChoiceBox.getValue();
            MaintenanceLog log = new MaintenanceLog();
            log.setId(selectedID);
            log.setDate(dateField);
            log.setMileage(Integer.parseInt(milesField));
            datasource.addToLog(log);

            typeChoiceBoxState = typeChoiceBox.getValue();
            refreshTable();
            logtable.setItems(logbox);
            
        }
    }
    
    public void loadtable(){
        refreshTable();
        miles.setCellValueFactory(new PropertyValueFactory<MaintenanceLog,Integer>("mileage"));
        date.setCellValueFactory(new PropertyValueFactory<MaintenanceLog,String>("date"));
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
    
}