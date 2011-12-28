package jp.gkyy.returnMail.DB;

public class MailBodyItem {
	private String _ID;
	private int gid;
	private int num;
	private Object item;
	
	public MailBodyItem(String _ID, int gid, int num, Object item){
		this._ID = _ID;
		this.gid = gid;
		this.num = num;
		this.item = item;
	}
	
	public String get_ID() {
		return _ID;
	}
	public int getGid() {
		return gid;
	}
	public int getNum() {
		return num;
	}
	public Object getItem() {
		return item;
	}
	
	public String toString(){
		return this.item.toString();
	}
	
}
