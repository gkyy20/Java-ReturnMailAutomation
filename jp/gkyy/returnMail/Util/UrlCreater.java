package jp.gkyy.returnMail.Util;

import java.util.Calendar;

import android.util.Log;

public class UrlCreater {

	/**
	 * Yahoo ‚ÌURL‚ğæ“¾
	 * @param from
	 * @param to
	 * @return
	 */
    public static String CreateYahooUrl(String from, String to){
    	String year = "";
    	String month = "";
    	String day = "";
    	RefDateData(year,month,day);
    	
    	String url = "http://transit.map.yahoo.co.jp/search/result?from=";
    	url = url + from + "&to=" + to + "&via=&ym=";
    	url = url + year + month + "&d=" + day;
    	url = url + "&hh=12&m1=0&m2=0&type=2&ws=2&s=0&ost=0&ei=utf-8&x=123&y=12&kw=";
     	url = url + to;
    	
     	Log.d("--UrlCreater.java--","utl : " + url);
     	
    	return url;
    }
    
    /**
     * Google ‚ÌURLæ“¾
     * @param from
     * @param to
     * @return
     */
    public static String CreateGoogleUrl(String from, String to){
    	String year = "";
    	String month = "";
    	String day = "";
    	RefDateData(year,month,day);
    	
    	String url = "http://www.google.co.jp/m/directions?dirflg=r&source=m&saddr=";
    	url = url + from + "&daddr=" + to + "&date=";
    	url = url + year + month + day;
    	url = url + "&time=1200&ttype=last&btnG=‚±‚ÌğŒ‚ÅŒŸõ";
    	
     	Log.d("--UrlCreater.java--","utl : " + url);
     	
    	return url;
    }
    
    private static void RefDateData(String year, String month, String day){
    	year = new Integer(Calendar.YEAR).toString();
    	month = "00" + new Integer(Calendar.MONTH).toString();
    	day = "00" + new Integer(Calendar.DATE).toString();
    	month = month.substring(month.length()-1);
    	day = day.substring(day.length()-1);
    }
}
