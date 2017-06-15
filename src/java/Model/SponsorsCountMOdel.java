/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author toni
 */
public class SponsorsCountMOdel {
    
    private int registerd;
    private int applied;

    public SponsorsCountMOdel() {
    }

    public SponsorsCountMOdel(int registerd, int applied) {
        this.registerd = registerd;
        this.applied = applied;
    }
    

    public int getRegisterd() {
        return registerd;
    }

    public void setRegisterd(int registerd) {
        this.registerd = registerd;
    }

    public int getApplied() {
        return applied;
    }

    public void setApplied(int applied) {
        this.applied = applied;
    }
    
    
}
