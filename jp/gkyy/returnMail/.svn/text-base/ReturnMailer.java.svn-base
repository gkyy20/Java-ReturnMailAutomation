package jp.gkyy.returnMail;

import jp.gkyy.returnMail.DB.DBOperator;
import jp.gkyy.returnMail.Util.EmojiMaster;
import jp.gkyy.returnMail.Util.MailBodyGenerator;
import jp.gkyy.returnMail.Util.SimOperator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ReturnMailer extends Activity implements OnClickListener {
	public static final String PREF_NAME = "jp.gkyy.ReturnMailerPref";
	public static final String PREF_ITEM_ADDRESS = "Address";
	public static final String PREF_ITEM_CARRIER = "Carrier";
	
	private static final int PICK_CONTACT = 0;
	private static final int SEND_MAIL = 1;
	private static final int WHOLE_SETTING = 2;
	
	private String _carrier = null;
	private EmojiMaster _em = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        DBOperator.setContentResolver(getContentResolver());

        this._em = new EmojiMaster(this);

        // デフォルトの読み込み
        String address = getSharedPreferences(PREF_NAME,MODE_WORLD_READABLE|MODE_WORLD_WRITEABLE).getString(PREF_ITEM_ADDRESS, "");
        if( !address.equals("") ) ((EditText)findViewById(R.id.ETAddress)).setText(address);
        
        // キャリアの取得
        setCarrierFromPref();
        
        // 電話帳ボタンのアクションを設定
        ((Button)findViewById(R.id.ButAddressBook)).setOnClickListener(this);

        // 本文生成ボタンのアクションを設定
        ((Button)findViewById(R.id.ButCreate)).setOnClickListener(this);

        // 実行ボタンのアクションを設定
        ((Button)findViewById(R.id.ButExec)).setOnClickListener(this);
        
        // 本文テキストボックスは絵文字を許可する
        Bundle bundle = ((EditText)findViewById(R.id.ETMailBody)).getInputExtras(true);
        if( bundle != null ) bundle.putBoolean("allowEmoji",true);
        
        if( MailBodyGenerator.isDBReaded() == false ){
            MailBodyGenerator.getInstance().readAllDBData(this._em);
        }
        
        // メール用のタイトルと本文を生成してしまう
		((TextView)findViewById(R.id.TVMailTitle)).setText("Re:");
		MailBodyGenerator.getInstance().generate();
		((EditText)findViewById(R.id.ETMailBody)).setText(MailBodyGenerator.getInstance().getGoogleString());
    }
    
	@Override
	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.ButAddressBook:
			      Intent pickIntent = new Intent(Intent.ACTION_PICK,ContactsContract.Contacts.CONTENT_URI);
			      startActivityForResult(pickIntent, PICK_CONTACT);
			      break;
			case R.id.ButCreate:
				((TextView)findViewById(R.id.TVMailTitle)).setText("Re:");
				MailBodyGenerator.getInstance().generate();
				((EditText)findViewById(R.id.ETMailBody)).setText(MailBodyGenerator.getInstance().getGoogleString());
				break;
			case R.id.ButExec:
				Intent sendIntent = new Intent();
				sendIntent.setAction(Intent.ACTION_SENDTO);
				sendIntent.setData(Uri.parse("mailto:" + ((TextView)findViewById(R.id.ETAddress)).getText()));
				sendIntent.putExtra(Intent.EXTRA_SUBJECT, ((TextView)findViewById(R.id.TVMailTitle)).getText());
				sendIntent.putExtra(Intent.EXTRA_TEXT, MailBodyGenerator.getInstance().getString(_carrier));
				startActivityForResult(sendIntent,SEND_MAIL);
				this.finish();
				break;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater lMi = new MenuInflater(getApplicationContext());
		lMi.inflate(R.menu.contextmenu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		Intent intent = null;
		switch( item.getItemId() ){
		case R.id.lastTrain:
			 intent = new Intent(getApplicationContext(),LastTrain.class);  
			 startActivity(intent);
			 break;
		case R.id.RMSettings:
			 intent = new Intent(getApplicationContext(),MailWholeSetting.class);  
			 startActivityForResult(intent, WHOLE_SETTING);
			 setCarrierFromPref();
			 break;
		case R.id.RMBodySettings:
			 intent = new Intent(getApplicationContext(),ReturnMailerSetting.class);  
			 intent.putExtra(MailEmojiSetting.EMOJI_MASTER, this._em);
			 startActivity(intent);
			 break;
		}
		return true;
	}
	
	@Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
    	super.onActivityResult(reqCode, resultCode, data);
    	switch(reqCode) {
	    	case(PICK_CONTACT):
	    		String uri = "";
	    		try{
			    	if(resultCode == Activity.RESULT_OK) {
			    		Cursor c = managedQuery(data.getData(), null, null, null, null);
			    		if (c.moveToFirst()){
				    		Cursor emails = managedQuery(ContactsContract.CommonDataKinds.Email.CONTENT_URI, 
				    									 null, 
														 ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ? ", 
														 new String[] {c.getString(c.getColumnIndex(ContactsContract.Contacts._ID))}, 
														 null);
				    		while(emails.moveToNext()) {
				    			uri = emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
						    	((EditText)findViewById(R.id.ETAddress)).setText(uri);
				    		}
			    		}
			        }
	    		}catch(Exception e){
	    			 Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
	    		}
		        Editor e = getSharedPreferences(PREF_NAME,MODE_WORLD_READABLE|MODE_WORLD_WRITEABLE).edit();
		        e.putString(PREF_ITEM_ADDRESS, uri);
		        e.commit();
		    	break;
			case(SEND_MAIL):
				break;
			case(WHOLE_SETTING):
				setCarrierFromPref();
				break;
    	}
    }

	private void setCarrierFromPref(){
        SharedPreferences pref =
        	getSharedPreferences(PREF_NAME,MODE_WORLD_READABLE|MODE_WORLD_WRITEABLE);

        _carrier = pref.getString(PREF_ITEM_CARRIER, "");
        if(_carrier.equals("")){
        	_carrier = SimOperator.getCarrierName(this);
	        Editor e = pref.edit();
	        e.putString(PREF_ITEM_CARRIER, _carrier);
	        e.commit();
        }		
	}
}