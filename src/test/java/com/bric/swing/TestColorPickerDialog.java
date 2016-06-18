// Copyright 2016 Sebastian Kuerten
//
// This file is part of the javagraphics port.

package com.bric.swing;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/**
 * @author Sebastian Kuerten (sebastian@topobyte.de)
 */
public class TestColorPickerDialog {

	public static void main(String[] args) {
		ColorPickerDialog dialog = new ColorPickerDialog((JFrame) null,
				Color.RED, true);
		dialog.setTitle("Select a color");
		dialog.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});
		dialog.setVisible(true);
	}

}
