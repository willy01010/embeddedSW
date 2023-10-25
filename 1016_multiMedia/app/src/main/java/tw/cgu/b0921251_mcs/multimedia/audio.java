package tw.cgu.b0921251_mcs.multimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class audio extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        TextView output = (TextView) findViewById(R.id.audio_status);


        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.audio );


        findViewById(R.id.audio_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                output.setText("音樂播放中...");
            }
        });


        findViewById(R.id.ausio_pause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
                output.setText("音樂暫停中...");
            }
        });


        findViewById(R.id.audio_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mediaPlayer.stop();
                    mediaPlayer.prepare();
                    output.setText("音樂已經停止播放...");
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }



}