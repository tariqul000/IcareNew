package stackoverflow.net.icare;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import stackoverflow.net.icare.database.ProfileDataSource;
import stackoverflow.net.icare.utill.Profile;


public class ProfileCreate extends ActionBarActivity implements
		OnClickListener {
    Button btnSave = null;
    EditText etName = null;
    EditText etAge = null;
    EditText etBloodGroup = null;
    EditText etWeight = null;
    EditText etHeight = null;
    EditText etDateOfBirth = null;
    EditText etSpecialNotes = null;
    Toast toast = null;

    String mName = "";
    String mAge = "";
    String mBloodGroup = "";
    String mWeight = "";
    String mHeight = "";
    String mDateOfBirth = "";
    String mSpecialNotes = "";

    ProfileDataSource icareDS = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_create);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbar_background)));
        // set the text field id to the variable.
        etName = (EditText) findViewById(R.id.createUserName);
        etAge = (EditText) findViewById(R.id.createAge);
        etBloodGroup = (EditText) findViewById(R.id.createBloodGroup);
        etWeight = (EditText) findViewById(R.id.createWeight);
        etHeight = (EditText) findViewById(R.id.createHeight);
        etDateOfBirth = (EditText) findViewById(R.id.createDateOfBirth);
        etSpecialNotes = (EditText) findViewById(R.id.createSpecialComment);
        btnSave = (Button) findViewById(R.id.Save);
        btnSave.setOnClickListener(this);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.icare, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onClick(View v) {
        mName = etName.getText().toString();
        mAge = etAge.getText().toString();
        mBloodGroup = etBloodGroup.getText().toString();
        mWeight = etWeight.getText().toString();
        mHeight = etHeight.getText().toString();
        mDateOfBirth = etDateOfBirth.getText().toString();
        mSpecialNotes = etSpecialNotes.getText().toString();

        // Assign values in the ICareProfile
        Profile profileDataInsert = new Profile();
        profileDataInsert.setName(mName);
        profileDataInsert.setAge(mAge);
        profileDataInsert.setBloodGroup(mBloodGroup);
        profileDataInsert.setWeight(mWeight);
        profileDataInsert.setHeight(mHeight);
        profileDataInsert.setDateOfBirth(mDateOfBirth);
        profileDataInsert.setSpecialNotes(mSpecialNotes);

		/*
		 * if update is needed then update otherwise submit
		 */
        icareDS = new ProfileDataSource(this);
        if (icareDS.insert(profileDataInsert) == true) {
            toast = Toast.makeText(this, getString(R.string.successfullySaved),
                    Toast.LENGTH_LONG);
            toast.show();
            startActivity(new Intent(ProfileCreate.this,
                    Home.class));
        } else {
            toast = Toast.makeText(this, getString(R.string.notSaved), Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
