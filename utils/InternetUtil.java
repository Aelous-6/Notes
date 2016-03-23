public class InternetUtil{
	
	public static final int NETWORK_NONE = 0;
	public static final int NETWORK_WIFI = 1;
	public static final int NETWORK_2G = 2;
	public static final int NETWORK_3G = 3;
	public static final int NETWORK_4G = 4;
	public static final int NETWORK_MOBILE = 5;
	
	/**
	  * 获取当前网络连接类型
	  */
	  public static int getNetworkState(Context context){
		  
		  ConnectivityManager conManager = (ConnectivityManager)
					context.getSystemService(Context.CONNECTIVITY_SERVICE);
		  if(null == conManager)
		  {
			  return NETWORK_NONE;
		  }
		  
		  //无网络连接
		  NetWorkInfo activeNetInfo = conManager.getActiveNetworkInfo();
		  if(activeNetInfo == null || !activeNetInfo.isAvailable()){
			  return NETWORK_NONE;
		  }	
		  
		  //wifi连接
		  NetWorkInfo wifiInfo = conManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		  if(wifiInfo != NULL){
			 NetworkInfo.State state = wifiInfo.getState();
			 if(null != state){
				 if(state == NetWorkInfo.State.CONNECTED !!
						state == NetWorkInfo.State.CONNECTING
						)
						{
							return NETWORK_WIFI;
						}
			 }
		  }
		  
		  //不是wifi连接
		  NetworkInfo netWorkInfo = conManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		  if(null != netWorkInfo){
			  NetworkInfo.state state = netWorkInfo.getState();
			  String strSubTypeName = netWorkInfo.getSubtypeName();
			  
			  if(null != state){
				  if(state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING){
					  switch(activeNetInfo.getSubtype()){
						  //2g网络
						case TelephonyManager.NETWORK_TYPE_GPRS://联通
						case TelephonyManager.NETWORK_TYPE_CDMA://电信
						case TelephonyManager.NETWORK_TYPE_EDGE://移动
						case TelephonyManager.NETWORK_TYPE_1xRTT:
						case TelephonyManager.NETWORK_TYPE_IDEN:
							return NETWORK_2G;
						  
						  //3g网络
						case TelephonyManager.NETWORK_TYPE_EVDO_A: // 电信3g  
                        case TelephonyManager.NETWORK_TYPE_UMTS:  
                        case TelephonyManager.NETWORK_TYPE_EVDO_0:  
                        case TelephonyManager.NETWORK_TYPE_HSDPA:  
                        case TelephonyManager.NETWORK_TYPE_HSUPA:  
                        case TelephonyManager.NETWORK_TYPE_HSPA:  
                        case TelephonyManager.NETWORK_TYPE_EVDO_B:  
                        case TelephonyManager.NETWORK_TYPE_EHRPD:  
                        case TelephonyManager.NETWORK_TYPE_HSPAP:  
							return NETWORK_3G;
						
						//4g网络
						 case TelephonyManager.NETWORK_TYPE_LTE:  
                            return NETWORK_4G;  
							
						default:  
                            //中国移动 联通 电信 三种3G制式  
                            if (strSubTypeName.equalsIgnoreCase("TD-SCDMA") || strSubTypeName.equalsIgnoreCase("WCDMA") || strSubTypeName.equalsIgnoreCase("CDMA2000")) {  
                                return NETWORN_3G;  
                            } else {  
                                return NETWORN_MOBILE;  
                            }  
					  }
				  }
			  }
			  
		  }
		  
		 return NETWORK_NONE;  
	  }
	
}
