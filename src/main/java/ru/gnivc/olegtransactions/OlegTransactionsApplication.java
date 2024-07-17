package ru.gnivc.olegtransactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.gnivc.olegtransactions.beans.SomeClass;

@SpringBootApplication
public class OlegTransactionsApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(OlegTransactionsApplication.class, args);

        SomeClass bean = context.getBean(SomeClass.class);
        bean.a();
    }

}
