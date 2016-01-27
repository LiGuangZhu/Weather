package com.Tusiji.coolweather.util;

public interface HttpCallbackListener {
	void onFinish(String response);

	void onError(Exception exception);
}
