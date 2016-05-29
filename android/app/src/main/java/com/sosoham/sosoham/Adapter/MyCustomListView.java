package com.sosoham.sosoham.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sosoham.sosoham.ContentSubmitActivity;
import com.sosoham.sosoham.R;
import com.sosoham.sosoham.Vo.HopeVo;

import java.util.ArrayList;

public class MyCustomListView extends BaseAdapter {

	private Activity m_activity;
	private ArrayList<HopeVo> hopevo;
	LayoutInflater mInflater;
	TypedArray testArrayIcon;
    int random;
	public MyCustomListView(Activity con, ArrayList<HopeVo> hopevo) {
		m_activity = con;
		this.hopevo = hopevo;
		mInflater = (LayoutInflater) m_activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		testArrayIcon = m_activity.getResources().obtainTypedArray(R.array.mic_volume_meter_res);
	}

	@Override
	public int getCount() {
		return hopevo.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final int pos = (int) (Math.random() * hopevo.size());
		ViewHolder holder;

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.array_list, parent, false);
			holder = new ViewHolder();
			holder.mains = (CardView) convertView.findViewById(R.id.card_view);
			holder.btn = (android.support.design.widget.FloatingActionButton) convertView.findViewById(R.id.fab);
			holder.mcontent = (TextView) convertView.findViewById(R.id.textView2);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		random = (int) (Math.random() * testArrayIcon.length());
		int drawableID = testArrayIcon.getResourceId(random, -1);
		holder.btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(m_activity, ContentSubmitActivity.class);
				m_activity.startActivity(i);
			}
		});
		if(hopevo.get(pos)!=null) {

			holder.mcontent.setText(hopevo.get((getCount() - pos - 1)).GIFT_CONTENT);
			holder.mains.setBackgroundResource(drawableID);
		}else {
			//holder.mains.setBackgroundResource(drawableID);
		}
		return convertView;
	}

	private static class ViewHolder {
		CardView mains;
		public FloatingActionButton btn;
		TextView mcontent;
		public int position;
	}
}

//		TextView nametxt = (TextView) convertView.findViewById(R.id.bidtxt);
//		TextView contenttxt = (TextView) convertView.findViewById(R.id.btimetxt);
//		ImageView iv = (ImageView) convertView.findViewById(R.id.bimg1);
//		
		//if(sl.get((getCount()-position-1)).Picture==null){
		//	sl.get((getCount()-position-1)).Picture="@drawable/empty";
		//}
		/*int resId = m_activity.getResources().getIdentifier(sl.get((getCount()-position-1)).Picture, "drawable",m_activity.getPackageName());
		task = new BitmapWorkerTask( holder.miv,m_activity);
		  if (cancelPotentialWork(resId, holder.miv)) {
		        final BitmapWorkerTask task = new BitmapWorkerTask(holder.miv,m_activity);
		        
		        AsyncDrawable asyncDrawable =
		                new AsyncDrawable(m_activity.getResources(),holder.miv.getDrawingCache(), task);
		        holder.miv.setImageDrawable(asyncDrawable);
		        task.execute(resId);
		    }
		 
		
		task.execute(resId);
			*/
//		BitmapFactory.Options options = new BitmapFactory.Options();
//		int scaleFactor = 2;
//		options.inJustDecodeBounds = false;
//		options.inSampleSize = scaleFactor;

		//Bitmap bitmap = BitmapFactory.decodeResource(m_activity.getResources(),resId, options);

		//holder.miv.setImageBitmap(bitmap);
		/*
		holder.mname.setText(sl.get((getCount()-position-1)).SANGHO);
		holder.mcontent.setText(sl.get((getCount()-position-1)).CONTENT);
	
		convertView.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				GoIntent(pos);
			}
		});
		return convertView;
	}
	public void GoIntent(int pos){
		Intent intent = new Intent(m_activity, ReadActivity.class);
		intent.putExtra("sangho", sl.get(pos).SANGHO);
		intent.putExtra("content", sl.get(pos).CONTENT);
		intent.putExtra("picture", sl.get(pos).Picture);
	
		//intent.put
		m_activity.startActivity(intent);
	}
	public boolean cancelPotentialWork(int data, ImageView imageView) {
	    final BitmapWorkerTask bitmapWorkerTask = task.getBitmapWorkerTask(imageView);

	    if (bitmapWorkerTask != null) {
	        final int bitmapData = bitmapWorkerTask.data;
	        if (bitmapData != data) {
	            // Cancel previous task
	            bitmapWorkerTask.cancel(true);
	        } else {
	            // The same work is already in progress
	            return false;
	        }
	    }
	    // No task associated with the ImageView, or an existing task was cancelled
	    return true;
	}
private static class ViewHolder{
	public ImageView miv;
	TextView mname;
	TextView mcontent;
	public int position;
}
*/



