
public class Instruction {
	//Register[] regArray = new Register[7];
	public String opcode;
	public Register dest;
	public Register source1;
	public Register source2;
	
	public Instruction(){
		opcode = null;
		dest = new Register();
		source1 = new Register();
		source2 = new Register();
	}
	
	public void createInstruction(String op, String d, String s1, String s2) {
		this.opcode = op;
		this.dest.setName(d);
		this.source1.setName(s1);
		this.source2.setName(s2);
		/*for(int i = 0; i < 7; i++) {
			regArray[i].setValue(0);
		}*/
	}
	
	public String printInstruction() {
		String s = new String();
		s = "<"+this.opcode+","+this.dest.getName()+","+this.source1.getName()+","+this.source2.getName()+">";
		return s;
	}
	
	public String getOp() {
		return this.opcode;
	}
	
	public String getDestName() {
		return this.dest.getName();
	}
	
	public void setDestValue(int a) {
		this.dest.setValue(a);
	}
	
	public int getDestValue() {
		return this.dest.getValue();
	}
	
	public String getS1Name() {
		return this.source1.getName();
	}
	
	public int getS1Value() {
		return this.source1.getValue();
	}
	
	public String getS2Name() {
		return this.source2.getName();
	}
	
	public int getS2Value() {
		return this.source2.getValue();
	}
	
	public int add() {
		int c = this.source1.getValue() + this.source2.getValue();
		return c;
	}
	
	public int subtract() {
		int d = this.source1.getValue() - this.source2.getValue();
		return d;
	}
		
	public int multiply() {
		int e = this.source1.getValue() * this.source2.getValue();
		return e;
	}
	
	public int divide() {
		int f;	
		if(this.source2.getValue()!=0){
			f = this.source1.getValue() / this.source2.getValue();
		}
		else throw new ArithmeticException("denominator == 0!");
		return f;
	}
}
