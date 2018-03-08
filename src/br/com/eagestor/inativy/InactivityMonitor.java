/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.inativy;

import br.com.eagestor.domain.Usuario;
import br.com.eagestor.view.Login;

/**
 *
 * @author Erick
 */
public class InactivityMonitor extends Timer {
    private Usuario user;
    private boolean ret = true;
	public InactivityMonitor() {
		super(2);
	}

	public void doIt() {
		if (InactivityListener.getInstance().getInactivitySeconds() > 40) {
                    this.setRet(true);
                    Login log = new Login(new javax.swing.JFrame(), true);
                    log.setVisible(true);
                    ret = true;
		}
	}

    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
}
