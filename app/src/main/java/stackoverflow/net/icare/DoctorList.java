package stackoverflow.net.icare;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import stackoverflow.net.icare.database.DoctorProfileDataSource;
import stackoverflow.net.icare.utill.DoctorProfile;

/**
 * Created by Prodip on 6/21/2015.
 */
public class DoctorList extends ActionBarActivity {

    ListView lstView;
    DoctorProfileDataSource database;
    List<DoctorProfile> arrayListDoctor;
    ArrayList<String> allDoctor;


    //TextView txtShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_list);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbar_background)));

        database = new DoctorProfileDataSource(this);

        allDoctor = new ArrayList<String>();

       // arrayListEmployee = new ArrayList<Employee>();
        arrayListDoctor = database.getAllDoctors();
        //Toast.makeText(getApplicationContext(), String.valueOf(arrayListDoctor), Toast.LENGTH_LONG).show();
        lstView = (ListView) findViewById(R.id.LV);
/*
        for (int i = 0; i > arrayListDoctor.size(); i++) {

            allDoctor.add(arrayListDoctor.get(i).toString());
        }

        lstView = (ListView) findViewById(R.id.LV);

       DoctorAdapter doctorAdapter = new DoctorAdapter(this, arrayListDoctor);
        lstView.setAdapter(doctorAdapter);
    */

        List<DoctorProfile> values = database.getAllDoctors();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<DoctorProfile> adapter = new ArrayAdapter<DoctorProfile>(this,
                android.R.layout.simple_list_item_1, values);
        lstView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }
}
