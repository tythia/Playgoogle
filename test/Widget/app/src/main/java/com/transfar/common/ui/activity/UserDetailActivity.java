package com.transfar.common.ui.activity;

import android.os.Bundle;

import com.transfar.common.ui.R;
import com.transfar.common.ui.view.widget.LJTitleBar;

public class UserDetailActivity extends BaseActivity {

	private LJTitleBar mTitleBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_detail);
		mTitleBar = (LJTitleBar) findViewById(R.id.title_bar);
		mTitleBar.setTitle("详情");
	}

}
