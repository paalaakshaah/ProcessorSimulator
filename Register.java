
public class Register {
	public String regname;
	public int regvalue;
	
	public Register(){
		regname = null;
		regvalue = 0;
	}
	
	public void createRegister(String a, int b){
		this.regname = a;
		this.regvalue = b;
	}
	
	public String printRegister() {
		String s = new String();
		if(this.regname != null){
			s = "<"+this.regname+","+this.regvalue+">";
		}
		return s;
	}

	public void setName(String a) {
		this.regname = a;
	}
	
	public String getName() {
		return this.regname;
	}
	
	public void setValue(int a) {
		this.regvalue = a;
	}
	
	public int getValue() {
		return this.regvalue;
	}
}
