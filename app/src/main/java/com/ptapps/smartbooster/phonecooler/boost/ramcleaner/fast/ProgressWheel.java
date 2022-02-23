package com.ptapps.smartbooster.phonecooler.boost.ramcleaner.fast;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.view.ViewCompat;

public class ProgressWheel extends View {
    private int barColor = -1442840576;
    private int barLength = 60;
    private Paint barPaint = new Paint();
    private int barWidth = 20;
    private RectF circleBounds = new RectF();
    private int circleColor = 0;
    private RectF circleInnerContour = new RectF();
    private RectF circleOuterContour = new RectF();
    private Paint circlePaint = new Paint();
    private int circleRadius = 80;
    private int contourColor = -1442840576;
    private Paint contourPaint = new Paint();
    private float contourSize = 0.0f;
    private int delayMillis = 10;
    private int fullRadius = 100;
    private RectF innerCircleBounds = new RectF();
    boolean isSpinning = false;
    private int layoutHeight = 0;
    private int layoutWidth = 0;
    private int paddingBottom = 5;
    private int paddingLeft = 5;
    private int paddingRight = 5;
    private int paddingTop = 5;
    private float progress = 0.0f;
    private int rimColor = -1428300323;
    private Paint rimPaint = new Paint();
    private int rimWidth = 20;
    private float spinSpeed = 2.0f;
    private String[] splitText = new String[0];
    private String text = "";
    private int textColor = ViewCompat.MEASURED_STATE_MASK;
    private Paint textPaint = new Paint();
    private int textSize = 20;

