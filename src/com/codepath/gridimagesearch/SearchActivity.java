package com.codepath.gridimagesearch;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class SearchActivity extends Activity {
	EditText etQuery;
	GridView gvResults;
	Button btnSearch;
	ArrayList<ImageResult> imageResults = new ArrayList<ImageResult>();
	ImageResultArrayAdapter imageAdapter;

	// Options strings must match valid strings for Google API (see GridSearchPrefs).
	String stImageSizeOption = "";
	String stImageColorOption = "";
	String stImageTypeOption = "";
	String stImageDomainOption = "";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		setupViews();
		imageAdapter = new ImageResultArrayAdapter(this, imageResults);
		gvResults.setAdapter(imageAdapter);
		gvResults.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent i = new Intent(getApplicationContext(), ImageDisplayActivity.class);
				ImageResult imageResult = imageResults.get(position);
				i.putExtra("result", imageResult);
				startActivity(i);
			}

		});

		// Attach the listener to the AdapterView onCreate
		gvResults.setOnScrollListener(new EndlessScrollListener() {
			@Override
			public void onLoadMore(int page, int totalItemsCount) {
				// Triggered only when new data needs to be appended to the list
				// Add whatever code is needed to append new items to your AdapterView
				customLoadMoreDataFromApi(page); 
				// or customLoadMoreDataFromApi(totalItemsCount); 
			}
		});
	}


	public void setupViews() {
		etQuery = (EditText) findViewById(R.id.etQuery);
		gvResults = (GridView) findViewById(R.id.gvResults);
		btnSearch = (Button) findViewById(R.id.btnSearch);
	}


	// Append more data into the adapter
	public void customLoadMoreDataFromApi(int offset) {
		// This method probably sends out a network request and appends new data items to your adapter. 
		// Use the offset value and add it as a parameter to your API request to retrieve paginated data.
		// Deserialize API response and then construct new objects to append to the adapter
		int iNewStart = (offset - 1) * 8;

		String query = etQuery.getText().toString();

		// Build string we want to search for using a.concat(b)
		// https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=android
		String stGoogleImageSearch = "https://ajax.googleapis.com/ajax/services/search/images?rsz=8"
				+ "&start=" + iNewStart;

		if ((stImageSizeOption != null) && !(stImageSizeOption.equals("")))
			stGoogleImageSearch = stGoogleImageSearch.concat("&imgsz=" + stImageSizeOption);

		if ((stImageColorOption != null) && !(stImageColorOption.equals("")))
			stGoogleImageSearch = stGoogleImageSearch.concat("&imgcolor=" + stImageColorOption);

		if ((stImageTypeOption != null) && !(stImageTypeOption.equals("")))
			stGoogleImageSearch = stGoogleImageSearch.concat("&imgtype=" + stImageTypeOption);

		if ((stImageDomainOption != null) && !(stImageDomainOption.equals("")))
			stGoogleImageSearch = stGoogleImageSearch.concat("&as_sitesearch=" + stImageDomainOption);

		stGoogleImageSearch = stGoogleImageSearch.concat("&v=1.0&q=" + Uri.encode(query));

		Log.d("DEBUG", "scrolling stGoogleImageSearch = " + stGoogleImageSearch);

		AsyncHttpClient client = new AsyncHttpClient();
		client.get(stGoogleImageSearch,
				new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject response) {
				JSONArray imageJsonResults = null;
				try {
					imageJsonResults = response.getJSONObject("responseData").
							getJSONArray("results");
					imageAdapter.addAll(ImageResult.fromJSONArray(imageJsonResults));
					Log.d("DEBUG", imageResults.toString());

				} catch(JSONException e) {
					e.printStackTrace();
				}	
			}
		});
	}	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_display, menu);
		return true;
	}


	private final int REQUEST_CODE = 120;

	public void onSettingsAction(MenuItem mi) {
		Intent i = new Intent(this, SearchOptionsActivity.class);
		i.putExtra("optionSize", stImageSizeOption); 
		i.putExtra("optionColor", stImageColorOption); 
		i.putExtra("optionType", stImageTypeOption);
		i.putExtra("optionDomain", stImageDomainOption);
		startActivityForResult(i, REQUEST_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
			// Extract name value from result extras
			stImageSizeOption = data.getExtras().getString("optionSize");
			stImageColorOption = data.getExtras().getString("optionColor");
			stImageTypeOption = data.getExtras().getString("optionType");
			stImageDomainOption = data.getExtras().getString("optionDomain");

			imageResults.clear();
			// Load 2 pages to get enough on screen to enable scrolling.
			customLoadMoreDataFromApi(1);
			customLoadMoreDataFromApi(2);
		}
	} 


	public void onImageSearch(View v) {
		imageResults.clear();
		// Load 2 pages to get enough on screen to enable scrolling.
		customLoadMoreDataFromApi(1);
		customLoadMoreDataFromApi(2);
	}
}
