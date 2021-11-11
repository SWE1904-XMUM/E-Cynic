package com.example.e_cynic.entityTests;

import com.example.e_cynic.entity.Address;
import com.example.e_cynic.utils.LoggingUtil;

import org.junit.Assert;
import org.junit.Test;

public class AddressTest {
    private String TAG = "AddressTest";

    @Test
    public void addressConstructorTest() {
        String firstLine = "Jalan Sunsuria";
        String secondLine = "Bandar Sunsuria";
        String thirdLine = "";
        String city = "Sepang";
        int postcode = 43900;
        String state = "Selangor";
        Address address = new Address(null, firstLine, secondLine, thirdLine, city, state, postcode);
        String expected = "Address{id=null, firstLine='Jalan Sunsuria', secondLine='Bandar Sunsuria', " +
                "thirdLine='', city='Sepang', state='Selangor', postcode=43900}";
        String result = address.toString();
        LoggingUtil.printMessage(TAG, result);
        Assert.assertEquals(expected, result);
    }
}
