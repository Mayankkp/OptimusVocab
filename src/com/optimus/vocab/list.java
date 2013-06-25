package com.optimus.vocab;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.xml.transform.Templates;



import android.R.integer;
import android.R.xml;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class list extends BaseAdapter {
	private ArrayList<String> list_items;
	int Total_question = 0;
	Context context;

	public list(ArrayList<String> list_items) {
		this.list_items = list_items;
	}

	public list(int totalQuesions,Context context) {
		// TODO Auto-generated constructor stub
		this.context=context;
		this.Total_question=totalQuesions;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Total_question;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		LayoutInflater infalInflater = (LayoutInflater) context
		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		arg1 = infalInflater.inflate(R.layout.list_item, null);
		TextView textView=(TextView)arg1.findViewById(R.id.text_view);
		textView.setText("Test"+(arg0+1));
		return arg1;
	}

}
