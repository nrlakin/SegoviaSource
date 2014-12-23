/*
 * PsDataManager.java
 *
 *	All Rights Reserved, Copyright(c) FUJITSU FRONTECH LIMITED 2013
 */

package com.fujitsu.frontech.palmsecure_smpl.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

import com.fujitsu.frontech.palmsecure_smpl.exception.PsAplException;
import com.fujitsu.frontech.palmsecure_smpl.segovia.CSVManager;
import com.fujitsu.frontech.palmsecure_smpl.segovia.Recipient;
import com.fujitsu.frontech.palmsecure_smpl.segovia.SegoviaVeinDataFilenameFilter;
import com.fujitsu.frontech.palmsecure_smpl.xml.PsFileAccessorLang;

public class PsDataManager {

	private static PsDataManager dataManager = new PsDataManager();
	// Maps gdid to palm vein dat files.
	private TreeMap<String, String> userMap = new TreeMap<String, String>();
	private static final String DIRECTORY_NAME_TO_STORE_DATA = "Data";
	//private TreeMap<String, String[]> userDataMap = new TreeMap<String, String[]>();
	private CSVManager segoviaData;

	private PsDataManager() {
	}

	public static PsDataManager GetInstance() {

		return dataManager;
	}

	// Neil: added 'campaign' as parameter to init.
	public void Ps_Sample_Apl_Java_Init(long sensorType, long guideMode, String campaign) throws PsAplException {

		File dataDir = new File(DIRECTORY_NAME_TO_STORE_DATA);
		if (!dataDir.exists()) {
			if (!dataDir.mkdir()) {
				PsAplException pae = new PsAplException(PsFileAccessorLang.ErrorMessage_AplErrorFileDirOpen);
				throw pae;
			}
			else
			{
				dataDir.listFiles(new SegoviaVeinDataFilenameFilter(sensorType, guideMode, campaign));
			}
		} else {
			File[] list = dataDir.listFiles(new SegoviaVeinDataFilenameFilter(sensorType, guideMode, campaign));

			if (list != null) {
				for (int i = 0; i < list.length; i++) {
					String gdid = SegoviaVeinDataFilenameFilter.GetGDID(list[i]);
					userMap.put(gdid, list[i].getAbsolutePath());
				}
			}
		}
		segoviaData = new CSVManager(dataDir, campaign);
		
	}

	//public void Ps_Sample_Apl_Java_Insert(String userID, String firstName, String village, String family_size, String roofing, byte[] data) throws PsAplException {
	public void insertNewRecipient(Recipient recipient, byte[] data) throws PsAplException {
		File dataDir = new File(DIRECTORY_NAME_TO_STORE_DATA);
		String[] data_array;		// Neil added this
		if (!dataDir.exists()) {
			dataDir.mkdir();
		}
		// Neil: Get gdid from recipient, will use as filename.
		String gdid = recipient.get("GDID");
		
		// Write vein data file.
		String fileName = SegoviaVeinDataFilenameFilter.GetFileName(dataDir, gdid);

		File file = new File(fileName);
		FileOutputStream outStream;
		try {
			outStream = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			PsAplException pae = new PsAplException(PsFileAccessorLang.ErrorMessage_AplErrorFileOpen);
			throw pae;
		}

		FileChannel outChannel = outStream.getChannel();
		ByteBuffer byteBuff = ByteBuffer.wrap(data);
		try {
			outChannel.write(byteBuff);
		} catch (IOException e) {
			PsAplException pae = new PsAplException(PsFileAccessorLang.ErrorMessage_AplErrorFileWrite);
			throw pae;
		} finally {
			try {
				outChannel.close();
			} catch(Exception e) {
			}
		}

		// Add user file to filename map, add recipient to CSV.
		userMap.put(gdid, fileName);
		segoviaData.writeRecord(recipient);
		
	}

	//public ArrayList<String> Ps_Sample_Apl_Java_GetAllUserId() {
	public ArrayList<String> getAllRecipients() {

		ArrayList<String> gdidList = new ArrayList<String>();

		Iterator<String> itr = userMap.keySet().iterator();
        while (itr.hasNext()) {
         	String mapKey = (String) itr.next();
			gdidList.add(mapKey);
        }

		return gdidList;
	}

	public boolean Ps_Sample_Apl_Java_IsRegist(String userId) {

		String data = userMap.get(userId);
		if (data == null) {
			return false;
		}
		else {
			return true;
		}
	}

	public int Ps_Sample_Apl_Java_GetRegistNum() {

		return userMap.size();
	}

	// Neil: Get data associated with recipient GDID 
	public Recipient GetRecipientMetadata(String gdid) throws PsAplException {
		return segoviaData.getRecord("GDID", gdid);
	}

	public void Ps_Sample_Apl_Java_Delete(String userId) throws PsAplException {
		// DANGER: Will not delete from CSV!
		String filename = userMap.get(userId);
		if (filename == null) {
			PsAplException pae = new PsAplException(PsFileAccessorLang.ErrorMessage_AplErrorFileDelete);
			throw pae;
		}

		File file = new File(filename);
		file.delete();
		userMap.remove(userId);
	}

	public byte[] Ps_Sample_Apl_Java_GetTemplate(String userId) throws PsAplException {

		String filename = userMap.get(userId);
		if (filename == null) {
			PsAplException pae = new PsAplException(PsFileAccessorLang.ErrorMessage_AplErrorDataFileNotFound);
			throw pae;
		}

		FileInputStream inStream;
		try {
			inStream = new FileInputStream(filename);
		} catch (FileNotFoundException e) {
			PsAplException pae = new PsAplException(PsFileAccessorLang.ErrorMessage_AplErrorDataFileNotFound);
			throw pae;
		}

		FileChannel inChannel = inStream.getChannel();
		ByteBuffer byteBuff = null;
		try {
			Long fileSize = new Long(inChannel.size());
			byteBuff = ByteBuffer.allocate(fileSize.intValue());
			inChannel.read(byteBuff);
		} catch (IOException e) {
			PsAplException pae = new PsAplException(PsFileAccessorLang.ErrorMessage_AplErrorFileOpen);
			throw pae;
		} finally {
			try {
				inChannel.close();
				inStream.close();
			} catch(Exception e) {
			}
		}

		return byteBuff.array().clone();
	}
	
	public String nextGDID() {
		return segoviaData.nextGDID();
	}
	
	public Recipient getRecipient(String gdid) {
		return segoviaData.getRecord("GDID", gdid);
	}
}
