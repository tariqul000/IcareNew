package stackoverflow.net.icare;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import stackoverflow.net.icare.adapter.DailyProfileViewAdapter;
import stackoverflow.net.icare.database.DietDataSource;
import stackoverflow.net.icare.database.SQLiteHelper;
import stackoverflow.net.icare.utill.DietChart;
import stackoverflow.net.icare.utill.FTFLConstants;


public class DietListTodayAndUpcoming extends ActionBarActivity {
	ListView mListViewOne = null;
	ListView mListViewTwo = null;
	DietDataSource mDietDataSource = null;
	SQLiteHelper mDBHelper = null;
	TextView tvDate = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diet_list_today_upcoming);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbar_background)));
//
//        ActionBar bar = getActionBar();
//        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0000ff")));

		mDBHelper = new SQLiteHelper(this);
		mDietDataSource = new DietDataSource(this);

		List<DietChart> allDailyDietChart = mDietDataSource
				.getTodayDietChart();

		DailyProfileViewAdapter arrayAdapterOne = new DailyProfileViewAdapter(
				this, allDailyDietChart);

		// adding it to the list view.
		mListViewOne = (ListView) findViewById(R.id.lvTodayDietChart);
		mListViewOne.setAdapter(arrayAdapterOne);

		List<String> upcomingDates = mDietDataSource.getUpcomingDates();
		// DailyProfileViewThreeAdapter arrayAdapterTwo = new
		// DailyProfileViewThreeAdapter(this, allDailyDietChart);
		ArrayAdapter<String> mDatesAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, upcomingDates);
		// adding it to the list view.
		mListViewTwo = (ListView) findViewById(R.id.lvUpComingDietChart);
		mListViewTwo.setAdapter(mDatesAdapter);

		mListViewTwo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent,
					android.view.View view, int position, long id) {

				tvDate = (TextView) view.findViewById(android.R.id.text1);
				String dateValue = tvDate.getText().toString(); // get date

				Intent mPreviewIntent = new Intent(getApplicationContext(),
						DailyDietChartView.class);
				mPreviewIntent.putExtra(FTFLConstants.ACTIVITYDATE, dateValue);

				startActivity(mPreviewIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_diet_list_today_and_upcoming, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.history:
			startActivity(new Intent(DietListTodayAndUpcoming.this,
					DietChartHistory.class));
			return true;
		case R.id.createDiet:
			startActivity(new Intent(DietListTodayAndUpcoming.this,
					CreateDietChart.class));
			return true;
		case R.id.myprofile:
			startActivity(new Intent(DietListTodayAndUpcoming.this,
					ProfilePreviewUpdate.class));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
