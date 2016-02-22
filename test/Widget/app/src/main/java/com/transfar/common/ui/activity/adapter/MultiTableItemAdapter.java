package com.transfar.common.ui.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.transfar.common.ui.R;
import com.transfar.common.ui.view.widget.LJMultiTableView;

/**
 * @author cai.pengrui on 2015/11/4.
 * @Package com.transfar.lbcapp.adapter
 * @Title: OpeartorManageAdapter
 * @Description: (用一句话描述该文件做什么) Copyright (c) 传化物流版权所有 2015<br>
 *               Create DateTime: 2015/11/4<br>
 */
public class MultiTableItemAdapter extends BaseAdapter {

	private Context context;
	private Integer selectIdx = -1;

	public MultiTableItemAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.lj_item_multi_table_demo, null);
			convertView.setTag(holder);
			holder.initView(convertView);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// holder.tableView.setRightImage2(position == selectIdx ? context
		// .getResources().getDrawable(
		// R.drawable.contact_gallery_list_show) : context
		// .getResources().getDrawable(
		// R.drawable.contact_gallery_list_hide));
		holder.tableView.setLeftText("15088886666", "威廉·杰斐逊·克林顿威廉·杰斐逊·克林顿", null);
		holder.tableView.setRightText1("查看");
		return convertView;
	}

	private class ViewHolder {
		private LJMultiTableView tableView;

		public void initView(View view) {
			tableView = (LJMultiTableView) view
					.findViewById(R.id.c_multi_table_view);
		}

	}

}
