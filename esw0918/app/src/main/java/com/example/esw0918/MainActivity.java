package com.example.esw0918;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.style.TabStopSpan;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.view.Menu;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    Button toastbt, popbt;

    //彈出式選單
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //彈出式選單
        getMenuInflater().inflate(R.menu.upmenu,menu);
        //彈出式選單
//option menu
        getMenuInflater().inflate(R.menu.optionmenu, menu);
//option menu
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.toolbar1:
                Toast.makeText(this, "訊息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.toolbar2:
                Toast.makeText(this, "追蹤", Toast.LENGTH_SHORT).show();
                break;
            case R.id.toolbar3:
                Toast.makeText(this, "設定", Toast.LENGTH_SHORT).show();
                break;
            case R.id.subtoolbar1:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                break;

        }

//option menu
        Button btn = (Button) findViewById(R.id.optionmenubt);
        switch(item.getItemId()){
            case R.id.menu_red:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                btn.setBackgroundColor(Color.RED);
                return true;
            case R.id.menu_yellow:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                btn.setBackgroundColor(Color.YELLOW);
                return true;
            case R.id.menu_green:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                btn.setBackgroundColor(Color.GREEN);
                return true;
        }
        return super.onOptionsItemSelected(item);
//option menu

    }
    //彈出式選單


    //長按開啟上下文選單
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        // 如果要给多个 view 注册上下文菜单，可以根据 v 参数来判断
        MenuInflater inflator = new MenuInflater(this);
        inflator.inflate(R.menu.floatmenu, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    public boolean onContextItemSelected(MenuItem item) {
        Button btn = (Button) findViewById(R.id.floatmenu);
        String label = "";
        switch (item.getItemId()) {
            case R.id.redf:
                label = "red";
                btn.setBackgroundColor(Color.RED);
                break;
            case R.id.yellowf:
                label = "yellow";
                btn.setBackgroundColor(Color.YELLOW);
                break;
            case R.id.greenf:
                label = "green";
                btn.setBackgroundColor(Color.GREEN);
                break;
        }

        Toast.makeText(getApplicationContext(),"你點擊了:"+ label,Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }
    //長按開啟上下文選單

    public void CountrySelectAlertDialog (){
        AlertDialog.Builder builder = new AlertDialog.Builder (MainActivity.this);


        Resources res = getResources();
        String[] Countrys = res.getStringArray(R.array.country_array);
        builder.setTitle ("國家列表");
        builder.setItems (Countrys, new DialogInterface.OnClickListener () {
            @Override
            public void onClick (DialogInterface dialogInterface, int i) {
                Toast.makeText (MainActivity.this,"Now you selected Country is : "+Countrys[i],Toast.LENGTH_LONG).show ();
//                Toast.makeText (MainActivity.this,"Now you selected Country is : ",Toast.LENGTH_LONG).show ();
            }
        });
        builder.create ().show ();
    }


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //按我
        toastbt = (Button) findViewById(R.id.toastbt);
        toastbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast toast = Toast.makeText(MainActivity.this,"我是吐司", Toast.LENGTH_SHORT);
                toast.show();



            }
        });
        //按我




        //彈出式選單
        popbt = (Button) findViewById(R.id.popmanu);
        popbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, popbt);
                popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        String  info  =  "" ;
                        switch  ( menuItem.getItemId ())
                        {
                            case  R.id.scan :
                                info  =  "你點了掃描" ;
                                break ;
                            case  R.id.add :
                                info  =  "你點了添加" ;
                                break ;
                        }

                        Toast.makeText(MainActivity.this, info, Toast.LENGTH_SHORT).show();

                        return true;
                    }
                });
                popupMenu.show();
            }
        });
        //彈出式選單


        //長按開啟上下文選單
        Button btn_float = (Button)findViewById(R.id.floatmenu);
        registerForContextMenu(btn_float);
        //長按開啟上下文選單


       Button btn_opt = (Button) findViewById(R.id.optionmenubt);
       btn_opt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Toast.makeText(MainActivity.this,"optbtn", Toast.LENGTH_SHORT);
               CountrySelectAlertDialog ();
           }
       });
    }


}