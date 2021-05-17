package com.mobile.aplikasi_kamera;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static final int REQ_CODE_CAM = 100;
    private static final int REQ_CODE_GAL = 101;

    private ImageView imageView;
    private Button button_cam, button_gal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        button_cam = (Button) findViewById(R.id.btn_Cam);
        button_gal = (Button) findViewById(R.id.btn_Gall);

        button_cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQ_CODE_CAM);
            }
        });

        button_gal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQ_CODE_GAL);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE_CAM) {
            Bitmap bmp = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bmp);
        }

        if (requestCode == REQ_CODE_GAL) {
            Uri imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }
}