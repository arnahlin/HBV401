
/**
 * Hotel booking Guest.
 * 
 */
public class Guest {
	private int kennitala;
	private String name;
	private int reservationID;

	public Guest(final int kennitala, final String name, int reservationID) {
        this.kennitala = kennitala;
        this.name = name;
        this.reservationID = reservationID;
	}
	
	public int getKennitala(){
		return kennitala;
	}

	public void setId(int kennitala){
		this.kennitala = kennitala;
	}

	public void setName(String name) {
        this.name = name;
	}
	
	public void getName() {
        return name;
    }
	




}
