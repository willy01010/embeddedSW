package tw.cgu.b0921251_mcs.multimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class video extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        VideoView video = (VideoView)findViewById(R.id.videoView);
        // 指定影片的URI
        video.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video);
        video.setMediaController(new MediaController(this));
        video.start(); // 開始播放
    }
}