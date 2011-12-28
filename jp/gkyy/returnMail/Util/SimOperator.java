package jp.gkyy.returnMail.Util;

import android.content.Context;
import android.telephony.TelephonyManager;

public class SimOperator {
	private static TelephonyManager _tm = null;
	
	public static String getCarrierCode(Context context){
		if( _tm == null ) _tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE); 
		return _tm.getSimOperator();
	}
	public static String getCarrierName(Context context){
		String code = getCarrierCode(context);
		if(code.equals("44010")){
			return "docomo";
		} else if ( code.equals("44030") ){
			return "au";
		} else if ( code.equals("44020") ){
			return "softbank";
		} else {
			return null;
		}
	}

}
