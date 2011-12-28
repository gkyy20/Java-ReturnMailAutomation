package jp.gkyy.returnMail.Util;

import java.io.Serializable;

public class EmojiCode implements Serializable {
	private static final long serialVersionUID = -4314082249045003244L;

	private EMOJI_TYPE emojiType = EMOJI_TYPE.google;
	private String _name;
	private String _docomoCode;
	private String _auCode;
	private String _softbankCode;
	private String _googleCode;
	
	public enum EMOJI_TYPE{
		docomo,
		au,
		softbank,
		google,
	}
	
	public static EMOJI_TYPE getEmojiType(String code){
		if( code.toUpperCase().equals("DOCOMO") ){
			return EMOJI_TYPE.docomo;
		} else if( code.equals("44010") ){
			return EMOJI_TYPE.docomo;
		} else if( code.toUpperCase().equals("AU") ){
			return EMOJI_TYPE.au;
		} else if( code.equals("44030") ){
			return EMOJI_TYPE.au;
		} else if( code.toUpperCase().equals("SOFTBANK") ){
			return EMOJI_TYPE.softbank;
		} else if( code.equals("44020") ){
			return EMOJI_TYPE.softbank;
		} else if( code.toUpperCase().equals("GOOGLE") ){
			return EMOJI_TYPE.google;
		} else {
			return null;
		}
	}

	public EmojiCode(String name, String docomo, String au, String sb, String google){
		this._name = name;
		this._docomoCode = docomo;
		this._auCode = au;
		this._softbankCode = sb;
		this._googleCode = google;
	}

	public String toString(){
		switch(emojiType){
		case docomo:
			return get_docomoCode();
		case au:
			return get_auCode();
		case softbank:
			return get_softbankCode();
		case google:
			return get_googleCode();
		default:
			return get_docomoCode();
		}
	}

	public String toString(EMOJI_TYPE et){
		switch(et){
		case docomo:
			return get_docomoCode();
		case au:
			return get_auCode();
		case softbank:
			return get_softbankCode();
		case google:
			return get_googleCode();
		default:
			return get_docomoCode();
		}
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String get_docomoCode() {
		return _docomoCode;
	}

	public String get_auCode() {
		return _auCode;
	}

	public String get_softbankCode() {
		return _softbankCode;
	}
	
	public String get_googleCode() {
		return _googleCode;
	}

	public String get_name() {
		return _name;
	}

	public EMOJI_TYPE getEmojiType() {
		return emojiType;
	}

	public void set_docomoCode(String docomoCode) {
		_docomoCode = docomoCode;
	}

	public void set_auCode(String auCode) {
		_auCode = auCode;
	}

	public void set_softbankCode(String softbankCode) {
		_softbankCode = softbankCode;
	}

	public void set_googleCode(String googleCode) {
		_googleCode = googleCode;
	}

	public void setEmojiType(EMOJI_TYPE emojiType) {
		this.emojiType = emojiType;
	}

	public void set_name(String name) {
		_name = name;
	}
	
	
}
