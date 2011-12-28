package jp.gkyy.returnMail.Util;

import java.util.ArrayList;
import java.util.List;

import jp.gkyy.returnMail.DB.MailBodyItem;

public class AbstractMailBodyItemMaster {
	protected ArrayList<ArrayList<MailBodyItem>> bodyItems = null;
	protected ArrayList<Integer> maxNums = null;
	
	public AbstractMailBodyItemMaster(int size){
		this.bodyItems = new ArrayList<ArrayList<MailBodyItem>>();
		this.maxNums = new ArrayList<Integer>();
		for( int i = 0; i < size ; i ++){
			this.bodyItems.add(new ArrayList<MailBodyItem>());
			this.maxNums.add(new Integer(0));
		}
	}
	
	public int size(){
		return this.bodyItems.size();
	}
	public int size(int x){
		return this.bodyItems.get(x).size();
	}
	public MailBodyItem get(int x, int y){
		return this.bodyItems.get(x).get(y);
	}
	public List<MailBodyItem> get(int x){
		return this.bodyItems.get(x);
	}
	public void remove(int x, int y){
		this.bodyItems.get(x).remove(y);
	}
	public void add(int x, MailBodyItem value){
		this.bodyItems.get(x).add(value);
		if( this.maxNums.get(x).intValue() < value.getNum() ){
			this.maxNums.set(x, new Integer(value.getNum()));
		}
	}
	public int getNextNum(int x){
		if( this.maxNums.size() < x ) return -1;
		return this.maxNums.get(x).intValue() + 1;
	}
}
