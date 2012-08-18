package net.insite;

import java.util.List;

import net.insite.domain.Chapter;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ChaptersAdapter extends BaseAdapter {

	private List<Chapter> data;
	private int layoutId;
	private Context context;
	
	public ChaptersAdapter(Context context, int layoutId){
		this.context = context;
		this.layoutId = layoutId;
		
	}

	public int getCount() {
		Log.i("ChaptersAdapter", "getCount");
		return data == null ? 0 : data.size();
	}

	public Object getItem(int index) {
		Log.i("ChaptersAdapter", "getItem");
		return data == null ? null : data.get(index);
	}

	public long getItemId(int index) {
		Log.i("ChaptersAdapter", "getItemId");
		return data == null ? null : data.get(index).hashCode();
	}

	public View getView(int index, View view, ViewGroup viewGroup) {
		Log.i("ChaptersAdapter", "getView");
		View newView = View.inflate(context, layoutId, null);
		
		TextView chapterNameTextView = (TextView)newView.findViewById(R.id.chapter_name);
		chapterNameTextView.setText(data.get(index).getName());
		
		return newView;
	}

	public void receiveData(List<Chapter> data) {
		Log.i("ChaptersAdapter", "receiveData" + data);
		this.data = data;
		super.notifyDataSetChanged();
	}
	
}