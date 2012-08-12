package net.insite;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ToursAdapter extends BaseAdapter {

	private List<Tour> data;
	private ViewGroup parent;
	private int childId;
	private Context context;
	
	public ToursAdapter(Context context, ViewGroup parent, int childId){
		this.context = context;
		this.parent = parent;
		this.childId = childId;
		
	}

	public int getCount() {
		Log.i("ToursAdapter", "getCount");
		return data == null ? 0 : data.size();
	}

	public Object getItem(int index) {
		Log.i("ToursAdapter", "getItem");
		return data == null ? null : data.get(index);
	}

	public long getItemId(int index) {
		Log.i("ToursAdapter", "getItemId");
		return data == null ? null : data.get(index).hashCode();
	}

	public View getView(int index, View view, ViewGroup viewGroup) {
		Log.i("ToursAdapter", "getvView");
		View newView = View.inflate(context, childId, null);
		TextView textView = (TextView)newView.findViewById(R.id.textView1);
		textView.setText(data.get(index).getTitle());
		return newView;
	}

	public void receiveData(List<Tour> data) {
		Log.i("ToursAdapter", "receiveData");
		this.data = data;
		super.notifyDataSetChanged();
	}
	
}