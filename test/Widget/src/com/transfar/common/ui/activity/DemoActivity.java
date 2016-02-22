package com.transfar.common.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.transfar.common.ui.R;
import com.transfar.common.ui.view.widget.LJTableView;
import com.transfar.common.ui.view.widget.LJTitleBar;

public class DemoActivity extends BaseActivity {

	private LJTitleBar mTitleBar;
	private LJTableView mTableViewUserName;
	private LJTableView mTableViewUserAuth;
	private LJTableView mTableViewMobile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo);
		mTitleBar = (LJTitleBar) findViewById(R.id.title_bar);
		mTableViewUserName = (LJTableView) findViewById(R.id.c_tv_name);
		mTableViewUserAuth = (LJTableView) findViewById(R.id.c_tv_auth);
		mTableViewMobile = (LJTableView) findViewById(R.id.c_tv_mobile);
		mTitleBar.setBackBtnVisible(false);
		mTitleBar.setTitle("首页");
		initClicker();
		updateUserInfo();
	}

	private void initClicker() {
		mTableViewUserAuth.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(DemoActivity.this,
						UserAuthActivity.class);
				startActivity(intent);
			}
		});
		mTableViewMobile.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(DemoActivity.this,
						MobileListActivity.class);
				startActivity(intent);
			}
		});
	}

	private void updateUserInfo() {
		mTableViewUserName.setRightText("Jim Green");
	}

}
