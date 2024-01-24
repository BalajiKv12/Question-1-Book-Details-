package com.example.books;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Books_RecyclerAdapter extends RecyclerView.Adapter<Books_RecyclerAdapter.MyViewHolder> {

    Context context;
    //creating arraylist of book objects
    ArrayList<books> book = new ArrayList<>();
    private recyclerInterface recyclerInterface;

    //creating constructor for adapter
    public Books_RecyclerAdapter(Context context, ArrayList<books> book, recyclerInterface recyclerInterface) {
        this.context = context;
        this.book = book;
        this.recyclerInterface=recyclerInterface;
    }

    @NonNull
    @Override
    //below method used to inflate the view when user scrolls up/down to show the details which coundn't fit the screen

    public Books_RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cardlayout,parent,false);
        return new Books_RecyclerAdapter.MyViewHolder(view);
    }

    @Override
    @SuppressLint("RecyclerView")
    //onBindViewHolder method for setting details in view for all books one by one by using their position
    public void onBindViewHolder(@NonNull Books_RecyclerAdapter.MyViewHolder holder,  int position) {

        //setting the details of the books to show
        holder.title.setText(book.get(position).getName());
        holder.id.setText(book.get(position).getId());
        holder.Author.setText(book.get(position).getAuthor());
        holder.year.setText(book.get(position).getYear());
        holder.image.setImageResource(book.get(position).getImage());

        //below method is for setting on click response of the view
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerInterface.onItemClick(book.get(position),position);
            }
        });
    }

    //getItemcount method return the size of the items to be displayed
    @Override
    public int getItemCount() {
        return book.size();
    }


    //MyViewHolder method for setting the view for recycle view
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView id,title,Author,year;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //assigning the views by finding their respective id
            image = itemView.findViewById(R.id.bookImage);
            id = itemView.findViewById(R.id.bookID);
            title = itemView.findViewById(R.id.bookTitle);
            Author = itemView.findViewById(R.id.bookAuthor);
            year = itemView.findViewById(R.id.bookYear);
            cardView=itemView.findViewById(R.id.Cardlayout);

        }
    }
}
