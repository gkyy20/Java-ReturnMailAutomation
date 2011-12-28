package jp.gkyy.returnMail;

import java.util.List;

import jp.gkyy.returnMail.DB.DBOperator;
import jp.gkyy.returnMail.DB.MailBodyItem;
import jp.gkyy.returnMail.DB.ReturnMailerDBDefinition;
import jp.gkyy.returnMail.DB.ReturnMailerDBDefinition.MailBodyItemColumns;
import jp.gkyy.returnMail.Util.EmojiCode;
import jp.gkyy.returnMail.Util.EmojiMaster;
import jp.gkyy.returnMail.Util.MailBodyGenerator;
import android.app.Activity;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MailEmojiSetting extends Activity implements OnClickListener, OnItemClickListener {
	public static final String LINE_NUM = "lineNumber";
	public static final String EMOJI_MASTER = "emojiMaster";
	private ArrayAdapter<EmojiCode> _adapter;
	private int _linenum = -1;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registemojiitem);
        ((Button)findViewById(R.id.ButSetEmojiItem)).setOnClickListener(this);
        ((Button)findViewById(R.id.ButClearEmojiItem)).setOnClickListener(this);

        EmojiMaster em = (EmojiMaster)getIntent().getSerializableExtra(EMOJI_MASTER);
        this._adapter = new ArrayAdapter<EmojiCode>(this,R.layout.grid_item,em.getGoogleCodeArray());
        ListView listview = (ListView) findViewById(R.id.LVEmoji);
        listview.setAdapter(this._adapter);
        listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        
        _linenum = getIntent().getIntExtra(MailBodySetting.LINE_NUM,0);
        List<MailBodyItem> eArray = MailBodyGenerator.getInstance().getbEMaster().get(_linenum);
        for( MailBodyItem mbi : eArray){
        	if( mbi.getItem() != null ){
	        	for( int i = 0 ; i < listview.getCount()-1; i++){
	        		if( ((EmojiCode)listview.getItemAtPosition(i))
	        			.get_name().equals(
	        					((EmojiCode)mbi.getItem()).get_name())){
	        			listview.setItemChecked(i, true); 
	        			break;
	        		}
	        	}
        	}
        }
    }
    
	@Override
	public void onClick(View v) {
		ListView listview = ((ListView) findViewById(R.id.LVEmoji));

		switch(v.getId()) {
		case R.id.ButSetEmojiItem:
			SparseBooleanArray sba = listview.getCheckedItemPositions();
			int cnt = 0;
			for( int i = 0 ; i < listview.getCount()-1 ; i++){
				if(sba.get(i)) cnt++;
			}
			if( cnt <= 0 ){
				Toast.makeText(this, "At Least 1 item should be selected!!", Toast.LENGTH_SHORT).show();
				return;
			}
			
			int gid = _linenum + MailBodyGenerator.EMOJI_FROM;
			if( DBOperator.delete(ReturnMailerDBDefinition.CONTENT_URI, 
					  MailBodyItemColumns.COL_GID + " = " + gid, 
					  null) >= 1 ){
				int num = 1;
				ContentValues cv = new ContentValues();
				String name = "";
				for( int i = 0; i < listview.getCount()-1 ; i++){
					if( sba.get(i) ){
						name = ((EmojiCode)listview.getItemAtPosition(i)).get_name();
						cv.put(ReturnMailerDBDefinition.MailBodyItemColumns.COL_GID, gid);
						cv.put(ReturnMailerDBDefinition.MailBodyItemColumns.COL_NUM, num);
						cv.put(ReturnMailerDBDefinition.MailBodyItemColumns.COL_ITEM, name);
						Uri uri = DBOperator.insert(ReturnMailerDBDefinition.CONTENT_URI, cv);
						if( uri != null ){
							num++;
						}
					}
				}
				
				MailBodyGenerator.getInstance().readEmojiData(gid);
				Toast.makeText(this, "Data registration was done successfully!!", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "Failed to register the data!!", Toast.LENGTH_SHORT).show();
			}
			
			break;
		case R.id.ButClearEmojiItem:
	        List<MailBodyItem> eArray = MailBodyGenerator.getInstance().getbEMaster().get(_linenum);
        	for( int i = 0 ; i < listview.getCount()-1; i++){
    	        listview.setItemChecked(i, false); 
    	        for( MailBodyItem mbi : eArray){
    	        	if( mbi.getItem() != null ){
		        		if( ((EmojiCode)listview.getItemAtPosition(i))
		        			.get_name().equals(
		        					((EmojiCode)mbi.getItem()).get_name())){
		        			listview.setItemChecked(i, true);
		        			break;
		        		}
		        	}
	        	}
	        }
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	}
}
