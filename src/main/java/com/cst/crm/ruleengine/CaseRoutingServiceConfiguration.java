package com.cst.crm.ruleengine;

import lombok.Getter;
import lombok.Setter;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.StringReader;

@Configuration
@ConfigurationProperties(prefix = "case-routing-data")
public class CaseRoutingServiceConfiguration {
    private static final String drlFile = "CaseRoutingRules.drl";

//    @Bean
//    public KieContainer kieContainer() {
//        KieServices kieServices = KieServices.Factory.get();
//        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
//        String ruleString = "package com.rules\n" +
//                "import  com.cst.crm.ruleengine.Case;\n" +
//                "//import com.cst.crm.ruleengine.Action\n" +
//                "\n" +
//                "dialect  \"mvel\"\n" +
//                "\n" +
//                "rule \"CaseRoutingRules\"\n" +
//                "    when\n" +
//                "        c : Case(verticalId <= 4)\n" +
//                "    then\n" +
//                "        c.setActionId(1);\n" +
//                "        System.out.println(\"..................Test..................................\");\n" +
//                "end";
//
//
        KieServices ks = KieServices.Factory.get();
        String inMemoryDrlFileName = "src/main/resources/inmemoryrules.drl";
        KieFileSystem kfs = ks.newKieFileSystem();
        kfs.write(inMemoryDrlFileName,
                ks.getResources().newReaderResource(new StringReader(ruleString)).setResourceType(ResourceType.DRL));

        KieBuilder kieBuilder = ks.newKieBuilder(kfs).buildAll();
        return ks.newKieContainer(kieBuilder.getKieModule().getReleaseId());

//        kieFileSystem.write(ResourceFactory.newClassPathResource(drlFile));
//        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
//        kieBuilder.buildAll();
//        KieModule kieModule = kieBuilder.getKieModule();
//        return kieServices.newKieContainer(kieModule.getReleaseId());
    }
}
