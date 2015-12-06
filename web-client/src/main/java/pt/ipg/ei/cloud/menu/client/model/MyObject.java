package pt.ipg.ei.cloud.menu.client.model;

import javax.validation.constraints.NotNull;

public class MyObject {

    @NotNull
    private String tbName;

    public String getTbName() {
        return tbName;
    }

    public void setTbName(String tbName) {
        this.tbName = tbName;
    }

}
