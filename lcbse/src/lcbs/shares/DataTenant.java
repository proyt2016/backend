package lcbs.shares;

public class DataTenant {
	private String name;
	private String domain;
	private String id;
	private Boolean isActive = true;
	private Boolean isDelete = false;
	private DataEmail email;
	public DataTenant(){}
	public DataTenant(String id, String name, String domain, Boolean isActive, DataEmail email) {
		super();
		this.id = id;
		this.name = name;
		this.domain = domain;
		this.isActive = isActive;
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	public DataEmail getEmail() {
		return email;
	}
	public void setEmail(DataEmail email) {
		this.email = email;
	}
	
}