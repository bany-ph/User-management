package org.bany.model;

public class Client extends User {
    private String address;
    private String phone;
    public Client(String name, String email, String password, String address, String phone) {
        super(name, email, password);
        this.address = address;
        this.phone = phone;
    }
    public Client(String name, String email, String password, String address) {
        super(name, email, password);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getRole() {
        return "Role → Client";
    }

    @Override
    public String showProfile() {
        return String.format("""
                User name → %s
                Email → %s
                """, getName(),
                getEmail());
    }
}
