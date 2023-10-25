package tw.cgu.b0921251_mcs.multimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class drawing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Draw2D d = new Draw2D(this);
        setContentView(d);

    }
}

// Georgeia Tech
// UC earvine
// marilane


