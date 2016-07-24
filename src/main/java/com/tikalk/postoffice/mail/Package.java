package com.tikalk.postoffice.mail;


import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Package implements Mail {
    private String address;
    private String country;
    private int zip;
    private double stampPrice;
    private long sentTime;
    private double weight;
    private double size;
}
