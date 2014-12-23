package com.fujitsu.frontech.palmsecure_smpl.segovia;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.ArrayList;
import java.util.TreeMap;

import org.apache.commons.csv.CSVRecord;

/*
 * Simple recipient class; basically a wrapper class for a TreeMap with set fields.
 */

public class Recipient {
	public TreeMap<String, String> allFields = new TreeMap<String,String>();
	public static final Object[] FIELD_NAMES = {
    	"GDID",
		"phone_number",
		"recipient_name",
		"spouse_name",
		"trustee_name",
		"home_name",
		"village",
		"location",
		"sublocation",
		"recipient_id_last4digits",
    	"spouse_id_last4digits",
    	"trustee_id_last4digits",
    	"acct_class",
    	"processed_alerts",
    	"campaignName",
    	"firstName",
    	"lastName",
    	"age",
    	"gender",
    	"currentStatus",
    	"image",
    	"registrationDate",
    	"recipient_status",
    	"notes"
	};
	
	/*
	 *  Construct recipient from GDID only.
	 */
    public Recipient(String GDID) {
    	super();
    	for (int i=0; i < FIELD_NAMES.length; i++) {
    		this.allFields.put(FIELD_NAMES[i].toString(), "");
    	}
    	this.allFields.put("GDID", GDID);
    	this.allFields.put("registrationDate", this.nowAsString());
    }
    
    /*
     *  Construct recipient from GDID and name.
     */
    public Recipient(String GDID, String name) {
    	super();
    	for (int i=0; i < FIELD_NAMES.length; i++) {
    		this.allFields.put(FIELD_NAMES[i].toString(), "");
    	}
    	this.allFields.put("GDID", GDID);
    	this.allFields.put("registrationDate", this.nowAsString());
    	this.allFields.put("recipient_name", name);
    }

    /*
     *  Construct recipient from CSV record.
     */
    public Recipient(CSVRecord record) {
    	super();
    	for (int i=0; i < FIELD_NAMES.length; i++) {
    		String field = FIELD_NAMES[i].toString();
    		this.allFields.put(field, record.get(field));
    	}
    }
    
    private String nowAsString() {
    	TimeZone tz = TimeZone.getTimeZone("UTC");
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
    	df.setTimeZone(tz);
    	return df.format(new Date());
    }
    
    public String get(String fieldname) {
    	return this.allFields.get(fieldname);
    }
    
    public void update(String fieldname, String newValue) {
    	this.allFields.put(fieldname, newValue);
    }
    
    public Iterable<String> fields() {
    	ArrayList<String> result = new ArrayList<String>();
    	for (int i=0; i < FIELD_NAMES.length; i++) {
    		result.add(this.allFields.get(FIELD_NAMES[i].toString()));
    	}
    	return result;
    }
    
    /*
    public Iterable<String> fields() {
    	ArrayList<String> result = new ArrayList<String>();
    	result.add(GDID);
    	result.add(phone_number);
    	result.add(recipient_name);
    	result.add(spouse_name);
    	result.add(trustee_name);
    	result.add(home_name);
    	result.add(village);
    	result.add(location);
    	result.add(sublocation);
    	result.add(recipient_id_last4digits);
    	result.add(spouse_id_last4digits);
    	result.add(trustee_id_last4digits);
    	result.add(acct_class);
    	result.add(processed_alerts);
    	result.add(campaignName);
    	result.add(firstName);
    	result.add(lastName);
    	result.add(age);
    	result.add(gender);
    	result.add(currentStatus);
    	result.add(image);
    	result.add(registrationDate);
    	result.add(recipient_status);
    	result.add(notes);
    	return result;
    }
    */
}
