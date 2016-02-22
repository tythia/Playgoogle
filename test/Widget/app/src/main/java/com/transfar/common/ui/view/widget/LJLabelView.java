package com.transfar.common.ui.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.transfar.common.ui.R;

/**
 * @author ruisong.li on 2015/11/16.
 * @Package com.transfar.lbcapp.component.view.widget
 * @Title: LJLabelView
 * @Description: 自定义说明性提示标签，支持左中右文字布局
 * Copyright (c) 传化物流版权所有 2015<br>
 * Create DateTime: 2015/11/16<br>
 */
public class LJLabelView extends RelativeLayout {

	private TextView mViewLeft;
	private TextView mViewContent;
	private TextView mViewRight;

	public LJLabelView(Context context) {
		this(context, null);
	}

	public LJLabelView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public LJLabelView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		LayoutInflater.from(context)
				.inflate(R.layout.lj_label_view, this, true);
		mViewLeft = (TextView) findViewById(R.id.tv_left);
		mViewContent = (TextView) findViewById(R.id.tv_content);
		mViewRight = (TextView) findViewById(R.id.tv_right);
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.LJ_Label_View);
		String left = a.getString(R.styleable.LJ_Label_View_labelLeftText);
		String content = a
				.getString(R.styleable.LJ_Label_View_labelContentText);
		String right = a.getString(R.styleable.LJ_Label_View_labelRightText);
		int leftColor = a.getColor(
				R.styleable.LJ_Label_View_labelLeftColor,
				context.getResources().getColor(
						R.color.lj_textcolor_gray_normal));
		int contentColor = a.getColor(
				R.styleable.LJ_Label_View_labelContentColor,
				context.getResources().getColor(
						R.color.lj_textcolor_gray_normal));
		int rightColor = a.getColor(
				R.styleable.LJ_Label_View_labelRightColor,
				context.getResources().getColor(
						R.color.lj_textcolor_gray_normal));
		a.recycle();
		setLeftText(left);
		setContentText(content);
		setRightText(right);
		setLeftTextColor(leftColor);
		setContentTextColor(contentColor);
		setRightTextColor(rightColor);
	}
	
	public void setLeftText(String str) {
		mViewLeft.setText(str);
	}

	public void setContentText(String str) {
		mViewContent.setText(str);
	}

	public void setRightText(String str) {
		mViewRight.setText(str);
	}

	public void setLeftTextColor(int colorResId) {
		mViewLeft.setTextColor(colorResId);
	}

	public void setContentTextColor(int colorResId) {
		mViewContent.setTextColor(colorResId);
	}

	public void setRightTextColor(int colorResId) {
		mViewRight.setTextColor(colorResId);
	}

	public TextView getLeftView() {
		return mViewLeft;
	}

	public TextView getContextView() {
		return mViewContent;
	}

	public TextView getRightView() {
		return mViewRight;
	}
}
