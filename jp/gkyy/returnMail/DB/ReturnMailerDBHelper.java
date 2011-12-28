package jp.gkyy.returnMail.DB;

import jp.gkyy.returnMail.DB.ReturnMailerDBDefinition.MailBodyItemColumns;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ReturnMailerDBHelper extends SQLiteOpenHelper {

	public ReturnMailerDBHelper(Context ctx){
		super(ctx,ReturnMailerDBDefinition.DB_NAME,null,ReturnMailerDBDefinition.DB_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + ReturnMailerDBDefinition.TB_MAIL_BODY + "(" + 
				   MailBodyItemColumns._ID + " INTEGER PRIMARY KEY,  " +
				   MailBodyItemColumns._COUNT + " INTEGER,  " +
				   MailBodyItemColumns.COL_GID + " " + 
				   MailBodyItemColumns.COL_GID_TYPE + ",  " +
				   MailBodyItemColumns.COL_NUM + " " + 
				   MailBodyItemColumns.COL_NUM_TYPE + ",  " +
				   MailBodyItemColumns.COL_ITEM + " " + 
				   MailBodyItemColumns.COL_ITEM_TYPE + "  " +
				   " ); ");
		ContentValues cv = new ContentValues();
		addContentValueItem(cv,1,1,"�����l");
		db.insert(ReturnMailerDBDefinition.TB_MAIL_BODY, null, cv);
		addContentValueItem(cv,1,2,"�����l�ł�");
		db.insert(ReturnMailerDBDefinition.TB_MAIL_BODY, null, cv);
		addContentValueItem(cv,1,3,"�I���܂���");
		db.insert(ReturnMailerDBDefinition.TB_MAIL_BODY, null, cv);
		addContentValueItem(cv,2,1,"������A���");
		db.insert(ReturnMailerDBDefinition.TB_MAIL_BODY, null, cv);
		addContentValueItem(cv,2,2,"������A��܂�");
		db.insert(ReturnMailerDBDefinition.TB_MAIL_BODY, null, cv);
		addContentValueItem(cv,2,3,"������A��܂���");
		db.insert(ReturnMailerDBDefinition.TB_MAIL_BODY, null, cv);

		addContentValueItem(cv,101,1,"����");
		db.insert(ReturnMailerDBDefinition.TB_MAIL_BODY, null, cv);
		addContentValueItem(cv,101,2,"��");
		db.insert(ReturnMailerDBDefinition.TB_MAIL_BODY, null, cv);
		addContentValueItem(cv,101,3,"�Ђ悱");
		db.insert(ReturnMailerDBDefinition.TB_MAIL_BODY, null, cv);
		addContentValueItem(cv,101,4,"�L");
		db.insert(ReturnMailerDBDefinition.TB_MAIL_BODY, null, cv);
		addContentValueItem(cv,101,5,"��[��");
		db.insert(ReturnMailerDBDefinition.TB_MAIL_BODY, null, cv);
		addContentValueItem(cv,101,6,"�҂��҂�");
		db.insert(ReturnMailerDBDefinition.TB_MAIL_BODY, null, cv);

		addContentValueItem(cv,102,1,"����l");
		db.insert(ReturnMailerDBDefinition.TB_MAIL_BODY, null, cv);
		addContentValueItem(cv,102,2,"�d��");
		db.insert(ReturnMailerDBDefinition.TB_MAIL_BODY, null, cv);
		addContentValueItem(cv,102,3,"�V����");
		db.insert(ReturnMailerDBDefinition.TB_MAIL_BODY, null, cv);
		addContentValueItem(cv,102,4,"��");
		db.insert(ReturnMailerDBDefinition.TB_MAIL_BODY, null, cv);
		addContentValueItem(cv,102,5,"�y���M��");
		db.insert(ReturnMailerDBDefinition.TB_MAIL_BODY, null, cv);
		addContentValueItem(cv,102,6,"��");
		db.insert(ReturnMailerDBDefinition.TB_MAIL_BODY, null, cv);
		addContentValueItem(cv,102,7,"�����C��");
		db.insert(ReturnMailerDBDefinition.TB_MAIL_BODY, null, cv);
	}
	
	private void addContentValueItem(ContentValues cv, int gid, int num, String item){
		cv.put(MailBodyItemColumns.COL_GID, gid);
		cv.put(MailBodyItemColumns.COL_NUM, num);
		cv.put(MailBodyItemColumns.COL_ITEM, item);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE " + ReturnMailerDBDefinition.TB_MAIL_BODY + ";");
	}

}
