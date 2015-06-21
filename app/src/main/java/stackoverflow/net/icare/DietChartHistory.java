package stackoverflow.net.icare;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import stackoverflow.net.icare.database.DietDataSource;
import stackoverflow.net.icare.database.SQLiteHelper;
import stackoverflow.net.icare.utill.FTFLConstants;


public class DietChartHistory extends ActionBarActivity {
	ListView mListView = null;
	TextView tvDate  = null;
	DietDataSource mDietDataSource  = null;
	SQLiteHelper mDBHelper  = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diet_chart_history);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbar_background)));
		
		mDBHelper = new SQLiteHelper(this);
		mDietDataSource = new DietDataSource(this);
		mListView = (ListView) findViewById(R.id.lvDietChartHistory);
		
		List<String> allDates = null;
		if(FTFLConstants.RADIOBTN == FTFLConstants.ZERO)
		{
			allDates = mDietDataSource.getAllDates();
		}
		
		else if(FTFLConstants.RADIOBTN == FTFLConstants.TWO)
		{	
			allDates = mDietDataSource.getPreviousDates(-7);
		}
		
		else
		{
			allDates = mDietDataSource.getPreviousDates(-30);
		}
		
		ArrayAdapter<String> mDatesAdapter = new ArrayAdapter<String>(this,
		        android.R.layout.simple_list_item_1, allDates);
		// adding it to the list view.
		mListView.setAdapter(mDatesAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent,
					View view, int position, long id) {

				tvDate = (TextView) view.findViewById(android.R.id.text1);
				String dateValue = tvDate.getText().toString(); 
				Intent mPreviewIntent = new Intent(getApplicationContext(),
						DailyDietChartView.class);
				mPreviewIntent.putExtra("cDate", dateValue);

				startActivity(mPreviewIntent);
			}
		});
	}
	
	public void viweAllData(View v){
		FTFLConstants.RADIOBTN = FTFLConstants.ZERO;
		 startActivity(new Intent(DietChartHistory.this,
				 DietChartHistory.class));
	}
	
	public void viewWeeklyData(View v){
		FTFLConstants.RADIOBTN = FTFLConstants.ONE;
		 
		 startActivity(new Intent(DietChartHistory.this,
				 DietChartHistory.class));
	}
	
	public void viewMonthlyData(View v){
		FTFLConstants.RADIOBTN = FTFLConstants.TWO;
		 
		 startActivity(new Intent(DietChartHistory.this,
				 DietChartHistory.class));
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_diet_chart_history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.home) {

            home();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public  void home(){
     Intent intent= new Intent(this,DietListTodayAndUpcoming.class) ;
        startActivity(intent);
    }
}
