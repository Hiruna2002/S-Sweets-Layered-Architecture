package lk.ijse.gdse.s_sweets.dto.tm;

import lombok.*;

@Getter
@Setter
@ToString

public class CustomerTM implements Comparable<CustomerTM>{
    private String cusId;
    private String name;
    private String address;
    private String phoneNo;

    public CustomerTM() {
    }

    public CustomerTM(String id, String name, String address, String phoneNo) {
        this.cusId = id;
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
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString() {
        return "CustomerTM{" +
                "id='" + cusId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phoneNo + '\'' +
                '}';
    }


    @Override
    public int compareTo(CustomerTM o) {
        return cusId.compareTo(o.getId());
    }
}
