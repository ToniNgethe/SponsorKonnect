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
public class FeesModel {
    private int id;
    private String name;
    private double first;
    private double second;
    private double third;
    private double total;
    private double paid;
    
    public FeesModel(int id, String name, double first, double second, double third, double total, double paid) {
        this.id = id;
        this.name = name;
        this.first = first;
        this.second = second;
        this.third = third;
        this.total = total;
        this.paid = paid;
    }

    public FeesModel() {
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFirst() {
        return first;
    }

    public void setFirst(double first) {
        this.first = first;
    }

    public double getSecond() {
        return second;
    }

    public void setSecond(double second) {
        this.second = second;
    }

    public double getThird() {
        return third;
    }

    public void setThird(double third) {
        this.third = third;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
    
}
