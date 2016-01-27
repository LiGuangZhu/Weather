package com.Tusiji.coolweather.util;

import com.Tusiji.coolweather.db.CoolWeatherDB;
import com.Tusiji.coolweather.mode.City;
import com.Tusiji.coolweather.mode.County;
import com.Tusiji.coolweather.mode.Province;

import android.text.TextUtils;

public class Utility {

	/**
	 * �����ʹ�����������ص�ʡ������
	 */
	public synchronized static boolean handleProvincesResponse(
			CoolWeatherDB coolWeatherDB, String response) {
		if (!TextUtils.isEmpty(response)) {
			String[] allProvinces = response.split(",");
			if (allProvinces != null && allProvinces.length > 0) {
				for (String p : allProvinces) {
					String[] arrays = p.split("\\|");
					Province province = new Province();
					province.setProvinceCode(arrays[0]);
					province.setProvinceName(arrays[1]);
					// // ���������������ݴ洢��Province��
					coolWeatherDB.SavaProvince(province);
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * �����ʹ�����������ص��м�����
	 */
	public synchronized static boolean handleCityResponse(
			CoolWeatherDB coolWeatherDB, String response, int provinceid) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCity = response.split(",");
			if (allCity != null && allCity.length > 0) {
				for (String c : allCity) {
					String[] arrays = c.split("//|");
					City city = new City();
					city.setCityCode(arrays[0]);
					city.setCityName(arrays[1]);
					city.setId(provinceid);
					//���������������ݴ洢��City��
					coolWeatherDB.SavaCity(city);
				}
				return true;
			}
		}
		return false;
	}

	/**
	* �����ʹ�����������ص��ؼ�����
	*/
	public synchronized static boolean handleCountyResponse(
			CoolWeatherDB coolWeatherDB, String response, int cityid) {
		if(!TextUtils.isEmpty(response)){
			String[] allcounts = response.split(",");
			if(allcounts != null && allcounts.length > 0){
				for (String c : allcounts) {
				String [] arrays = 	c.split("//|");
				County county = new County();
				county.setCountyCode(arrays[0]);
				county.setCountyName(arrays[1]);
				county.setCityId(cityid);
				// ���������������ݴ洢��County��
				coolWeatherDB.saveCounty(county);
				}
				return true;
			}
		}
		
		return false;

	}
}
