package com.fujitsu.frontech.palmsecure_smpl.segovia;

import org.apache.commons.csv.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

public class CSVManager {

	private final String campaign;
	private final String infile;
	private final String outfile;
	private int count;
	
	public CSVManager(File dir, String campaign) {
		this.campaign = campaign;
		
		StringBuffer buff = new StringBuffer(dir.getAbsolutePath());
		buff = buff.append(File.separator);
		buff = buff.append(campaign);
		buff = buff.append(".csv");
		
		this.infile = buff.toString();
		this.outfile = buff.toString();
		
		File records = new File(this.outfile);
		FileWriter out = null;
		CSVPrinter printer = null;
		
		if (!records.exists()) {
			try {
				out = new FileWriter(records, true);
				printer = new CSVPrinter(out, CSVFormat.DEFAULT);
				printer.printRecord(Recipient.FIELD_NAMES);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					out.flush();
					out.close();
					printer.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		// Get current count
		this.count = 0;
		try {
			Reader in = new FileReader(this.infile);
			Iterable<CSVRecord> recipients = CSVFormat.DEFAULT.withHeader().parse(in);
			for (CSVRecord person : recipients) {
				this.count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateRecord(Recipient recipient) {
		/*
		 * todo: add update method
		 */
	}
	
	public Recipient getRecord(String fieldName, String fieldValue) {
		try {
			Reader in = new FileReader(infile);
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(in);
			for (CSVRecord record : records) {
				if (record.get(fieldName).equals(fieldValue)) {
					return new Recipient(record);
				}
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void writeRecord(Recipient recipient) {
		File outfile = null;
		FileWriter out = null;
		CSVPrinter printer = null;
		
		try {
			outfile = new File(this.outfile);
			if (!outfile.exists()) {
				out = new FileWriter(outfile, true);
				printer = new CSVPrinter(out, CSVFormat.DEFAULT);
				printer.printRecord(Recipient.FIELD_NAMES);
			} else {
				out = new FileWriter(outfile, true);
				printer = new CSVPrinter(out, CSVFormat.DEFAULT);
			}
			//CSVPrinter printer = CSVFormat.DEFAULT.withHeader(this.csvFields).print(out);
			printer.printRecord(recipient.fields());
			this.count++;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.flush();
				out.close();
				printer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public int count() {
		return this.count;
	}
	
	public String nextGDID() {
		return this.campaign + String.format("%04d",this.count+1);
	}
	
	public static void main(String args[]) {
		File dataDir = new File("Data");
		CSVManager test = new CSVManager(dataDir,"CT201412");
		try {
			Recipient recipient = test.getRecord("GDID", "KE2014036608");
			System.out.println(recipient.get("GDID"));
		} catch (NullPointerException e) {
			System.out.println("Recipient not found.");
			Recipient user = new Recipient("KE2014036608", "Neil");
			test.writeRecord(user);
			System.out.println("New record created.");
		}
		System.out.println("Num recipients: " + Integer.toString(test.count()));
		System.out.println("Next available GDID: " + test.nextGDID());
	}
	
}
