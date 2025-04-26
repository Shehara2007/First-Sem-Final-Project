package lk.ijse.sciencelab.Dto;

public class Funder {
    private String funderId;
    private String funderName;
    private double amount;
    private String project;
    private String organisation;

    public Funder() {}

    public Funder(String funderId, String funderName, double amount, String project, String organisation) {
        this.funderId = funderId;
        this.funderName = funderName;
        this.amount = amount;
        this.project = project;
        this.organisation = organisation;
    }

    public String getFunderId() { return funderId; }
    public void setFunderId(String funderId) { this.funderId = funderId; }

    public String getFunderName() { return funderName; }
    public void setFunderName(String funderName) { this.funderName = funderName; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getProject() { return project; }
    public void setProject(String project) { this.project = project; }

    public String getOrganisation() { return organisation; }
    public void setOrganisation(String organisation) { this.organisation = organisation; }

}