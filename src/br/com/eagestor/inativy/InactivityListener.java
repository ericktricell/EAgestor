/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.inativy;

import java.awt.Component;
import java.awt.Container;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.Stack;

public class InactivityListener implements WindowStateListener, MouseListener, KeyListener {

	private static InactivityListener instance;
	private long lastAction = 0;

	public int getInactivitySeconds() {
		return (int) (System.currentTimeMillis() - lastAction) / 1000;
	}

	private void setLastAction() {
		lastAction = System.currentTimeMillis();
	}

	/**
	 * add the listeners to the frame and to its childs
	 * 
	 * @param f
	 */
	public static void addListener(Window main) {

		main.addWindowStateListener(InactivityListener.getInstance());

		Stack stack = new Stack();
		stack.push(main);

		while (!stack.empty()) {

			Container container = (Container) stack.pop();
			container.addKeyListener(InactivityListener.getInstance());
			container.addMouseListener(InactivityListener.getInstance());

			Component[] components = container.getComponents();

			for (int i = 0; i < components.length; i++) {
				if (components[i] instanceof Container) {
					stack.push(components[i]);
				} else {
					// non containers 
					components[i].addKeyListener(InactivityListener.getInstance());
					components[i].addMouseListener(InactivityListener.getInstance());
				}
			}
		}

	}

	public static InactivityListener getInstance() {
		if (instance == null) {
			instance = new InactivityListener();
		}
		return instance;
	}

	private InactivityListener() {
		setLastAction();
	}

	public void windowStateChanged(WindowEvent e) {
		setLastAction();
	}

	public void mousePressed(MouseEvent e) {
		setLastAction();
	}

	public void keyPressed(KeyEvent e) {
		setLastAction();
	}

	public void mouseClicked(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}
