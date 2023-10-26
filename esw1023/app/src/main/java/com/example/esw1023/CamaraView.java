package com.example.esw1023;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CamaraView extends AppCompatActivity implements SurfaceHolder.Callback, View.OnClickListener {

    private Camera camera;
    boolean isPreviewRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_camara_view);

        // 取得SurfaceView元件
        SurfaceView surfaceview = (SurfaceView) findViewById(R.id.cameraview);
        surfaceview.setOnClickListener(this);
        SurfaceHolder surfaceHolder = surfaceview.getHolder();
        surfaceHolder.addCallback(this);
    }

    // 建立PictureCallback物件
    Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
        public void onPictureTaken(byte[] imageData, Camera c) {
            if (imageData != null) {
                saveImage(CamaraView.this, imageData, 50);
                // 相片預覽
                camera.startPreview();
            }
        }
    };

    // 實作SurfaceHolder.Callback介面方法
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        camera = Camera.open();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int w, int h) {
        if (isPreviewRunning) {
            camera.stopPreview();  // 停止預覽
        }
        Camera.Parameters p = camera.getParameters();
        p.setPreviewSize(w, h);
        camera.setParameters(p);
        try {
            camera.setPreviewDisplay(holder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        camera.startPreview();
        isPreviewRunning = true;
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        camera.stopPreview();
        isPreviewRunning = false;
        camera.release();
    }

    // 實作OnClickListener介面方法
    public void onClick(View arg0) {
        camera.takePicture(null, pictureCallback, pictureCallback);
    }

    // 儲存JPEG格式的圖片
    public boolean saveImage(Context mContext, byte[] imageData, int quality) {
        // 建立File物件的儲存路徑
        File path = new File(Environment.getExternalStorageDirectory().getPath());
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 5;
            Bitmap image = BitmapFactory.decodeByteArray(imageData, 0, imageData.length, options);
            FileOutputStream fos = new FileOutputStream(path.toString() + "/image.jpg");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            // 圖檔格式JPEG
            image.compress(Bitmap.CompressFormat.JPEG, quality, bos);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
