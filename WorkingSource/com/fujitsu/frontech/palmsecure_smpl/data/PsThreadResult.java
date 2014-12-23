/*
 * PsThreadResult.java
 *
 *	All Rights Reserved, Copyright(c) FUJITSU FRONTECH LIMITED 2013
 */

package com.fujitsu.frontech.palmsecure_smpl.data;

import java.util.ArrayList;
import java.util.TreeMap;

import com.fujitsu.frontech.palmsecure.*;
import com.fujitsu.frontech.palmsecure_smpl.segovia.Recipient;

public class PsThreadResult {

	public long result;
	public int retryCnt;
	public boolean authenticated;
	public ArrayList<String> userId;
	public ArrayList<Integer> farAchieved;
	public byte[] info;
	public JAVA_PvAPI_ErrorInfo errInfo;
	public Recipient recipient;

	public PsThreadResult() {
		result = 0;
		retryCnt = 0;
		authenticated = false;
		userId = new ArrayList<String>();
		farAchieved = new ArrayList<Integer>();
		errInfo = new JAVA_PvAPI_ErrorInfo();
		recipient = null;
	}
}
