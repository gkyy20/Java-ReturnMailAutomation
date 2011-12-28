package jp.gkyy.returnMail;

import java.util.List;

import jp.gkyy.returnMail.DB.DBOperator;
import jp.gkyy.returnMail.DB.MailBodyItem;
import jp.gkyy.returnMail.DB.ReturnMailerDBDefinition;
import jp.gkyy.returnMail.DB.ReturnMailerDBDefinition.MailBodyItemColumns;
import jp.gkyy.returnMail.Util.MailBodyGenerator;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MailBodySetting extends Activity implements OnClickListener {
	public static final String LINE_NUM = "lineNumber";
	 private ArrayAdapter<MailBodyItem> _adapter;
	 private int _lineNum;
	 ListView _listView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registbodyitem);
        
        ((Button)findViewById(R.id.ButResistBodyItem)).setOnClickListener(this);
        ((Button)findViewById(R.id.ButDeleteBodyItem)).setOnClickListener(this);
        
        _lineNum = getIntent().getIntExtra(MailBodySetting.LINE_NUM,0);
		_adapter = new ListViewAdapter<MailBodyItem>(this, 
				                                    R.layout.list_item,
				                                    MailBodyGenerator.getInstance().getbIMaster().get(_lineNum));
        _listView = (ListView) findViewById(R.id.LVBodyItem);
        _listView.setAdapter(_adapter);
        _listView.setItemsCanFocus(false); 
        _listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE); 
    }

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.ButResistBodyItem:
			String str = ((EditText)findViewById(R.id.ETbodyitem)).getText().toString();
			if( !str.equals("")){
				int num = MailBodyGenerator.getInstance().getbIMaster().getNextNum(_lineNum);

				ContentValues cv = new ContentValues();
				cv.put(ReturnMailerDBDefinition.MailBodyItemColumns.COL_GID, _lineNum + 1);
				cv.put(ReturnMailerDBDefinition.MailBodyItemColumns.COL_NUM, num);
				cv.put(ReturnMailerDBDefinition.MailBodyItemColumns.COL_ITEM, str);
				Uri uri = DBOperator.insert(ReturnMailerDBDefinition.CONTENT_URI, cv);
				
				if( uri != null ){
					MailBodyItem mbi = new MailBodyItem( uri.getPathSegments().get(1), _lineNum, num, str);
					MailBodyGenerator.getInstance().getbIMaster().add(_lineNum, mbi);
					((EditText)findViewById(R.id.ETbodyitem)).setText("");
					_adapter.notifyDataSetChanged();
					Toast.makeText(this, "New Item was added!!", Toast.LENGTH_SHORT).show();
				}
			}
			break;
		case R.id.ButDeleteBodyItem:
			if( MailBodyGenerator.getInstance().getbIMaster().size(_lineNum) <= 1 ){
				Toast.makeText(this, "Can't delete all Item!!", Toast.LENGTH_SHORT).show();
				break;
			}
			String _id = ((MailBodyItem)_listView.getItemAtPosition(_listView.getCheckedItemPosition())).get_ID();
			if( DBOperator.delete(ReturnMailerDBDefinition.CONTENT_URI, 
								  MailBodyItemColumns._ID + " = " + _id, 
								  null) >= 1 ){
				MailBodyGenerator.getInstance().getbIMaster().remove(_lineNum, _listView.getCheckedItemPosition());
				_adapter.notifyDataSetChanged();
				Toast.makeText(this, "One Item was deleted!!", Toast.LENGTH_SHORT).show();
			}
			break;
		}
	}
	
	public class ListViewAdapter<T> extends ArrayAdapter<T> {
		private LayoutInflater inflater; 
		private int layoutId; 

		public ListViewAdapter( Context context, 
							    int textViewResourceId,
							    List<T> objects) {
			super(context, textViewResourceId, objects);
			this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
			this.layoutId = textViewResourceId; 
		}

		@Override 
		public View getView(int position, View convertView, ViewGroup parent) { 
			if (convertView == null) { 
				convertView = inflater.inflate(layoutId, parent, false); 
			} 

			((CheckedTextView)convertView).setText(_listView.getItemAtPosition(position).toString()); 
			if(position == _listView.getCheckedItemPosition()){
				((CheckedTextView)convertView).setBackgroundColor(Color.MAGENTA); 
			}
			else {
				((CheckedTextView)convertView).setBackgroundColor(Color.TRANSPARENT); 
			}
			return convertView; 
		} 
	}
}
