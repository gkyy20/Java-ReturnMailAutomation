package jp.gkyy.returnMail.Util;

import java.util.Random;

import jp.gkyy.returnMail.DB.DBOperator;
import jp.gkyy.returnMail.DB.MailBodyItem;
import jp.gkyy.returnMail.DB.ReturnMailerDBDefinition;
import jp.gkyy.returnMail.DB.ReturnMailerDBDefinition.MailBodyItemColumns;
import jp.gkyy.returnMail.Util.EmojiCode.EMOJI_TYPE;
import android.app.Activity;
import android.database.Cursor;

public class MailBodyGenerator {
	Activity _app;

	public static final int EMOJI_FROM = 101;
	
	private EmojiMaster _em = null;
	private static MailBodyGenerator singleton = null;
	private static boolean _firstDBRead = false;
	
	private static Random ran = new Random();
	private MailBodyEmojiMaster bEMaster;
	private MailBodyStringMaster bIMaster;
	
	private String _docomoString = "";
	private String _auString = "";
	private String _softbankString = "";
	private String _googleString = "";
	private String _otherString = "";
	
	private MailBodyGenerator(){
		bEMaster = new MailBodyEmojiMaster(2);
		bIMaster = new MailBodyStringMaster(2);
	}
	
	public static boolean isDBReaded(){
		return _firstDBRead;
	}
	public static void setDBReaded(boolean b){
		_firstDBRead = b;
	}
	
	public static MailBodyGenerator getInstance(){
		if( singleton == null ) singleton = new MailBodyGenerator();
		return singleton;
	}
	
	public void generate(){
		StringBuffer sbDocomo = new StringBuffer();
		StringBuffer sbAu = new StringBuffer();
		StringBuffer sbSB = new StringBuffer();
		StringBuffer sbGoogle = new StringBuffer();
		StringBuffer sbOther = new StringBuffer();
		
 		for( int i = 0 ; i < 2 ; i++ ){
 			String line = this.bIMaster.get(i, ran.nextInt(this.bIMaster.size(i))).getItem().toString();
 			sbDocomo.append(line);
 			sbAu.append(line);
 			sbSB.append(line);
 			sbGoogle.append(line);
 			sbOther.append(line);
 			
 			EmojiCode ec = (EmojiCode)this.bEMaster.get(i, ran.nextInt(this.bEMaster.size(i))).getItem();
 			sbDocomo.append(ec.toString(EMOJI_TYPE.docomo));
 			sbAu.append(ec.toString(EMOJI_TYPE.au));
 			sbSB.append(ec.toString(EMOJI_TYPE.softbank));
 			sbGoogle.append(ec.toString(EMOJI_TYPE.google));

 			sbDocomo.append("\n");
			sbAu.append("\n");
			sbSB.append("\n");
			sbGoogle.append("\n");
 			sbOther.append("\n");
		}
 		
 		this._docomoString = new String(sbDocomo);
 		this._auString = new String(sbAu);
 		this._softbankString = new String(sbSB);
 		this._googleString = new String(sbGoogle);
 		this._otherString = new String(sbOther);
	}

	public MailBodyEmojiMaster getbEMaster() {
		return bEMaster;
	}

	public MailBodyStringMaster getbIMaster() {
		return bIMaster;
	}

	public String getDocomoString() {
		return _docomoString;
	}

	public String getAuString() {
		return _auString;
	}

	public String getSoftbankString() {
		return _softbankString;
	}

	public String getOtherString() {
		return _otherString;
	}
	
	public String getGoogleString() {
		return _googleString;
	}
	
	public String getString(EMOJI_TYPE emoji){
		if( emoji == null ) return getOtherString();
		switch(emoji){
		case docomo:
			return getDocomoString();
		case au:
			return getAuString();
		case softbank:
			return getSoftbankString();
		case google:
			return getGoogleString();
		default:
			return getOtherString();
		}
	}

	public String getString(String emoji){
		if( emoji == null ) return getOtherString();
		EMOJI_TYPE eType = EmojiCode.getEmojiType(emoji);
		if( eType == null ) return getOtherString();
		
		switch(eType){
		case docomo:
			return getDocomoString();
		case au:
			return getAuString();
		case softbank:
			return getSoftbankString();
		case google:
			return getGoogleString();
		default:
			return getOtherString();
		}
	}
	
	public void readAllDBData(EmojiMaster em){
		_em = em;
        Cursor c = DBOperator.query(ReturnMailerDBDefinition.CONTENT_URI,
			    ReturnMailerDBDefinition.MAILBODY_PROJECTION_MAP.keySet().toArray(new String[0]), 
			    null, null, null);
		while(c.moveToNext()){
			if( c.getInt(c.getColumnIndex(MailBodyItemColumns.COL_GID)) < EMOJI_FROM ){
				MailBodyGenerator.getInstance().getbIMaster().
				add( c.getInt(c.getColumnIndex(MailBodyItemColumns.COL_GID)) - 1,
					 new MailBodyItem(
							 c.getString(c.getColumnIndex(MailBodyItemColumns._ID)),
							 c.getInt(c.getColumnIndex(MailBodyItemColumns.COL_GID)), 
							 c.getInt(c.getColumnIndex(MailBodyItemColumns.COL_NUM)), 
							 c.getString(c.getColumnIndex(MailBodyItemColumns.COL_ITEM))
						)
					);
			} else {
				MailBodyGenerator.getInstance().getbEMaster().
				add( c.getInt(c.getColumnIndex(MailBodyItemColumns.COL_GID)) - EMOJI_FROM,
					 new MailBodyItem(
							 c.getString(c.getColumnIndex(MailBodyItemColumns._ID)),
							 c.getInt(c.getColumnIndex(MailBodyItemColumns.COL_GID)), 
							 c.getInt(c.getColumnIndex(MailBodyItemColumns.COL_NUM)), 
							 this._em.getEmoji(
							 c.getString(c.getColumnIndex(MailBodyItemColumns.COL_ITEM)))
						)
					);
				
			}
		}
		MailBodyGenerator.setDBReaded(true);
	}

	public void readEmojiData(int gid){
		if( gid < EMOJI_FROM) return;
        Cursor c = DBOperator.query(ReturnMailerDBDefinition.CONTENT_URI,
			    ReturnMailerDBDefinition.MAILBODY_PROJECTION_MAP.keySet().toArray(new String[0]), 
			    MailBodyItemColumns.COL_GID + " = " + gid, null, null);
        MailBodyGenerator.getInstance().getbEMaster().get(gid - EMOJI_FROM).clear();
		while(c.moveToNext()){
			MailBodyGenerator.getInstance().getbEMaster().
				add( c.getInt(c.getColumnIndex(MailBodyItemColumns.COL_GID)) - EMOJI_FROM,
					 new MailBodyItem(
							 c.getString(c.getColumnIndex(MailBodyItemColumns._ID)),
							 c.getInt(c.getColumnIndex(MailBodyItemColumns.COL_GID)), 
							 c.getInt(c.getColumnIndex(MailBodyItemColumns.COL_NUM)), 
							 this._em.getEmoji(
							 c.getString(c.getColumnIndex(MailBodyItemColumns.COL_ITEM)))
						)
					);
		}
	}
}
