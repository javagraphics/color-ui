/*
 * @(#)ColorSwatch.java
 *
 * $Date: 2014-12-14 06:25:07 +0100 (So, 14 Dez 2014) $
 *
 * Copyright (c) 2011 by Jeremy Wood.
 * All rights reserved.
 *
 * The copyright of this software is owned by Jeremy Wood. 
 * You may not use, copy or modify this software, except in  
 * accordance with the license agreement you entered into with  
 * Jeremy Wood. For details see accompanying license terms.
 * 
 * This software is probably, but not necessarily, discussed here:
 * https://javagraphics.java.net/
 * 
 * That site should also contain the most recent official version
 * of this software.  (See the SVN repository for more details.)
 */
package com.bric.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.Toolkit;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;

import com.bric.blog.ResourceSample;
import com.bric.plaf.PlafPaintUtils;

/** This is a square, opaque panel used to indicate
 * a certain color.
 * <P>The color is assigned with the <code>setForeground()</code> method.
 * <P>Also the user can right-click this panel and select 'Copy' to send
 * a 100x100 image of this color to the clipboard.  (This feature was
 * added at the request of a friend who paints; she wanted to select a
 * color and then quickly print it off, and then mix her paints to match
 * that shade.)
 * 
 * 
 * <!-- ======== START OF AUTOGENERATED SAMPLES ======== -->
 * <p><img src="https://javagraphics.java.net/resources/samples/ColorSwatch/sample.png" alt="new&#160;com.bric.swing.ColorSwatch(&#160;java.awt.Color.blue,&#160;50&#160;)">
 * <!-- ======== END OF AUTOGENERATED SAMPLES ======== -->
 */
@ResourceSample( sample = "new com.bric.swing.ColorSwatch( java.awt.Color.blue, 50 )")
public class ColorSwatch extends JPanel {
	private static final long serialVersionUID = 1L;
	
	int w;
	
	/** If this client property maps to true, then a "Copy" menu item will be available in a contextual menu.
	 * The default value for this key is assumed to be false if undefined.
	 */
	public static final String PROPERTY_COPY_CONTEXTUAL_MENU_ITEM = ColorSwatch.class+".copyContextualMenuItem";
	
	public ColorSwatch(int width) {
		w = width;
		setPreferredSize(new Dimension(width,width));
		setMinimumSize(new Dimension(width,width));
		PropertyChangeListener pcl = new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				updateContextualMenu();
			}
		};
		
		addPropertyChangeListener( PROPERTY_COPY_CONTEXTUAL_MENU_ITEM, pcl);
		updateContextualMenu();
	}
	
	protected void updateContextualMenu() {
		ContextualMenuHelper.clear(this);
		if( Boolean.TRUE.equals( getClientProperty(PROPERTY_COPY_CONTEXTUAL_MENU_ITEM) ) ) {
			String menuItemName = ColorPicker.strings.getObject("Copy").toString();
			Runnable runnable = new Runnable() {
				public void run() {
					BufferedImage image = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
					Graphics2D g = image.createGraphics();
					g.setColor(getBackground());
					g.fillRect(0, 0, image.getWidth(), image.getHeight());
					g.dispose();
					Transferable contents = new ImageTransferable(image);
					Toolkit.getDefaultToolkit().getSystemClipboard().setContents(contents, null);
				}
			};
			ContextualMenuHelper.add(this, menuItemName, runnable);
		}
	}

	public ColorSwatch(Color color,int width) {
		this(width);
		setForeground(color);
	}
	
	private static TexturePaint checkerPaint = null;
	private static TexturePaint getCheckerPaint() {
		if(checkerPaint==null) {
			int t = 8;
			BufferedImage bi = new BufferedImage(t*2,t*2,BufferedImage.TYPE_INT_RGB);
			Graphics g = bi.createGraphics();
			g.setColor(Color.white);
			g.fillRect(0,0,2*t,2*t);
			g.setColor(Color.lightGray);
			g.fillRect(0,0,t,t);
			g.fillRect(t,t,t,t);
			checkerPaint = new TexturePaint(bi,new Rectangle(0,0,bi.getWidth(),bi.getHeight()));
		}
		return checkerPaint;
	}
	
	@Override
	public void paint(Graphics g0) {
		super.paint(g0); //may be necessary for some look-and-feels?
		
		Graphics2D g = (Graphics2D)g0;
		
		Color c = getForeground();
		int w2 = Math.min(getWidth(), w);
		int h2 = Math.min(getHeight(), w);
		Rectangle r = new Rectangle(getWidth()/2-w2/2,getHeight()/2-h2/2, w2, h2);
		
		if(c.getAlpha()<255) {
			TexturePaint checkers = getCheckerPaint();
			g.setPaint(checkers);
			g.fillRect(r.x, r.y, r.width, r.height);
		}
		g.setColor(c);
		g.fillRect(r.x, r.y, r.width, r.height);
		PlafPaintUtils.drawBevel(g, r);
	}
}

