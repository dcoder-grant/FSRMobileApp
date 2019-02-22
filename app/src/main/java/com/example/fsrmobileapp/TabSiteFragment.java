package com.example.fsrmobileapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.fsrmobileapp.json.IssueJavaObject;
import com.example.fsrmobileapp.json.IssueJSONUtil;
import com.example.fsrmobileapp.json.JSONStorageManager;
import com.example.fsrmobileapp.json.TestIssueJSON;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class TabSiteFragment extends Fragment {
    private static final String TAG = "TabSiteFragment";
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "SiteFrag.onCreate:Starting.");
        view = inflater.inflate(R.layout.fragment_tab_site,container,false);

        //Runs JSON loading in separate thread
        new UpdatedSiteList().execute();

        Log.d(TAG, "SiteFrag.onCreate:Ending.");
        return view;
    }

    //Starts new activity of viewing Issue Details page
    public void viewIssueDetails(String issueNumber) {
        Intent intent = new Intent(this.getContext(), IssueViewer.class);
        Bundle b = new Bundle();
        b.putCharSequence("issueNumber", issueNumber);
        intent.putExtras(b);
        startActivity(intent);
    }

    //Create separate thread to process the JSON objects for the list
    public class UpdatedSiteList extends AsyncTask<Void, Void, ArrayList<ArrayList<String>>> {
        public void onPreExecute(){
        }

        //This is the actual work that will be done in the separate thread
        @Override
        protected ArrayList<ArrayList<String>> doInBackground(Void... voids) {
            Log.d(TAG, "SiteFrag.doinbackground:Starting.");

            //Create List of ArrayList of Strings for list of details for each issue
            ArrayList<ArrayList<String>> listOfLists = new ArrayList<>();

            JSONStorageManager storageManager = new JSONStorageManager();
            //Check if local storage file exists and create it if it doesn't exist
            //TODO: remove fake data creation from  createFile method called in getStorageFileExists method
            if(storageManager.getStorageFileExists(getContext())){
                listOfLists=storageManager.readFile(getContext(), listOfLists);
            }
            return listOfLists;
        }

        //This is the method that can update the UI based on publishProgress from inside doInBackground method
        protected void onProgressUpdate(Void...voids){
        //TODO: add updates based on the size of the issue list
        }

        protected void  onPostExecute(ArrayList<ArrayList<String>> listOfLists){
            Log.d(TAG, "SiteFrag.onPostExectue:Starting.");
            //Turn array of arrays into table and post on view
            TableLayout siteTableLayout = (TableLayout)view.findViewById(R.id.tableSites);
            if (listOfLists.size()==0){
                TableRow issueRow = new TableRow(getContext());
                TextView textView = new TextView(getContext());
                textView.setText("List is empty. \nPlease click Download.");
                issueRow.addView(textView);
                //Add row to table
                siteTableLayout.addView(issueRow);
            }else {
                //Convert List of issue details lists into table rows
                for (final ArrayList<String> x : listOfLists) {
                    TableRow issueRow = new TableRow(getContext());
                    issueRow = updateTableRow(x, issueRow);

                    //Add event listener to row
                    issueRow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //gets the String for Issue# and sends to method for viewing new intent
                            viewIssueDetails(x.get(0) );
                        }
                    });

                    //Add row to table
                    siteTableLayout.addView(issueRow);
                }
            }
            Log.d(TAG, "SiteFrag.onPostExectue:Ending.");
        }
    }

    public TableRow updateTableRow(ArrayList<String> listOfDetails, TableRow issueRow){
        //Iterate through details of each issue but skip adding watchers for table
        //Details order in list is issue#,Site,Type,Assigned,Status,Watchers
        for(int i=0; i<(listOfDetails.size()-1);i++){
            //Parse text from detail to textview
            TextView textViewTableCell = new TextView(getContext());
            textViewTableCell.setText(listOfDetails.get(i));

            //Format each textview
            textViewTableCell.setBackgroundColor(Color.WHITE);
            textViewTableCell.setPadding(2,2,2,2);
            //textViewTableCell.set

            //Add textviews to rows and format
            issueRow.addView(textViewTableCell);
            issueRow.setBackgroundColor(Color.BLACK);
            issueRow.setPadding(2,2,2,2);
            //issueRow.set
        }
        return issueRow;
    }
}
