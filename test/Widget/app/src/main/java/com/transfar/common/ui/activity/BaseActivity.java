package com.transfar.common.ui.activity;

import android.app.Activity;
import android.widget.Toast;

public class BaseActivity extends Activity {

	public void showToast(String str) {
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}
}
