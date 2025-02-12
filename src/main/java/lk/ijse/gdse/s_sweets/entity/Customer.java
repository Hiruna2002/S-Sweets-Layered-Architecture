package lk.ijse.gdse.s_sweets.entity;

import java.io.Serializable;


public class Customer implements Serializable {
    private String cusId;
    private String name;
    private String address;
    private String phoneNo;

    public Customer() {
    }

    public Customer(String cusId, String name, String address,String phoneNo) {
        this.cusId = cusId;
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
    }

    public String getId() {
        return cusId;
    }

    public void setId(String id) {
        this.cusId = cusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo(){
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo){
        this.phoneNo=phoneNo;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "id='" + cusId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phoneNo + '\'' +
                '}';
    }
}
