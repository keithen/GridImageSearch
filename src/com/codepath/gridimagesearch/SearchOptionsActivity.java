package com.codepath.gridimagesearch;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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
//		ArrayAdapter sizeAdap = (ArrayAdapter) spSize.getAdapter();
//		int spSizePosition = sizeAdap.getPosition(stOptionSize);
//		spSize.setSelection(spSizePosition);
		spSize.setSelection(i);

		stOptionColor = getIntent().getStringExtra("optionColor");
		i = GridSearchPrefs.getColorIndex(stOptionColor);
		spColor = (Spinner) findViewById(R.id.spColorFilter);
//		ArrayAdapter colorAdap = (ArrayAdapter) spColor.getAdapter();
//		int spColorPosition = colorAdap.getPosition(stOptionColor);
//		spColor.setSelection(spColorPosition);
		spColor.setSelection(i);

		stOptionType = getIntent().getStringExtra("optionType");
		i = GridSearchPrefs.getTypeIndex(stOptionType);
		spType = (Spinner) findViewById(R.id.spImageType);
//		ArrayAdapter typeAdap = (ArrayAdapter) spType.getAdapter();
//		int sptypePosition = typeAdap.getPosition(stOptionType);
//		spSize.setSelection(sptypePosition);
		spSize.setSelection(i);

		stOptionDomain = getIntent().getStringExtra("optionDomain");
		tvDomain = (TextView) findViewById(R.id.etSiteFilter);
		tvDomain.setText(stOptionDomain);
	}

	
	public void onSave(View v) {
		  // closes the activity and returns to first screen
		  this.finish(); 
		}

}
