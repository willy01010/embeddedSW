package tw.cgu.b0921251_mcs.multimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent it_audio = new Intent(this, audio.class);
        findViewById(R.id.musicBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(it_audio);
            }
        });


        Intent it_video = new Intent(this, video.class);
        findViewById(R.id.videoBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(it_video);
            }
        });


        Intent it_record = new Intent(this, record.class);
        findViewById(R.id.recordBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(it_record);
            }
        });


        Intent it_drawing = new Intent(this, drawing.class);
        findViewById(R.id.drawingBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(it_drawing);
            }
        });
    }
}