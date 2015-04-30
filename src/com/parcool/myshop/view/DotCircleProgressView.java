package com.parcool.myshop.view;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;




import com.parcool.myshop.R;
import com.parcool.myshop.utils.CommonUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;


public class DotCircleProgressView extends View {

	private Paint mPaint;
	private int dotColor = Color.BLUE;// 圆点颜色，设置默认值为:Color.BLUE
	private float dotSizeMin = 5;// 最小的圆点大小，设置默认值为：5
	private float dotSizeMax = 20;// 最大圆点大小，设置默认值为：20
	private int dotCount = 8;// 圆点数量，设置默认值为：8
	private float[] eachDotSize = null;
	private float r = 0;
	

	public int getDotColor() {
		return dotColor;
	}

	public void setDotColor(int dotColor) {
		this.dotColor = dotColor;
	}

	public float getDotSizeMin() {
		return dotSizeMin;
	}

	public void setDotSizeMin(float dotSizeMin) {
		this.dotSizeMin = dotSizeMin;
	}

	public float getDotSizeMax() {
		return dotSizeMax;
	}

	public void setDotSizeMax(float dotSizeMax) {
		this.dotSizeMax = dotSizeMax;
	}

	public int getDotCount() {
		return dotCount;
	}

	public void setDotCount(int dotCount) {
		this.dotCount = dotCount;
	}

	@SuppressLint("NewApi")
	public DotCircleProgressView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		// TODO Auto-generated constructor stub
		init(context, attrs);
	}

	public DotCircleProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		init(context, attrs);
	}

	public DotCircleProgressView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context, attrs);
	}

	public DotCircleProgressView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public void initView(Context context){
		dotSizeMin = CommonUtil.getInstance().dp2px(context, dotSizeMin);
		dotSizeMax = CommonUtil.getInstance().dp2px(context, dotSizeMax);
		init(context,null);
	}

	/***
	 * 初始化大小、数量、颜色
	 * 
	 * @param attrs
	 */
	private void init(Context context, AttributeSet attrs) {
		// TODO Auto-generated method stub
		if (attrs != null) {
			TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DotCircleProgressViewStyle);
			// 获取圆点的颜色
			dotColor = typedArray.getColor(R.styleable.DotCircleProgressViewStyle_dotColor, Color.BLUE);
			// 获取最小圆点的大小
			dotSizeMin = typedArray.getDimension(R.styleable.DotCircleProgressViewStyle_dotSizeMin, 5);
			// 获取最大圆点的大小
			dotSizeMax = typedArray.getDimension(R.styleable.DotCircleProgressViewStyle_dotSizeMax, 20);
			// 获取圆点的数量
			dotCount = typedArray.getInt(R.styleable.DotCircleProgressViewStyle_dotCount, 8);
			typedArray.recycle();
		}
		mPaint = new Paint();
		mPaint.setColor(dotColor);
		// 抗锯齿
		mPaint.setAntiAlias(true);
		// 设置填充
		mPaint.setStyle(Style.FILL);
		eachDotSize = new float[dotCount];
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	private float increment = 0;

	private double degree = PI / 2;
	private double dds = 0;

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		// 如果第0位为0，那么就判定它为空。
		// 因为onDraw()函数中是必须低消耗的，那么这样就减少for循环的次数始终为1
		// 本来这里是打算用动画进行旋转的，根本不会有多次invalidate()，
		// 也就不会有多次onDraw()，那以后如果想用线程控制旋转呢？呵呵
		if (increment == 0) {
			increment = (dotSizeMax - dotSizeMin) / dotCount;
		}
		if (dds == 0) {
			dds = PI / (dotCount / 2);
		}
		if (eachDotSize[0] == 0) {
			for (int i = 0; i < dotCount; i++) {
				if (i == 0) {
					eachDotSize[0] = dotSizeMin;
				} else if (i == dotCount - 1) {
					eachDotSize[i] = dotSizeMax;
				} else {
					eachDotSize[i] = dotSizeMin + increment * i;
				}
			}
		}
		if (r == 0) {
			r = getMeasuredWidth() / 2 - dotSizeMax;// 半径
		}

		for (int i = 0; i < dotCount; i++) {
			float x = getMeasuredWidth() / 2 + (float) (r * cos(degree));
			float y = getMeasuredWidth() / 2 - (float) (r * sin(degree));
			canvas.drawCircle(x, y, eachDotSize[i] / 2, mPaint);
			degree = degree + dds;
		}

	}

}
