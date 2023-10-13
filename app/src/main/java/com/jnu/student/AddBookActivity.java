package com.jnu.student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
public class AddBookActivity extends AppCompatActivity {
    EditText editTextTitle;
    Button buttonAdd;
    int coverResIdOriginal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        editTextTitle = findViewById(R.id.edit_text_title);
        buttonAdd = findViewById(R.id.button_add);
        coverResIdOriginal = R.drawable.book_3; // 修改为 drawable 文件夹下的 book_3
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextTitle.getText().toString();
                if (TextUtils.isEmpty(title.trim())) { // 空格也算作空内容
                    editTextTitle.setError("Title cannot be empty");
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("title", title);
                    intent.putExtra("coverResId", coverResIdOriginal);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}
