package jp.gkyy.returnMail;

import android.content.Intent;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class ReturnMailerSetting extends TabHostActivity {

	@Override
	public void SetTabs() {
		addBodyItemSettingTab("FirstLine",0);
		addEmojiSettingTab("FirstEmoji",0);
		addBodyItemSettingTab("SecondLine",1);
		addEmojiSettingTab("SecondEmoji",1);
	}

	private void addBodyItemSettingTab(String name, int num){
        TabHost host = this.getTabHost();
        TabSpec spec = host.newTabSpec( name );
        spec.setIndicator( name );
        Intent intent = new Intent();
        intent.putExtra(MailBodySetting.LINE_NUM, num);
        intent.setClass( this, MailBodySetting.class );
        spec.setContent( intent );
        host.addTab( spec );
	}

	private void addEmojiSettingTab(String name, int num){
        TabHost host = this.getTabHost();
        TabSpec spec = host.newTabSpec( name );
        spec.setIndicator( name );
        Intent intent = new Intent();
        intent.putExtra( MailEmojiSetting.LINE_NUM, num);
        intent.putExtra( MailEmojiSetting.EMOJI_MASTER, 
        		         this.getIntent().getSerializableExtra(MailEmojiSetting.EMOJI_MASTER));
        intent.setClass( this, MailEmojiSetting.class );
        spec.setContent( intent );
        host.addTab( spec );
	}

}
