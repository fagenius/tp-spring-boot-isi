package sn.isi.entities;

import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author Ibrahima
 *
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IAUserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false,length = 200)
	private String nom;
	@ManyToOne
	private AppUserEntity appUser;
	@OneToMany(mappedBy = "iaUser")
	private List<IEFUserEntity> iefUsersEntities;
}
