import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Write a description of class MIPSim here.
 * 
 * @author Palak Shah 
 * @version 1.0
 */
public class MIPSim extends Instruction
{
	public static String[] instSet;	
	public static String[] regSet;
	public static File readI;
	public static File readR;
	public static File writeS; 
		
	/**
     * Constructor for objects of class MIPSim
     */
    public MIPSim()
    {
      
        
    }

    public static void main(String[] args) {
    	String Opcode = new String();
    	Scanner commandLine = new Scanner(System.in);
        System.out.println("Enter the commandline: (./MIPSim -I <instruction sile> -R <register file> -O <output file>) "); //Prompt User to enter commandline
        String[] cmdLine = new String[7];
        for(int i = 0; i < 7; i++) {		//Read command line and save it into an array
        	cmdLine[i] = commandLine.next();
	    }
        commandLine.close();
        
        if(cmdLine[0].equals("./MIPSim")){
        	for(int i = 0; i < 7; i++) {		//Identify the Instruction,Register and Simulation files
    	       	if(cmdLine[i].equalsIgnoreCase("-I")){
    	       		readI = new File(cmdLine[i+1]);
    	       	}
    	       	
    	       	if(cmdLine[i].equalsIgnoreCase("-R")){
    	       		readR = new File(cmdLine[i+1]);
    	       	}
    	       	
    	       	if(cmdLine[i].equalsIgnoreCase("-O")){
    	       		writeS = new File(cmdLine[i+1]);
    	       	}
        	}
        }
        else{
        	System.out.println("Command could not be recognised. Run the program again");
        }
        
      //Code to read instruction and store in IM_in
        readInstruction(readI);
//Initializing the Instructions
        ArrayList<Instruction> IM_in = new ArrayList<Instruction>();
        ArrayList<Instruction> IM_out = new ArrayList<Instruction>();
    	Instruction IB_in = new Instruction();
    	Instruction IB_out = new Instruction();
    	Instruction SB_in = new Instruction();
    	Instruction SB_out = new Instruction();
    	Instruction MB_in = new Instruction();
    	Instruction MB_out = new Instruction();
    	Instruction PB_in = new Instruction();
    	Instruction PB_out = new Instruction();
    	Instruction Exe_in = new Instruction();
    	Instruction Exe_out = new Instruction();
//IM_in is filled
        for(int i = 0; i<instSet.length; i++){
   			int n = instSet[i].length();
   			String temp = instSet[i].substring(1,n-1);
   			String[] instBreakdown = temp.split(",");
   			IM_in.add(new Instruction(instBreakdown[0],instBreakdown[1],instBreakdown[2],instBreakdown[3]));   			
   		}

//Code to read Register and store in RF_in
  	readRegister(readR);
//Initializing the Registers
  	ArrayList<Register> RB_in = new ArrayList<Register>();
   	ArrayList<Register> RB_out = new ArrayList<Register>();
   	ArrayList<Register> RF_in = new ArrayList<Register>();
   	ArrayList<Register> RF_out = new ArrayList<Register>(); 
   		
//RF is filled
   	for(int i = 0; i<regSet.length; i++){
   		int n = regSet[i].length(); 			
   		String temp = regSet[i].substring(1,n-1);
   		String[] regBreakdown = temp.split(",");
   		RF_in.add(new Register(regBreakdown[0],Integer.parseInt(regBreakdown[1])));
   	}
//MAIN FOREVER LOOP
   		for(int i=0; i<5; i++) {
   			for(int j = IM_in.size()-1; j>=i; j--){
   				IM_out.add(new Instruction(IM_in[j].getOp(),IM_in[j].getDest().getName,IM_in[j].getSource1().getName,IM_in[j].getSource2().getName);
   			}
   			for(int m=0;m<i;m++){
   				IM_out[m] = null;
   			}
   			
   			for(int k = 0; k<7; k++) {
   				RF_out[k] = RF_in[k];
   				System.out.println("RF_out: " + RF_out[k].getName());
   			}
   			
   			
   			IB_in[i] = FETCH(IM_out[i], RF_out, i);
   			//Decode();
   			//Execute();
   		}
   		
   		//writeSimulation(writeS);
    }
    
    public static Instruction FETCH(Instruction IM1, Register[] RF1, int i) {
    		boolean s1 = false;
    		boolean s2 = false;
    		for(int j=0;j<7;j++){
    			if(IM1.getS1Name() == RF1[j].getName()){
    				s1 = true;
    			}
    			if(IM1.getS2Name() == RF1[j].getName()){
    				s2 = true;
    			}
    		}
    		if(s1 == true && s2 == true) {
    			return IM1;
    		}
    		else return null;
    }
    /**
     * FILE READING
     */
    public static void readInstruction(File instfile) {
    	//File instfile = new File(inst);
    	ArrayList<String> tempInst = new ArrayList<String>();
    	try{
        	Scanner instruction = new Scanner(instfile);
            while (instruction.hasNextLine()) {
                tempInst.add(instruction.nextLine());
            }
            instSet = tempInst.toArray(new String[0]);
            for(int i=0;i<instSet.length;i++) {
            	System.out.println(instSet[i]);
            	//IM_in[i] = new Instruction();
            }
            instruction.close();


          }catch (Exception e){
              System.err.println("Error: First error is here " + e.getMessage());
          }
    }
    
    public static void readRegister(File regfile) {
    	//File regfile = new File(reg);
    	ArrayList<String> tempReg = new ArrayList<String>();
    	try{
        	Scanner register = new Scanner(regfile);
            while (register.hasNextLine()) {
                tempReg.add(register.nextLine());
            }
            regSet = tempReg.toArray(new String[0]);
            /*for(int i=0;i<regSet.length;i++) {
            	System.out.println(regSet[i]);
            }*/
            register.close();


          }catch (Exception e){
              System.err.println("Error: " + e.getMessage());
          }
    }
    
    public void writeSimulation(File simfile){
    	
    }
    
}



