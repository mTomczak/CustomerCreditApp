package app.Wraper;


import app.model.Credit;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * The class that wraps the Credit List.
 */

@Component
public class CreditWraper {
    private List<Credit> creditList;

    public List<Credit> getCreditList() {
        return creditList;
    }

    public void setCreditList(List<Credit> creditList) {
        this.creditList = creditList;
    }

    public CreditWraper() {
        this.creditList = new ArrayList<>();
    }
}
