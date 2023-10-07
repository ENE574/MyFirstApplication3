package com.jnu.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public class RecycleViewBookAdapter extends RecyclerView.Adapter<RecycleViewBookAdapter.BookViewHolder> {
        private List<Book> bookList;

        // 构造方法，传入书籍列表数据
        public RecycleViewBookAdapter(List<Book> bookList) {
            this.bookList = bookList;
        }

        // 创建ViewHolder
        @NonNull
        @Override
        public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
            return new BookViewHolder(itemView);
        }

        // 绑定ViewHolder
        @Override
        public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
            Book book = bookList.get(position);

            // 设置书籍封面和标题
            holder.imageViewCover.setImageResource(book.getCoverResId());
            holder.textViewTitle.setText(book.getTitle());
        }

        // 获取书籍数量
        @Override
        public int getItemCount() {
            return bookList.size();
        }

        // 定义ViewHolder
        class BookViewHolder extends RecyclerView.ViewHolder {
            ImageView imageViewCover;
            TextView textViewTitle;

            public BookViewHolder(@NonNull View itemView) {
                super(itemView);
                imageViewCover = itemView.findViewById(R.id.image_view_book_cover);
                textViewTitle = itemView.findViewById(R.id.text_view_book_title);
            }
        }
    }

    public class Book {
        private int coverResId;
        private String title;

        public Book(int coverResId, String title) {
            this.coverResId = coverResId;
            this.title = title;
        }

        public int getCoverResId() {
            return coverResId;
        }

        public String getTitle() {
            return title;
        }
    }

    RecyclerView recyclerViewBooks;
    List<Book> bookList;

    // 添加书籍数据到bookList
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewBooks = findViewById(R.id.recycle_view_books);
        bookList = new ArrayList<>();

        // 添加书籍数据到bookList
        bookList.add(new Book(R.drawable.book_2, "软件项目管理案例教程（第4版）"));
        bookList.add(new Book(R.drawable.book_no_name, "创新工程实践"));
        bookList.add(new Book(R.drawable.book_1, "信息安全教学基础（第二版）"));

        RecycleViewBookAdapter adapter = new RecycleViewBookAdapter(bookList);
        recyclerViewBooks.setAdapter(adapter);
        recyclerViewBooks.setLayoutManager(new LinearLayoutManager(this));
    }
}


//    private RecyclerView recyclerView;
//    private List<String> data;
//    private MyAdapter adapter;
//    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
//        private List<String> data;
//
//        public MyAdapter(List<String> data) {
//            this.data = data;
//        }
//
//        @NonNull
//        @Override
//        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
//            return new MyViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//            String item = data.get(position);
//            holder.textView.setText(item);
//        }
//
//        @Override
//        public int getItemCount() {
//            return data.size();
//        }
//
//        public  class MyViewHolder extends RecyclerView.ViewHolder {
//            TextView textView;
//
//            public MyViewHolder(@NonNull View itemView) {
//                super(itemView);
//                textView = itemView.findViewById(R.id.text_view);
//            }
//        }
//    }
//    public class Item {
//        private int imageResId;
//        private String title;
//
//        public Item(int imageResId, String title) {
//            this.imageResId = imageResId;
//            this.title = title;
//        }
//
//        public int getImageResId() {
//            return imageResId;
//        }
//
//        public String getTitle() {
//            return title;
//        }
//    }
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        recyclerView = findViewById(R.id.recycler_view);
//        data = new ArrayList<>();
//        // 添加需要显示的数据到data
//
//        data.add(String.valueOf(new Item(R.drawable.book_2, "软件项目管理案例教程（第4版）")));
//        data.add(String.valueOf(new Item(R.drawable.book_1, "创新工程实践")));
//        data.add(String.valueOf(new Item(R.drawable.book_no_name, "信息安全教学基础（第二版）")));
//
//        adapter = new MyAdapter(data);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//    }


//    private TextView textViewHello;
//    private TextView textViewJNU;
//    private Button buttonChangeText;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
////        setContentView(R.layout.activity_main);
////        RelativeLayout relativeLayout = new RelativeLayout(this);
////        RelativeLayout.LayoutParams params =  new RelativeLayout.LayoutParams(
////                RelativeLayout.LayoutParams.WRAP_CONTENT,
////                RelativeLayout.LayoutParams.WRAP_CONTENT);
////        params.addRule(RelativeLayout.CENTER_IN_PARENT);  //设置布局中的控件居中显示
////        TextView textView = new TextView(this);                       //创建TextView控件
////        textView.setText("你好Android");                     //设置TextView的文字内容
////        textView.setTextColor(Color.RED);                                  //设置TextView的文字颜色
////        textView.setTextSize(18);                                                //设置TextView的文字大小
////        relativeLayout.addView(textView, params);                  //添加TextView对象和TextView的布局属性
////        setContentView(relativeLayout);                                  //设置在Activity中显示RelativeLayout
//        TextView textView = findViewById(R.id.text_view_hello_world);
//        String helloText = getResources().getString(R.string.hello_android);
//        textView.setText(helloText);
//        setContentView(R.layout.activity_main);
//
//        textViewHello = findViewById(R.id.textViewHello);
//        textViewJNU = findViewById(R.id.textViewJNU);
//        buttonChangeText = findViewById(R.id.buttonChangeText);
//
//        buttonChangeText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String tempText = textViewHello.getText().toString();
//                textViewHello.setText(textViewJNU.getText());
//                textViewJNU.setText(tempText);
//
//                Toast.makeText(MainActivity.this, "交换成功", Toast.LENGTH_SHORT).show();
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setTitle("交换成功")
//                        .setMessage("两个TextView的文本已成功交换")
//                        .setPositiveButton("确定", null)
//                        .create()
//                        .show();
//            }
//        });
//        buttonChangeText.setOnClickListener(v -> {
//            // 交换两个TextView的文本
//            String temp = textViewHello.getText().toString();
//            textViewHello.setText(textViewJNU.getText().toString());
//            textViewJNU.setText(temp);
//
//            // 显示Toast
//            Toast.makeText(MainActivity.this, "交换成功", Toast.LENGTH_SHORT).show();
//
//            // 显示AlertDialog
//            new AlertDialog.Builder(MainActivity.this)
//                    .setTitle("成功")
//                    .setMessage("交换成功")
//                    .setPositiveButton("确定", null)
//                    .show();
//        });
//    }

//}