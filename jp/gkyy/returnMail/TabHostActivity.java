package jp.gkyy.returnMail;

import android.app.TabActivity;
import android.os.Bundle;

public abstract class TabHostActivity extends TabActivity {

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
        setContentView(R.layout.tab_top);
        SetTabs();
    }
    
    public abstract void SetTabs();

}
