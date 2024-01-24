package com.example.books;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //creating variables for edittext and button
    EditText title,id,author,year;
    Button load,upload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //finding the id of editText and button
        // assigning it to variables
        title=findViewById(R.id.bookName);
        id=findViewById(R.id.id);
        author=findViewById(R.id.Author);
        year=findViewById(R.id.year);
        load=findViewById(R.id.Load);
        upload=findViewById(R.id.Upload);

        //setting onClickListener for upload button
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getting the values from the editText and storing it as string
                String eTitle = title.getText().toString();
                String eId = id.getText().toString();
                String eAuthor=author.getText().toString();
                String eYear = year.getText().toString();

                //checking whether all fields are filled or not
                //if not then toast message will pop up
                if(eTitle.isEmpty() || eId.isEmpty() || eAuthor.isEmpty() || eYear.isEmpty())
                {
                    android.widget.Toast.makeText(getApplicationContext(), "Please Enter All Fields", Toast.LENGTH_LONG).show();
                }
                else
                {
                    // class to add values in the database
                    ContentValues values = new ContentValues();

                    // fetching text from user
                    values.put(MyContentProvider.id,eId);
                    values.put(MyContentProvider.title,eTitle);
                    values.put(MyContentProvider.Author,eAuthor);
                    values.put(MyContentProvider.year,eYear);

                    // inserting into database through content URI
                    getContentResolver().insert(MyContentProvider.CONTENT_URI, values);

                    // displaying a toast message
                    android.widget.Toast.makeText(getApplicationContext(), "Books details updated", Toast.LENGTH_LONG).show();

                    //resetting the edittext
                    title.setText("");
                    id.setText("");
                    author.setText("");
                    year.setText("");
                }
            }
        });

        //setting onClickListener for load button
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //creating new intent and starting second activity
                Intent up = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(up);
            }
        });

    }
}