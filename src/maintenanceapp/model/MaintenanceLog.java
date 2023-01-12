package maintenanceapp.model;

public class MaintenanceLog {
    
    private String id;
    private Integer mileage;
    private Integer date;
    
    public MaintenanceLog(){
        
    }
    
    public MaintenanceLog(String id, int mileage, int date){
        this.id = id;
        this.mileage = mileage;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
    
}
