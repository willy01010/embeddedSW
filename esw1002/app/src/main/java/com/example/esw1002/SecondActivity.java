package com.example.esw1002;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SecondActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_PERMISSION = 20;
    SharedPreferences prefs;
    private static final int READ_BLOCK_SIZE = 100;
    private String fname = "note.txt";
    private String fname2 = "note2.txt";
    private String fpath = "/";
    private File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
//取得SharedPreferences的設定資料
        prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
        String str = prefs.getString("Amount", "1008");
        // 取得TextView元件
        TextView output = (TextView) findViewById(R.id.textView2);
        output.setText("讀取出來的AMOUNT是: " + str);  // 指定內容

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);
        }


        // 檢查是否有外部儲存裝置
        if (!Environment.getExternalStorageState().
                equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "沒有外部儲存裝置...",
                    Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        try {
            // 在SD卡建立檔案
            File sd = Environment.getExternalStorageDirectory();
            File dir = new File(sd.getAbsolutePath() + fpath);
            if (!dir.exists()) {
                dir.mkdir();
                System.out.println("------Makedir----");
            }
            file = new File(dir, fname);

            System.out.println("------FLIE_DIR----"+file);

            if(file.exists()){
                System.out.println("------F OK----");
            }else{
                System.out.println("------F false----");
            }

        }
        catch (Exception ex) {
            ex.printStackTrace();

        }


    }

    public void btnSave_Click(View view){
        EditText input = (EditText) findViewById(R.id.txtInput);
        String str = input.getText().toString();
        try{
            // 開啟寫入檔案
            FileOutputStream out = openFileOutput(fname, MODE_PRIVATE);
            OutputStreamWriter sw = new OutputStreamWriter(out);
            sw.write(str);  // 將字串寫入串流
            sw.flush();     // 輸出串流資料
            sw.close();     // 關閉串流
            Toast.makeText(this, "Write File Successful", Toast.LENGTH_SHORT).show();
            input.setText("");
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void btnRead_Click(View view){
        try{
            // 開啟讀取檔案
            FileInputStream in = openFileInput(fname);
            InputStreamReader sr = new InputStreamReader(in);
            char[] buffer = new char[READ_BLOCK_SIZE];
            String str = "";
            int count;
            // 讀取檔案內容
            while((count = sr.read(buffer)) > 0){
                String s = String.copyValueOf(buffer,0,count);
                str += s;
                buffer = new char[READ_BLOCK_SIZE];
            }
            sr.close();// 關閉串流
            Toast.makeText(this, "Read File Successful", Toast.LENGTH_SHORT).show();
            TextView output = (TextView) findViewById(R.id.readOutput);
            output.setText("讀取內容:\n" + str);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//SD file

    // Button元件的事件處理
    public void btnSave_ClickSD(View view) {
        EditText input = (EditText) findViewById(R.id.txtInputSD);
        String str = input.getText().toString();
        try {
            // 開啟寫入檔案
//            FileOutputStream out = new FileOutputStream(file);
//            OutputStreamWriter sw = new OutputStreamWriter(out);
//            sw.write(str);  // 將字串寫入串流
//            sw.flush();     // 輸出串流資料
//            sw.close();     // 關閉串流

            FileOutputStream out = openFileOutput(fname2, MODE_PRIVATE);
            OutputStreamWriter sw = new OutputStreamWriter(out);
            sw.write(str);  // 將字串寫入串流
            sw.flush();     // 輸出串流資料
            sw.close();     // 關閉串流
            Toast.makeText(this, "成功寫入SD檔案...",
                    Toast.LENGTH_SHORT).show();
            input.setText("");  // 清除EditText元件的內容
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void btnRead_ClickSD(View view) {
        try {
            // 開啟讀取檔案
//            FileInputStream in = new FileInputStream(file);
//            InputStreamReader sr = new InputStreamReader(in);
//            char[] buffer = new char[READ_BLOCK_SIZE];
//            String str = "";
//            int ch;
//            // 讀取檔案內容
//            while ((ch = sr.read(buffer)) > 0) {
//                String s = String.copyValueOf(buffer,0, ch);
//                str += s;
//                buffer = new char[READ_BLOCK_SIZE];
//            }
//            sr.close();     // 關閉串流

            FileInputStream in = openFileInput(fname2);
            InputStreamReader sr = new InputStreamReader(in);
            char[] buffer = new char[READ_BLOCK_SIZE];
            String str = "";
            int count;
            // 讀取檔案內容
            while((count = sr.read(buffer)) > 0){
                String s = String.copyValueOf(buffer,0,count);
                str += s;
                buffer = new char[READ_BLOCK_SIZE];
            }
            sr.close();// 關閉串流
            Toast.makeText(this, "成功讀取SD檔案...",
                    Toast.LENGTH_SHORT).show();
            TextView output = (TextView) findViewById(R.id.readOutputSD);
            output.setText("讀取SD內容:\n" + str);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}