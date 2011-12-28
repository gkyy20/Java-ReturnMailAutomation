package jp.gkyy.returnMail;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MailWholeSetting extends Activity implements OnClickListener{

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerwholesetting);
        ((Button)findViewById(R.id.BWholeSetting)).setOnClickListener(this);
        
        String carrier = getSharedPreferences(ReturnMailer.PREF_NAME,MODE_WORLD_READABLE|MODE_WORLD_WRITEABLE).getString(ReturnMailer.PREF_ITEM_CARRIER, "");
        if( carrier != "" ){
        	Spinner spn = ((Spinner)findViewById(R.id.SPNWholeMailer));
        	for( int i = 0 ; i < spn.getCount(); i++){
        		if( spn.getItemAtPosition(i).toString().equals(carrier) ){
        			spn.setSelection(i);
        			break;
        		}
        	}
        }
    }

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.BWholeSetting:
			String item = ((Spinner)findViewById(R.id.SPNWholeMailer)).getSelectedItem().toString();
	        SharedPreferences pref =
	        	getSharedPreferences(ReturnMailer.PREF_NAME,MODE_WORLD_READABLE|MODE_WORLD_WRITEABLE);
	        Editor e = pref.edit();
	        e.putString(ReturnMailer.PREF_ITEM_CARRIER, item);
	        e.commit();
	        Toast.makeText(this, "Setting was applied!!", Toast.LENGTH_SHORT).show();
	        finish();
			break;
		}
	}
}
