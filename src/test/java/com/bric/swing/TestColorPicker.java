// Copyright 2016 Sebastian Kuerten
//
// This file is part of the javagraphics port.

package com.bric.swing;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Sebastian Kuerten (sebastian@topobyte.de)
 */
public class TestColorPicker {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Color Picker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.setContentPane(panel);

		ColorPicker picker = new ColorPicker();
		panel.add(picker);

		picker.setColor(Color.RED);

		frame.pack();
		frame.setVisible(true);
	}

}
