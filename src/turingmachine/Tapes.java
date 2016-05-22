
package turingmachine;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import turingmachine.Operations.*;

/**
 *
 * @author David
 */
public class Tapes {

    enum LineType { Directive, State, Filter, OP }

    private static HashMap<String, State> states = new HashMap<>();
    
    private static State lastStateCreated;
    private static Filter lastFilterCreated;

    private static String treatTape(String line) {
        
        String tape = line;
        Response response = new Response(line, states);
        
        mainLoop:
        while(true){
            
//            System.out.println(response);

            ArrayList<Operation> operations = response.getOperations();
            
            for (int i = 0; i < operations.size(); i++) {
                
                response = operations.get(i).perform(response);
//                System.out.println("    "+response);
                
                if(response.hasFinished()) break mainLoop;
            }
            
        }
        
        return response.getActualTape();
    }

    public static void main(String[] args) {

        ArrayList<String> stringResponses = new ArrayList<>();
        Scanner sc = getData("submitInput.txt");

        sc.nextLine();
        sc.nextLine();

        //extracion de la maquina de turing
        readingLoop:
        while (sc.hasNextLine()) {
            //cada linea
            String line = sc.nextLine();

            LineType type = getType(line);
 
            switch (type) {            
                case Directive:
                    //solo va a pasar cuando acabe la code y empiece la tape                    
                    break readingLoop;
                case State:
                    createState(line);
                    break;
                case Filter:
                    createFilter(line);
                    break;
                case OP:                
                    createOperation(line);
                    break;                    
                default:                    
//                    createOperation(line);
                    System.out.println("voynich.manuscript.Tapes.main()");
                    break;
            }
        }

        //empiezan los "tapes"
        int i = 0;
        while (sc.hasNextLine()) {//descomentar
            //cada linea
            String line = sc.nextLine();
//            System.out.println("line "+line);
            
            int startString = line.indexOf("'");
            int endString = line.indexOf("'", startString+1);
//            System.out.println("indices= "+(startString+1)+" "+endString);
            
            if(line.equals("...")){
                continue;
            }
            line = line.substring(startString+1, endString);
//            System.out.println("cadena = "+line);
        
            System.out.println("Tape #"+(i+1)+": "+treatTape(line));
            stringResponses.add("Tape #"+(i+1)+": "+treatTape(line));
            i++;
        }
        
        toDisk(stringResponses);
    }

    private static void createOperation(String line) {
        
        //anadir la operacion al ultimo filtro        
        line = line.replace(" ", "");
        String[] pieces = line.split(":");
        Operation operation = createOperationType(pieces[0], pieces[1]);
        lastFilterCreated.add(pieces[0], operation);
    }

    private static void createFilter(String line) {
        //anadir el filtro al ultimo estado
        //asignar filtro a ultimo filtro crado
        line = line.charAt(5)+"";
        Filter filter = new Filter(line);
        lastStateCreated.addFilter(line, filter);
        lastFilterCreated = filter;
    }

    private static void createState(String line) {
        //crear nuevo estado
        //asignar nuevo estado a ultimo estado creado
        line = line.replace(" ", "").replace(":", "");
        State state =  new State(line);
        states.put(line, state);
        lastStateCreated = state;
    }

    private static Operation createOperationType(String type, String attributes) {
        
        switch (type) {
            case "move":
                return new Move(type, attributes);
            case "state":
                return new MoveToState(type, attributes);
            case "write":
                return new Write(type, attributes);
            default:
                System.out.println("voynich.manuscript.Tapes.createOperationType()");
                return null;
        }
    }
    
    private static LineType getType(String line) {

        int nSpaces = 0;

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ' ') {
                nSpaces++;
            }
            else break;
        }

        switch (nSpaces) {

            case 0:
                return LineType.Directive;
            case 2:
                return LineType.State;
            case 4:
                return LineType.Filter;
            case 6:
                return LineType.OP;
            default:
                System.out.println("voynich.manuscript.Tapes.getType() "+nSpaces+" "+line);
                return null;
        }
    }
    
    private static Scanner getData(String textInputtxt) {

        Scanner sc = null;

        try {
            sc = new Scanner(new FileReader(textInputtxt));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tapes.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sc;
    }

    private static void toDisk(ArrayList<String> stringResponses) {
    
        try {
            FileWriter fw = new FileWriter("Respuestas.txt");
            
            for (int i = 0; i < stringResponses.size(); i++) {
                fw.write(stringResponses.get(i)+"\n");
            }
            
            fw.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Tapes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
