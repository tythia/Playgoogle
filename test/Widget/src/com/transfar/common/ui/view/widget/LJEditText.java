package com.transfar.common.ui.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import com.transfar.common.ui.R;


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
