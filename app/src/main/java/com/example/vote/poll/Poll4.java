package com.example.vote.poll;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Poll4 extends AppCompatActivity {

    ListView list;
    String itemvalue;
    Button submit;
    TextView text;


    String[] Candidates = {"Narendra Modi", "Rahul Gandhi", "Nitin Gadkari",
            "Rajnath Singh", "Mamata Banerjee", "Soniya Gandhi", "Priyanka Gandhi", "Omkar Panda"};

    String[] Election_Party = {"Bharatiya Janata Party", "Indian National Congress", "Bharatiya Janata Party", "Bharatiya Janata Party", "All India Trinamool Congress", "Indian National Congress", "Labour", "Independent"};

   /* the string is where to store data when we select something */
    String db_vote;
    String db_gender;
    String db_age;
    String db_marital;
    String db_children;
    String db_occupation;
    String db_income;
    String db_party;
    String db_comment;
    String db_candidates;

    MyDBManager db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poll4);
        db = new MyDBManager(this);


        list = (ListView) findViewById(R.id.cand);
        submit = (Button) findViewById(R.id.submit);
        text= (TextView) findViewById(R.id.Q7);



        list.setAdapter(new MyCustomAdapter(Poll4.this, R.layout.row, Candidates, Election_Party));


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemvalue = list.getItemAtPosition(position).toString();

                Toast.makeText(getApplicationContext(), itemvalue,
                        Toast.LENGTH_LONG).show();
                        db_candidates=itemvalue;
            }
        });

        Bundle bundle = getIntent().getExtras();
        db_vote = (bundle.getString("Vote"));
        db_gender = (bundle.getString("Gender"));
        db_age = (bundle.getString("Age"));
        db_marital = (bundle.getString("Marital"));
        db_children =(bundle.getString("Children"));
        db_occupation = (bundle.getString("Occupation"));
        db_income = (bundle.getString("Income"));
        db_party = (bundle.getString("Party"));
        db_comment = (bundle.getString("Comment"));
        db_candidates = (bundle.getString("Candidates"));

        // Click and submit poll
        submit.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){


                db.open();

                db.insertPoll(db_vote, db_gender, db_age, db_marital, db_children, db_occupation, db_income, db_party, db_comment, db_candidates, itemvalue);
                db.close();

                /* put finish() to go back to the main screen*/
                finish();

            }
        });
    }

    public class MyCustomAdapter extends ArrayAdapter<String> {

        String[] values;
        String[] names;

        // Creating our own adaptor to be able to customise each row
        public MyCustomAdapter(Context context, int textViewResourceId,
                               String[] objects, String[] results)
        {
            super(context, textViewResourceId, objects);
            names=objects;
            values=results;
        }



        // This getview method is called each time a row needs to be formatted for the list
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View row =convertView;
            if(row == null){
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row, parent, false);
            }

            TextView label=(TextView)row.findViewById(R.id.cand2);
            label.setText(names[position]);
            ImageView icon=(ImageView)row.findViewById(R.id.icon1);
            TextView icon2=(TextView)row.findViewById(R.id.cand3);



            if (Candidates[position]=="Narendra Modi"){
                icon.setImageResource(R.drawable.nm);
                icon2.setText(values[position]);
            }

            else if (Candidates[position]== "Rahul Gandhi")
            {
                icon.setImageResource(R.drawable.rg);
                icon2.setText(values[position]);
            }else if (Candidates[position]== "Nitin Gadkari")
            {
                icon.setImageResource(R.drawable.ng);
                icon2.setText(values[position]);
            }
            else if (Candidates[position]==  "Rajnath Singh")
            {
                icon.setImageResource(R.drawable.rs);
                icon2.setText(values[position]);
            }
            else if (Candidates[position]== "Mamata Banerjee")
            {
                icon.setImageResource(R.drawable.mb);
                icon2.setText(values[position]);
            }
            else if (Candidates[position]==  "Soniya Gandhi")
            {
                icon.setImageResource(R.drawable.sg);
                icon2.setText(values[position]);
            }
            else if (Candidates[position]==   "Priyanka Gandhi")
            {
                icon.setImageResource(R.drawable.pg);
                icon2.setText(values[position]);
            }
            else if (Candidates[position]==  "Omkar Panda")
            {
                icon.setImageResource(R.drawable.op);
                icon2.setText(values[position]);
            }


            return row;
        }




    }
}
