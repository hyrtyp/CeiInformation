package com.hyrt.cei.ui.information;

import com.hyrt.cei.ui.personcenter.PersonCenter;
import com.hyrt.ceiphone.R;
import com.hyrt.ceiphone.common.Announcement;
import com.hyrt.ceiphone.common.Disclaimer;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class CeiInformationBottomMenu extends android.support.v4.app.Fragment
		implements OnClickListener {

	public RelativeLayout zjzx_botton_1;
	public RelativeLayout zjzx_botton_2;
	public RelativeLayout zjzx_botton_3;
	public RelativeLayout zjzx_botton_4;
	public RelativeLayout layout_tzgg;
	public RelativeLayout layout_grzx;
	public RelativeLayout layout_gywm;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.information_bottom_menu,
				container, false);
		zjzx_botton_1 = (RelativeLayout) view.findViewById(R.id.zjzx_botton_1);
		zjzx_botton_1.setOnClickListener(this);

		zjzx_botton_2 = (RelativeLayout) view.findViewById(R.id.zjzx_botton_2);
		zjzx_botton_2.setOnClickListener(this);

		zjzx_botton_3 = (RelativeLayout) view.findViewById(R.id.zjzx_botton_3);
		zjzx_botton_3.setOnClickListener(this);

		zjzx_botton_4 = (RelativeLayout) view.findViewById(R.id.zjzx_botton_4);
		zjzx_botton_4.setOnClickListener(this);

		layout_tzgg = (RelativeLayout) view.findViewById(R.id.read_report_tzgg);
		layout_tzgg.setOnClickListener(this);

		layout_grzx = (RelativeLayout) view.findViewById(R.id.read_report_grzx);
		layout_grzx.setOnClickListener(this);

		layout_gywm = (RelativeLayout) view.findViewById(R.id.read_report_gywm);
		layout_gywm.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		Intent intent = new Intent();

		switch (v.getId()) {
		case R.id.zjzx_botton_1:
			if (getActivity() instanceof InformationOne) {

			} else {
				intent.setClass(getActivity(), InformationOne.class);
				startActivity(intent);
			}
			break;
		case R.id.zjzx_botton_2:
			if (getActivity() instanceof InformationTwo) {

			} else {
				intent.setClass(getActivity(), InformationTwo.class);
				startActivity(intent);
			}
			break;
		case R.id.zjzx_botton_3:
			if (getActivity() instanceof InformationThree) {

			} else {
				intent.setClass(getActivity(), InformationThree.class);
				startActivity(intent);
			}
			break;
		case R.id.zjzx_botton_4:
			if (getActivity() instanceof InformationCollect) {

			} else {
				if (checkIsLogin()) {
					intent.setClass(getActivity(), InformationCollect.class);
					startActivity(intent);
				} else {
					Toast.makeText(getActivity(), "请登录后查看", Toast.LENGTH_SHORT)
							.show();
				}
			}
			break;
		case R.id.read_report_tzgg:
			if (getActivity() instanceof Announcement) {

			} else {
				intent.setClass(getActivity(), Announcement.class);
				startActivity(intent);
			}

			break;
		case R.id.read_report_grzx:
			if (getActivity() instanceof PersonCenter) {

			} else {
				if (checkIsLogin()) {
					intent.setClass(getActivity(), PersonCenter.class);
					startActivity(intent);
				} else {
					Toast.makeText(getActivity(), "请登录后查看", Toast.LENGTH_SHORT)
							.show();
				}
			}
			break;
		case R.id.read_report_gywm:
			if (getActivity() instanceof Disclaimer) {

			} else {
				intent.setClass(getActivity(), Disclaimer.class);
				startActivity(intent);
			}
			break;
		}
	}

	private boolean checkIsLogin() {
		SharedPreferences settings = getActivity().getSharedPreferences(
				"loginInfo", Activity.MODE_PRIVATE);
		String loginName = settings.getString("LOGINNAME", "");
		if (loginName.equals("")) {
			return false;
		} else {
			return true;
		}

	}

}
