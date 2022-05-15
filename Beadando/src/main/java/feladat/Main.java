package feladat;


import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@ComponentScans({ 
	  @ComponentScan(basePackages = "repository"), 
	  @ComponentScan(basePackageClasses = ServiceRunner.class)
	})
public class Main {

public static void main(String[] args) {
	ConfigurableApplicationContext appContext = SpringApplication.run(Main.class, args);
	Mainmenu mainmenu = appContext.getBean(Mainmenu.class);
}


public static SessionFactory getSessionFactory() {

    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
      .applySettings(dbSettings())
      .build();

    Metadata metadata = new MetadataSources(serviceRegistry)
      .addAnnotatedClass(User.class)
      .addAnnotatedClass(Background.class)
      .addAnnotatedClass(App.class)
      .buildMetadata();

    return metadata.buildSessionFactory();
}

private static Map<String, String> dbSettings() {
	Map<String, String> dbSettings = new HashMap<>();
    dbSettings.put(Environment.URL, "jdbc:h2:mem:demo");
    dbSettings.put(Environment.USER, "sa");
    dbSettings.put(Environment.PASS, "");
    dbSettings.put(Environment.DRIVER, "org.h2.Driver");
    dbSettings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
    dbSettings.put(Environment.SHOW_SQL, "true");
    dbSettings.put(Environment.HBM2DDL_AUTO, "create");
    return dbSettings;
}
}