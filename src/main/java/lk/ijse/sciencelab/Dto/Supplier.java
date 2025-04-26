package lk.ijse.sciencelab.Dto;

public class Supplier {
    private String supplierId;
    private String supplierName;
    private String contact;
    private String equipment;
    private String typeOfSupplier;

    public Supplier() {}

    public Supplier(String supplierId, String supplierName, String contact, String equipment, String typeOfSupplier) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.contact = contact;
        this.equipment = equipment;
        this.typeOfSupplier = typeOfSupplier;
    }

    public String getSupplierId() { return supplierId; }
    public void setSupplierId(String supplierId) { this.supplierId = supplierId; }

    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public String getEquipment() { return equipment; }
    public void setEquipment(String equipment) { this.equipment = equipment; }

    public String getTypeOfSupplier() { return typeOfSupplier; }
    public void setTypeOfSupplier(String typeOfSupplier) { this.typeOfSupplier = typeOfSupplier; }

}