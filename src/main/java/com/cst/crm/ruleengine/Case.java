package com.cst.crm.ruleengine;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

//@Getter @Setter
@Slf4j
public class Case {
    public int id;


    public String verticalId;
    public int l1Id;
    public int actionId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVerticalId() {
        return verticalId;
    }

    public void setVerticalId(String verticalId) {
        this.verticalId = verticalId;
    }

    public int getL1Id() {
        return l1Id;
    }

    public void setL1Id(int l1Id) {
        this.l1Id = l1Id;
    }

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        log.info("................chala........................");
        this.actionId = actionId;
    }
}
