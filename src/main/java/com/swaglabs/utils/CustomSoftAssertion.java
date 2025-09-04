package com.swaglabs.utils;

import org.testng.asserts.SoftAssert;

public class CustomSoftAssertion extends SoftAssert {
    // This class can be extended to add custom soft assertion methods if needed

    public static CustomSoftAssertion softAssertion = new CustomSoftAssertion();
    public static void CustomAssertAll() {
        try { 
            softAssertion.assertAll("Custom Soft Assertion");
        }
        catch (Exception e) {
            System.out.println("Soft assertion failures");
        }
    }

}
