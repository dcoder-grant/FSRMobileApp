package com.example.fsrmobileapp.json;

import java.util.ArrayList;
import java.util.List;

public class TestIssueJSON {

    public List<IssueJavaObject> createIssues(int issueCount) {
        List<IssueJavaObject> issueList = new ArrayList<>();
        for (int i = 0; i < issueCount; i++) {
            IssueJavaObject issueJavaObject = new IssueJavaObject();
            issueJavaObject.setIssueNumber(i);
            issueJavaObject.setIssueSite("Site"+i);
            issueJavaObject.setIssueType("Type"+i);
            if ((i%2)==0) {
                issueJavaObject.setIssueStatus("Open");
            }else {
                issueJavaObject.setIssueStatus("Closed");
            }
            issueJavaObject.setIssueWatcherList(createWatchList(3));
            issueJavaObject.setIssueAssignedUser(createUser(i));
            issueList.add(issueJavaObject);
        }
        return issueList;
    }
    public List<User> createWatchList (int watcherCount){
        List <User> userWatchList = new ArrayList();
        for (int i=0; i<watcherCount; i++){
            User createdUser = this.createUser(i);
            userWatchList.add(createdUser);
        }
        return userWatchList;
    }

    public User createUser(int i){
        User createdUser = new User();
        createdUser.setUsername("User"+i);
        createdUser.setEmail("Email"+i);
    return createdUser;
    }

}
