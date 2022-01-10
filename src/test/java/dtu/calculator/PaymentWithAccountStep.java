package dtu.calculator;
import dtu.ws.fastmoney.*;
import io.cucumber.java.After;
import io.cucumber.java.en.*;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.util.List;

//import static io.smallrye.common.constraint.Assert.assertTrue;

public class PaymentWithAccountStep {
    SimpleDTUPay dtuPay = new SimpleDTUPay();
    List<Payment> payments;
    int amount;
    boolean successful = false;
    Exception exception;
    CreateAccountMapper customer;
    CreateAccountMapper merchant;
    AccountService accountService = new AccountService();
    String customerId;
    String merchantId;
    TransactionMapper tm;


    @Given("a customer with a bank account with balance {double}")
    public void aCustomerWithABankAccountWithBalance(double arg0) throws BankServiceException_Exception {
        customerId = accountService.createAccount("customer", "lastName", "123", BigDecimal.valueOf(arg0), true);
    }

    @And("that the customer is registered with DTU Pay")
    public void thatTheCustomerIsRegisteredWithDTUPay() {
        assertNotNull(dtuPay.getAccount(customerId));
    }

    @Given("a merchant with a bank account with balance {double}")
    public void aMerchantWithABankAccountWithBalance(double arg0) throws BankServiceException_Exception {
        merchantId = accountService.createAccount("merchant", "lastName", "321", BigDecimal.valueOf(arg0), true);
    }

    @And("that the merchant is registered with DTU Pay")
        public void thatTheMerchantIsRegisteredWithDTUPay() {
        assertNotNull(dtuPay.getAccount(merchantId) != null);
    }

    @When("the merchant initiates a payment for {double} kr by the customer")
    public void theMerchantInitiatesPaymentForBalanceByTheCustomer(double arg0) {
        tm = new TransactionMapper(customerId, merchantId, "something", BigDecimal.valueOf(arg0));
    }

    @And("the balance of the customer at the bank is {double} kr")
    public void theBalanceOfTheCustomerAtTheBankIsKr(double arg0) {
        Account account = dtuPay.getAccount(customerId);
        assertEquals(account.getBalance(), BigDecimal.valueOf(arg0));
    }

    @And("the balance of the merchant at the bank is {double} kr")
    public void theBalanceOfTheMerchantAtTheBankIsKr(double arg0) {
        Account account = dtuPay.getAccount(merchantId);
        assertEquals(account.getBalance(), BigDecimal.valueOf(arg0));
    }

    @After
    public void cleanUpAccounts() throws BankServiceException_Exception {
        accountService.retireAccount(customerId);
        accountService.retireAccount(merchantId);
    }

    @Then("the payment is successful")
    public void thePaymentIsSuccessful() {
        assertTrue(dtuPay.transaction(tm));
    }
}
