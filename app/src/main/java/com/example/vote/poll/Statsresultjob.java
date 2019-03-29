package com.example.vote.poll;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.lang.*;
import java.text.DecimalFormat;

public class Statsresultjob extends AppCompatActivity {


    TextView results;
    MyDBManager db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statsresultjob);

        results = (TextView) findViewById(R.id.results);

        db = new MyDBManager(this);


        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            results.setText("Selected age range is: "+bundle.getString("Age") + "\n" + "Selected gender is: " + " ");
            results.append(bundle.getString("Gender") + "\n" + "Selected occupation is: " + " ");
            results.append(bundle.getString("Occupation") + "\n");

        }

        db.open();

        int n1 = getGenderANDAgeAndOccupationRows(bundle.getString("Gender"), bundle.getString("Age"),bundle.getString("Occupation"), "Narendra Modi");
        int n2 = getGenderANDAgeAndOccupationRows(bundle.getString("Gender"), bundle.getString("Age"),bundle.getString("Occupation"), "Rahul Gandhi");
        int n3 = getGenderANDAgeAndOccupationRows(bundle.getString("Gender"), bundle.getString("Age"),bundle.getString("Occupation"), "Nitin Gadkari");
        int n4 = getGenderANDAgeAndOccupationRows(bundle.getString("Gender"), bundle.getString("Age"),bundle.getString("Occupation"), "Rajnath Singh");
        int n5 = getGenderANDAgeAndOccupationRows(bundle.getString("Gender"), bundle.getString("Age"),bundle.getString("Occupation"), "Mamata Banerjee");
        int n6 = getGenderANDAgeAndOccupationRows(bundle.getString("Gender"), bundle.getString("Age"),bundle.getString("Occupation"), "Soniya Gandhi");
        int n7 = getGenderANDAgeAndOccupationRows(bundle.getString("Gender"), bundle.getString("Age"),bundle.getString("Occupation"), "Priyanka Gandhi");
        int n8 = getGenderANDAgeAndOccupationRows(bundle.getString("Gender"), bundle.getString("Age"),bundle.getString("Occupation"), "Omkar Panda");

        // Calculate statistics
        int n = n1+n2+n3+n4+n5+n6+n7+n8;
        double p1 = n1;
        p1 = (p1/(n))*100;
        double p2 = n2;
        p2 = (p2/(n)*100);
        double p3 = n3;
        p3 = (p3/(n)*100);
        double p4 = n4;
        p4 = (p4/(n)*100);
        double p5 = n5;
        p5 = (p5/(n)*100);
        double p6 = n6;
        p6 = (p6/(n)*100);
        double p7 = n7;
        p7 = (p7/(n)*100);
        double p8 = n8;
        p8 = (p8/(n)*100);

        // Choose the format of statistics result
        DecimalFormat df = new DecimalFormat("#.##");

        //Get statistics
        results.append("\nThe number of voters in this category is : "+n+"\n\nVotes are broken down as follows:");
        results.append("\n\n"+df.format(p1)+"% will vote for Narendra Modi");
        results.append("\n\n"+df.format(p2)+"% will vote for Rahul Gandhi");
        results.append("\n\n"+df.format(p3)+"% will vote for Nitin Gadkari");
        results.append("\n\n"+df.format(p4)+"% will vote for Rajnath Singh");
        results.append("\n\n"+df.format(p5)+"% will vote for Mamata Banerjee");
        results.append("\n\n"+df.format(p6)+"% will vote for Soniya Gandhi");
        results.append("\n\n"+df.format(p7)+"% will vote for Priyanka Gandhi");
        results.append("\n\n"+df.format(p8)+"% will vote for Omkar Panda");

        db.close();
    }

    public int getGenderANDAgeAndOccupationRows(String Gender, String Age, String Occupation, String Candidate) {
        Cursor c = db.getPollbyGenderAndAgeAndOccupation(Gender, Age, Occupation, Candidate);
        int n=0;
        if (c.moveToFirst()) {
            do {
                n++;
            }
            while (c.moveToNext());
        }
        return n;
    }

}





