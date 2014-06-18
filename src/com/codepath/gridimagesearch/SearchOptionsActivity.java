package com.codepath.gridimagesearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class SearchOptionsActivity extends Activity {
	private String stOptionSize;
	private String stOptionColor;
	private String stOptionType;
	private String stOptionDomain;
	private Spinner spSize;
	private Spinner spColor;
	private Spinner spType;
	private TextView tvDomain;

	/*
	 * I did not implement listeners for the spinners because we don't operate
	 * on the choices until we return from this activity. So polling them just
	 * before the activity is dismissed is good enough.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_options);

		stOptionSize = getIntent().getStringExtra("optionSize");
		int i = GridSearchPrefs.getSizeIndex(stOptionSize);
		spSize = (Spinner) findViewById(R.id.spImageSize);
		spSize.setSelection(i);

		stOptionColor = getIntent().getStringExtra("optionColor");
		i = GridSearchPrefs.getColorIndex(stOptionColor);
		spColor = (Spinner) findViewById(R.id.spColorFilter);
		spColor.setSelection(i);

		stOptionType = getIntent().getStringExtra("optionType");
		i = GridSearchPrefs.getTypeIndex(stOptionType);
		spType = (Spinner) findViewById(R.id.spImageType);
		spType.setSelection(i);

		stOptionDomain = getIntent().getStringExtra("optionDomain");
		tvDomain = (TextView) findViewById(R.id.etSiteFilter);
		tvDomain.setText(stOptionDomain);
	}


	public void onSave(View v) {
		// Prepare data intent 
		Intent data = new Intent();

		int n;
		String s;

		n = spSize.getSelectedItemPosition();
		s = GridSearchPrefs.getSizeString(n);
		data.putExtra("optionSize", s);

		n = spColor.getSelectedItemPosition();
		s = GridSearchPrefs.getColorString(n);
		data.putExtra("optionColor", s);

		n = spType.getSelectedItemPosition();
		s = GridSearchPrefs.getTypeString(n);
		data.putExtra("optionType", s);

		s = tvDomain.getText().toString();
		data.putExtra("optionDomain", s);

		// Activity finished ok, return the data
		setResult(RESULT_OK, data); // set result code and bundle data for response
		finish(); // closes the activity, pass data to parent
	}

}
