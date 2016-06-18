// Copyright 2016 Sebastian Kuerten
//
// This file is part of the javagraphics port.

package com.bric.swing;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Sebastian Kuerten (sebastian@topobyte.de)
 */
public class TestColorPickerPanel {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Color Picker Panel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.setContentPane(panel);

		ColorPickerPanel picker = new ColorPickerPanel();
		panel.add(picker);

		picker.setRGB(255, 0, 0);

		frame.pack();
		frame.setVisible(true);
	}

}
