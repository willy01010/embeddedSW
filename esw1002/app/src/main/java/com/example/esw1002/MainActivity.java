package com.example.esw1002;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static final String PPREF_AMOUNT = "Amount";
    private static final String PPREF_RATE = "Rate";
    private EditText txtAmount, txtRate;
    private SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//取得EditText元件
        txtAmount = (EditText) findViewById(R.id.txtAmount);
        txtRate = (EditText) findViewById(R.id.txtRate);
//取得SharedPreferences物件
        prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
//        prefs = getPreferences(MODE_PRIVATE);
// 取得Editor物件
        SharedPreferences.Editor prefEdit = prefs.edit();
// 存入偏好設定資料至Editor物件
//        prefEdit.putString("AMOUNT", "500");
//        prefEdit.apply(); // 寫入檔案
    }

    @Override
    protected void onResume() {
        super.onResume();
//        取得偏好設定資料
        String amount = prefs.getString(PPREF_AMOUNT,"10000");
        txtAmount.setText(amount);
        float rate = prefs.getFloat(PPREF_RATE, 31.5F);
        txtRate.setText(String.valueOf(rate));
    }

    protected void onPause() {
        super.onPause();
//        取得Editor物件
        SharedPreferences.Editor prefEdit = prefs.edit();
//        存入偏好設定資料至Editor物件
        prefEdit.putString(PPREF_AMOUNT, txtAmount.getText().toString());
        float rate;
        rate = (float) Double.parseDouble(txtRate.getText().toString());
        prefEdit.putFloat(PPREF_RATE, rate);
        prefEdit.apply();// 寫入檔案

    }

    public void button_Click(View view){
        double amount, rate, result;
        amount = Double.parseDouble(txtAmount.getText().toString());
        rate = Double.parseDouble(txtRate.getText().toString());
        result = amount / rate;
        TextView output = (TextView) findViewById(R.id.readOutput);
        output.setText("美金:" +Double.toString(result));

    }

    // Button元件的事件處理
    public void button_Click_second(View view) {
        // 取得SharedPreferences的設定資料
        String str = prefs.getString(PPREF_AMOUNT, "1005");
        Toast.makeText(this, "存入偏好設定資料MainActivity: " + str,
                Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, SecondActivity.class);
        startActivity(i);
    }





    // Button元件的事件處理
    public void btnRead_ClickRaw(View view) {
        try { // 開啟讀取檔案
            InputStream in = this.getResources().openRawResource(R.raw.note);
            InputStreamReader sr = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(sr);
            String s = null, str = "";
            // 讀取檔案內容
            while ((s = br.readLine()) != null)
                str += s + "\n";
            br.close();
            sr.close();     // 關閉串流
            Toast.makeText(this, "成功讀取檔案...",
                    Toast.LENGTH_SHORT).show();
            TextView output = (TextView) findViewById(R.id.readOutputRAW);
            output.setText("讀取內容:\n" + str);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}



