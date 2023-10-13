package com.jnu.student;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> addBookLauncher;
    private ActivityResultLauncher<Intent> modifyBookLauncher;
    public static final int ADD_BOOK_REQUEST = 1;
    public static final int MODIFY_BOOK_REQUEST = 2;

    public class RecycleViewBookAdapter extends RecyclerView.Adapter<RecycleViewBookAdapter.BookViewHolder> {
        private List<Book> bookList;

        public RecycleViewBookAdapter(List<Book> bookList) {
            this.bookList = bookList;
        }

        @NonNull
        @Override
        public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
            return new BookViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
            Book book = bookList.get(position);
            if (book.getCoverResId() != 0) {
                holder.imageViewCover.setImageResource(book.getCoverResId());
            } else {
                holder.imageViewCover.setImageResource(book.getCoverResIdOriginal());
                holder.imageViewCover.setVisibility(View.VISIBLE); // Add this line of code
            }
            holder.textViewTitle.setText(book.getTitle());
        }

        @Override
        public int getItemCount() {
            return bookList.size();
        }

        class BookViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
            ImageView imageViewCover;
            TextView textViewTitle;

            public BookViewHolder(@NonNull View itemView) {
                super(itemView);
                imageViewCover = itemView.findViewById(R.id.image_view_book_cover);
                textViewTitle = itemView.findViewById(R.id.text_view_book_title);
                itemView.setOnCreateContextMenuListener(this);
            }

            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.setHeaderTitle("Options");
                menu.add(0, getAdapterPosition(), 0, "Add");
                menu.add(0, getAdapterPosition(), 0, "Modify");
                menu.add(0, getAdapterPosition(), 0, "Delete");
            }
        }
    }

    public class Book {
        private int coverResId;
        private String title;
        private int coverResIdOriginal; // New field

        public Book(int coverResId, String title) {
            this.coverResId = coverResId;
            this.coverResIdOriginal = coverResId; // Initialize original cover resource id
            this.title = title;
        }

        public int getCoverResId() {
            return coverResId;
        }

        public int getCoverResIdOriginal() {
            return coverResIdOriginal;
        }

        public void setCoverResId(int coverResId) {
            this.coverResId = coverResId;
        }

        public void setCoverResIdOriginal(int coverResIdOriginal) {
            this.coverResIdOriginal = coverResIdOriginal;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    RecyclerView recyclerViewBooks;
    List<Book> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        addBookLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            String title = data.getStringExtra("title");
                            int coverResId = data.getIntExtra("coverResId", 0);
                            coverResId = R.drawable.book_3;
                            bookList.add(new Book(coverResId, title));
                            bookList.get(bookList.size() - 1).setCoverResIdOriginal(coverResId);
                            recyclerViewBooks.getAdapter().notifyDataSetChanged();
                            Toast.makeText(this, "Book added", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        modifyBookLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            int position = data.getIntExtra("position", -1);
                            String title = data.getStringExtra("title");
                            int coverResId = data.getIntExtra("coverResId", 0);
                            if (position != -1) {
                                bookList.get(position).setTitle(title);
                                bookList.get(position).setCoverResId(coverResId);
                                recyclerViewBooks.getAdapter().notifyItemChanged(position);
                                Toast.makeText(this, "Book modified", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewBooks = findViewById(R.id.recycle_view_books);
        bookList = new ArrayList<>();
        bookList.add(new Book(R.drawable.book_2, "软件项目管理案例教程（第4版）"));
        bookList.add(new Book(R.drawable.book_no_name, "创新工程实践"));
        bookList.add(new Book(R.drawable.book_1, "信息安全教学基础（第二版）"));

        RecycleViewBookAdapter adapter = new RecycleViewBookAdapter(bookList);
        recyclerViewBooks.setAdapter(adapter);
        recyclerViewBooks.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = item.getItemId();
        switch (item.getTitle().toString()) {
            case "Add":
                Intent addIntent = new Intent(MainActivity.this, AddBookActivity.class);
                addBookLauncher.launch(addIntent);
                return true;
            case "Modify":
                Intent modifyIntent = new Intent(MainActivity.this, ModifyBookActivity.class);
                modifyIntent.putExtra("position", position);
                modifyBookLauncher.launch(modifyIntent);
                return true;
            case "Delete":
                bookList.remove(position);
                recyclerViewBooks.getAdapter().notifyItemRemoved(position);
                Toast.makeText(this, "Book deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == ADD_BOOK_REQUEST) {
                String title = data.getStringExtra("title");
                int coverResId = data.getIntExtra("coverResId", 0);
                coverResId = R.drawable.book_3;
                bookList.add(new Book(coverResId, title));
                bookList.get(bookList.size() - 1).setCoverResIdOriginal(coverResId);
                recyclerViewBooks.getAdapter().notifyDataSetChanged();
                Toast.makeText(this, "Book added", Toast.LENGTH_SHORT).show();
            } else if (requestCode == MODIFY_BOOK_REQUEST) {
                int position = data.getIntExtra("position", -1);
                if (position != -1) {
                    String title = data.getStringExtra("title");
                    int coverResId = data.getIntExtra("coverResId", 0);
                    bookList.get(position).setTitle(title);
                    bookList.get(position).setCoverResId(coverResId);
                    bookList.get(position).setCoverResIdOriginal(coverResId); // Update original cover resource id
                    recyclerViewBooks.getAdapter().notifyItemChanged(position);
                    Toast.makeText(this, "Book modified", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}