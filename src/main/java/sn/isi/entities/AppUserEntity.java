package sn.isi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * @author Ibrahima
 *
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, length = 150)
	private String nom;
	@Column(nullable = false, length = 200)
	private String prenom;
	@Column(unique = true, nullable = false, length = 50)
	private String email;
	@Column(nullable = false, length = 16)
	private String password;
	@Column(nullable = false)
	private int etat;
	@ManyToMany(cascade = {CascadeType.ALL})
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(
			name = "k_user_role",
			joinColumns = @JoinColumn(
					name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
					name = "role_id", referencedColumnName = "id"))
	private List<AppRolesEntity> appRolesEntities;
	@OneToMany(mappedBy = "appUser")
	private List<IAUserEntity> iaUserEntities;
}
