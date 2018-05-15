package JavaClasses;

import java.util.Date;
import java.util.List;

public class LedgerEntry {
    private Date date;
    private String id;
    private List<String> productCardsId;
    private String contractorId;




    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getProductCardsId() {
        return productCardsId;
    }

    public void setProductCardsId(List<String> productCardsId) {
        this.productCardsId = productCardsId;
    }

    public String getContractorId() {
        return contractorId;
    }

    public void setContractorId(String contractorId) {
        this.contractorId = contractorId;
    }
}
