package com.transfar.common.ui.view.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.transfar.common.ui.R;

/**
 * @author ruisong.li on 2015/11/16.
 * @Package com.transfar.lbcapp.component.view.widget
 * @Title: LJInputView
 * @Description: 自定义输入标签，支持文字输入+按钮及 （图片）+文字提示+输入框+提交按钮 Copyright (c) 传化物流版权所有
 *               2015<br>
 *               Create DateTime: 2015/11/16<br>
 */
public class LJInputView extends RelativeLayout {

	// 控件类型，上/中/下/单独条目
	public final static int TYPE_TOP = 0;
	public final static int TYPE_CENTER = 1;
	public final static int TYPE_BOTTOM = 2;
	public final static int TYPE_SINGLE = 3;
	// 输入文本类型
	public static final int INPUT_TYPE_NUMBER = 0;
	public static final int INPUT_TYPE_PASSWORD = 1;
	public static final int INPUT_TYPE_NONE = 2;
	public static final int INPUT_TYPE_NULL = 3;
	// 输入文本的对齐方式
	public static final int TEXT_ALIGN_LEFT = 0;
	public static final int TEXT_ALIGN_CENTER = 1;
	public static final int TEXT_ALIGN_RIGHT = 2;

	private ImageView mIVLeft;
	private TextView mTVLeft;
	private LJEditText mEditText;
	private ImageView mIVDelete;
	private Button mBtnCommit;

	private String mLeft;
	private String mContent;
	private String mHint;
	private String mBtnText;
	private int mLeftColor;
	private int mContentColor;
	private int mLeftCharNum;
	private int mHintColor;
	private Drawable mLeftImage;
	private int mType;
	private int mInputType;
	private int mInputLength;
	private boolean mShowDelete;
	private int mTextAlign;

	private Paint mPaint;
	private int mLineHeight;

	public LJInputView(Context context) {
		this(context, null);
	}

