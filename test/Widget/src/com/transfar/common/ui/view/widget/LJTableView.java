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
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.transfar.common.ui.R;

/**
 * 
 * <!-- 左侧文字 --> <attr name="leftText" format="string" /> <!-- 右侧文字 --> <attr
 * name="rightText" format="string" /> <!-- 中间文字 --> <attr name="contentText"
 * format="string" /> <!-- 是否显示右箭头 --> <attr name="showArrow" format="boolean"
 * /> <!-- 中间文字颜色 --> <attr name="contentTextColor" format="reference|color" />
 * <!-- 右侧文字颜色 --> <attr name="rightTextColor" format="reference" /> <!--
 * 左侧显示的图片 --> <attr name="leftImage" format="reference" /> <!-- 是否可以改变背景色 -->
 * <attr name="changeBackground" format="boolean" /> <!-- 左侧文字数目，不够的已空格补充 -->
 * <attr name="leftCharNum" format="integer" /> <!-- 控件显示位置 --> <attr
 * name="type"> <flag name="top" value="0" /> <flag name="center" value="1" />
 * <flag name="bottom" value="2" /> <flag name="single" value="3" /> </attr>
 * 
 * @description 条目显示控件, 支持“左测文字-中间文字-右侧文字（-右箭头）”</br>
 *              “(左侧图片)-左侧文字-中间图片-右侧文字-右箭头”等样式
 * @author ruisong.li on 2015/11/12.
 * @Package com.transfar.lbcapp.ui.view.widget
 * @Title: LJTableView Copyright (c) 传化物流版权所有 2015<br>
 *         Create DateTime: 2015/11/12<br>
 */
public class LJTableView extends RelativeLayout implements OnClickListener {

	// 控件类型，上/中/下/单独条目
	public final static int TYPE_TOP = 0;
	public final static int TYPE_CENTER = 1;
	public final static int TYPE_BOTTOM = 2;
	public final static int TYPE_SINGLE = 3;

	// 设置文字颜色
	public final static int COLOR_RED = 0;
	public final static int COLOR_GREEN = 1;
	public final static int COLOR_BLUE = 2;
	public final static int COLOR_GRAY = 3;
	public final static int COLOR_BLACK = 4;

	// 控件
	private ImageView mLeftImage;
	private ImageView mContentImage;
	private ImageView mArrow;
	private TextView mLeftText;
	private TextView mRightText;
	private TextView mContentText;
	private LinearLayout mLeftView;
	private LinearLayout mRightContainer;

	// 内容相关设置
	private String mLeft;
	private String mRight;
	private String mContent;
	private boolean mShowArrow;
	private boolean mChangeBg;
	private int mType;
	private int mImage;
	private int mLeftColor;
	private int mContentColor;
	private int mRightTextColor;
	private int mLeftCharNum;
	private int mRightTextHintColor;
	private String mRightHint;

	private Paint mPaint;
	private int mLineHeight;
	private boolean mShowLine;

	public LJTableView(Context context) {
		this(context, null);
	}

