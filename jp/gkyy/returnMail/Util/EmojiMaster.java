package jp.gkyy.returnMail.Util;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;

public class EmojiMaster implements Serializable{
	private static final long serialVersionUID = 5951360857243592977L;
	private HashMap<String,EmojiCode> emojiMap = new HashMap<String,EmojiCode>();

	public EmojiMaster(){
	}
	public EmojiMaster(Activity app){
		setUpEmoji(app);
	}
	
	private void setUpEmoji(Activity app){
		EmojiCodeFileReader ecfr = new EmojiCodeFileReader();
		try {
			emojiMap = ecfr.read(app);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public EmojiCode getEmoji(String key){
		return emojiMap.get(key);
	}
	
	public HashMap<String,EmojiCode> getEomojiMap(){
		return emojiMap;
	}
	
	public List<EmojiCode> getGoogleCodeArray(){
		List<EmojiCode> result = new ArrayList<EmojiCode>();
		for(EmojiCode code : this.emojiMap.values()){
			result.add(code);
		}
		return result;
	}
	
	public HashMap<String, EmojiCode> getEmojiMap() {
		return emojiMap;
	}
	public void setEmojiMap(HashMap<String, EmojiCode> emojiMap) {
		this.emojiMap = emojiMap;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
