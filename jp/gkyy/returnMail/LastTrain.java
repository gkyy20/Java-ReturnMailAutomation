package jp.gkyy.returnMail;

import jp.gkyy.returnMail.Util.BrowserAccessor;
import jp.gkyy.returnMail.Util.UrlCreater;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LastTrain extends Activity {
	public static final String PREF_NAME = "jp.gkyy.LastTrainPref";
	public static final String PREF_ITEM_DEFTO = "DefaultTo";
	public static final String PREF_CHK_URL = "CheckURL";

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	// 初期設定
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lasttrain);
        
        // デフォルトの読み込み
        SharedPreferences pref =
        	getSharedPreferences(PREF_NAME,MODE_WORLD_READABLE|MODE_WORLD_WRITEABLE);
        String defTo = pref.getString(PREF_ITEM_DEFTO, "善行");
        if( defTo.equals("") == false ) SetTo(defTo);
        
        // 実行ボタンのアクションを設定
        Button butExec = (Button)findViewById(R.id.BExec);
        butExec.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String from = GetFrom();
				String to = GetTo();
				
				if( from.equals("") || to.equals("")){
					Toast.makeText(v.getContext(), "Please set from and to", Toast.LENGTH_LONG).show();
					return;
				}

		        // 設定のチェック
		        SharedPreferences pref = getSharedPreferences(PREF_NAME,MODE_WORLD_READABLE);
		        int urlCheck = pref.getInt(PREF_CHK_URL, R.id.RBYahoo);
		        String url = "";
		        if( urlCheck == R.id.RBYahoo ){ url = UrlCreater.CreateYahooUrl(from,to); }
		        else if( urlCheck == R.id.RBGoogle ){ url = UrlCreater.CreateGoogleUrl(from,to); }
	        	else{ url = UrlCreater.CreateYahooUrl(from,to);}
		        
		        // ブラウザを開く
				if( BrowserAccessor.openUrl(LastTrain.this, url) < 0 ){
					Toast.makeText(v.getContext(), "failure to open browser", Toast.LENGTH_LONG).show();
				}
				finish();
			}
		});
        
        butExec = (Button)findViewById(R.id.BChangeSetting);
        butExec.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				 Intent intent = new Intent(LastTrain.this,LTSetting.class);  
				 startActivityForResult(intent, 0);
			}
		});
    }
    
    private String GetFrom(){
    	EditText etfrom = (EditText)findViewById(R.id.ETFrom);
    	return etfrom.getText().toString();
    }
    
    private String GetTo(){
    	EditText etTo = (EditText)findViewById(R.id.ETTo);
    	return etTo.getText().toString();
    }

    private void SetTo(String value){
    	EditText etTo = (EditText)findViewById(R.id.ETTo);
    	etTo.setText(value);
    }
    

}