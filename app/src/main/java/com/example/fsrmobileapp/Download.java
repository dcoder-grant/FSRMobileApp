package com.example.fsrmobileapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fsrmobileapp.json.IssueJSONUtil;
import com.example.fsrmobileapp.json.IssueJavaObject;
import com.example.fsrmobileapp.json.JSONStorageManager;
import com.example.fsrmobileapp.json.TestIssueJSON;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Download extends AppCompatActivity {
    private static String TAG="DownloadActivity";
    private Context thisContext;
    private View view;
    private static String fileName="localJSON.json";


    public String multiLineSyncMessage = "This is a multiline message from the Download.java class."
            +"\nPlease make sure you have a proper internet connection to this device."
            +"\nOnce a download occurs the changes made on this device will be overwritten with the previous database records."
            +"\nIf you are not ready to download then click the \"Home\" button ";

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        TextView tv = (TextView) findViewById(R.id.tvSyncWarning);
        tv.setText(multiLineSyncMessage);

        //Make context accessible to ASyncTask
        setDownloadContext(this.getApplicationContext());

        Button btnDownload = (Button) findViewById(R.id.btnDownload);
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new IssueListDownloader().execute();            }
        });
    }

    public void redirectHome(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void setDownloadContext(Context context){
        this.thisContext = context;
    }

    //Create separate thread to process the JSON objects for the list
    public class IssueListDownloader extends AsyncTask<Void, Void, Void> {
        public void onPreExecute(){
        }
        //This is the actual work that will be done in the separate thread
        @Override
        protected Void doInBackground(Void...voids) {
            Log.d(TAG, "Download.doinbackground:Starting.");

            JSONStorageManager storageManager = new JSONStorageManager();
            //Check if local storage file exists and create it if it doesn't exist
            //TODO: remove fake data creation from  createFile method called in getStorageFileExists method
            if(storageManager.getStorageFileExists(thisContext)) {
                try {
                    FileOutputStream fos = thisContext.openFileOutput(fileName, Context.MODE_PRIVATE);
                    //Create JSONs and print the string value in File

                    //TODO: Replace this fake data with service call for JSONs
                    //Create test data java object issues
                    List<IssueJavaObject> issueList = new TestIssueJSON().createIssues(3);
                    //Process each JSON Object in issueList
                    JSONArray issueJSONArray = new JSONArray();
                    for (int i = 0; i < issueList.size(); i++) {
                        //Create JSON of each issue
                        //Add JSON issue to top level JSONArray of isssues
                        issueJSONArray.put(IssueJSONUtil.toJSONIssue(issueList.get(i)));
                    }
                    //Write string to file
                    fos.write(issueJSONArray.toString().getBytes());
                    fos.close();
                } catch (FileNotFoundException fileNotFound) {
                    return null;
                } catch (IOException ioException) {
                    return null;
                }
            }
            return null;
        }

        //This is the method that can update the UI based on publishProgress from inside doInBackground method
        protected void onProgressUpdate(Void...voids){
            //TODO: add updates based on the size of the issue list
        }

        protected void  onPostExecute(Void result){
            Log.d(TAG, "Download.onPostExectue:Starting.");
            //Turn array of arrays into table and post on view
            TextView lastUpdatedString = (TextView)findViewById(R.id.tvLastSyncTime);
            Date todaysDate = Calendar.getInstance().getTime();
            lastUpdatedString.setText(todaysDate.toString());
            Log.d(TAG, "Download.onPostExectue:Ending.");
        }
    }
}
