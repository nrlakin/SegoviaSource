package com.fujitsu.frontech.palmsecure_smpl.segovia;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class SegoviaLogoLabel extends JLabel {


	private static final long serialVersionUID = 1L;

	private BufferedImage displayedImage = null;
	private BufferedImage logoImage = null;

	public SegoviaLogoLabel() {
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(displayedImage, 0, 0, null);
	}

	public void SetLogo(BufferedImage logo) {

		if (logoImage == null) {

			int displayW = getWidth();
			int displayH = getHeight();

			BufferedImage resize = new BufferedImage(displayW, displayH, logo.getType());
			Graphics g = resize.getGraphics();
			g.drawImage(
					logo.getScaledInstance(displayW, displayH, Image.SCALE_SMOOTH),
					0, 0, displayW, displayH, null);
			g.dispose();

			logoImage = resize;
		}

		displayedImage = logoImage;

	}
}

