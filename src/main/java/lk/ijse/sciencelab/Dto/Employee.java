package lk.ijse.sciencelab.Dto;

public class Employee {
    private String employeeId;
    private String role;
    private String employeeName;
    private String contact;
    private String groupId;

    public Employee() {}

    public Employee(String employeeId, String role, String employeeName, String contact, String groupId) {
        this.employeeId = employeeId;
        this.role = role;
        this.employeeName = employeeName;
        this.contact = contact;
        this.groupId = groupId;
    }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public String getGroupId() { return groupId; }
    public void setGroupId(String groupId) { this.groupId = groupId; }

}