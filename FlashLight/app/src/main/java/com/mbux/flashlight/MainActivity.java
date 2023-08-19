package com.mbux.flashlight;

import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private CameraManager cameraManager;
    private String cameraId;
    private ImageView lightImageView, btnImageView;
    private boolean isFlashlightOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        lightImageView = findViewById(R.id.lightOnOff);
        btnImageView = findViewById(R.id.btnOnOff);

        btnImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFlashlightOn) {
                    turnOffFlashlight();
                } else {
                    turnOnFlashlight();
                }
            }
        });
    }

    private void turnOnFlashlight() {
        try {
            cameraId = cameraManager.getCameraIdList()[0];
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId, true);
            }
            isFlashlightOn = true;
            updateImages();
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void turnOffFlashlight() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId, false);
            }
            isFlashlightOn = false;
            updateImages();
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void updateImages() {
        Drawable lightImage = ContextCompat.getDrawable(this, isFlashlightOn ? R.drawable.lighton : R.drawable.lightoff);
        lightImageView.setImageDrawable(lightImage);

        Drawable btnImage = ContextCompat.getDrawable(this, isFlashlightOn ? R.drawable.btnon : R.drawable.btnoff);
        btnImageView.setImageDrawable(btnImage);
    }

    // ... Rest of your code

    // Handle permission request result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            turnOnFlashlight();
        }
    }
}
