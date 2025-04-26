package lk.ijse.sciencelab.Dto;

public class Progress {
    private String progressId;
    private String projectId;
    private String status;
    private String lastUpdatedDate;

    public Progress() {}

    public Progress(String progressId, String projectId, String status, String lastUpdatedDate) {
        this.progressId = progressId;
        this.projectId = projectId;
        this.status = status;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getProgressId() { return progressId; }
    public void setProgressId(String progressId) { this.progressId = progressId; }

    public String getProjectId() { return projectId; }
    public void setProjectId(String projectId) { this.projectId = projectId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getLastUpdatedDate() { return lastUpdatedDate; }
    public void setLastUpdatedDate(String lastUpdatedDate) { this.lastUpdatedDate = lastUpdatedDate; }

}