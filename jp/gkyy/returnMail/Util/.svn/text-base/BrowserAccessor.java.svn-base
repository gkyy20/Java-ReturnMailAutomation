package jp.gkyy.returnMail.Util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class BrowserAccessor {

	/**
	 * ブラウザを起動します。
	 * @param context
	 * @param uriString
	 */
	public static int openUrl(Context context, String uriString){
		Intent intent;
		try{
			intent = new Intent(Intent.ACTION_VIEW,Uri.parse(uriString));
			context.startActivity(intent);
			return 0;
		}catch(Exception e){
			Log.d("--BrowserAccessor.java--","err : " + e.getMessage());
			Log.d("--BrowserAccessor.java--","err : " + e.getStackTrace());
			return -1;
		}
		
	}
}
