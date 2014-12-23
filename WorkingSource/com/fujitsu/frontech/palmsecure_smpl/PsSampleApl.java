/*
 * PsSampleApl.java
 *
 *	All Rights Reserved, Copyright(c) FUJITSU FRONTECH LIMITED 2013
 */

package com.fujitsu.frontech.palmsecure_smpl;

import javax.swing.UIManager;
import javax.swing.UIManager.*;


public class PsSampleApl {

	public static void main(String[] args) {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// just use default
		}

		PsMainFrame mainFrame = new PsMainFrame();

		boolean result = mainFrame.Ps_Sample_Apl_Java();
		if (result != true) {
			System.exit(0);
		}
		mainFrame.setVisible(true);
	}
}
