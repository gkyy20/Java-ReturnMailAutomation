package jp.gkyy.returnMail.DB;

import java.util.HashMap;

import android.net.Uri;
import android.provider.BaseColumns;

public class ReturnMailerDBDefinition {
	public static final String DB_NAME = "returnMailer.db";
	public static final int DB_VERSION = 1;
	
	public static final String TB_MAIL_BODY = "mailBodyItem";
	public static final Uri CONTENT_URI = Uri.parse("content://jp.gkyy.returnMail/mailBodyItem");

	public static final class MailBodyItemColumns implements BaseColumns{
		public static final String COL_GID = "GID";
		public static final String COL_GID_TYPE = "INTEGER";

		public static final String COL_NUM = "NUM";
		public static final String COL_NUM_TYPE = "INTEGER";

		public static final String COL_ITEM = "ITEM";
		public static final String COL_ITEM_TYPE = "VARCHAR";

		public static final String DEFAULT_SORT_ORDER = "_ID";
	}
	
	public static final HashMap<String,String> MAILBODY_PROJECTION_MAP;
	static{
		MAILBODY_PROJECTION_MAP = new HashMap<String,String>();
		MAILBODY_PROJECTION_MAP.put(
				ReturnMailerDBDefinition.MailBodyItemColumns._ID, 
				ReturnMailerDBDefinition.MailBodyItemColumns._ID);
		MAILBODY_PROJECTION_MAP.put(
				ReturnMailerDBDefinition.MailBodyItemColumns._COUNT, 
				ReturnMailerDBDefinition.MailBodyItemColumns._COUNT);
		MAILBODY_PROJECTION_MAP.put(
				ReturnMailerDBDefinition.MailBodyItemColumns.COL_GID, 
				ReturnMailerDBDefinition.MailBodyItemColumns.COL_GID);
		MAILBODY_PROJECTION_MAP.put(
				ReturnMailerDBDefinition.MailBodyItemColumns.COL_NUM, 
				ReturnMailerDBDefinition.MailBodyItemColumns.COL_NUM);
		MAILBODY_PROJECTION_MAP.put(
				ReturnMailerDBDefinition.MailBodyItemColumns.COL_ITEM, 
				ReturnMailerDBDefinition.MailBodyItemColumns.COL_ITEM);
	}

}
