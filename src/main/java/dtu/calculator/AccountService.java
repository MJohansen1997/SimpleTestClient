package dtu.calculator;

import dtu.ws.fastmoney.*;

import java.math.BigDecimal;

public class AccountService {
    BankService bank = new BankServiceService().getBankServicePort();

    public String createAccount(String firstName, String lastName, String CPR, BigDecimal balance, boolean withAccountID) throws BankServiceException_Exception {
        ObjectFactory objectFactory = new ObjectFactory();
        User user = objectFactory.createUser();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setCprNumber(CPR);
        return bank.createAccountWithBalance(user,balance);
    }

    public void retireAccount(String id) throws BankServiceException_Exception {
        bank.retireAccount(id);
    }
}
