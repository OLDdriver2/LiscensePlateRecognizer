package com.example.cqedu.lpr;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraView;

import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;

import org.opencv.core.Mat;

public class MainActivity extends AppCompatActivity {
    static{
        System.loadLibrary("opencv_java3");
    }
    CameraView cameraView;
    Bitmap mBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPower();
        //OpencvHelper.cascade(getResources().getAssets().);

        cameraView=findViewById(R.id.camera);
        cameraView.addCameraListener(new CameraListener() {
            @Override
            public void onPictureTaken(byte[] picture) {
                Bitmap bitmap = Bytes2Bitmap(picture);
                setBitmap(bitmap);
                Mat mat =new Mat();
                mat = OpencvHelper.Bitmap2Mat(bitmap);
            }
        });

    }
    public void setBitmap(Bitmap bitmap){
        mBitmap=bitmap;
    }
    public Bitmap Bytes2Bitmap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }
    public void  btnClick(View view){
        //cameraView.capturePicture();
        cameraView.captureSnapshot();
    }

    protected void onResume() {
        super.onResume();
        cameraView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cameraView.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cameraView.destroy();
    }

    public void requestPower() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PERMISSION_GRANTED) {
                    //Toast.makeText(getBaseContext(), "" + "Permission" + permissions[i] + "Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "" + "Permission" + permissions[i] + "Denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
