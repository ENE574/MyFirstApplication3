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

public class ModifyBookActivity extends AppCompatActivity {
    EditText editTextTitle;
    Button buttonModify;
    int position;
    int coverResId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_book);
        coverResId = getIntent().getIntExtra("coverResId", R.drawable.default_cover);
        ImageView imageViewCover = findViewById(R.id.image_view_cover);
        imageViewCover.setImageResource(coverResId);

        if (coverResId != 0) {
            imageViewCover.setImageResource(coverResId);
        } else {
            imageViewCover.setImageResource(R.drawable.default_cover);
        }
        editTextTitle = findViewById(R.id.edit_text_title);
        buttonModify = findViewById(R.id.button_modify);

        position = getIntent().getIntExtra("position", -1);
        coverResId = getIntent().getIntExtra("coverResId", 0);

        if (position == -1) {
            finish();
        }
        buttonModify.setOnClickListener(v -> {
            String title = editTextTitle.getText().toString().trim();
            if (!TextUtils.isEmpty(title)) {
                Intent intent = new Intent();
                intent.putExtra("title", title);
                // 将原来的封面资源id返回
                intent.putExtra("coverResId", coverResId);
                intent.putExtra("position", position);
                setResult(RESULT_OK, intent);
                finish();
            } else {
                Toast.makeText(ModifyBookActivity.this, "Title cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });
    }
}