    public ProgressWheel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        parseAttributes(context.obtainStyledAttributes(attributeSet, R.styleable.ProgressWheel));
    }

    
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int paddingLeft2 = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int paddingTop2 = (measuredHeight - getPaddingTop()) - getPaddingBottom();
        int mode = MeasureSpec.getMode(i2);
        int mode2 = MeasureSpec.getMode(i);
        if (mode == 0 || mode2 == 0) {
            paddingLeft2 = Math.max(paddingTop2, paddingLeft2);
        } else if (paddingLeft2 > paddingTop2) {
            paddingLeft2 = paddingTop2;
        }
        setMeasuredDimension(getPaddingLeft() + paddingLeft2 + getPaddingRight(), paddingLeft2 + getPaddingTop() + getPaddingBottom());
    }

    
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.layoutWidth = i;
        this.layoutHeight = i2;
        setupBounds();
        setupPaints();
        invalidate();
    }

    private void setupPaints() {
        this.barPaint.setColor(this.barColor);
        this.barPaint.setAntiAlias(true);
        this.barPaint.setStyle(Paint.Style.STROKE);
        this.barPaint.setStrokeWidth((float) this.barWidth);
        this.rimPaint.setColor(this.rimColor);
        this.rimPaint.setAntiAlias(true);
        this.rimPaint.setStyle(Paint.Style.STROKE);
        this.rimPaint.setStrokeWidth((float) this.rimWidth);
        this.circlePaint.setColor(this.circleColor);
        this.circlePaint.setAntiAlias(true);
        this.circlePaint.setStyle(Paint.Style.FILL);
        this.textPaint.setColor(this.textColor);
        this.textPaint.setStyle(Paint.Style.FILL);
        this.textPaint.setAntiAlias(true);
        this.textPaint.setTextSize((float) this.textSize);
        this.contourPaint.setColor(this.contourColor);
        this.contourPaint.setAntiAlias(true);
        this.contourPaint.setStyle(Paint.Style.STROKE);
        this.contourPaint.setStrokeWidth(this.contourSize);
    }

    private void setupBounds() {
        int min = Math.min(this.layoutWidth, this.layoutHeight);
        int i = this.layoutWidth - min;
        int i2 = (this.layoutHeight - min) / 2;
        this.paddingTop = getPaddingTop() + i2;
        this.paddingBottom = getPaddingBottom() + i2;
        int i3 = i / 2;
        this.paddingLeft = getPaddingLeft() + i3;
        this.paddingRight = getPaddingRight() + i3;
        int width = getWidth();
        int height = getHeight();
        int i4 = this.barWidth;
        this.innerCircleBounds = new RectF(((float) this.paddingLeft) + (((float) i4) * 1.5f), ((float) this.paddingTop) + (((float) i4) * 1.5f), ((float) (width - this.paddingRight)) - (((float) i4) * 1.5f), ((float) (height - this.paddingBottom)) - (((float) i4) * 1.5f));
        int i5 = this.paddingLeft;
        int i6 = this.barWidth;
        this.circleBounds = new RectF((float) (i5 + i6), (float) (this.paddingTop + i6), (float) ((width - this.paddingRight) - i6), (float) ((height - this.paddingBottom) - i6));
        this.circleInnerContour = new RectF(this.circleBounds.left + (((float) this.rimWidth) / 2.0f) + (this.contourSize / 2.0f), this.circleBounds.top + (((float) this.rimWidth) / 2.0f) + (this.contourSize / 2.0f), (this.circleBounds.right - (((float) this.rimWidth) / 2.0f)) - (this.contourSize / 2.0f), (this.circleBounds.bottom - (((float) this.rimWidth) / 2.0f)) - (this.contourSize / 2.0f));
        this.circleOuterContour = new RectF((this.circleBounds.left - (((float) this.rimWidth) / 2.0f)) - (this.contourSize / 2.0f), (this.circleBounds.top - (((float) this.rimWidth) / 2.0f)) - (this.contourSize / 2.0f), this.circleBounds.right + (((float) this.rimWidth) / 2.0f) + (this.contourSize / 2.0f), this.circleBounds.bottom + (((float) this.rimWidth) / 2.0f) + (this.contourSize / 2.0f));
        int i7 = width - this.paddingRight;
        int i8 = this.barWidth;
        int i9 = (i7 - i8) / 2;
        this.fullRadius = i9;
        this.circleRadius = (i9 - i8) + 1;
    }

    @SuppressLint("ResourceType")
    private void parseAttributes(TypedArray typedArray) {
        this.barWidth = (int) typedArray.getDimension(2, (float) this.barWidth);
        this.rimWidth = (int) typedArray.getDimension(9, (float) this.rimWidth);
        this.spinSpeed = (float) ((int) typedArray.getDimension(10, this.spinSpeed));
        this.barLength = (int) typedArray.getDimension(1, (float) this.barLength);
        int integer = typedArray.getInteger(6, this.delayMillis);
        this.delayMillis = integer;
        if (integer < 0) {
            this.delayMillis = 10;
        }
        if (typedArray.hasValue(11)) {
            setText(typedArray.getString(11));
        }
        this.barColor = typedArray.getColor(0, this.barColor);
        this.textColor = typedArray.getColor(12, this.textColor);
        this.rimColor = typedArray.getColor(8, this.rimColor);
        this.circleColor = typedArray.getColor(3, this.circleColor);
        this.contourColor = typedArray.getColor(4, this.contourColor);
        this.textSize = (int) typedArray.getDimension(13, (float) this.textSize);
        this.contourSize = typedArray.getDimension(5, this.contourSize);
        typedArray.recycle();
    }

    
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(this.innerCircleBounds, 360.0f, 360.0f, false, this.circlePaint);
        canvas.drawArc(this.circleBounds, 360.0f, 360.0f, false, this.rimPaint);
        canvas.drawArc(this.circleOuterContour, 360.0f, 360.0f, false, this.contourPaint);
        if (this.isSpinning) {
            canvas.drawArc(this.circleBounds, this.progress - 90.0f, (float) this.barLength, false, this.barPaint);
        } else {
            canvas.drawArc(this.circleBounds, -90.0f, this.progress, false, this.barPaint);
        }
        float descent = ((this.textPaint.descent() - this.textPaint.ascent()) / 2.0f) - this.textPaint.descent();
        String[] strArr = this.splitText;
        for (String str : strArr) {
            canvas.drawText(str, ((float) (getWidth() / 2)) - (this.textPaint.measureText(str) / 2.0f), ((float) (getHeight() / 2)) + descent, this.textPaint);
        }
        if (this.isSpinning) {
            scheduleRedraw();
        }
    }

    private void scheduleRedraw() {
        float f = this.progress + this.spinSpeed;
        this.progress = f;
        if (f > 360.0f) {
            this.progress = 0.0f;
        }
        postInvalidateDelayed((long) this.delayMillis);
    }

    public boolean isSpinning() {
        return this.isSpinning;
    }

    public void resetCount() {
        this.progress = 0.0f;
        setText("0%");
        invalidate();
    }

    public void stopSpinning() {
        this.isSpinning = false;
        this.progress = 0.0f;
        postInvalidate();
    }

    public void startSpinning() {
        this.isSpinning = true;
        postInvalidate();
    }

    public void incrementProgress() {
        incrementProgress(1);
    }

    public void incrementProgress(int i) {
        this.isSpinning = false;
        float f = this.progress + ((float) i);
        this.progress = f;
        if (f > 360.0f) {
            this.progress = f % 360.0f;
        }
        postInvalidate();
    }

    public void setProgress(int i) {
        this.isSpinning = false;
        this.progress = (float) i;
        postInvalidate();
    }

    public void setText(String str) {
        this.text = str;
        this.splitText = str.split("\n");
    }

    public int getCircleRadius() {
        return this.circleRadius;
    }

    public void setCircleRadius(int i) {
        this.circleRadius = i;
    }

    public int getBarLength() {
        return this.barLength;
    }

    public void setBarLength(int i) {
        this.barLength = i;
    }

    public int getBarWidth() {
        return this.barWidth;
    }

    public void setBarWidth(int i) {
        this.barWidth = i;
        Paint paint = this.barPaint;
        if (paint != null) {
            paint.setStrokeWidth((float) i);
        }
    }

    public int getTextSize() {
        return this.textSize;
    }

    public void setTextSize(int i) {
        this.textSize = i;
        Paint paint = this.textPaint;
        if (paint != null) {
            paint.setTextSize((float) i);
        }
    }

    public int getPaddingTop() {
        return this.paddingTop;
    }

    public void setPaddingTop(int i) {
        this.paddingTop = i;
    }

    public int getPaddingBottom() {
        return this.paddingBottom;
    }

    public void setPaddingBottom(int i) {
        this.paddingBottom = i;
    }

    public int getPaddingLeft() {
        return this.paddingLeft;
    }

    public void setPaddingLeft(int i) {
        this.paddingLeft = i;
    }

    public int getPaddingRight() {
        return this.paddingRight;
    }

    public void setPaddingRight(int i) {
        this.paddingRight = i;
    }

    public int getBarColor() {
        return this.barColor;
    }

    public void setBarColor(int i) {
        this.barColor = i;
        Paint paint = this.barPaint;
        if (paint != null) {
            paint.setColor(i);
        }
    }

    public int getCircleColor() {
        return this.circleColor;
    }

    public void setCircleColor(int i) {
        this.circleColor = i;
        Paint paint = this.circlePaint;
        if (paint != null) {
            paint.setColor(i);
        }
    }

    public int getRimColor() {
        return this.rimColor;
    }

    public void setRimColor(int i) {
        this.rimColor = i;
        Paint paint = this.rimPaint;
        if (paint != null) {
            paint.setColor(i);
        }
    }

    public Shader getRimShader() {
        return this.rimPaint.getShader();
    }

    public void setRimShader(Shader shader) {
        this.rimPaint.setShader(shader);
    }

    public int getTextColor() {
        return this.textColor;
    }

    public void setTextColor(int i) {
        this.textColor = i;
        Paint paint = this.textPaint;
        if (paint != null) {
            paint.setColor(i);
        }
    }

    public float getSpinSpeed() {
        return this.spinSpeed;
    }

    public void setSpinSpeed(float f) {
        this.spinSpeed = f;
    }

    public int getRimWidth() {
        return this.rimWidth;
    }

    public void setRimWidth(int i) {
        this.rimWidth = i;
        Paint paint = this.rimPaint;
        if (paint != null) {
            paint.setStrokeWidth((float) i);
        }
    }

    public int getDelayMillis() {
        return this.delayMillis;
    }

    public void setDelayMillis(int i) {
        this.delayMillis = i;
    }

    public int getContourColor() {
        return this.contourColor;
    }

    public void setContourColor(int i) {
        this.contourColor = i;
        Paint paint = this.contourPaint;
        if (paint != null) {
            paint.setColor(i);
        }
    }

    public float getContourSize() {
        return this.contourSize;
    }

    public void setContourSize(float f) {
        this.contourSize = f;
        Paint paint = this.contourPaint;
        if (paint != null) {
            paint.setStrokeWidth(f);
        }
    }

    public int getProgress() {
        return (int) this.progress;
    }
}
