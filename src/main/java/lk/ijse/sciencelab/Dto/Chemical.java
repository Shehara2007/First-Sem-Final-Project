package lk.ijse.sciencelab.Dto;

public class Chemical {
    private String chemicalId;
    private String chemicalName;
    private int quantity;
    private String concentration;
    private String supplierId;

    public Chemical() {}

    public Chemical(String chemicalId, String chemicalName, int quantity, String concentration, String supplierId) {
        this.chemicalId = chemicalId;
        this.chemicalName = chemicalName;
        this.quantity = quantity;
        this.concentration = concentration;
        this.supplierId = supplierId;
    }

    public String getChemicalId() { return chemicalId; }
    public void setChemicalId(String chemicalId) { this.chemicalId = chemicalId; }

    public String getChemicalName() { return chemicalName; }
    public void setChemicalName(String chemicalName) { this.chemicalName = chemicalName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getConcentration() { return concentration; }
    public void setConcentration(String concentration) { this.concentration = concentration; }

    public String getSupplierId() { return supplierId; }
    public void setSupplierId(String supplierId) { this.supplierId = supplierId; }

}