package stackoverflow.net.icare;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import stackoverflow.net.icare.database.ProfileDataSource;
import stackoverflow.net.icare.utill.Profile;


public class ProfilePreviewUpdate extends ActionBarActivity implements View.OnClickListener {

	Button update_btn = null;
   Button home_btn ;
	EditText et_name = null;
	EditText et_age = null;
	EditText etBloodGroup = null;
	EditText et_weight = null;
	EditText et_height = null;
	EditText et_dateOfBirth = null;
	EditText et_specialNotes = null;
	Toast toast = null;

	String mName = "";
	String mAge = "";
	String mBloodGroup = "";
	String mWeight = "";
	String mHeight = "";
	String mDateOfBirth = "";
	String mSpecialNotes = "";

	Profile mUpdateProfile = null;
	ProfileDataSource mDataSource = null;

    private int startYear=1980;
    private int startMonth=6;
    private int startDay=15;
    private AgeCalculation age = null;

    static final int DATE_START_DIALOG_ID = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_preview_update);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbar_background)));
       init();
home_btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(ProfilePreviewUpdate.this,Home.class);
        startActivity(intent);
    }
});


	}

    private void init() {
        age=new AgeCalculation();


        // set the textfield id to the variable.
        et_name = (EditText) findViewById(R.id.viewName);
        et_age = (EditText) findViewById(R.id.viewAge);
        etBloodGroup = (EditText) findViewById(R.id.viewBloodGroup);
        et_weight = (EditText) findViewById(R.id.viewWeight);
        et_height = (EditText) findViewById(R.id.viewHeight);
        et_dateOfBirth = (EditText) findViewById(R.id.viewDOB);
        et_specialNotes = (EditText) findViewById(R.id.viewSpComment);
        update_btn = (Button) findViewById(R.id.update);
        home_btn = (Button) findViewById(R.id.buttonHome);

		/*
		 * get the profile which include all data from database according
		 * profileId of the clicked item.
		 */
        mDataSource = new ProfileDataSource(this);
        mUpdateProfile = mDataSource.singleProfileData();

        String mName = mUpdateProfile.getName();
        String mAge = mUpdateProfile.getAge();
        String mEyeColor = mUpdateProfile.getBlooGroup();
        String mWeight = mUpdateProfile.getWeight();
        String mHeight = mUpdateProfile.getHeight();
        String mDateOfBirth = mUpdateProfile.getDateOfBirth();
        String mSpecialNotes = mUpdateProfile.getSpecialNotes();

        // set the value of database to the text field.
        et_name.setText(mName);
        et_age.setText(mAge);
        etBloodGroup.setText(mEyeColor);
        et_weight.setText(mWeight);
        et_height.setText(mHeight);
        //et_dateOfBirth.setText(mDateOfBirth);
        et_specialNotes.setText(mSpecialNotes);

        et_dateOfBirth.setText(age.getCurrentDate());
        et_dateOfBirth.setOnClickListener(this);

    }

    public void updateProfile(View v) {

		mName = et_name.getText().toString();
		mAge = et_age.getText().toString();
		mBloodGroup = etBloodGroup.getText().toString();
		mWeight = et_weight.getText().toString();
		mHeight = et_height.getText().toString();
		mDateOfBirth = et_dateOfBirth.getText().toString();
		mSpecialNotes = et_specialNotes.getText().toString();

		// Assign values in the ICareProfile
		Profile profileDataInsert = new Profile();
		profileDataInsert.setName(mName);
		profileDataInsert.setAge(mAge);
		profileDataInsert.setBloodGroup(mBloodGroup);
		profileDataInsert.setWeight(mWeight);
		profileDataInsert.setHeight(mHeight);
		profileDataInsert.setDateOfBirth(mDateOfBirth);
		profileDataInsert.setSpecialNotes(mSpecialNotes);

		mDataSource = new ProfileDataSource(this);

		if (mDataSource.updateData(1, profileDataInsert) == true) {
			toast = Toast.makeText(this,
					getString(R.string.successfullyUpdated), Toast.LENGTH_LONG);
			toast.show();
			Intent intent = new Intent(ProfilePreviewUpdate.this,
					ProfilePreviewUpdate.class);
			finish();
		} else {
			toast = Toast.makeText(this, getString(R.string.notUpdated),
					Toast.LENGTH_LONG);
			toast.show();
		}
	}


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.viewDOB:
                showDialog(DATE_START_DIALOG_ID);
                break;

            default:
                break;
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_START_DIALOG_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        startYear, startMonth, startDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener
            = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            startYear=selectedYear;
            startMonth=selectedMonth;
            startDay=selectedDay;
            age.setDateOfBirth(startYear, startMonth, startDay);
            et_dateOfBirth.setText(""+selectedDay+":"+(startMonth+1)+":"+startYear);
            calculateAge();
        }
    };

    private void calculateAge()
    {
        age.calcualteYear();
        age.calcualteMonth();
        age.calcualteDay();
        //Toast.makeText(getBaseContext(), "click the resulted button"+age.getResult() , Toast.LENGTH_SHORT).show();
        et_age.setText(age.getResult());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile_preview_update, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deleteProfile:
                mDataSource.deleteData(1);
                startActivity(new Intent(ProfilePreviewUpdate.this,
                        ProfileCreate.class));
                finish();
                return true;
            case R.id.home:
                startActivity(new Intent(ProfilePreviewUpdate.this,
                       Home.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