	public LJInputView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public LJInputView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setWillNotDraw(false);
		LayoutInflater.from(context)
				.inflate(R.layout.lj_input_view, this, true);
		mIVLeft = (ImageView) findViewById(R.id.iv_left);
		mTVLeft = (TextView) findViewById(R.id.tv_left);
		mEditText = (LJEditText) findViewById(R.id.et_content);
		mBtnCommit = (Button) findViewById(R.id.btn_submit);
		mIVDelete = (ImageView) findViewById(R.id.iv_delete);
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.LJ_Input_View);
		mLeftImage = a.getDrawable(R.styleable.LJ_Input_View_inputLeftImage);
		mLeft = a.getString(R.styleable.LJ_Input_View_inputLeftText);
		mContent = a.getString(R.styleable.LJ_Input_View_inputContentText);
		mHint = a.getString(R.styleable.LJ_Input_View_inputContentHint);
		mBtnText = a.getString(R.styleable.LJ_Input_View_inputBtnText);
		mLeftColor = a.getColor(R.styleable.LJ_Input_View_inputLeftTextColor,
				context.getResources()
						.getColor(R.color.lj_textcolor_black_dark));
		mHintColor = a.getColor(
				R.styleable.LJ_Input_View_inputHintColor,
				context.getResources().getColor(
						R.color.lj_textcolor_gray_lighter));
		mContentColor = a.getColor(
				R.styleable.LJ_Input_View_inputContentTextColor,
				context.getResources()
						.getColor(R.color.lj_textcolor_black_dark));
		mLeftCharNum = a.getInteger(R.styleable.LJ_Input_View_inputLeftCharNum,
				0);
		mType = a.getInteger(R.styleable.LJ_Input_View_inputViewType, -1);
		mInputType = a.getInteger(R.styleable.LJ_Input_View_inputType, -1);
		mInputLength = a.getInteger(
				R.styleable.LJ_Input_View_inputMaxInputLength, -1);
		mTextAlign = a.getInteger(R.styleable.LJ_Input_View_inputTextAlign,
				TEXT_ALIGN_LEFT);
		mShowDelete = a.getBoolean(R.styleable.LJ_Input_View_inputShowDelete,
				true);
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
		if (mLeftImage != null) {
			setLeftImage(mLeftImage);
		}
		if (mLeftCharNum > 0) {
			setLeftText(mLeft, mLeftCharNum);
		} else {
			setLeftText(mLeft);
		}
		setContentText(mContent);
		setContentHint(mHint);
		setButtonText(mBtnText);
		setLeftTextColor(mLeftColor);
		setContentHintColor(mHintColor);
		setContentColor(mContentColor);
		setInputLength(mInputLength);
		setShowDelete(mShowDelete);
		mIVDelete.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mEditText.setText("");
			}
		});
		mEditText.setSingleLine(true);
		setInputType(mInputType);
		setContentTextAlign(mTextAlign);
		mEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				if (mShowDelete && arg0.length() > 0) {
					mIVDelete.setVisibility(View.VISIBLE);
				} else {
					mIVDelete.setVisibility(View.GONE);
				}
			}
		});
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		initView();
	}

	public ImageView getLeftImage() {
		return mIVLeft;
	}

	public LJEditText getContentView() {
		return mEditText;
	}

	public Button getRightButton() {
		return mBtnCommit;
	}

	public String getContent() {
		return mEditText.getText().toString();
	}

	/**
	 * 设置左侧图片
	 * 
	 * @param resId
	 * 
	 * @see #setLeftImage(int)
	 */
	public void setLeftImage(Drawable drawable) {
		mIVLeft.setImageDrawable(drawable);
		mIVLeft.setVisibility(View.VISIBLE);
	}

	/**
	 * 设置左侧图片
	 * 
	 * @param resId
	 * 
	 * @see #setLeftImage(Drawable)
	 */
	public void setLeftImage(int resId) {
		mIVLeft.setImageResource(resId);
		mIVLeft.setVisibility(View.VISIBLE);
	}

	public void setLeftTextColor(int colorResId) {
		mTVLeft.setTextColor(colorResId);
	}

	public void setContentColor(int colorResId) {
		mEditText.setTextColor(colorResId);
	}

	public void setContentHintColor(int colorResId) {
		mEditText.setHintTextColor(colorResId);
	}

	public void setLeftText(String str) {
		if (str != null && !"".equals(str)) {
			mTVLeft.setVisibility(View.VISIBLE);
		}
		mTVLeft.setText(str);
	}

	public void setLeftText(String str, int charNum) {
		if (str != null && charNum > 0) {
			while (str.length() < charNum) {
				str += "　";
			}
		}
		setLeftText(str);
	}

	public void setContentText(String str) {
		mEditText.setText(str);
	}

	public void setContentHint(String hint) {
		mEditText.setHint(hint);
	}

	public void setButtonText(String str) {
		if (str != null && !"".equals(str)) {
			mBtnCommit.setVisibility(View.VISIBLE);
		}
		mBtnCommit.setText(str);
	}

	/**
	 * <li>{@link #TYPE_TOP}</li> <li>{@link #TYPE_CENTER}</li> <li>
	 * {@link #TYPE_BOTTOM}</li> <li>{@link #TYPE_SINGLE}</li>
	 * 
	 * @param type
	 */
	public void setViewType(int type) {
		mType = type;
	}

	/**
	 * <li>{@link #INPUT_TYPE_NUMBER}</li> <li>{@link #INPUT_TYPE_PASSWORD}</li>
	 * <li>
	 * {@link #INPUT_TYPE_NONE}</li>
	 * <li>
	 * {@link #INPUT_TYPE_NULL}</li>
	 * 
	 * @param type
	 */
	public void setInputType(int type) {
		switch (type) {
		case INPUT_TYPE_NUMBER:
			mInputType = INPUT_TYPE_NUMBER;
			type = InputType.TYPE_CLASS_NUMBER;
			break;
		case INPUT_TYPE_PASSWORD:
			mInputType = INPUT_TYPE_PASSWORD;
			type = InputType.TYPE_CLASS_TEXT
					| InputType.TYPE_TEXT_VARIATION_PASSWORD;
			break;
		case INPUT_TYPE_NULL:
			mInputType = INPUT_TYPE_PASSWORD;
			type = InputType.TYPE_NULL;
			break;
		case INPUT_TYPE_NONE:
		default:
			mInputType = INPUT_TYPE_NONE;
			type = InputType.TYPE_CLASS_TEXT;
			break;
		}
		mEditText.setInputType(type);
	}

	/**
	 * <li>{@link #TEXT_ALIGN_LEFT}</li> <li>{@link #TEXT_ALIGN_CENTER}</li> <li>
	 * {@link #TEXT_ALIGN_RIGHT}</li>
	 * 
	 * @param align
	 */
	public void setContentTextAlign(int align) {
		switch (align) {
		case TEXT_ALIGN_LEFT:
			mEditText.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
			break;
		case TEXT_ALIGN_CENTER:
			mEditText.setGravity(Gravity.CENTER);
			break;
		case TEXT_ALIGN_RIGHT:
			mEditText.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
			break;
		}
	}

	private void setShowDelete(boolean show) {
		mShowDelete = show;
	}

	/**
	 * 限制文本输入长度
	 * 
	 * @param maxLength
	 */
	private void setInputLength(int maxLength) {
		if (maxLength > 0) {
			mEditText
					.setFilters(new InputFilter[] { new InputFilter.LengthFilter(
							maxLength) });
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
		float posX = 0;
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
			posX = 0;
		}
		canvas.drawRect(posX, this.getHeight() - mLineHeight, this.getWidth(),
				this.getHeight(), mPaint);
	}

}
