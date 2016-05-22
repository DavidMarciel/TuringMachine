/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turingmachine.Operations;

import turingmachine.Response;

/**
 *
 * @author 530132
 */
public class Write implements Operation{

    String attributes;
    String value;
    
        
    public Write(String type, String attributes) {
        
        this.value = type;
        this.attributes = attributes.charAt(1)+"";
        
//        System.out.println("Creado movimiento tipo: "+type+" atributos "+attributes);
    }

    @Override
    public Response perform(Response response) {
        
        int i = response.getActualPosition();
        
        response.setActualTape(
              response.getActualTape().substring(0,i)
            + attributes 
            + response.getActualTape().substring(i+1)
        );
        
//        System.out.println(
//              "     perform write "+attributes+"  "
//            + response.getActualTape().substring(0,i)
//            + attributes 
//            + response.getActualTape().substring(i+1));
        
        return response;
    }

}
