package com.transfar.common.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.transfar.common.ui.R;
import com.transfar.common.ui.activity.adapter.MultiTableItemAdapter;
import com.transfar.common.ui.view.widget.LJTitleBar;

public class MobileListActivity extends BaseActivity {

	private LJTitleBar mTitleBar;
	private MultiTableItemAdapter mAdapter;
	private ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mobile_list);
		mTitleBar = (LJTitleBar) findViewById(R.id.title_bar);
		mListView = (ListView) findViewById(R.id.list_view);
		mTitleBar.setTitle("选择手机号");
		mAdapter = new MultiTableItemAdapter(this);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				Intent intent = new Intent(MobileListActivity.this,
						UserDetailActivity.class);
				startActivity(intent);
			}
		});
	}

}
