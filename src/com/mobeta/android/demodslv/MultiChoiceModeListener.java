package com.mobeta.android.demodslv;



import android.content.Context;
import android.graphics.Color;
import android.text.GetChars;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.GridView;
import android.widget.ListView;

public class MultiChoiceModeListener implements
		ListView.MultiChoiceModeListener {
	ListView mGrid;

	public MultiChoiceModeListener(ListView v,Context ctx) {
		
		mGrid = v;
		// TODO Auto-generated constructor stub
	}

	public boolean onCreateActionMode(ActionMode mode, Menu menu) {
		mode.setTitle("Select Items");
		mode.setSubtitle("One item selected");
		return true;
	}

	public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
		return true;
	}

	public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
		return true;
	}

	public void onDestroyActionMode(ActionMode mode) {
	}

	public void onItemCheckedStateChanged(ActionMode mode, int position,
			long id, boolean checked) {
		//mGrid.setDrawSelectorOnTop(checked);
		// mGrid.setDrawSelectorOnTop(checked);
		int selectCount = mGrid.getCheckedItemCount();
		//View v=getChildAt(position);
		//v.setBackgroundColor(Color.parseColor("#222222"));
		//setForeground(checked ? getResources().getDrawable(R.drawable.blue_color): null);
		//View v=(View) mGrid.getItemAtPosition(position);
		//v.setBackgroundResource(Color.parseColor("#222222"));
		switch (selectCount) {
		case 1:
			mode.setSubtitle("One item selected");
			break;
		default:
			mode.setSubtitle("" + selectCount + " items selected");
			break;
		}
	}

	
	


}