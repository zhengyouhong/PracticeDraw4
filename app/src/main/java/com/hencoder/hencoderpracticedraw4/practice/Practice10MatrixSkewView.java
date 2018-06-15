package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice10MatrixSkewView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);

    public Practice10MatrixSkewView(Context context) {
        super(context);
    }

    public Practice10MatrixSkewView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice10MatrixSkewView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        Matrix matrix = new Matrix();
        float[] srcPos = {getWidth() / 2 - bitmap.getWidth() / 2, getHeight() / 2 - bitmap.getHeight() / 2,
                getWidth() / 2 + bitmap.getWidth() / 2, getHeight() / 2 - bitmap.getHeight() / 2,
                getWidth() / 2 + bitmap.getWidth() / 2, getHeight() / 2 + bitmap.getHeight() / 2,
                getWidth() / 2 - bitmap.getWidth() / 2,getHeight() / 2 + bitmap.getHeight() / 2};
        float[] desPos = {srcPos[0], srcPos[1],
                        srcPos[2] + 100, srcPos[3] - 100,
                        srcPos[4] + 100, srcPos[5] + 100,
                        srcPos[6], srcPos[7] };
        matrix.setPolyToPoly(srcPos, 0, desPos, 0, 4);
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();

//        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
    }
}
