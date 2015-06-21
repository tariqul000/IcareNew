package stackoverflow.net.icare.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {
	// ICare Profile Table
	public static final String I_CARE_PROFILE = "icareprofiles";
	public static final String COL_ICARE_PROFILE_ID = "id";
	public static final String COL_ICARE_PROFILE_NAME = "name";
	public static final String COL_ICARE_PROFILE_AGE = "age";
	public static final String COL_ICARE_PROFILE_BLOOD_GROUP = "blood_group";
	public static final String COL_ICARE_PROFILE_WEIGHT = "weight";
	public static final String COL_ICARE_PROFILE_HEIGHT = "height";
	public static final String COL_ICARE_PROFILE_DATE_OF_BIRTH = "dateofbirth";
	public static final String COL_ICARE_PROFILE_SPECIAL_NOTES = "special_notes";

	// ICare Activity Chart Table
	public static final String ICARE_DIET_CHART = "icaredietchart";
	public static final String COL_ICARE_DIET_ID = "diet_id";
	public static final String COL_ICARE_DIET_DATE = "date";
	public static final String COL_ICARE_DIET_TIME = "time";
	public static final String COL_ICARE_DIET_FOOD_MENU = "food_menu";
	public static final String COL_ICARE_DIET_EVENT_NAME = "event_name";
	public static final String COL_ICARE_DIET_ALARM = "set_alarm";


    // ICare Doctor Profile Table
    public static final String I_CARE_DOCTOR_PROFILE = "icaredoctorprofiles";
    public static final String COL_ICARE_DOCTOR_PROFILE_ID = "_id";
    public static final String COL_ICARE_DOCTOR_PROFILE_NAME = "dname";
    public static final String COL_ICARE_DOCTOR_PROFILE_QUALIFICATION = "qualification";
    public static final String COL_ICARE_DOCTOR_PROFILE_DESIGNATION = "designation";
    public static final String COL_ICARE_DOCTOR_PROFILE_EXPERTISE  = "expertise";
    public static final String COL_ICARE_DOCTOR_PROFILE_ORGANIZATION = "organization";
    public static final String COL_ICARE_DOCTOR_PROFILE_CHAMBER = "chamber";
    public static final String COL_ICARE_DOCTOR_PROFILE_VISITING_HOURS = "visitinghours";
    public static final String COL_ICARE_DOCTOR_PROFILE_LOCATION = "location";
    public static final String COL_ICARE_DOCTOR_PROFILE_PHONE = "phone";
    public static final String COL_ICARE_DOCTOR_PROFILE_EMAIL = "email";


    // database name and version
	private static final String DATABASE_NAME = "iCare.db";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String DATABASE_CREATE_PROFILE = "create table "
			+ I_CARE_PROFILE + "( " + COL_ICARE_PROFILE_ID
			+ " integer primary key autoincrement, " + " "
			+ COL_ICARE_PROFILE_NAME + " text not null," + " "
			+ COL_ICARE_PROFILE_AGE + " text not null," + " "
			+ COL_ICARE_PROFILE_BLOOD_GROUP + " text not null," + " "
			+ COL_ICARE_PROFILE_WEIGHT + " text not null," + " "
			+ COL_ICARE_PROFILE_HEIGHT + " text not null," + " "
			+ COL_ICARE_PROFILE_DATE_OF_BIRTH + " text not null," + " "
			+ COL_ICARE_PROFILE_SPECIAL_NOTES + " text not null);";

    // Database creation sql statement
    private static final String DATABASE_CREATE_DOCTOR_PROFILE = "create table "
            + I_CARE_DOCTOR_PROFILE + "( " + COL_ICARE_DOCTOR_PROFILE_ID
            + " integer primary key autoincrement, " + " "
            + COL_ICARE_DOCTOR_PROFILE_NAME + " text not null," + " "
            + COL_ICARE_DOCTOR_PROFILE_QUALIFICATION + " text," + " "
            + COL_ICARE_DOCTOR_PROFILE_DESIGNATION + " text," + " "
            + COL_ICARE_DOCTOR_PROFILE_EXPERTISE + " text," + " "
            + COL_ICARE_DOCTOR_PROFILE_ORGANIZATION + " text," + " "
            + COL_ICARE_DOCTOR_PROFILE_CHAMBER + " text," + " "
            + COL_ICARE_DOCTOR_PROFILE_VISITING_HOURS + " text," + " "
            + COL_ICARE_DOCTOR_PROFILE_LOCATION + " text," + " "
            + COL_ICARE_DOCTOR_PROFILE_PHONE + " text," + " "
            + COL_ICARE_DOCTOR_PROFILE_EMAIL + " text);";

	private static final String DATABASE_CREATE_DACTIVITY = "create table "
			+ ICARE_DIET_CHART + "(" + COL_ICARE_DIET_ID
			+ " integer primary key autoincrement, " + COL_ICARE_DIET_DATE
			+ " text not null," + COL_ICARE_DIET_TIME + " text not null,"
			+ COL_ICARE_DIET_FOOD_MENU + " text not null,"
			+ COL_ICARE_DIET_EVENT_NAME + " text not null,"
			+ COL_ICARE_DIET_ALARM + " text not null);";

	public SQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE_PROFILE);
		database.execSQL(DATABASE_CREATE_DACTIVITY);
        database.execSQL(DATABASE_CREATE_DOCTOR_PROFILE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(SQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + I_CARE_PROFILE);
		db.execSQL("DROP TABLE IF EXISTS " + ICARE_DIET_CHART);
        db.execSQL("DROP TABLE IF EXISTS " + I_CARE_DOCTOR_PROFILE);
		onCreate(db);
	}

}
