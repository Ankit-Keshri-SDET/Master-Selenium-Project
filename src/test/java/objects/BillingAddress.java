package objects;

public class BillingAddress {
    private String billingFirstName;
    private String billingLastName;
    private String billingAddress;
    private String billingCity;
    private String billingZipCode;
    private String billingEmail;

    public BillingAddress() {
    }

    public BillingAddress(String billingFirstName, String billingLastName,
                          String billingAddress, String billingCity,
                          String billingZipCode, String billingEmail) {
        this.billingFirstName = billingFirstName;
        this.billingLastName = billingLastName;
        this.billingAddress = billingAddress;
        this.billingCity = billingCity;
        this.billingZipCode = billingZipCode;
        this.billingEmail = billingEmail;
    }

    public String getBillingFirstName() {
        return billingFirstName;
    }

    public BillingAddress setBillingFirstName(String billingFirstName) {
        this.billingFirstName = billingFirstName;
        return this;
    }

    public String getBillingLastName() {
        return billingLastName;
    }

    public BillingAddress setBillingLastName(String billingLastName) {
        this.billingLastName = billingLastName;
        return this;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public BillingAddress setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
        return this;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public BillingAddress setBillingCity(String billingCity) {
        this.billingCity = billingCity;
        return this;
    }

    public String getBillingZipCode() {
        return billingZipCode;
    }

    public BillingAddress setBillingZipCode(String billingZipCode) {
        this.billingZipCode = billingZipCode;
        return this;

    }

    public String getBillingEmail() {
        return billingEmail;
    }

    public BillingAddress setBillingEmail(String billingEmail) {
        this.billingEmail = billingEmail;
        return this;
    }
}
