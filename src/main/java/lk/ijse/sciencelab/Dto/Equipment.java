package lk.ijse.sciencelab.Dto;

public class Equipment {
    private String equipmentId;
    private String equipmentName;
    private int quantity;
    private String type;
    private String supplierId;

    public Equipment() {}

    public Equipment(String equipmentId, String equipmentName, int quantity, String type, String supplierId) {
        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
        this.quantity = quantity;
        this.type = type;
        this.supplierId = supplierId;
    }

    public String getEquipmentId() { return equipmentId; }
    public void setEquipmentId(String equipmentId) { this.equipmentId = equipmentId; }

    public String getEquipmentName() { return equipmentName; }
    public void setEquipmentName(String equipmentName) { this.equipmentName = equipmentName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getSupplierId() { return supplierId; }
    public void setSupplierId(String supplierId) { this.supplierId = supplierId; }

}