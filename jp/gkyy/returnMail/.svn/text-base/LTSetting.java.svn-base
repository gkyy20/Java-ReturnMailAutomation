package jp.gkyy.returnMail;

import jp.gkyy.returnMail.R.id;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class LTSetting extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	// 初期設定
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        
        // 現在の設定を表示
        SharedPreferences pref = getSharedPreferences(LastTrain.PREF_NAME,MODE_WORLD_READABLE|MODE_WORLD_WRITEABLE);
        SetDefTo(pref.getString(LastTrain.PREF_ITEM_DEFTO, "善行"));
        SetUrlCheck(pref.getInt(LastTrain.PREF_CHK_URL, id.RBYahoo));

        // 実行ボタンのアクションを設定
        Button butExec = (Button)findViewById(R.id.BSettingExec);
        butExec.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
		        // デフォルトの設定
		        SharedPreferences pref = getSharedPreferences(LastTrain.PREF_NAME,MODE_WORLD_READABLE|MODE_WORLD_WRITEABLE);
		        Editor e = pref.edit();
		        e.putString("DefaultTo", GetDefTo());
		        e.putInt("CheckURL", GetUrlCheck());
		        e.commit();
		        Log.d("--Setting.java--", "DefTo : " + GetDefTo());
		        Log.d("--Setting.java--", "URL   : " + GetUrlCheck());
		        finish();
			}
		});
    }

    private void SetDefTo(String value){
    	EditText etTo = (EditText)findViewById(R.id.ETDefTo);
    	etTo.setText(value);
    }

    private String GetDefTo(){
    	EditText etTo = (EditText)findViewById(R.id.ETDefTo);
    	return etTo.getText().toString();
    }
    
    private void SetUrlCheck(int id){
        RadioGroup rg = (RadioGroup)findViewById(R.id.RGUrl);
        rg.check(id);    	
    }

    private int GetUrlCheck(){
        RadioGroup rg = (RadioGroup)findViewById(R.id.RGUrl);
        return rg.getCheckedRadioButtonId();
    }
}
