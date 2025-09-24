package org.bany.model;

public class Client extends User {
    private String address = "";
    private String phone = "";

    public Client(String name, String email, String password) {
        super(name, email, password);
    }

    public String getAddress() {
        return !address.isEmpty() ? address : "NOT PROVIDED";
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return !phone.isEmpty() ? phone : "NOT PROVIDED " ;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getRole() {
        return "CLIENT";
    }

    @Override
    public String showProfile() {
        return String.format("""
                
                USER NAME → %s
                EMAIL → %s
                STATUS → %s
                ROLE → %s
                PHONE → %s
                ADDRESS → %s
                """, getName(), getEmail(),
                getStatus(),getRole(),
                getPhone(),getAddress());
    }
}
