public class Guest {
	public String kennitala;
	public String name;
	public int reservationID;

	public Guest(String kennitala, String name, int reservationID) {
        this.kennitala = kennitala;
        this.name = name;
        this.reservationID = reservationID;
	}

	public Guest() {

	}
	
	public String getKennitala(){
		return kennitala;
	}

	public void setId(String kennitala){
		this.kennitala = kennitala;
	//	StdOut.println(kennitala);
	}

	public void setName(String name) {
		this.name = name;
	//	StdOut.println(name);
	}
	
	public String getName() {
        return name;
    }
	
	public static void main(String[] args)  {
	}

}