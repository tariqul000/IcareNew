package stackoverflow.net.icare;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Prodip on 6/21/2015.
 */
public class Home extends ActionBarActivity {
    Button addNewDoctor;
    Button updateDietChart;
    Button dietInfo;
    Button profileView;
    Button doctorList;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbar_background)));
        init();
       addNewDoctor.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(Home.this, DoctorProfileCreate.class);
               startActivity(intent);
           }
       });

updateDietChart.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(Home.this, CreateDietChart.class);
        startActivity(intent);
    }
});

       dietInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this, DietListTodayAndUpcoming.class);
                startActivity(intent);
            }
        });
        doctorList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this, DoctorList.class);
                startActivity(intent);
            }
        });
        profileView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this, ProfilePreviewUpdate.class);
                startActivity(intent);
            }
        });
    }

    private void init() {

       addNewDoctor= (Button) findViewById(R.id.btnAddNewDoctor);
        updateDietChart= (Button) findViewById(R.id.btnUpdateDietChart);
        dietInfo= (Button) findViewById(R.id.buttonDietInfo);
        profileView= (Button) findViewById(R.id.buttonProfileView);
        doctorList= (Button) findViewById(R.id.buttonDoctorList);

    }




}
