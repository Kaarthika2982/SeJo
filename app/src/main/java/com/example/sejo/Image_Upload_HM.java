package com.example.sejo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class Image_Upload_HM extends AppCompatActivity {

    ImageView imageView;

    FloatingActionButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload_hm);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.blue)));
        imageView = findViewById(R.id.imageView13);
        button = findViewById(R.id.floatingActionButton);
        button.setOnClickListener(v -> ImagePicker.with(Image_Upload_HM.this)
                .crop()
                .compress(1024)
                .maxResultSize(1080,1080)
                .start());
    }
}