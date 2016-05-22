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
public interface Operation {

    public Response perform(Response response);
    
}
