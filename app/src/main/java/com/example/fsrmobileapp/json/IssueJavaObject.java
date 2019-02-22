package com.example.fsrmobileapp.json;

import java.util.List;

public class IssueJavaObject {
    private int issueNumber;
    private String issueType;
    private String issueSite;
    private String issueStatus;
    private User issueAssignedUser;
    private List<User> issueWatcherList;

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getIssueSite() {
        return issueSite;
    }

    public void setIssueSite(String issueSite) {
        this.issueSite = issueSite;
    }

    public String getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus;
    }

    public User getIssueAssignedUser() {
        return issueAssignedUser;
    }

    public void setIssueAssignedUser(User issueAssignedUser) {
        this.issueAssignedUser = issueAssignedUser;
    }

    public List<User> getIssueWatcherList() {
        return issueWatcherList;
    }

    public void setIssueWatcherList(List<User> issueWatcherList) {
        this.issueWatcherList = issueWatcherList;
    }

}
