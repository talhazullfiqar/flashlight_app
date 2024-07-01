package com.example.flashlights;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageButton flashlightButton;
    private boolean isFlashlightOn = false;
    private CameraManager cameraManager;
    private String cameraId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashlightButton = findViewById(R.id.flashlightButton);
        cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);

        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        flashlightButton.setOnClickListener(v -> toggleFlashlight());
    }

    private void toggleFlashlight() {
        try {
            if (isFlashlightOn) {
                cameraManager.setTorchMode(cameraId, false);
                isFlashlightOn = false;
                flashlightButton.setImageResource(R.drawable.ic_flashlight_off); // Use your ic_flashlight_off resource
                Toast.makeText(this, "Flashlight is Off", Toast.LENGTH_SHORT).show();
            } else {
                cameraManager.setTorchMode(cameraId, true);
                isFlashlightOn = true;
                flashlightButton.setImageResource(R.drawable.ic_flashlight_on); // Use your ic_flashlight_on resource
                Toast.makeText(this, "Flashlight is On", Toast.LENGTH_SHORT).show();
            }
        } catch (CameraAccessException e) {
            e.printStackTrace(); // Replace with proper logging
        }
    }

}
