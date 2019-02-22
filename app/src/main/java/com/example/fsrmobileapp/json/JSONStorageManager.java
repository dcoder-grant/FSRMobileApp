package com.example.fsrmobileapp.json;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JSONStorageManager {
    private static String fileName="localJSON.json";

    public ArrayList<ArrayList<String>> readFile(Context context, ArrayList<ArrayList<String>> listOfLists) {
        try {
            //Reading the file and placing into a stringBuilder
            FileInputStream fileInputStream = context.openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            try {
                //Turning that string into a JSONArray of objects
                JSONArray jsonArrayOfIssues =new JSONArray(stringBuilder.toString());

                //Turning JSONArray of objects into list of issue detail lists
                for(int i=0; i<jsonArrayOfIssues.length();i++){
                    //Create list of details of each object
                    ArrayList<String> issueDetailsList = new ArrayList<>();
                    //Get issue details from JSON of issue
                    //Details order in list is issue#,Site,Type,Assigned,Status,Watchers
                    issueDetailsList = IssueJSONUtil.getIssueDetails(jsonArrayOfIssues.getJSONObject(i), issueDetailsList);
                    listOfLists.add(issueDetailsList);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return listOfLists;
        } catch (FileNotFoundException fileNotFound) {
            return null;
        } catch (IOException ioException) {
            return null;
        }
    }

    //Can write the given string to the file or can just create a file if null string param
    public boolean createFile(Context context, String jsonString){
        try {
            //openFileOutput method creates file if it doesn't exist
            FileOutputStream fos = context.openFileOutput(fileName,Context.MODE_PRIVATE);
            if (jsonString != null) {
                fos.write(jsonString.getBytes());
            }
            fos.close();
            return true;
        } catch (FileNotFoundException fileNotFound) {
            return false;
        } catch (IOException ioException) {
            return false;
        }
    }

    //Checks if a file exists and creates it if it doesnt exist
    public boolean getStorageFileExists(Context context){
        //Note that this method references the file from the root menu while the fileStreams use the application storage location
        File file =new File("/data/data/com.example.fsrmobileapp/files/localJSON.json");
        if(file.exists()){
            return true;
        }else{
            return createFile(context, null);
        }
    }
}
