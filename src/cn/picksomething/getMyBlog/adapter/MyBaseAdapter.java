package cn.picksomething.getMyBlog.adapter;

import java.util.HashMap;
import java.util.List;

import cn.picksomething.getmyblog.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyBaseAdapter extends BaseAdapter {
	private Context context;
	private List<HashMap<String, Object>> listData;

	public MyBaseAdapter(Context context, List<HashMap<String, Object>> listData) {
		this.context = context;
		this.listData = listData;
	}

	@Override
	public int getCount() {
		return listData.size();
	}

	@Override
	public HashMap<String, Object> getItem(int position) {
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
			viewHolder.title = (TextView) convertView.findViewById(R.id.ItemTitle);
			// 将viewHolder绑定到convertView
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.title.setText((String) getItem(position).get("rel"));
		viewHolder.title.setSelected(true);

		return convertView;
	}

	final static class ViewHolder {
		TextView title;
	}

}
