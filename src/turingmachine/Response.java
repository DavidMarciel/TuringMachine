/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turingmachine;

import java.util.ArrayList;
import java.util.HashMap;
import turingmachine.Operations.*;

/**
 *
 * @author david
 */
public class Response {
    
    private static HashMap<String, State> states;
    
    private String actualStateString = "start";
    private String actualTape;
    private int actualPosition = 0;
    
    Response(String line, HashMap<String, State> states) {        
        this.states = states;        
        this.actualTape = line;
    }
    
    public int getActualPosition() {
        return actualPosition;
    }

    public void setActualPosition(int actualPosition) {
        this.actualPosition = actualPosition;
//        System.out.println("    actualposition "+actualPosition);
    }

    public String getActualTape() {
        return actualTape;
    }

    public void setActualTape(String actualTape) {
        this.actualTape = actualTape;
    }

    public void setActualStateString(String actualStateString) {
        this.actualStateString = actualStateString;
    }

    public ArrayList<Operation> getOperations() {
    
        if(actualPosition>=actualTape.length()){
            actualTape += " ";
        }
        
        String stringFiltro = actualTape.charAt(actualPosition)+"";
        
        State s = states.get(actualStateString);
        
        Filter f = s.getFilter(stringFiltro);
        
        ArrayList<Operation> o = f.getOperations();
        
        return o;
    }

    @Override
    public String toString() {
        return actualPosition+" "+actualTape+" "+actualStateString;
    }

    boolean hasFinished() {
        return actualStateString.equals("end");
    }
    
    
    
}
