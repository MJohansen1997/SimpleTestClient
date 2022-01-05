package dtu.calculator;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleDTUPay {
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
                .get(new GenericType<>(){});
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
