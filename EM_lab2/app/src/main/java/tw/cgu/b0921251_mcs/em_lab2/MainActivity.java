package tw.cgu.b0921251_mcs.em_lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void Alert(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("關於").setMessage("版本: 4.4.2版\n課程:嵌入式軟體設計").show();
    }


    public void AlertwithBtn(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("確認")
            .setMessage("確認結束本程式?")
            .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialoginterface, int i) {
                    finish();
                } })
            .setNegativeButton("取消", null)
            .show();
    }

    public void AlertwithRadio(View view){
        String[] options = {"紅色", "黃色", "綠色" };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setSingleChoiceItems(options, 0, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            int finalColor = Color.WHITE;
                            if (which == 0) finalColor = Color.RED;
                            else if (which == 1) finalColor = Color.YELLOW;
                            else if (which == 2) finalColor = Color.GREEN;
                            findViewById(R.id.changeColor).setBackgroundColor(finalColor);
                            Toast.makeText(MainActivity.this, String.valueOf(which), Toast.LENGTH_SHORT).show();
                        }
                    })
            .setPositiveButton("確定", null)
            .show();
    }


    public void AlertwithMulti(View view){
        String[] items = {"Android", "iOS", "Windows Phone", "Firefox OS"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        boolean[] checked = {false, false, false, false};

        builder.setMultiChoiceItems(items, checked, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which, boolean b) {
            }
        })
            .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0 ; i < 4 ; i ++) {
                        if (checked[i])
                            sb.append(items[i] + ", ");

                    }

                    Toast.makeText(MainActivity.this, sb.toString().substring(0, sb.toString().length()-1), Toast.LENGTH_LONG).show();
//                        builder.
                }
            })
            .show();
    }







}