package stackoverflow.net.icare;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import stackoverflow.net.icare.adapter.DailyProfileViewAdapter;
import stackoverflow.net.icare.database.DietDataSource;
import stackoverflow.net.icare.database.SQLiteHelper;
import stackoverflow.net.icare.utill.DietChart;
import stackoverflow.net.icare.utill.FTFLConstants;


public class DailyDietChartView extends ActionBarActivity {
	ListView mListView = null;
	DietDataSource mDietDataSource = null;
	SQLiteHelper mDBHelper = null;
	DietChart dailyDietChart = null;
	int id_To_Update = 0;
	String mDate = "";
	TextView textDate;
	String mId = "";
	TextView mId_tv = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.daily_diet_chart_view);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbar_background)));
		final String[] option = new String[] { getString(R.string.editData),
				getString(R.string.deleteData) };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.select_dialog_item, option);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle(getString(R.string.selectData));
		builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				if (which == 0) {
					editData(mId);
				}
				if (which == 1) {
					deleteData(mId);
				}
			}
		});
		final AlertDialog dialog = builder.create();

		mDBHelper = new SQLiteHelper(this);
		mDietDataSource = new DietDataSource(this);
		textDate = (TextView) findViewById(R.id.textDailyDietChartDate);

		Intent mIntent = getIntent();
		mDate = mIntent.getStringExtra(FTFLConstants.ACTIVITYDATE);

		textDate.setText(mDate);
		List<DietChart> allDailyDietChart = mDietDataSource
				.getDailyDietChart(mDate);

		DailyProfileViewAdapter arrayAdapter = new DailyProfileViewAdapter(
				this, allDailyDietChart);

		// adding it to the list view.
		mListView = (ListView) findViewById(R.id.lvDailyDietChart);
		mListView.setAdapter(arrayAdapter);
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				mId_tv = (TextView) view.findViewById(R.id.viewId);
				mId = mId_tv.getText().toString(); // get id
				dialog.show();
			}
		});
	}

	public void editData(String eId) {
		Intent mEditIntent = new Intent(getApplicationContext(),
				CreateDietChart.class);
		mEditIntent.putExtra("FTFLConstants.ACTIVITYID", eId);
		// startActivity(mEditIntent);
		startActivityForResult(mEditIntent, 2);
	}

	public void deleteData(String eId) {
		mDietDataSource = new DietDataSource(this);
		Long lId = Long.parseLong(eId);
		mDietDataSource.deleteData(lId);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.daily_diet_chart_view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.home) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
