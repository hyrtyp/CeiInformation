package com.hyrt.ceiphone;

import java.util.ArrayList;
import java.util.List;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.hyrt.cei.adapter.InfomationGalleryAdapter;
import com.hyrt.cei.application.CeiApplication;
import com.hyrt.cei.ui.common.LoginActivity;
import com.hyrt.cei.ui.information.InfoSearchActivity;
import com.hyrt.cei.ui.information.InformationActivity;
import com.hyrt.cei.ui.information.InformationCollect;
import com.hyrt.cei.ui.information.InformationReadActivity;
import com.hyrt.cei.util.AsyncImageLoader;
import com.hyrt.cei.vo.ColumnEntry;
import com.hyrt.cei.vo.ImageResourse;
import com.hyrt.readreport.CeiShelfBookActivity;
import com.hyrt.readreport.ReadReportMainActivity;

public class ContainerActivity extends SherlockFragmentActivity {

	public static String loginName;

	// 菜单
	public Fragment mFragmentmenu;
	public FragmentManager fm;
	public FragmentTransaction ft;

	public static Intent intent_InfoSearchActivity = new Intent();
	public static Intent intent_LoginActivity = new Intent();

	// 维护activity集合
	public static final List<Activity> activities = new ArrayList<Activity>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		activities.add(this);

		super.onCreate(savedInstanceState);

		showLoginBtnByUserName();

		fm = getSupportFragmentManager();
		ft = fm.beginTransaction();
		mFragmentmenu = fm.findFragmentByTag("menu");
		if (mFragmentmenu == null) {
			
			if(isInformationReadActivity()){
				if (loginName.equals("")) {
//					mFragmentmenu = new MenuFragmentShouCangbtn();
				}else{
					mFragmentmenu = new MenuFragmentShouCangbtn();
				}
			}else if(isInformationCollect()){
				mFragmentmenu = new MenuFragmentShouCang();
			}else if(isInfoSearchActivity()){
				
			}else{
				if (loginName.equals("")) {
					mFragmentmenu = new MenuFragmentNoLogin();
				} else {
					mFragmentmenu = new MenuFragmentIsLogin();
				}
			} 
			
		}
		intent_InfoSearchActivity.setClass(this, InfoSearchActivity.class);
		intent_LoginActivity.setClass(this, LoginActivity.class);
		// 添加菜单
		if(mFragmentmenu!=null){
			ft.add(mFragmentmenu, "menu");
			ft.commit();
		}
		
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// Respond to the action bar's Up/Home button
		case android.R.id.home:
			for (int i = activities.size()-1; i >0 ; i--) {
				activities.get(i).finish();
			}
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(this instanceof InformationActivity){
			getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		}else{
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
		
		ColumnEntry columnEntry = ((CeiApplication) getApplication()).columnEntry;
		ImageResourse imageResource = new ImageResourse();
		imageResource.setIconUrl(columnEntry.getLogo());
		imageResource.setIconId(columnEntry.getLogo());
		((CeiApplication) (this.getApplication())).asyncImageLoader
				.loadDrawable(imageResource,
						new AsyncImageLoader.ImageCallback() {

							@Override
							public void imageLoaded(Drawable drawable,
									String path) {
								getSupportActionBar().setIcon(drawable);
							}
						});
	}

	@Override
	protected void onDestroy() {
		activities.remove(this);
		super.onDestroy();
	}

	public static void destroyActivities() {
		for (int i = 0; i < activities.size(); i++) {
			activities.get(i).finish();
		}
	}

	private boolean isInformationCollect(){
		if(this instanceof InformationCollect){
			return true;
		}else{
			return false;
		}
	}
	
	private boolean isInformationReadActivity(){
		if(this instanceof InformationReadActivity){
			return true;
		}else{
			return false;
		}
	}
	
	private boolean isInfoSearchActivity(){
		if(this instanceof InfoSearchActivity){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * A fragment that displays a menu. This fragment happens to not have a UI
	 * (it does not implement onCreateView), but it could also have one if it
	 * wanted.
	 */
	public static class MenuFragmentNoLogin extends SherlockFragment {
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setHasOptionsMenu(true);
		}

		@Override
		public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
			menu.add("登录")
					.setIcon(R.drawable.main_login)
					.setIntent(intent_LoginActivity)
					.setShowAsAction(
							MenuItem.SHOW_AS_ACTION_IF_ROOM
									| MenuItem.SHOW_AS_ACTION_WITH_TEXT);
			menu.add("搜索")
					.setIcon(R.drawable.zjzx_search)
					.setIntent(intent_InfoSearchActivity)
					.setShowAsAction(
							MenuItem.SHOW_AS_ACTION_IF_ROOM
									| MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		}
		
	}

	public static class MenuFragmentIsLogin extends SherlockFragment {
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setHasOptionsMenu(true);
		}

		@Override
		public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
			menu.add(loginName).setShowAsAction(
					MenuItem.SHOW_AS_ACTION_IF_ROOM
							| MenuItem.SHOW_AS_ACTION_WITH_TEXT);
			menu.add("搜索")
					.setIcon(R.drawable.zjzx_search)
					.setIntent(intent_InfoSearchActivity)
					.setShowAsAction(
							MenuItem.SHOW_AS_ACTION_IF_ROOM
									| MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		}

	}
	
	public static class MenuFragmentShouCang extends SherlockFragment {
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setHasOptionsMenu(true);
		}

		@Override
		public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
			menu.add("编辑")
					.setTitle("编辑")
					.setShowAsAction(
					MenuItem.SHOW_AS_ACTION_IF_ROOM
							| MenuItem.SHOW_AS_ACTION_WITH_TEXT);
			menu.add("清空")
					.setTitle("清空")
					.setShowAsAction(
					MenuItem.SHOW_AS_ACTION_IF_ROOM
							| MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		}

	}
	public static class MenuFragmentShouCangbtn extends SherlockFragment {
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setHasOptionsMenu(true);
		}

		@Override
		public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
			menu.add("收藏")
					.setIcon(R.drawable.zjzx_shoucang)
					.setTitle("收藏")
					.setShowAsAction(
							MenuItem.SHOW_AS_ACTION_IF_ROOM
									| MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		}

	}
	// 根据登陆与否判断是否显示登陆按钮
	private void showLoginBtnByUserName() {
		// 获取登陆名
		SharedPreferences settings = getSharedPreferences("loginInfo",
				Activity.MODE_PRIVATE);
		loginName = settings.getString("LOGINNAME", "");
	}

}
