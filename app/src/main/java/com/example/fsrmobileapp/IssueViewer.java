package com.example.fsrmobileapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class IssueViewer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_viewer);
        TextView textView = (TextView)findViewById(R.id.tvIssueNum);
        Bundle b = getIntent().getExtras();
        if(b!=null){
            textView.setText(b.getCharSequence("issueNumber"));
        }
    }

    public void redirectHome(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
