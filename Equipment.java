import java.util.HashMap;
import java.util.Map;

public class Equipment {
    private int eid;
    private String ename;
    private String brand;
    private int totalQuantity;
    private int availableQuantity;

    public Equipment(int eid, String ename, String brand, int totalQuantity, int availableQuantity) {
        this.eid = eid;
        this.ename = ename;
        this.brand = brand;
        this.totalQuantity = totalQuantity;
        this.availableQuantity = availableQuantity;
    }

    public void addEquipment(int quantity) {
        this.totalQuantity += quantity;
        this.availableQuantity += quantity;
    }

    public void deleteEquipment(int quantity) {
        if (this.availableQuantity >= quantity) {
            this.availableQuantity -= quantity;
        } else {
            System.out.println("Insufficient quantity");
        }
    }

    public void printAvailableEquipment() {
        System.out.println("Equipment ID: " + this.eid);
        System.out.println("Equipment Name: " + this.ename);
        System.out.println("Brand: " + this.brand);
        System.out.println("Total Quantity: " + this.totalQuantity);
        System.out.println("Available Quantity: " + this.availableQuantity);
    }

    // Getters and Setters
    public int getEid() {
        return eid;
    }

    public String getEname() {
        return ename;
    }

    public String getBrand() {
        return brand;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    private static Map<Integer, Equipment> equipmentMap = new HashMap<>();

    public static void addEquipmentToMap(Equipment equipment) {
        equipmentMap.put(equipment.eid, equipment);
    }

    public static Equipment getEquipmentById(int eid) {
        return equipmentMap.get(eid);
    }
}
