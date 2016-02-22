package com.transfar.common.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.transfar.common.ui.R;
import com.transfar.common.ui.view.widget.LJTitleBar;

public class UserAuthActivity extends BaseActivity {

	private LJTitleBar mTitleBar;
	private Button mBtnCommit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_auth);
		mTitleBar = (LJTitleBar) findViewById(R.id.title_bar);
		mBtnCommit = (Button) findViewById(R.id.btn_commit);
		mTitleBar.setTitle("会员认证");
		mBtnCommit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showToast("提交成功，待审核");
				finish();
			}
		});
	}

}
