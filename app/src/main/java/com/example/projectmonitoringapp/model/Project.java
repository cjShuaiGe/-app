package com.example.projectmonitoringapp.model;

public class Project {
     private String projectId;
     private String projectName;
     private String projectDesc;
     private String projectUrl;
     private String status;
     private String unsealDate;
     private String registerDate;

     public String getRegisterDate() {
          return registerDate;
     }

     public void setRegisterDate(String registerDate) {
          this.registerDate = registerDate;
     }

     public String getProjectId() {
          return projectId;
     }

     public void setProjectId(String projectId) {
          this.projectId = projectId;
     }

     public String getProjectName() {
          return projectName;
     }

     public void setProjectName(String projectName) {
          this.projectName = projectName;
     }

     public String getProjectDesc() {
          return projectDesc;
     }

     public void setProjectDesc(String projectDesc) {
          this.projectDesc = projectDesc;
     }

     public String getProjectUrl() {
          return projectUrl;
     }

     public void setProjectUrl(String projectUrl) {
          this.projectUrl = projectUrl;
     }

     public String getStatus() {
          return status;
     }

     public void setStatus(String status) {
          this.status = status;
     }

     public String getUnsealDate() {
          return unsealDate;
     }

     public void setUnsealDate(String unsealDate) {
          this.unsealDate = unsealDate;
     }
}
