/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turingmachine.Operations;

import turingmachine.Response;

/**
 *
 * @author David
 */
public class MoveToState implements Operation{

    String value;
    String attributes;
    
    public MoveToState(String type, String attributes) {
        
        this.value = type;
        this.attributes = attributes;
        
//        System.out.println("Creada operacion: "+type+" atributos "+attributes);
    }

    @Override
    public Response perform(Response response) {
        
        response.setActualStateString(attributes);
//        System.out.println("     perform change state "+attributes);
        
        return response;        
    }

}
