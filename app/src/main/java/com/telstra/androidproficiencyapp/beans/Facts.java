package com.telstra.androidproficiencyapp.beans;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Facts {

@Expose
private String title;
@Expose
private List<Row> rows = new ArrayList<Row>();

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
* The rows
*/
public List<Row> getRows() {
return rows;
}

/**
* 
* @param rows
* The rows
*/
public void setRows(List<Row> rows) {
this.rows = rows;
}

}