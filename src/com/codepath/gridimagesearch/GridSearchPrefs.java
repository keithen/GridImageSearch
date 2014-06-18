package com.codepath.gridimagesearch;

import java.io.Serializable;

public class GridSearchPrefs implements Serializable {

	private static final long serialVersionUID = -333363989371722652L;
	
	/*
	 * GridSearchPrefs is a class to pass previous search criteria to the 
	 * option selection view.
	 * To allow for our spinners to be internationalized, we will use the
	 * index of the spinner as the data we choose. But we need particular 
	 * strings to pass back to the Google image search API's, so we will
	 * provide conversion routines from index to legal text per JSON
	 * documentation.
	 * Info from <https://developers.google.com/image-search/v1/jsondevguide>
	 */
	
	String stChosenSize = "";
	String stChosenColor = "";
	String stChosenType = "";
	String stChosenDomain = "";
	
	public GridSearchPrefs(String stInitSize, String stInitColor, String stInitType, String stInitDomain) {
		stChosenSize = stInitSize;
		stChosenColor = stInitColor;
		stChosenType = stInitType;
		stChosenDomain = stInitDomain;
	}
	

	/*
	 * CONVERSION ROUTINES
	 * 
	 * Strings must match spelling and case expected by JSON dev guide.
	 * Must return an integer that matches the order of string arrays in res/strings.xml.
	 */
	
	/*
	 * JSON DOCUMENTATION FOR SIZE OPTIONS:
	 * Restricts the search to images of the specified size, where size can be one of:
	 * 		imgsz=icon restricts results to small images
	 * 		imgsz=small|medium|large|xlarge restricts results to medium-sized images
	 * 		imgsz=xxlarge restricts results to large images
	 * 		imgsz=huge restricts resykts to extra-large images
	 * 
	 * ASSIGNMENT INSTRUCTIONS
	 * User can configure advanced search filters such as:
	 *     Size (small, medium, large, extra-large)
	 * 
	 * STRING.XML
	 * 	<string-array name="grid_option_size_array">
	 * 		<item>"Any"</item>
	 * 		<item>"Small"</item>
	 * 		<item>"Medium"</item>
	 * 		<item>"Large"</item>
	 * 		<item>"Extra Large"</item>
	 * 	</string-array>
	 */
	
	static final String stOptionSmall = "small";
	static final String stOptionMedium = "medium";
	static final String stOptionLarge = "large";
	static final String stOptionXLarge = "xlarge";

	static public int getSizeIndex(String sizeStr) {
		int n = 0;
		
		if (sizeStr.equals(stOptionSmall))
			n = 1;
		else if (sizeStr.equals(stOptionMedium))
			n = 2;
		else if (sizeStr.equals(stOptionLarge))
			n = 3;
		else if (sizeStr.equals(stOptionXLarge))
			n = 4;
		
		return n;
	}

	static public String getSizeString(int i) {
		String sizeStr = "";

		switch (i) {
			case 1:  
				sizeStr = stOptionSmall;
				break;
			case 2:  
				sizeStr = stOptionMedium;
				break;
			case 3:  
				sizeStr = stOptionLarge;
				break;
			case 4:  
				sizeStr = stOptionXLarge;
				break;
		} 
		return sizeStr;
	}


	/*
	 * JSON DOCUMENTATION FOR COLOR OPTIONS:
	 * Restricts the search to images of the specified color, where color can be one of:
	 * 		imgcolor=black
	 * 		imgcolor=blue
	 * 		imgcolor=brown
	 * 		imgcolor=gray
	 * 		imgcolor=green
	 * 		imgcolor=orange
	 * 		imgcolor=pink
	 * 		imgcolor=purple
	 * 		imgcolor=red
	 * 		imgcolor=teal
	 * 		imgcolor=white
	 * 		imgcolor=yellow
	 * 
	 * ASSIGNMENT INSTRUCTIONS
	 * User can configure advanced search filters such as:
	 *     Color filter (black, blue, brown, gray, green, etc...)
	 * 
	 * STRING.XML
	 * 	<string-array name="grid_option_color_array">
	 * 		<item>"Any"</item>
	 * 		<item>"Black"</item>
	 * 		<item>"Blue"</item>
	 * 		<item>"Brown"</item>
	 * 		<item>"Gray"</item>
	 * 		<item>"Green"</item>
	 * 		<item>"Orange"</item>
	 * 		<item>"Pink"</item>
	 * 		<item>"Purple"</item>
	 * 		<item>"Red"</item>
	 * 		<item>"Teal"</item>
	 * 		<item>"White"</item>
	 * 		<item>"Yellow"</item>
	 * 	</string-array>
	 */

