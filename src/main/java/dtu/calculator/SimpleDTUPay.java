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
        WebTarget target = client.target("http://localhost:8080/payment");
        String result = target.request(MediaType.APPLICATION_JSON)
                .accept(MediaType.TEXT_PLAIN_TYPE)
                .post(Entity.json(transactionMapper), String.class);
        return result.contains("Successful");
    }

    public Account getAccount(String id) {
        WebTarget target = client.target("http://localhost:8080/payment/account/" + id);
        Account result = target.request(MediaType.APPLICATION_JSON)
                .accept(MediaType.TEXT_PLAIN_TYPE)
                .get(new GenericType<>() {});
        return result;
    }
}
