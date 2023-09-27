/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author kollo
 */
public class VitalHistory {
    private ArrayList<Vitals> history;
    public VitalHistory(){
        this.history = new ArrayList<Vitals>();
    }

    public ArrayList<Vitals> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<Vitals> history) {
        this.history = history;
    }
    public Vitals addNewVital(){
        Vitals newVitals = new Vitals();
        history.add(newVitals);
        return newVitals;
    }
    public void deleteVital(Vitals vs){
//        Vitals delVitals = new Vitals();
        history.remove(vs);
    }
}