	static final String stOptionBlack = "black";
	static final String stOptionBlue = "blue";
	static final String stOptionBrown = "brown";
	static final String stOptionGray = "gray";
	static final String stOptionGreen = "green";
	static final String stOptionOrange = "orange";
	static final String stOptionPink = "pink";
	static final String stOptionPurple = "purple";
	static final String stOptionRed = "red";
	static final String stOptionTeal = "teal";
	static final String stOptionWhite = "white";
	static final String stOptionYellow = "yellow";

	static public int getColorIndex(String colorStr) {
		int n = 0;
		
		if (colorStr.equals(stOptionBlack))
			n = 1;
		else if (colorStr.equals(stOptionBlue))
			n = 2;
		else if (colorStr.equals(stOptionBrown))
			n = 3;
		else if (colorStr.equals(stOptionGray))
			n = 4;
		else if (colorStr.equals(stOptionGreen))
			n = 5;
		else if (colorStr.equals(stOptionOrange))
			n = 6;
		else if (colorStr.equals(stOptionPink))
			n = 7;
		else if (colorStr.equals(stOptionPurple))
			n = 8;
		else if (colorStr.equals(stOptionRed))
			n = 9;
		else if (colorStr.equals(stOptionTeal))
			n = 10;
		else if (colorStr.equals(stOptionWhite))
			n = 11;
		else if (colorStr.equals(stOptionYellow))
			n = 12;
		
		return n;
	}

	static public String getColorString(int i) {
		String colorStr = "";

		switch (i) {
			case 1:  
				colorStr = stOptionBlack;
				break;
			case 2:  
				colorStr = stOptionBlue;
				break;
			case 3:  
				colorStr = stOptionBrown;
				break;
			case 4:  
				colorStr = stOptionGray;
				break;
			case 5:  
				colorStr = stOptionGreen;
				break;
			case 6:  
				colorStr = stOptionOrange;
				break;
			case 7:  
				colorStr = stOptionPink;
				break;
			case 8:  
				colorStr = stOptionPurple;
				break;
			case 9:  
				colorStr = stOptionRed;
				break;
			case 10:  
				colorStr = stOptionTeal;
				break;
			case 11:  
				colorStr = stOptionWhite;
				break;
			case 12:  
				colorStr = stOptionYellow;
				break;
		} 
		return colorStr;
	}


	/*
	 * JSON DOCUMENTATION FOR IMAGE TYPE OPTIONS:
	 * Restricts the search to one of the following image types:
	 * 		imgtype=face restricts results to images of faces.
	 * 		imgtype=photo restricts results to photographic images.
	 * 		imgtype=clipart restricts results to clipart images.
	 * 		imgtype=lineart restricts results to line drawing images.
	 * 
	 * ASSIGNMENT INSTRUCTIONS
	 * User can configure advanced search filters such as:
	 *     Type (faces, photo, clip art, line art)
	 * 
	 * STRING.XML
	 * 	<string-array name="grid_option_type_array">
	 * 		<item>"Any"</item>
	 * 		<item>"Faces"</item>
	 * 		<item>"Photo"</item>
	 * 		<item>"Clip Art"</item>
	 * 		<item>"Line Art"</item>
	 * 	</string-array>
	 */

	static final String stOptionFace = "face";
	static final String stOptionPhoto = "photo";
	static final String stOptionClip = "clipart";
	static final String stOptionLine = "lineart";

	static public int getTypeIndex(String typeStr) {
		int n = 0;
		
		if (typeStr.equals(stOptionFace))
			n = 1;
		else if (typeStr.equals(stOptionPhoto))
			n = 2;
		else if (typeStr.equals(stOptionClip))
			n = 3;
		else if (typeStr.equals(stOptionLine))
			n = 4;
		
		return n;
	}

	static public String getTypeString(int i) {
		String typeStr = "";

		switch (i) {
			case 1:  
				typeStr = stOptionFace;
				break;
			case 2:  
				typeStr = stOptionPhoto;
				break;
			case 3:  
				typeStr = stOptionClip;
				break;
			case 4:  
				typeStr = stOptionLine;
				break;
		} 
		return typeStr;
	}

}
