package tw.cgu.b0921251_mcs.multimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.File;


public class record extends AppCompatActivity {
    private MediaRecorder recorder;
    private Button start, stop;
    private TextView output;
    File path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);


        try {
            // 取得Button元件
            start = (Button) findViewById(R.id.btnStart);
            stop = (Button) findViewById(R.id.btnStop);
            output = (TextView) findViewById(R.id.record_status);
            recorder = new MediaRecorder();
            path = new File(Environment.getExternalStorageDirectory(), "myrecord.3gp");
            resetRecorder();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }



        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText("開始錄音....");
                try {
                    recorder.start(); // 開始錄音
                    // 設定按鈕狀態
                    start.setEnabled(false);
                    stop.setEnabled(true);
                } catch (Exception ex) {
                    Log.d("Ch13_4", "start_Click " + ex.getMessage());
                }
            }
        });



        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText("停止錄音....");
                recorder.stop(); // 停止錄音
                // 設定按鈕狀態
                start.setEnabled(true);
                stop.setEnabled(false);
            }
        });

    }
    // 重設錄音機
    private void resetRecorder() {
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        recorder.setOutputFile(path.getAbsolutePath());
        try {
            recorder.prepare();
            output.setText("錄音程序準備完成....");
            start.setEnabled(true);
        } catch (Exception ex) {
            Log.d("Ch13_4", "resetRecorder: " + ex.getMessage());
        }
    }
}
