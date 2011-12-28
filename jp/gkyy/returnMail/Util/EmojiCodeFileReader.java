package jp.gkyy.returnMail.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import android.app.Activity;
import android.content.res.AssetManager;

public class EmojiCodeFileReader {

	 public HashMap<String,EmojiCode> read(Activity app) throws IOException {
    	HashMap<String,EmojiCode> emojiMap = new HashMap<String,EmojiCode>();
    	AssetManager as = app.getResources().getAssets();
		InputStream is = as.open("Emoji_UTF8.csv");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));  
        String str;     
        while((str = br.readLine()) != null){
        	String[] arr = str.split(",");
        	if( arr.length == 6 ){
        		if( arr[5].equals("1")){
	        		emojiMap.put(arr[0], 
	        				     new EmojiCode(arr[0],
	        				    		       convertUTFCode(arr[1]),
	        				    		       convertUTFCode(arr[2]),
	        				    		       convertUTFCode(arr[3]),
	        				    		       convertUTFCode(arr[4])));
        		}
        	}
        }   
		return emojiMap;
	}

	private String convertUTFCode(String in){
		String[] arr = in.split(" ");
		if( arr.length != 3 && arr.length != 4 ) return null;
		int len = arr.length;
		byte byts[] = new byte[len];
		for( int i = 0 ; i < len; i++){
			byts[i] = (byte) (Integer.parseInt(arr[i], 16));
		}
		try {
			return (new String(byts,0,len,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}	
}
