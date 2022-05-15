package feladat;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="user")
@Getter @Setter @NoArgsConstructor public class User {

	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "useridgenerator")
	@SequenceGenerator(name = "useridgenerator", sequenceName = "useridseq")
	private Long userNumber;
	@Column
	private String name;
	@Column
	private Long bgid;
	@Column
	private Long numberOfBgs = 0L;
	@Column
	private int themeid = 0;
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Background> backgrounds = new ArrayList<>();
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<App> ownapps = new ArrayList<>();
	
	
	  User (String name, Long bgid){
	    	this.name = name;
	    	this.bgid =	bgid;
		    }
		  User (String name){
		    	this.name = name;
			    }
		  
		  public Background selectBgbyID(Long choice) {
				Background bg = null;
				for(Background bg1 : backgrounds) {
					
					if (bg1.getBackgroundid() == choice) {
						bg = bg1;
					}
				}
				return bg;
			}
		  public App selectOwnAppbyID(Long choice) {
				App app = null;
				for(App app1 : ownapps) {
					
					if (app1.getAppid() == choice) {
						app = app1;
					}
				}
				return app;
			}
		  public void newApp(App name) {
			  ownapps.add(name);
		  }
		  public void delApp(App choiceAppDel) {
			  ownapps.remove(choiceAppDel);
		  }
 
		  //user's own Background array
		  public void newBg(Background name) {
			  backgrounds.add(name);
		  }
		  
		  public void delBg(Background choiceBgDel) {
			  backgrounds.remove(choiceBgDel);
		  }

		  
		  @Override
		  public String toString(){
		       return name + " = " + userNumber;
			}
}