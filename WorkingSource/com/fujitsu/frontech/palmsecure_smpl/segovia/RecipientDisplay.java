package com.fujitsu.frontech.palmsecure_smpl.segovia;

import java.awt.Component;

import javax.swing.JOptionPane;

import com.fujitsu.frontech.palmsecure_smpl.xml.PsFileAccessorLang;

public class RecipientDisplay extends JOptionPane {

	private static final long serialVersionUID = 1L;
	private static final String  NEW_LINE_CODE = System.getProperty("line.separator");

	public static void showRecipientDialog(Component parent, Recipient recipient) {
	
		String format =" Recipient: %s" + NEW_LINE_CODE;
		format +=	   "      GDID: %s" + NEW_LINE_CODE;
		format +=	   "       Age: %s" + NEW_LINE_CODE;
		format +=	   "  Location: %s" + NEW_LINE_CODE;
		format +=	   "   Village: %s" + NEW_LINE_CODE;
		format +=	   "Registered: %s" + NEW_LINE_CODE;
		
		String message = String.format(format,
				recipient.get("recipient_name"),
				recipient.get("GDID"),
				recipient.get("age"),
				recipient.get("location"),
				recipient.get("village"),
				recipient.get("registrationDate"));
		
		showMessageDialog(parent, message, "", INFORMATION_MESSAGE);
		
	}
}
