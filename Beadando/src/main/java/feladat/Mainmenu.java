package feladat;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class Mainmenu {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AppRepository appRepository;
	@Autowired
	private BackgroundRepository backgroundRepository;
	
	
	//Instance Variables
	Scanner keyboard = new Scanner(System.in);
 	List <User> users = new ArrayList<>();
	String[] themes = {"Simple","Light","Dark"};
 	boolean exit = false;
 	boolean back = false;
 	private Long currentUser = 1L;	
 	
//main menu
public void runMenu() {
		User userDefault = userRepository.save(new User("Default User", 1L));
		users.add(userDefault);
		giveDefaultBgs(userDefault);
		userRepository.save(userDefault);
        while (!exit) {
        	back = false;
        	printHeader();
            printMenu();
            int choice = getMenuChoice(3);
            performAction(choice);     
        }
    }

private void giveDefaultBgs (User newUser) {
	Background  bg1 = backgroundRepository.save(new Background("Landscape"));
	Background  bg2 = backgroundRepository.save(new Background("Painting"));
	Background  bg3 = backgroundRepository.save(new Background("Drawing"));
newUser.newBg(bg1);
newUser.newBg(bg2);
newUser.newBg(bg3);
}
private User selectuserbyID(Long choice) {
	User user = null;
	for(User user1 : users) {
		
		if (user1.getUserNumber() == choice) {
			user = user1;
		}
	}
	return user;
}

private void printHeader() {
	User myUser = selectuserbyID(currentUser);
        System.out.println("Welcome "+ myUser.getName());
        System.out.println("Your background is set as " + myUser.selectBgbyID(myUser.getBgid())+ ".");
        System.out.println("With a " + themes[myUser.getThemeid()] + " theme.");
    }
	
private void printMenu() {
        System.out.println("Please make a selection");
        System.out.println("(1) Apps");
        System.out.println("(2) Users");
        System.out.println("(3) Mydevice");
        System.out.println("(0) Exit");
    }
  
    //main menu choice  
private int getMenuChoice(int x) {
        int choice = -1;
        do {
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(keyboard.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection. Numbers only please.");
            }
            if (choice < 0 || choice > x) {
                System.out.println("Choice outside of range. Please chose again.");
            }
        } while (choice < 0 || choice > x);
        return choice;
	}
	
    //main menu action
private void performAction(int choice) {
        switch (choice) {
        
            case 0:
                System.out.println("Thank you for using our application.");
                System.exit(0);
                break;
                
            case 1: 
            	while (!back) {
            			printsubMenu1Apps();
            			int choice1 = getMenuChoice(4);
            			performsubAction1(choice1);
            		}
            	break;
            	
            case 2: 
    				while (!back) {
    					printsubMenu2Users();
    					int choice2 = getMenuChoice(4);
    					performsubAction2(choice2);
    		}
                break;
                
            case 3: 
    				while (!back) {
    					printsubMenu3Mydevice();
    					int choice3 = getMenuChoice(4);
    					performsubAction3(choice3);
    		}
                break;

            default:
                System.out.println("Unknown error has occured.");
        }
	}


	//submenu apps
private void printsubMenu1Apps() {
        System.out.println("Please make a selection");
        System.out.println("(1) My Apps");
        System.out.println("(2) Add New App");
        System.out.println("(3) Delete App");
        System.out.println("(0) Back");
	}
	
private void performsubAction1(int choice1) {
	switch (choice1) {
	
		//back
	case 0:
		back = true;
		break;
		
		//running an app from my apps
	case 1:
		User myUserCase1 = selectuserbyID(currentUser);
		if (selectuserbyID(currentUser).getOwnapps().size() <= 0){
			System.out.println("Your user has 0 apps installed, please install an Application first.");
		}
		else {
			System.out.println("Your user has these Apps downloaded: ");
			System.out.println(myUserCase1.getOwnapps());
		        Long choiceAppCase1 = -1L;
		        do {
		            System.out.print("Enter your choice to run: ");
		            try {
		                choiceAppCase1 = Long.parseLong(keyboard.nextLine());
		            } catch (NumberFormatException e) {
		                System.out.println("Invalid selection. Numbers only please.");
		            }
		        } while (myUserCase1.selectOwnAppbyID(choiceAppCase1) == null);
		        
		      System.out.println(myUserCase1.selectOwnAppbyID(choiceAppCase1) + " is running...");
		}	
		break;
		
		//Installing an app
	case 2:
		User myUserCase2 = selectuserbyID(currentUser);
		System.out.println("Please declare the name of the app:");
		String newApp = keyboard.nextLine();
		App newApp1 = appRepository.save(new App(newApp));
		myUserCase2.newApp(newApp1);
		userRepository.save(myUserCase2);
		break;
		
		//Deleting an app
	case 3:
		User myUserCase3 = selectuserbyID(currentUser);
		System.out.println("You have these apps installed:");
		System.out.println(selectuserbyID(currentUser).getOwnapps());
	        Long choiceAppCase3 = -1L;
	        do {
	            System.out.print("Enter your choice to delete: ");
	            try {
	            	choiceAppCase3 = Long.parseLong(keyboard.nextLine());
	            } catch (NumberFormatException e) {
	                System.out.println("Invalid selection. Numbers only please.");
	            }
	        } while (myUserCase3.selectOwnAppbyID(choiceAppCase3) == null);
	      System.out.println(myUserCase3.selectOwnAppbyID(choiceAppCase3) + " is deleted.");
	      myUserCase3.delApp(myUserCase3.selectOwnAppbyID(choiceAppCase3));
	      appRepository.deleteById(choiceAppCase3);
	    break;
	    
	default: 
        System.out.println("Unknown error has occured.");
	}
}

	//submenu users
private void printsubMenu2Users() {
    System.out.println("Please make a selection");
    System.out.println("1) Select current User");
    System.out.println("2) Add new User");
    System.out.println("3) Modify current User");
    System.out.println("4) Delete current User");
    System.out.println("0) Back");
	}

private void performsubAction2(int choice2) {
	switch (choice2) {
	
	//back
case 0:
	back = true;
	break;
	
	//Select Current User
case 1:
	if (users.size() <= 1) {
		System.out.println("Please add another User first.");
	}
	else {
		System.out.println("Registered Users:");
		System.out.println(users);
	        Long choiceUserCase1 = -1L;
	        do {
	            System.out.print("Enter your choice to select: ");
	            try {
	            	choiceUserCase1 = Long.parseLong(keyboard.nextLine());;
	            } catch (NumberFormatException e) {
	                System.out.println("Invalid selection. Numbers only please.");
	            }
	        } while (selectuserbyID(choiceUserCase1) == null);
	        
	      currentUser = choiceUserCase1;
	      System.out.println("Logged in as: " + selectuserbyID(currentUser).getName());
	}
	break;

	//Add New User
case 2:
	System.out.println("Please declare the name of the new User:");
	String newUserNameCase2 = keyboard.nextLine();
	User newUserCase2 = userRepository.save(new User(newUserNameCase2));
	users.add(newUserCase2);
	giveDefaultBgs(newUserCase2);
	newUserCase2.setBgid(newUserCase2.getBackgrounds().get(0).getBackgroundid());
	userRepository.save(newUserCase2);
	
	break;
	
	//Modify Current User
case 3:
	User myUserCase3 = selectuserbyID(currentUser);
	System.out.println("Please declare your new name.");
	String newNameCase3 = keyboard.nextLine();
	myUserCase3.setName(newNameCase3);
	System.out.println("Your name has been changed to: " + newNameCase3);
	userRepository.save(myUserCase3);
	  break;
	  
      //Delete Current User
case 4:
	if (users.size() <= 1) {
			System.out.println("Please add another User first.");
	}
	else {
			System.out.println("Are you sure you want to delete this User?");
			System.out.println("(yes/no)");
			if (keyboard.nextLine().equalsIgnoreCase("yes")) {
				users.remove(selectuserbyID(currentUser));
				userRepository.deleteById(currentUser);
			
			System.out.println("Please select a new User to log in:");
			System.out.println("Registered Users:");
			System.out.println(users);
		        Long choiceUserCase4 = -1L;
		        do {
		            System.out.print("Enter your choice to select: ");
		            try {
		            	choiceUserCase4 = Long.parseLong(keyboard.nextLine());;
		            } catch (NumberFormatException e) {
		                System.out.println("Invalid selection. Numbers only please.");
		            }
		        } while (selectuserbyID(choiceUserCase4) == null);
		      currentUser = choiceUserCase4;
		      System.out.println("Logged in as: " + selectuserbyID(currentUser).getName());
			break;
			}
			else {
				System.out.println("Your User did not get deleted.");
			}
		}	
	}
}

	//submenu mydevice
private void printsubMenu3Mydevice() {
    System.out.println("Please make a selection");
    System.out.println("1) Select Theme");
    System.out.println("2) Select current Background");
    System.out.println("3) Add new Background");
    System.out.println("4) Delete Background");
    System.out.println("0) Back");
	}
	
private void performsubAction3(int choice3) {
	switch (choice3) {
	//back
	case 0:
		back = true;
		break;
		
		//Select Theme
	case 1:
		User myUserCase1 = selectuserbyID(currentUser);
			System.out.println("Select a theme from the list:");
			for (int i=0; i < 3 ; i++) {
				System.out.print(i+1 + ")"+themes[i]+", ");
			}
		        int choice = -1;
		        do {
		            try {
		                choice = Integer.parseInt(keyboard.nextLine());
		                choice--;
		            } 
		            catch (NumberFormatException e) {
		                System.out.println("Invalid selection. Numbers only please.");
		            }
		            if (choice < 0 || choice > 3) {
		                System.out.println("Choice outside of range. Please choose again.");
		            }
		        } while (choice < 0 || choice > 3);
		      myUserCase1.setThemeid(choice);
		      System.out.println("Your new theme is " + themes[myUserCase1.getThemeid()] + ".");
		      userRepository.save(myUserCase1);
		      
		break;

		//Select Background
	case 2:
		User myUserCase2 = selectuserbyID(currentUser);
		System.out.println("Please select a background from the list:");
		System.out.println(myUserCase2.getBackgrounds());
		
	        Long choiceBg = -1L;
	        do {
	            try {
	            	choiceBg = Long.parseLong(keyboard.nextLine());
	            } 
	            catch (NumberFormatException e) {
	                System.out.println("Invalid selection. Numbers only please.");
	            }
	        } while (myUserCase2.selectBgbyID(choiceBg) == null);
	        myUserCase2.setBgid(choiceBg);
	      System.out.println("Your new background is " + myUserCase2.selectBgbyID(myUserCase2.getBgid()) + ".");
	      userRepository.save(myUserCase2);
		break;
		
		//Add Background
	case 3:
		User myUserCase3 = selectuserbyID(currentUser);
		System.out.println("Please declare the name of the new Background:");
		String bgName = keyboard.nextLine();
		Background newBackground = backgroundRepository.save(new Background(bgName));
		myUserCase3.newBg(newBackground);
		userRepository.save(myUserCase3);
		   break;

	      //Delete Background
	case 4:
		User myUserCase4 = selectuserbyID(currentUser);
		if (myUserCase4.getBackgrounds().size() <= 1) {
				System.out.println("Please add another Background first.");
		}
		else {
			System.out.println("Please select a background from the list:");
			System.out.println(myUserCase4.getBackgrounds());

		        Long choiceBgtoDel = -1L;
		        do {
		            try {
		            	choiceBgtoDel = Long.parseLong(keyboard.nextLine());
		            } 
		            catch (NumberFormatException e) {
		                System.out.println("Invalid selection. Numbers only please.");
		            }

		        } while (myUserCase4.selectBgbyID(choiceBgtoDel) == null);
		        
	            if (myUserCase4.getBgid() == choiceBgtoDel){
					System.out.println("You can't delete the current background.");
				}
	            else {
				System.out.println("Are you sure you want to delete this Background?");
				System.out.println("(yes/no)");
				if	(keyboard.nextLine().equalsIgnoreCase("yes")) {
					myUserCase4.delBg(myUserCase4.selectBgbyID(choiceBgtoDel));
					backgroundRepository.deleteById(choiceBgtoDel);
									}
	            	}
				break;
			}
		}
	}   
}
