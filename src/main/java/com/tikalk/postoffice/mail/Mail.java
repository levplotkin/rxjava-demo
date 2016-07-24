package com.tikalk.postoffice.mail;

import lombok.Builder;
import lombok.Data;

import java.util.Date;


 public interface Mail {
  String getAddress();
  String getCountry();
  int getZip();

}
