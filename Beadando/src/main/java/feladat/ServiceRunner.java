package feladat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ServiceRunner implements CommandLineRunner {
	
	@Autowired
	private Mainmenu mainmenu;

	@Override
	public void run(String... args) throws Exception {
		mainmenu.runMenu();
	}


}
