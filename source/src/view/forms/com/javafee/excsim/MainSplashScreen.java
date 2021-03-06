package com.javafee.excsim;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

class MainSplashScreen extends JWindow {
	private static final long serialVersionUID = 1L;

	private static MainSplashScreen mainSplashScreen = null;

	private MainSplashScreen(String filename, final Frame f, int waitTime) {
		super(f);
		JLabel pictureLabel = new JLabel(new ImageIcon(filename));
		getContentPane().add(pictureLabel, BorderLayout.CENTER);
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension labelSize = pictureLabel.getPreferredSize();
		setLocation(screenSize.width / 2 - (labelSize.width / 2), screenSize.height / 2 - (labelSize.height / 2));

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setVisible(false);
				dispose();
			}
		});

		final int pause = waitTime;
		final Runnable closerRunner = new Runnable() {
			@Override
			public void run() {
				setVisible(false);
				f.setVisible(true);
				dispose();
			}
		};

		Runnable waitRunner = new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(pause);
					SwingUtilities.invokeAndWait(closerRunner);
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		setVisible(true);
		Thread splashThread = new Thread(waitRunner, "SplashThread");
		splashThread.start();
	}

	public static MainSplashScreen getInstance(String filename, final Frame f, int waitTime) {
		if (mainSplashScreen == null)
			mainSplashScreen = new MainSplashScreen(filename, f, waitTime);

		return mainSplashScreen;
	}

	public static Boolean isNull() {
		return mainSplashScreen != null ? false : true;
	}
}