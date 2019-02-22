package com.example.fsrmobileapp.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class IssueJSONUtil {

    private IssueJavaObject issueJavaObject;

    public static JSONObject toJSONIssue(IssueJavaObject issueJavaObject) {
        try{
            JSONObject issueObj = new JSONObject();
            issueObj.put("Number", issueJavaObject.getIssueNumber());
            issueObj.put("Type", issueJavaObject.getIssueType());
            issueObj.put("Site", issueJavaObject.getIssueSite());
            issueObj.put("Status", issueJavaObject.getIssueStatus());

            JSONObject userObject = new JSONObject();
            userObject.put("AssignedUsername", issueJavaObject.getIssueAssignedUser().getUsername());
            userObject.put("AssignedEmail", issueJavaObject.getIssueAssignedUser().getEmail());
            issueObj.put("Assigned", userObject);

            JSONArray watchersArray = new JSONArray();
            for (User user: issueJavaObject.getIssueWatcherList()){
                JSONObject watcherObject = new JSONObject();
                watcherObject.put("Username", user.getUsername());
                watcherObject.put("Email", user.getEmail());
                watchersArray.put(watcherObject);
            }
            issueObj.put("Watchers", watchersArray);
            return issueObj;
        }
        catch(JSONException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static ArrayList<String> getIssueDetails(JSONObject issueJSON, ArrayList<String> issueDetailsList){
        //get watchers sub object
        String watcherString =new String();
        JSONArray watchersArray = new JSONArray();
        try {
            watchersArray = issueJSON.getJSONArray("Watchers");

            for (int x = 0; x < watchersArray.length(); x++) {
                JSONObject watcherUser = watchersArray.getJSONObject(x);
                if (!(watcherString == null)) {
                    watcherString += "," + watcherUser.get("Username").toString();
                } else {
                    watcherString = watcherUser.get("Username").toString();
                }
            }

        //get assigned sub object
        JSONObject assignedObj = new JSONObject();
        assignedObj=issueJSON.getJSONObject("Assigned");

        //Parse info from issue into the list of strings for the issue
        issueDetailsList.add(issueJSON.get("Number").toString());
        issueDetailsList.add(issueJSON.get("Site").toString());
        issueDetailsList.add(issueJSON.get("Type").toString());
        issueDetailsList.add(assignedObj.get("AssignedUsername").toString());
        issueDetailsList.add(issueJSON.get("Status").toString());
        issueDetailsList.add(watcherString);
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return issueDetailsList;
    }
}
