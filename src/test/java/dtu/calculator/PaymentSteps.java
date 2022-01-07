//package dtu.calculator;
//
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//
//
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//public class PaymentSteps {
//    String cid, mid;
//    SimpleDTUPay dtuPay = new SimpleDTUPay();
//    List<Payment> payments;
//    int amount;
//    boolean successful = false;
//    Exception exception;
//
//    /* Scenario: Successful Payment */
//    @Given("a customer with id {string}")
//    public void aCustomerWithId(String cid) {
//        this.cid = cid;
//    }
//
//    @And("a merchant with id {string}")
//    public void aMerchantWithId(String mid) {
//        this.mid = mid;
//    }
//
//    @When("the merchant initiates a payment for {int} kr by the customer")
//    public void theMerchantInitiatesAPaymentForKrByTheCustomer(int amount) {
//        successful = dtuPay.pay(amount, cid, mid);
//    }
//
//    @Then("the payment is successful")
//    public void thePaymentIsSuccessful() {
//        assertTrue(successful);
//    }
//
//    /* Scenario: List of payments */
//    @Given("a successful payment of {string} kr from customer {string} to merchant {string}")
//    public void aSuccessfulPaymentOfKrFromCustomerToMerchant(String amount, String send, String rec) {
//        successful = dtuPay.pay(Integer.parseInt(amount), send, rec);
//    }
//
//    @When("the manager asks for a list of payments")
//    public void theManagerAsksForAListOfPayments() {
//        payments = dtuPay.getPayments();
//        System.out.println(payments);
//    }
//
//    @Then("the list contains a payments where customer {string} paid {string} kr to merchant {string}")
//    public void theListContainsAPaymentsWhereCustomerPaidKrToMerchant(String send, String amount, String rec) {
//        assertTrue(dtuPay.findPayment(Integer.parseInt(amount), send, rec));
//    }
//
//
//    /* Scenario: Customer is not known */
//    @When("the merchant initiates a payment for {string} kr by the customer")
//    public void theMerchantInitiatesAPaymentForKrByTheCustomer(String arg0) {
//        try {
//            successful = dtuPay.pay(amount, cid, mid);
//        }catch (IllegalArgumentException exception) {
//            this.exception = exception;
//        }
//    }
//
//    @Then("the payment is not successful")
//    public void thePaymentIsNotSuccessful() {
//        assertFalse(successful);
//    }
//
//    @And("an error message is returned saying {string}")
//    public void anErrorMessageIsReturnedSaying(String arg0) {
//        assertEquals(arg0, exception.getMessage());
//    }
//}
