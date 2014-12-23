package com.fujitsu.frontech.palmsecure_smpl.segovia;
import java.io.File;
import java.io.FilenameFilter;

public class SegoviaCSVFilter implements FilenameFilter {
	private static final String EXTENSION = ".csv";
	private static String campaign;

	public SegoviaCSVFilter(String campaign) {
		SegoviaCSVFilter.campaign = campaign;
	}

	public boolean accept(File dir, String name) {

		boolean result = false;

		String checkFormat = campaign;

		File file = new File(dir, name);
		if (file.isFile()) {

			String fileName = file.getName();
			if (fileName.endsWith(EXTENSION)) {
				if (fileName.startsWith(checkFormat)) {
					if (fileName.length() <=
							(checkFormat.length() + EXTENSION.length())) {
						result = true;
					}
				}
			}
		}

		return result;
	}

	public static String GetCampaign(File file) {

		String id = file.getName();								// CCCCCCCC.csv
		id = id.substring(0, id.indexOf(EXTENSION));			// CCCCCCCC
		return id;
	}

	public static String GetFileName(File dir, String campaign) {

		StringBuffer buff = new StringBuffer(dir.getAbsolutePath());
		buff = buff.append(File.separator);
		buff = buff.append(campaign);
		buff = buff.append(EXTENSION);

		return buff.toString();
	}
}


