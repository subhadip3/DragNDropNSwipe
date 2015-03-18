package com.mobeta.android.demodslv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;


import com.mobeta.android.demodslv.Contact;
import com.mobeta.android.demodslv.DatabaseHandler;
// import com.snandi.android.DragSortListView;
import com.mobeta.android.demodslv.R;
import com.mobeta.android.dslv.DragSortController;
import com.mobeta.android.dslv.DragSortListView;


public class MainActivity extends ListActivity
{
	private ArrayAdapter<String> adapter;

	DragSortListView list;
	// MyAdapter adapter;
	List<Contact> content2, content1;
	ArrayList<String> arrayList;

	DatabaseHandler db;

	private DragSortListView.DropListener onDrop = new DragSortListView.DropListener()
	{
		@Override
		public void drop(int from, int to)
		{

			
			// modifying in database
			Contact temp;
			Log.d("Dropping from: ", "" + from);
			DatabaseHandler db = new DatabaseHandler(getApplicationContext());
			if (to < from)//2 4
			{
				temp = content1.get(from);
				temp.setNewPos(to + 1);
				db.updateContact(temp);
				for (int i = to; i < from; i++)
				{
					temp = content1.get(i);
					temp.setNewPos(i + 2);
					db.updateContact(temp);
				}
			}
			else if(to>from)
			{
				temp = content1.get(from);
				temp.setNewPos(to + 1);
				db.updateContact(temp);
				for (int i = from + 1; i <= to; i++)
				{
					temp = content1.get(i);
					temp.setNewPos(i);
					db.updateContact(temp);
				}
			}
			else
			{
				temp = content1.get(from);
				
				
				Log.d("x1"+list.x1,"x2"+list.x2);
				if((list.x2-list.x1)>500||(list.x2-list.x1)<-500)
				{
					for(int i=from+1;i<content1.size();i++)
					{
						Contact temp1;
						temp1 = content1.get(i);
						Log.d("positioning"+i,temp1.getNewPos());
						int x=Integer.parseInt(temp1.getNewPos())-1;
						temp1.setNewPos(x);
						db.updateContact(temp1);
					}
					
					
					
				  adapter.remove(arrayList.get(from));
				  adapter.notifyDataSetChanged();
				  
			      db.deleteContact(temp);
				}
				
			}
			
			 /*content1=db.getAllContacts(); 
			 temp = content1.get(from);
				 content1.remove(from);
				 content1.add(to, temp);*/
			content1=new ArrayList<Contact>();
			//content2=new ArrayList<Contact>();
			content2 = db.getAllContacts();
			for(int i=0;i<content2.size();i++)
			{
				Contact temp1;
				temp1 = content2.get(i);
				//Log.d("positioning"+i,temp1.getNewPos());
				Log.d("Newpos:",""+temp1.getNewPos());
				//db.updateContact(temp1);
			}
			Log.d("Content2 size=", "" + content2.size());
			
			for (int i = 1; i <= content2.size(); i++)
			{
				for (int j = 0; j < content2.size(); j++)
				{
					if (Integer.parseInt(content2.get(j).getNewPos()) == i)
					{
						content1.add(content2.get(j));
						break;
					}
				}
			}
			Log.d("Content1 size=", "" + content1.size());
			
			
			db.close();
			adapter.notifyDataSetChanged();
			// modifying in adapter
			if (from != to)
			{	
				// DragSortListView list = getListView();
				String item = adapter.getItem(from);
				adapter.remove(item);
				adapter.insert(item, to); // list.moveCheckState(from, to);
				// Log.d("DSLV","Selected item is " + list.getCheckedItemPosition());
			}

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.checkable_main);

		
		
		
		//DragSortListView lv=(DragSortListView)findViewById(R.id.list);
		
		db = new DatabaseHandler(getApplicationContext());
		// adding to db
	    Contact contact = new Contact(1, "ghg", "12345", 1);
		db.addContact(contact);
		contact = new Contact(2, "hhg", "12345", 2);
		db.addContact(contact);
		contact = new Contact(3, "hgfh", "12345", 3);
		db.addContact(contact);
		contact = new Contact(4, "jhgh", "123", 4);
		db.addContact(contact);
		contact = new Contact(5, "jgjg", "123", 5);
		db.addContact(contact);
		contact = new Contact(6, "ghg", "12345", 6);
		db.addContact(contact);
		contact = new Contact(7, "hhg", "12345", 7);
		db.addContact(contact);
		contact = new Contact(8, "hgfh", "12345", 8);
		db.addContact(contact);
		contact = new Contact(9, "jhgh", "123", 9);
		db.addContact(contact);
		contact = new Contact(10, "jgjg", "123", 10);
		db.addContact(contact);
	
		
		
		
		
		
		
		
		content1 = new ArrayList<Contact>();

		content2 = db.getAllContacts();
		
		for(int i=0;i<content2.size();i++)
		{
			Log.d("id", ""+content2.get(i).getID());
			Log.d("newpos", ""+content2.get(i).getNewPos());
		}
		
		Log.d("content2size", ""+content2.size());

		for (int i = 1; i <= content2.size(); i++)
		{
			for (int j = 0; j < content2.size(); j++)
			{
				if (Integer.parseInt(content2.get(j).getNewPos()) == i)
				{
					content1.add(content2.get(j));
					break;
				}
			}
		}

		Log.d("content1size", ""+content1.size());
		String[] array=new String[content1.size()];
		for(int i=0;i<content1.size();i++)
		{
			array[i]=content1.get(i).getID();
		}
		 //= { content1.get(0).getID(), content1.get(1).getID(), content1.get(2).getID(), content1.get(3).getID(), content1.get(4).getID() };

		arrayList = new ArrayList<String>(Arrays.asList(array));

		adapter = new ArrayAdapter<String>(this, R.layout.list_item_radio, R.id.text, arrayList);
		setListAdapter(adapter);
		
		/*
		 * db = new DatabaseHandler(getApplicationContext()); List<Contact> content1 = new ArrayList<Contact>(); for
		 * (int i = 1; i <= 5; i++) { Contact contact = new Contact(i, i); db.addContact(contact); } List<Contact>
		 * content2 = db.getAllContacts(); for (int i = 1; i <= content2.size(); i++) { for (int j = 0; j <
		 * content2.size(); j++) { if (Integer.parseInt(content2.get(j).getNewPos()) == i) {
		 * content1.add(content2.get(j)); break; } } } setListAdapter(new DragNDropAdapter(this, new int[] {
		 * R.layout.dragitem }, new int[] { R.id.TextView01, R.id.TextView02 }, content1));// new
		 */
	    list = getListView();
	    
	    
		list.setDropListener(onDrop);
		list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		list.setMultiChoiceModeListener(new MultiChoiceModeListener(list,getApplicationContext()));
		//list.setOnClickListener(n);
		
		// list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	}

	@Override
	public DragSortListView getListView()
	{
		return (DragSortListView) super.getListView();
	}

}
