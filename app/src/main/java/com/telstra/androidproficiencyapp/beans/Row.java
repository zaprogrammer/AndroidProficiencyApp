package com.telstra.androidproficiencyapp.beans;

import com.google.gson.annotations.Expose;

public class Row {

@Expose
private String title;
@Expose
private String description;
@Expose
private String imageHref;

/**
* 
* @return
* The title
*/
public String getTitle() {
return title;
}

/**
* 
* @param title
* The title
*/
public void setTitle(String title) {
this.title = title;
}

/**
* 
* @return
* The description
*/
public String getDescription() {
return description;
}

/**
* 
* @param description
* The description
*/
public void setDescription(String description) {
this.description = description;
}

/**
* 
* @return
* The imageHref
*/
public String getImageHref() {
    return imageHref;
}

/**
* 
* @param imageHref
* The imageHref
*/
public void setImageHref(String imageHref) {
    this.imageHref = imageHref;
}

}