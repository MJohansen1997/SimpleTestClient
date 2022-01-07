package dtu.calculator;

import dtu.ws.fastmoney.*;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleDTUPay {

//    URL url = new URL("http://fm-17.compute.dtu.dk");
//    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    Client client = ClientBuilder.newClient();

    public boolean transaction(TransactionMapper transactionMapper) {
        WebTarget target = client.target("http://localhost:8080/payment/transaction");
        String result = target.request(MediaType.APPLICATION_JSON)
                .accept(MediaType.TEXT_PLAIN_TYPE)
                .post(Entity.json(transactionMapper), String.class);
        return result.contains("Successful");
    }

    public String createAccount(CreateAccountMapper createAccountMapper) {
        WebTarget target = client.target("http://localhost:8080/payment/account");
        String result = target.request(MediaType.APPLICATION_JSON)
               .accept(MediaType.TEXT_PLAIN_TYPE)
               .post(Entity.json(createAccountMapper), String.class);
        if(result.contains("Successful"))
            return result.split(":")[1].trim();
        throw new IllegalArgumentException(result);
    }

    public boolean retireAccount(String id) {
        WebTarget target = client.target("http://localhost:8080/payment/account/" + id);
        Response resp = target.request()
                            .accept(MediaType.TEXT_PLAIN_TYPE)
                            .delete();
        return resp.getStatus() == 200;
    }

    public Account getAccount(String id) {
        WebTarget target = client.target("http://localhost:8080/payment/account/" + id);
        Account result = target.request(MediaType.APPLICATION_JSON)
                .accept(MediaType.TEXT_PLAIN_TYPE)
                .get(new GenericType<>() {});
        return result;
    }

    public boolean pay(int amount, String send, String rec) {
        Payment newCustomer = new Payment(send, rec, amount);
        System.out.println(newCustomer.getAmount() + newCustomer.getSend() + newCustomer.getRec());
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/payment/confirmPayment");
        String result = target.request(MediaType.APPLICATION_JSON)
                .accept(MediaType.TEXT_PLAIN_TYPE)
                .post(Entity.json(newCustomer), String.class);
        if (result.equals("true") || result.equals("false"))
            return Boolean.parseBoolean(result);
        throw new IllegalArgumentException(result);
    }

    public List<Payment> getPayments() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/payment/getPaymentList");
        return target.request()
                .accept(MediaType.APPLICATION_JSON)
                .get(new GenericType<>() {
                });
    }

    public boolean findPayment(int amount, String send, String rec) {
        Payment newCustomer = new Payment(send, rec, amount);
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/payment/findPayment");
        String response = target.request(MediaType.APPLICATION_JSON)
                .accept(MediaType.TEXT_PLAIN_TYPE)
                .post(Entity.json(newCustomer), String.class);
        return Boolean.parseBoolean(response);
    }
}
