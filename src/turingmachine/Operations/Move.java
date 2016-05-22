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
public class Move implements Operation{

    String value;
    String attributes;
    
    
    public Move(String type, String attributes) {
    
        this.value = type;
        this.attributes = attributes;;
    }

    @Override
    public Response perform(Response response) {
    
        int posicionActual = response.getActualPosition();
        
        if(attributes.equals("left")){
            posicionActual-=1;
//            System.out.println("     perform move left "+posicionActual);
        }
        else if ( attributes.equals("right")){
            posicionActual+=1;
//            System.out.println("     perform move right "+posicionActual);
        }
        
        response.setActualPosition(posicionActual);
        
        return response;
    }

    
}
