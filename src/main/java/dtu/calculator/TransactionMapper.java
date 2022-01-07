package dtu.calculator;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement // Needed for XML serialization and deserialization
//@Data // Automatic getter and setters and equals etc
@NoArgsConstructor // Needed for JSON deserialization and XML serialization and deserialization
//@AllArgsConstructor
public class TransactionMapper{

    String debitor;
    String creditor;

    String description;
    BigDecimal amount;

    public TransactionMapper(String debitor, String creditor, String description, BigDecimal amount) {
        this.debitor = debitor;
        this.creditor = creditor;
        this.description = description;
        this.amount = amount;
    }

    public String getDebitor() {
        return debitor;
    }

    public void setDebitor(String debitor) {
        this.debitor = debitor;
    }

    public String getCreditor() {
        return creditor;
    }

    public void setCreditor(String creditor) {
        this.creditor = creditor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal balance) {
        this.amount = balance;
    }
}
