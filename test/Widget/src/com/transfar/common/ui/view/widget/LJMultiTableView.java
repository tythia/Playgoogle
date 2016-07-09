package com.transfar.common.ui.view.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.transfar.common.ui.R;

public class LJMultiTableView extends RelativeLayout {

	// 控件类型，上/中/下/单独条目
	public final static int TYPE_TOP = 0;
	public final static int TYPE_CENTER = 1;
	public final static int TYPE_BOTTOM = 2;
	public final static int TYPE_SINGLE = 3;

	private LinearLayout mLLLeft2;
	private LinearLayout mLLLeft3;
	private TextView mViewLeft1;
	private TextView mViewLeft2;
	private TextView mViewLeft3;
	private TextView mViewRight1;
	private TextView mViewRight2;
	private ImageView mImageViewLeft2;
	private ImageView mImageViewLeft3;
	private ImageView mImageViewRight1;
	private ImageView mImageViewRight2;
	private ImageView mImageViewArrow;

	private int mViewType = -1;
	private Paint mPaint;
	private int mLineHeight;
	private boolean mShowLine;

	public LJMultiTableView(Context context) {
		this(context, null);
	}

	public LJMultiTableView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public LJMultiTableView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		LayoutInflater.from(context).inflate(R.layout.lj_multi_table_view,
				this, true);
		mLLLeft2 = (LinearLayout) findViewById(R.id.ll_left2);
		mLLLeft3 = (LinearLayout) findViewById(R.id.ll_left3);
		mViewLeft1 = (TextView) findViewById(R.id.tv_left1);
		mViewLeft2 = (TextView) findViewById(R.id.tv_left2);
		mViewLeft3 = (TextView) findViewById(R.id.tv_left3);
		mViewRight1 = (TextView) findViewById(R.id.tv_right1);
		mViewRight2 = (TextView) findViewById(R.id.tv_right2);
		mImageViewLeft2 = (ImageView) findViewById(R.id.iv_left2);
		mImageViewLeft3 = (ImageView) findViewById(R.id.iv_left3);
		mImageViewRight1 = (ImageView) findViewById(R.id.iv_right1);
		mImageViewRight2 = (ImageView) findViewById(R.id.iv_right2);
		mImageViewArrow = (ImageView) findViewById(R.id.iv_arrow);
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.LJ_Multi_Table_View);
		String left1 = a.getString(R.styleable.LJ_Multi_Table_View_MTLeftText1);
		String left2 = a.getString(R.styleable.LJ_Multi_Table_View_MTLeftText2);
		String left3 = a.getString(R.styleable.LJ_Multi_Table_View_MTLeftText3);
		String right1 = a
				.getString(R.styleable.LJ_Multi_Table_View_MTRightText1);
		String right2 = a
				.getString(R.styleable.LJ_Multi_Table_View_MTRightText2);
		int rightColor1 = a.getColor(
				R.styleable.LJ_Multi_Table_View_MTRightTextColor1,
				context.getResources().getColor(
						R.color.lj_textcolor_gray_normal));
		int rightColor2 = a.getColor(
				R.styleable.LJ_Multi_Table_View_MTRightTextColor2,
				context.getResources().getColor(
						R.color.lj_textcolor_gray_normal));
		Drawable leftImage2 = a
				.getDrawable(R.styleable.LJ_Multi_Table_View_MTLeftImage2);
		Drawable leftImage3 = a
				.getDrawable(R.styleable.LJ_Multi_Table_View_MTLeftImage3);
		Drawable rightImage1 = a
				.getDrawable(R.styleable.LJ_Multi_Table_View_MTRightImage1);
		Drawable rightImage2 = a
				.getDrawable(R.styleable.LJ_Multi_Table_View_MTRightImage2);
		boolean showArrow = a.getBoolean(
				R.styleable.LJ_Multi_Table_View_MTShowArrow, false);
		boolean changeBackground = a.getBoolean(
				R.styleable.LJ_Multi_Table_View_MTChangeBackground, false);
		mViewType = a.getInt(R.styleable.LJ_Multi_Table_View_MTType, -1);
		a.recycle();

		setLeftText(left1, left2, left3);
		setRightText1(right1);
		setRightText2(right2);
		setLeftImage2(leftImage2);
		setLeftImage3(leftImage3);
		setRightImage1(rightImage1);
		setRightImage2(rightImage2);
		setRightTextColor1(rightColor1);
		setRightTextColor2(rightColor2);
		setShowArrow(showArrow);
		setBackground(changeBackground);

		mPaint = new Paint();
		mPaint.setColor(this.getContext().getResources()
				.getColor(R.color.lj_color_line_divider));
		mPaint.setAntiAlias(true);

		mLineHeight = this.getResources().getDimensionPixelSize(
				R.dimen.lj_divider_line_height);
	}

	/**
	 * 是否需要点击效果
	 * 
	 * @param enableBg
	 */
	public void setBackground(boolean enableBg) {
		if (enableBg) {
			setBackgroundResource(R.drawable.bg_common_list_item);
		} else {
			setBackgroundResource(R.color.lj_color_white);
		}
	}

	/**
	 * <li>{@link #TYPE_TOP}</li> <li>{@link #TYPE_CENTER}</li> <li>
	 * {@link #TYPE_BOTTOM}</li> <li>{@link #TYPE_SINGLE}</li>
	 * 
	 * @param type
	 */
	public void setViewType(int type) {
		mViewType = type;
		switch (type) {
		case TYPE_TOP:
			mShowLine = true;
			break;
		case TYPE_CENTER:
		case TYPE_BOTTOM:
		case TYPE_SINGLE:
		default:
			mShowLine = true;
			break;
		}
	}

	public void setRightTextColor1(int colorResId) {
		mViewRight1.setTextColor(colorResId);
	}

	public void setRightTextColor2(int colorResId) {
		mViewRight2.setTextColor(colorResId);
	}

	/**
	 * 是否显示分隔线
	 * 
	 * @param showLine
	 */
	public void setShowLine(boolean showLine) {

	}

	public TextView getLeftTextView1() {
		return mViewLeft1;
	}

	public TextView getLeftTextView2() {
		return mViewLeft2;
	}

	public TextView getLeftTextView3() {
		return mViewLeft3;
	}

	public ImageView getLeftImageView2() {
		return mImageViewLeft2;
	}

	public ImageView getLeftImageView3() {
		return mImageViewLeft3;
	}

	public TextView getRightTextView1() {
		return mViewRight1;
	}

	public TextView getRightTextView2() {
		return mViewRight2;
	}

	public ImageView getRightImageView1() {
		return mImageViewRight1;
	}

	public ImageView getRightImageView2() {
		return mImageViewRight2;
	}

	public ImageView getArrowView() {
		return mImageViewArrow;
	}

	public void setText(TextView view, String str) {
		if (view != null) {
			if (str != null && !"".equals(str)) {
				view.setText(str);
				view.setVisibility(View.VISIBLE);
			} else {
				view.setVisibility(View.GONE);
			}
		}
	}

	/**
	 * 设置控件左边文字，不需要设置的字段传null
	 * 
	 * @param text1
	 * @param text2
	 * @param text3
	 */
	public void setLeftText(String text1, String text2, String text3) {
		if (text1 != null && !"".equals(text1)) {
			mViewLeft1.setText(text1 + "　");
			mViewLeft1.setVisibility(View.VISIBLE);
		} else {
			mViewLeft1.setVisibility(View.GONE);
		}
		if (text2 != null && !"".equals(text2)) {
			mViewLeft2.setText(text2 + "　");
			mViewLeft2.setVisibility(View.VISIBLE);
			mLLLeft2.setVisibility(View.VISIBLE);
		} else {
			mViewLeft2.setVisibility(View.GONE);
		}
		if (text3 != null && !"".equals(text3)) {
			mViewLeft3.setText(text3);
			mViewLeft3.setVisibility(View.VISIBLE);
			mLLLeft3.setVisibility(View.VISIBLE);
		} else {
			mViewLeft3.setVisibility(View.GONE);
		}
	}

	/**
	 * 加空格兼容金立4.4.2手机
	 * 
	 * @param str
	 */
	public void setLeftText1(String str) {
		setText(mViewLeft1, str == null ? null : str + "　");
	}

	public void setLeftText2(String str) {
		setViewVisibility(mLLLeft2, ViewUtil.isEmpty(str));
		setText(mViewLeft2, str == null ? null : str + "　");
	}

	public void setLeftText3(String str) {
		setViewVisibility(mLLLeft3, ViewUtil.isEmpty(str));
		setText(mViewLeft3, str == null ? null : str + "　");
	}

	public void setRightText1(String str) {
		setText(mViewRight1, str);
	}

	public void setRightText2(String str) {
		setText(mViewRight2, str);
	}

	public void setLeftImage2(int drawableResId) {
		setImageResource(mImageViewLeft2, drawableResId);
		mLLLeft2.setVisibility(View.VISIBLE);
	}

	public void setLeftImage2(Drawable drawable) {
		setImageDrawable(mImageViewLeft2, drawable);
		mLLLeft2.setVisibility(View.VISIBLE);
	}

	public void setLeftImage3(int drawableResId) {
		setImageResource(mImageViewLeft3, drawableResId);
		mLLLeft3.setVisibility(View.VISIBLE);
	}

	public void setLeftImage3(Drawable drawable) {
		setImageDrawable(mImageViewLeft3, drawable);
		mLLLeft3.setVisibility(View.VISIBLE);
	}

	public void setRightImage1(int drawableResId) {
		setImageResource(mImageViewRight1, drawableResId);
	}

	public void setRightImage1(Drawable drawable) {
		setImageDrawable(mImageViewRight1, drawable);
	}

	public void setRightImage2(int drawableResId) {
		setImageResource(mImageViewRight2, drawableResId);
	}

	public void setRightImage2(Drawable drawable) {
		setImageDrawable(mImageViewRight2, drawable);
	}

	private void setImageDrawable(ImageView view, Drawable drawable) {
		if (view != null) {
			if (drawable != null) {
				view.setVisibility(View.VISIBLE);
			}
			view.setImageDrawable(drawable);
		}
	}

	private void setImageResource(ImageView view, int resId) {
		if (view != null) {
			view.setVisibility(View.VISIBLE);
			view.setImageResource(resId);
		}
	}

	private void setViewVisibility(View view, boolean show) {
		if (view != null) {
			if (show) {
				view.setVisibility(View.VISIBLE);
			} else {
				view.setVisibility(View.GONE);
			}
		}
	}

	public void setShowArrow(boolean show) {
		if (show) {
			mImageViewArrow.setVisibility(View.VISIBLE);
		} else {
			mImageViewArrow.setVisibility(View.GONE);
		}
	}

	@SuppressLint("NewApi")
	public void draw(Canvas canvas) {
		super.draw(canvas);
		// draw top line
		if (this.mViewType == TYPE_TOP || this.mViewType == TYPE_SINGLE) {
			// 设置顶部线条颜色额
			mPaint.setColor(this.getContext().getResources()
					.getColor(R.color.lj_color_app_bg));
			canvas.drawRect(0, 0, this.getWidth(), mLineHeight, mPaint);
		}

		float posX = 0;// mLLLeft2.getX();
		if (this.mViewType == TYPE_BOTTOM || this.mViewType == TYPE_SINGLE) {
			// 设置底部线条颜色
			mPaint.setColor(this.getContext().getResources()
					.getColor(R.color.lj_color_app_bg));
			posX = 0;
		} else {
			// 设置中间线条颜色
			mPaint.setColor(this.getContext().getResources()
					.getColor(R.color.lj_color_line_divider));
			// 可在此设置中间线条的左间距离
			posX = 0;
		}
		if (mShowLine) {
			canvas.drawRect(posX, this.getHeight() - mLineHeight,
					this.getWidth(), this.getHeight(), mPaint);
		}
	}
}
