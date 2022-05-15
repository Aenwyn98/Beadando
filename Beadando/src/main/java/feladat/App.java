package feladat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name="app")
@Getter @Setter @NoArgsConstructor public class App {


	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "appidgenerator")
	@SequenceGenerator(name = "appidgenerator", sequenceName = "appidseq")
	private Long appid;
	@Column
	private String name;
    @ManyToOne
    @JoinColumn(name="app")
    private User user;
    
	 App (String name){
		 this.name = name;
	 	}

	 
@Override
	 public String toString(){
    return name + " = " + appid;
	    }
}
