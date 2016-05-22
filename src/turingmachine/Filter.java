/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turingmachine;

import java.util.ArrayList;
import turingmachine.Operations.*;

/**
 *
 * @author David
 */
public class Filter {
    
    private String value;
    private ArrayList<Operation> operations = new ArrayList<>();

    Filter(String line) {
        value = line;
//        System.out.println("Anadido filtro: "+value);
    }

    void add(String opValue, Operation operation) {
        
        operations.add( operation);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ArrayList<Operation> getOperations() {
        return operations;
    }

    public void setOperations(ArrayList<Operation> operations) {
        this.operations = operations;
    }

}
