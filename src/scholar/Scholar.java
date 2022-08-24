package scholar;

//import application.Scholar;
//import application.ScholarController;
//import application.ScholarView;
public class Scholar{
	
	private String scholarId;
	private String name;
	private String contact;
	private String email;
	private String username;
	private String password;
	
	Scholar(){
	}
	
	Scholar(String scholarId, String name, String contact, String email, String username, String password) {
		this.scholarId = scholarId;
		this.name = name;
		this.contact = contact;
		this.email = email;
		this.username = username;
		this.password = password;
	}
	
	public void setScholarId(String scholarId) {
		this.scholarId = scholarId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getScholarId() {
		return scholarId;
	}

	public String getName() {
		return name;
	}

	public String getContact() {
		return contact;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}

 


