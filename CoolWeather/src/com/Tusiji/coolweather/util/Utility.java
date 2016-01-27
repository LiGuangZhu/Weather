package com.Tusiji.coolweather.util;

import com.Tusiji.coolweather.db.CoolWeatherDB;
import com.Tusiji.coolweather.mode.City;
import com.Tusiji.coolweather.mode.County;
import com.Tusiji.coolweather.mode.Province;

import android.text.TextUtils;

public class Utility {

	/**
	 * 解析和处理服务器返回的省级数据
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
					// // 将解析出来的数据存储到Province表
					coolWeatherDB.SavaProvince(province);
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * 解析和处理服务器返回的市级数据
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
					//将解析出来的数据存储到City表
					coolWeatherDB.SavaCity(city);
				}
				return true;
			}
		}
		return false;
	}

	/**
	* 解析和处理服务器返回的县级数据
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
				// 将解析出来的数据存储到County表
				coolWeatherDB.saveCounty(county);
				}
				return true;
			}
		}
		
		return false;

	}
}
