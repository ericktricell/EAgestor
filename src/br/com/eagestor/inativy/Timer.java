/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.inativy;

public abstract class Timer implements Runnable {

	private int miliseconds;

	public Timer(int seconds) {
		this.miliseconds = seconds * 1000;
	}

	public void run() {

		while (true) {
			try {
				Thread.sleep(miliseconds);
			} catch (InterruptedException e) {}
			doIt();
		}

	}

	public abstract void doIt();    
}