	public LJTableView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public LJTableView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		LayoutInflater.from(context).inflate(R.layout.lj_table_view, this, true);
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.LJ_Table_View);
		mContent = a.getString(R.styleable.LJ_Table_View_contentText);
		mLeft = a.getString(R.styleable.LJ_Table_View_leftText);
		mRight = a.getString(R.styleable.LJ_Table_View_rightText);
		mRightHint = a.getString(R.styleable.LJ_Table_View_rightTextHint);
		mShowArrow = a.getBoolean(R.styleable.LJ_Table_View_showArrow, true);
		mType = a.getInteger(R.styleable.LJ_Table_View_type, -1);
		mLeftCharNum = a.getInteger(R.styleable.LJ_Table_View_leftCharNum, 0);
		mLeftColor = a.getColor(R.styleable.LJ_Table_View_leftTextColor, context
				.getResources().getColor(R.color.lj_textcolor_black_dark));
		mContentColor = a.getColor(
				R.styleable.LJ_Table_View_contentTextColor,
				context.getResources().getColor(
						R.color.lj_textcolor_gray_normal));
		mRightTextColor = a.getColor(
				R.styleable.LJ_Table_View_rightTextColor,
				context.getResources().getColor(
						R.color.lj_textcolor_gray_normal));
		mRightTextHintColor = a.getColor(
				R.styleable.LJ_Table_View_rightTextHintColor,
				context.getResources().getColor(
						R.color.lj_textcolor_hint));
		mImage = a.getResourceId(R.styleable.LJ_Table_View_leftImage, 0);
		mChangeBg = a.getBoolean(R.styleable.LJ_Table_View_changeBackground, true);
		a.recycle();
		initView();

		mPaint = new Paint();
		mPaint.setColor(this.getContext().getResources()
				.getColor(R.color.lj_color_line_divider));
		mPaint.setAntiAlias(true);

		mLineHeight = this.getResources().getDimensionPixelSize(
				R.dimen.lj_divider_line_height);

	}

	private void initView() {
		mLeftView = (LinearLayout) findViewById(R.id.ll_left);
		mRightContainer = (LinearLayout) findViewById(R.id.ll_right_container);
		mLeftImage = (ImageView) findViewById(R.id.iv_left);
		mContentImage = (ImageView) findViewById(R.id.iv_content_image);
		mArrow = (ImageView) findViewById(R.id.iv_arrow);
		mLeftText = (TextView) findViewById(R.id.tv_left);
		mRightText = (TextView) findViewById(R.id.tv_right);
		mContentText = (TextView) findViewById(R.id.tv_content);

		if (mLeftCharNum > 0) {
			setLeftText(mLeft, mLeftCharNum);
		} else {
			setLeftText(mLeft);
		}
		setRightText(mRight);
		setContent(mContent);
		if (mLeft != null && !"".equals(mLeft)) {
			mLeftText.setVisibility(View.VISIBLE);
		}
		if (mRight != null && !"".equals(mRight)) {
			mRightText.setVisibility(View.VISIBLE);
		}
		if (mContent != null && !"".equals(mContent)) {
			mContentText.setVisibility(View.VISIBLE);
		}
		showArrow(mShowArrow);
		if (mChangeBg) {
			setOnClickListener(this);
		}
		setBackground(mChangeBg);
		setViewType(mType);
		if (mImage > 0) {
			setLeftImage(mImage);
		}
		mLeftText.setTextColor(mLeftColor);
		mContentText.setTextColor(mContentColor);
		mRightText.setTextColor(mRightTextColor);
		mRightText.setHintTextColor(mRightTextHintColor);
		if (mRightHint != null && mRightHint.length() > 0) {
			mRightText.setHint(mRightHint);
			mRightText.setVisibility(View.VISIBLE);
		}
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
		mType = type;
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

	/**
	 * 设置文字颜色 ,有效的colorId为： <li>{@link #COLOR_BLACK}</li> <li>
	 * {@link #COLOR_BLUE}</li> <li>
	 * {@link #COLOR_GRAY}</li> <li>{@link #COLOR_GREEN}</li> <li>
	 * {@link #COLOR_RED}</li>
	 * 
	 * @param view
	 * @param colorId
	 */
	public void setTextColor(TextView view, int colorId) {
		if (view != null) {
			int color = getResources().getColor(
					R.color.lj_textcolor_black_normal);
			switch (colorId) {
			case COLOR_BLACK:
				color = getResources().getColor(
						R.color.lj_textcolor_black_normal);
				break;
			case COLOR_BLUE:
				color = getResources().getColor(R.color.lj_color_blue);
				break;
			case COLOR_GRAY:
				color = getResources().getColor(
						R.color.lj_textcolor_gray_lighter);
				break;
			case COLOR_GREEN:
				color = getResources().getColor(R.color.lj_color_green);
				break;
			case COLOR_RED:
				color = getResources().getColor(R.color.lj_color_red);
				break;
			}
			view.setTextColor(color);
		}
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		initView();
	}

	public ImageView getLeftImageView() {
		return mLeftImage;
	}

	public TextView getLeftTextView() {
		return mLeftText;
	}

	/**
	 * 获取内容显示文字控件
	 * 
	 * @return
	 */
	public TextView getContentView() {
		return mContentText;
	}

	public ImageView getContentImageView() {
		return mContentImage;
	}

	public TextView getRightTextView() {
		return mRightText;
	}

	/**
	 * 获取右侧显示容器，可添加自定义view
	 * 
	 * @return
	 */
	public LinearLayout getRightContainer() {
		return mRightContainer;
	}

	/**
	 * 设置左侧图片
	 * 
	 * @param resId
	 * 
	 * @see #setLeftImage(Drawable)
	 */
	public void setLeftImage(int resId) {
		mLeftImage.setImageResource(resId);
		mLeftImage.setVisibility(View.VISIBLE);
	}

	/**
	 * 设置左侧图片
	 * 
	 * @param resId
	 * @see #setLeftImage(int)
	 */
	public void setLeftImage(Drawable drawable) {
		mLeftImage.setImageDrawable(drawable);
		mLeftImage.setVisibility(View.VISIBLE);
	}

	/**
	 * 设置左侧图片
	 * 
	 * @param resId
	 * 
	 * @see #setContentImage(Drawable)
	 */
	public void setContentImage(int resId) {
		mContentImage.setImageResource(resId);
		mContentImage.setVisibility(View.VISIBLE);
	}

	/**
	 * 设置中间图片
	 * 
	 * @param resId
	 * @see #setContentImage(int)
	 */
	public void setContentImage(Drawable drawable) {
		mContentImage.setImageDrawable(drawable);
		mContentImage.setVisibility(View.VISIBLE);
	}

	/**
	 * 设置显示的文字
	 * 
	 * @param str
	 */
	public void setContent(String str) {
		mContentText.setText(str);
	}

	/**
	 * 设置左侧文字
	 * 
	 * @param str
	 */
	public void setLeftText(String str) {
		mLeftText.setText(str);
	}

	/**
	 * 设置左侧文字
	 * 
	 * @param str
	 */
	public void setLeftText(String str, int charNum) {
		if (str != null && str.length() > 0 && charNum > 0) {
			while (str.length() < charNum) {
				str += "　";
			}
		}
		mLeftText.setText(str);
	}

	/**
	 * 设置右侧文字
	 * 
	 * @param str
	 */
	public void setRightText(String str) {
		mRightText.setText(str);
	}

	/**
	 * 在空件右侧，箭头左侧添加自定义view
	 * 
	 * @param view
	 */
	public void addRightChildView(View view) {
		if (view != null) {
			int childs = mRightContainer.getChildCount();
			if (childs > 2) {
				for (int i = 0; i < childs - 2; i++) {
					mRightContainer.removeView(mRightContainer.getChildAt(0));
				}
			}
			mRightContainer.addView(view, 0);
		}
	}

	/**
	 * 是否显示右箭头
	 * 
	 * @param show
	 */
	public void showArrow(boolean show) {
		if (show) {
			mArrow.setVisibility(View.VISIBLE);
		} else {
			mArrow.setVisibility(View.GONE);
		}
	}

	@SuppressLint("NewApi")
	public void draw(Canvas canvas) {
		super.draw(canvas);
		// draw top line
		if (this.mType == TYPE_TOP || this.mType == TYPE_SINGLE) {
			// 设置顶部线条颜色额
			mPaint.setColor(this.getContext().getResources()
					.getColor(R.color.lj_color_app_bg));
			canvas.drawRect(0, 0, this.getWidth(), mLineHeight, mPaint);
		}

		float posX = mLeftView.getX();
		if (this.mType == TYPE_BOTTOM || this.mType == TYPE_SINGLE) {
			// 设置底部线条颜色
			mPaint.setColor(this.getContext().getResources()
					.getColor(R.color.lj_color_app_bg));
			posX = 0;
		} else {
			// 设置中间线条颜色
			mPaint.setColor(this.getContext().getResources()
					.getColor(R.color.lj_color_line_divider));
			// 可在此设置中间线条的左间距离
			posX = 0;//mLeftText.getX();
		}
		if (mShowLine) {
			canvas.drawRect(posX, this.getHeight() - mLineHeight,
					this.getWidth(), this.getHeight(), mPaint);
		}
	}

	@Override
	public void onClick(View v) {

	}

}
