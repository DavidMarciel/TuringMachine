/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turingmachine;

import java.util.HashMap;

/**
 *
 * @author David
 */
public class State {
    private String name;
    private HashMap<String, Filter> filters = new HashMap<>();

    State(String line) {
        name = line;
//        System.out.println("Estado creado: "+name);
    }
    
    void addFilter(String line, Filter filter) {
        
        filters.put(line, filter);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Filter> getFilters() {
        return filters;
    }

    public void setFilters(HashMap<String, Filter> filters) {
        this.filters = filters;
    }
    
    public Filter getFilter(String s) {
        return filters.get(s);
    }
}
