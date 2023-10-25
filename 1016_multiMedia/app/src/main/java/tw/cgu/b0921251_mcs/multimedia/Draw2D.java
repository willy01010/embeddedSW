package tw.cgu.b0921251_mcs.multimedia;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Draw2D extends View {
    public Draw2D(Context context) {
        super(context);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        try {
            super.onDraw(canvas);
            // 建立Paint物件
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            // 在整個Canvas物件的背景填滿色彩
            paint.setColor(Color.WHITE);
            canvas.drawPaint(paint);
            // 畫圓
            paint.setAntiAlias(true);
            paint.setColor(Color.RED);
            canvas.drawCircle(80, 300, 100, paint);
            // 畫長方形
            paint.setColor(Color.BLUE);
            canvas.drawRect(20, 5, 250, 100, paint);
            //        // 畫出資源圖形
            //        Resources res = this.getResources();
            //        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.ic_launcher_foreground);
            //        canvas.drawBitmap(bitmap, 50 ,200 , paint);
            // 畫出文字內容
            paint.setColor(Color.GREEN);
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            paint.setTextSize(30);
            canvas.drawText("我的畫布!", 50, 180, paint);
            // 畫出旋轉的文字
            paint.setColor(Color.BLACK);
            paint.setTextSize(25);
            String str = "旋轉的文字!";
            // 旋轉繪出文字內容
            canvas.rotate(-45, 200, 200);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawText(str, 200, 200, paint);
            // 取消旋轉
            canvas.restore();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
