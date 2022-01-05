package dtu.calculator;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement // Needed for XML serialization and deserialization
//@Data // Automatic getter and setters and equals etc
@NoArgsConstructor // Needed for JSON deserialization and XML serialization and deserialization
@AllArgsConstructor
public class Payment {
    private String send;
    private String rec;
    private int amount;

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    public String getRec() {
        return rec;
    }

    public void setRec(String rec) {
        this.rec = rec;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
