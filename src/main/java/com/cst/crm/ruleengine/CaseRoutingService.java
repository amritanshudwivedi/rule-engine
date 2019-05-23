package com.cst.crm.ruleengine;

import lombok.extern.slf4j.Slf4j;
import org.drools.core.io.impl.ResourceFactoryServiceImpl;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.cdi.KSession;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.util.Arrays;

@Service
@Slf4j
public class CaseRoutingService {


    private KieContainer kieContainer;

    @Autowired
    public CaseRoutingService() {
//        this.kieContainer = kieContainer;

    }

    public Action findAction(String vId) {

//        KieServices kieServices = KieServices.Factory.get();
//        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        String ruleString = "package com.rules\n" +
                "import  com.cst.crm.ruleengine.Case\n" +
                "dialect  \"mvel\"\n" +
                "rule \"1\"\n" +
                "    when\n" +
                "        c : Case(verticalId == \"4\")\n" +
                "    then\n" +
                "        c.setActionId(1);\n" +
                "    end";

        Case caseObj = new Case();
        caseObj.setId(1);
        caseObj.setVerticalId(vId);

        KieServices ks = KieServices.Factory.get();
        String inMemoryDrlFileName = "src/main/resources/inmemoryrules.drl";
        KieFileSystem kfs = ks.newKieFileSystem();
        kfs.write(inMemoryDrlFileName,
                ks.getResources().newReaderResource(new StringReader(ruleString)).setResourceType(ResourceType.DRL));

        KieBuilder kieBuilder = ks.newKieBuilder(kfs).buildAll();
        KieContainer kContainer = ks.newKieContainer(kieBuilder.getKieModule().getReleaseId());
        KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
        KieBase kbase = kContainer.newKieBase(kbconf);
        KieSession kieSession = kbase.newKieSession();


        kieSession.insert(caseObj);
        kieSession.fireAllRules();
        kieSession.dispose();


        Action action = new Action();
        action.setId(caseObj.getActionId());
        return action;
    }

}
