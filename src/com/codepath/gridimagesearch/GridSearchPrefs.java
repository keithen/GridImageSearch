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
	 * provide conversion routines from index to legal text (ie American
	 * spelling).
	 */
	
	String stChosenSize = "";
	String stChosenColor = "";
	String stChosenType = "";
	
	public GridSearchPrefs(String stInitSize, String stInitColor, String stInitType) {
		stChosenSize = stInitSize;
		stChosenColor = stInitColor;
		stChosenType = stInitType;
	}
	
	// Info from <https://developers.google.com/image-search/v1/jsondevguide>

//    Size (small, medium, large, extra-large)
//    Color filter (black, blue, brown, gray, green, etc...)
//    Type (faces, photo, clip art, line art)
//    Site (espn.com)

	/*
	 *  imgsz
	

imgsz=icon
	

Restricts the search to images of the specified size, where size can be one of:

    imgsz=icon restricts results to small images
    imgsz=small|medium|large|xlarge restricts results to medium-sized images
    imgsz=xxlarge restricts results to large images
    imgsz=huge restricts resykts to extra-large images

	 */
	public enum Size {
    	SMALL, MEDIUM, LARGE, EXTRALARGE
    }
	
	/*
    imgcolor=black
    imgcolor=blue
    imgcolor=brown
    imgcolor=gray
    imgcolor=green
    imgcolor=orange
    imgcolor=pink
    imgcolor=purple
    imgcolor=red
    imgcolor=teal
    imgcolor=white
    imgcolor=yellow
	 */
	
	public enum Color {
    	RED, ORANGE, YELLOW, GREEN, BLUE, PURPLE, BROWN, BLACK, GRAY, WHITE
    }
	
	/*
	 *  imgtype (experimental)
	

imgtype=face
	

Restricts the search to one of the following image types:

    imgtype=face restricts results to images of faces.
    imgtype=photo restricts results to photographic images.
    imgtype=clipart restricts results to clipart images.
    imgtype=lineart restricts results to line drawing images.

	 */
	
	public enum Type {
    	FACES, PHOTO, CLIPART, LINEART
    }

	
}
