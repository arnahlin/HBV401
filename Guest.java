import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
/**
 * Hotel booking Guest.
 * 
 */
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
			Guest gestur = new Guest();
			StdOut.print("Enter full name and ID number ");
			String nafn = "";
			String IDnumber = "";

				nafn = StdIn.readLine();
				gestur.setName(nafn);
				IDnumber = StdIn.readLine();
				gestur.setId(IDnumber);

			System.out.println("Nafn: " +gestur.name);
			System.out.println("Kennitala: " +gestur.kennitala);
		}



}