package com.cst.crm.ruleengine;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CaseRoutingController {

    private final CaseRoutingService caseRoutingService;

    public CaseRoutingController(CaseRoutingService caseRoutingService) {
        this.caseRoutingService = caseRoutingService;
    }

    @GetMapping("vertical_id/{vId}")
    public Action getAction(@PathVariable String vId) {
        return caseRoutingService.findAction(vId);
    }

    @GetMapping("test")
    public Action getHello() {
        return Action.builder().id(1).build();
    }
}
