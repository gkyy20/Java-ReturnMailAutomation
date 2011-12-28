package jp.gkyy.returnMail.DB;

import jp.gkyy.returnMail.DB.ReturnMailerDBDefinition.MailBodyItemColumns;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class ReturnMailerDBProvider extends ContentProvider {
	private SQLiteDatabase mDB;
	
	private static final int MAIL_BODY    = 1001;
	private static final int MAIL_BODY_ID = 1002;
	
	private static final UriMatcher URL_MATCHER;
	static{
		URL_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
		URL_MATCHER.addURI(ReturnMailerDBDefinition.CONTENT_URI.getAuthority(), "mailBodyItem", MAIL_BODY);
		URL_MATCHER.addURI(ReturnMailerDBDefinition.CONTENT_URI.getAuthority(), "mailBodyItem/#", MAIL_BODY_ID);
	}

	@Override
	public boolean onCreate() {
		ReturnMailerDBHelper dbHelper = new ReturnMailerDBHelper(getContext());
		this.mDB = dbHelper.getWritableDatabase();
		return this.mDB != null;
	}
	
	@Override
	public int delete(Uri uri, String whereClause, String[] whereArgs) {
		if( uri == null ) throw new IllegalArgumentException();
		int res = 0;
		
		switch(URL_MATCHER.match(uri)){
			case MAIL_BODY:
				res = this.mDB.delete(ReturnMailerDBDefinition.TB_MAIL_BODY, 
									  whereClause, whereArgs);
				break;
			case MAIL_BODY_ID:
				String feedID = uri.getPathSegments().get(1);
				res = this.mDB.delete(ReturnMailerDBDefinition.TB_MAIL_BODY, 
									  MailBodyItemColumns._ID + " = " + feedID + (!TextUtils.isEmpty(whereClause) ? " AND (" + whereClause + ")" : ""),
									  whereArgs);
				break;
			default:
				throw new IllegalArgumentException("Unknown Uri " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return res;
	}

	@Override
	public String getType(Uri arg0) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		if( uri == null || values == null  ) throw new IllegalArgumentException();
		
		switch(URL_MATCHER.match(uri)){
			case MAIL_BODY:
				long rowID = this.mDB.insert("mailBodyItem", "", values);
				if( rowID > 0 ){
					Uri rUri = ContentUris.withAppendedId(uri, rowID);
					getContext().getContentResolver().notifyChange(rUri, null);
					return rUri;
				}
				return null;
			default:
				throw new IllegalArgumentException();
		}
	}

	@Override
	public Cursor query(Uri      uri, 
						String[] projection, 
						String   selection, 
						String[] selectionArgs,
						String   sortOrder) {
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		String orderBy = null;
		switch(URL_MATCHER.match(uri)){
			case MAIL_BODY:
				qb.setTables(ReturnMailerDBDefinition.TB_MAIL_BODY);
				
				if(TextUtils.isEmpty(sortOrder)){
					orderBy = MailBodyItemColumns.DEFAULT_SORT_ORDER;
				} else {
					orderBy = sortOrder;
				}
				break;
			case MAIL_BODY_ID:
				qb.setTables(ReturnMailerDBDefinition.TB_MAIL_BODY);
				qb.appendWhere( MailBodyItemColumns._ID + " = " + uri.getLastPathSegment());
				if(TextUtils.isEmpty(sortOrder)){
					orderBy = MailBodyItemColumns.DEFAULT_SORT_ORDER;
				} else {
					orderBy = sortOrder;
				}
				break;
			default:
				throw new IllegalArgumentException();
		}
		Cursor c = qb.query(this.mDB, projection, selection, selectionArgs, null, null, orderBy);
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
