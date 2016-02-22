package com.transfar.common.ui.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import com.transfar.common.ui.R;

/**
 * @author ruisong.li on 2015/11/16.
 * @Package com.transfar.lbcapp.component.view.widget
 * @Title: LJEditText
 * @Description: 自定义文本输入框 Copyright (c) 传化物流版权所有 2015<br>
 *               Create DateTime: 2015/11/16<br>
 */
public class LJEditText extends EditText {

	public LJEditText(Context context) {
		this(context, null);
	}

	public LJEditText(Context context, AttributeSet attrs) {
		this(context, attrs, android.R.attr.editTextStyle);
	}

	public LJEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setBackgroundColor(context.getResources().getColor(
				R.color.lj_color_transparent));
	}
}
