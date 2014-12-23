/*
 *	PsVeinDataFilenameFilter.java
 *
 *	All Rights Reserved, Copyright(c) FUJITSU FRONTECH LIMITED 2013
 */

package com.fujitsu.frontech.palmsecure_smpl.segovia;

import java.io.File;
import java.io.FilenameFilter;

public class SegoviaVeinDataFilenameFilter implements FilenameFilter {

	private static final String VEIN_DATA_FILE_EXT = ".dat";
	private static final int GDID_LENGTH = 12;
	private static String sensorType;
	private static String guideMode;
	private static String campaign;

	public SegoviaVeinDataFilenameFilter(long sensorType, long guideMode, String campaign) {
		SegoviaVeinDataFilenameFilter.sensorType = Long.toString(sensorType);
		SegoviaVeinDataFilenameFilter.guideMode = Long.toString(guideMode);
		SegoviaVeinDataFilenameFilter.campaign = campaign;
	}

	public boolean accept(File dir, String name) {

		boolean result = false;

		String checkFormat = sensorType + guideMode + "_" + campaign;

		File file = new File(dir, name);
		if (file.isFile()) {

			String fileName = file.getName();
			if (fileName.endsWith(VEIN_DATA_FILE_EXT)) {
				if (fileName.startsWith(checkFormat)) {
					if (fileName.length() <=
							(checkFormat.length() + GDID_LENGTH + VEIN_DATA_FILE_EXT.length())) {
						result = true;
					}
				}
			}
		}

		return result;
	}

	public static String GetGDID(File file) {

		String gdid = file.getName();								// XY_ZZZZZZZZZZZZZZZZ.dat
		gdid = gdid.substring(0, gdid.indexOf(VEIN_DATA_FILE_EXT));	// XY_ZZZZZZZZZZZZZZZZ
		gdid = gdid.substring(3);									// ZZZZZZZZZZZZZZZZ

		return gdid;
	}

	public static String GetFileName(File dir, String gdid) {

		String header = sensorType + guideMode + "_";

		StringBuffer buff = new StringBuffer(dir.getAbsolutePath());
		buff = buff.append(File.separator);
		buff = buff.append(header);
		buff = buff.append(gdid);
		buff = buff.append(VEIN_DATA_FILE_EXT);

		return buff.toString();
	}
}
