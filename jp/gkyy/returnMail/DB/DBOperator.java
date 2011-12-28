package jp.gkyy.returnMail.DB;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DBOperator {
	protected static ContentResolver mContentResolver;
	
	public static ContentResolver getContentResolver() {
		return mContentResolver;
	}

	public static void setContentResolver(ContentResolver resolver) {
		mContentResolver = resolver;
	}

	public static Uri insert(Uri uri, ContentValues cv){
		return mContentResolver.insert(uri, cv);
	}
	
	public static Uri insertIfNotExists(Uri uri,
									    String selection, 
									    String[] selectionArgs,
									    ContentValues cv ){
		Uri u = null;
		String[] projection = {BaseColumns._ID};
		Cursor c = mContentResolver.query(uri, projection, selection, selectionArgs, null);
		if( c.getCount() <= 0 ){
			u = insert( uri, cv );
		} else {
			c.moveToFirst();
			String tid = c.getString(c.getColumnIndex(BaseColumns._ID));
			u = ContentUris.withAppendedId(uri, Long.parseLong(tid));
		}
		c.close();
		return u;
	}
	
	public static int delete(Uri uri, String whereClause, String[] whereArgs){
		return mContentResolver.delete(uri, whereClause, whereArgs);
	}
	
	public static int update(Uri uri, ContentValues cv, String selection, String[] selectionArgs){
		return mContentResolver.update(uri, cv, selection, selectionArgs);
	}
	
	public static Cursor query(Uri uri, String[] projection, 
			                   String selection, String[] selectionArgs,
			                   String sortOrder){
		return mContentResolver.query(uri, projection, selection, selectionArgs, sortOrder);
	}
}
