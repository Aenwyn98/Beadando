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
@Table(name="background")
@Getter @Setter @NoArgsConstructor public class Background {
	

	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "bgidgenerator")
	@SequenceGenerator(name = "bgidgenerator", sequenceName = "bgidseq")
	private Long backgroundid;
	@Column
	private String name;
    @ManyToOne
    @JoinColumn(name="background")
    private User user;
    
	  Background (String name){
		 this.name = name;
	  }
	
@Override
    public String toString(){
        return name + " = " + backgroundid;
    }
}
