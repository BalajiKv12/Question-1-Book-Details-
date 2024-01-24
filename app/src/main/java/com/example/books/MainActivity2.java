package com.example.books;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity implements recyclerInterface {
    RecyclerView recyclerView;
    CardView cardView;

    //creating arraylist of books
    ArrayList<books> book = new ArrayList<>();

    //creating an instance of Books_RecyclerAdapter
    Books_RecyclerAdapter adapter = new Books_RecyclerAdapter(this,book,this);
    @SuppressLint({"Range", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //getting the intent from first activity
        Intent u = getIntent();
        //matching the variable with recyclerView by finding its id
        recyclerView = findViewById(R.id.recycleView);
        // creating a cursor object of the
        // content URI
        Cursor cursor = getContentResolver().query(MyContentProvider.CONTENT_URI,
                null, null, null, null);

        // iteration of the cursor
        // to print whole table

        if(cursor.moveToFirst()) {
            String i,t,a,y;
            while (!cursor.isAfterLast()) {
                i =cursor.getString(cursor.getColumnIndex(MyContentProvider.id));
                t = cursor.getString(cursor.getColumnIndex(MyContentProvider.title));
                a = cursor.getString(cursor.getColumnIndex(MyContentProvider.Author));
                y = cursor.getString(cursor.getColumnIndex(MyContentProvider.year));
                book.add(new books(i,t,a,y,R.drawable.book));
                cursor.moveToNext();
            }

            //setting adapter with recyclerView
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

        }
    }

    //overriding a method from recyclerInterface
    @Override
    public void onItemClick(books book,int position) {

        //On clicking the cardLayout a new activity is created to display the details of the view
        Intent details = new Intent(MainActivity2.this, MainActivity3.class);
        details.putExtra("Title", book.getName());
        details.putExtra("Id",book.getId());
        details.putExtra("Author",book.getAuthor());
        details.putExtra("Year",book.getYear());
        String p = Integer.toString(position);
        details.putExtra("position",p);
        //launching third activity
        getResult.launch(details);
    }

    //ActivityResult is a method which gets the result when the third activity finishes and makes changes in second activity
    ActivityResultLauncher<Intent> getResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            //if result code is 4 then the result comes from update button which is in third activity
            if(result.getResultCode()==4)
            {
                //getting the intent and updated details of the book
                Intent intent = result.getData();
                String p = intent.getStringExtra("position");
                int position = Integer.parseInt(p);
                String bTitle = intent.getStringExtra("title");
                String bId = intent.getStringExtra("id");
                String bAuthor = intent.getStringExtra("author");
                String bYear = intent.getStringExtra("year");

                //adding new details of that book in same position
                book.set(position,new books(bId,bTitle,bAuthor,bYear,R.drawable.book));
                Log.i("UPDATE","uPDATED");
                //notifying the adapter for some changes done
                adapter.notifyItemChanged(position);
            }
            //if result code is 5 then the result comes from delete button which is in third activity
            else if (result.getResultCode()==5) {
                //getting the intent and deleting the book details from the arraylist
                Intent intent = result.getData();
                String p = intent.getStringExtra("position");
                int position = Integer.parseInt(p);
                //removing the book
                book.remove(position);
                //notifying the adapter for deleting the book
                adapter.notifyItemRemoved(position);
            }
        }
    });
}