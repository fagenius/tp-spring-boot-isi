package sn.isi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class AppRolesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false,length = 100)
    private String nom;

    @ManyToMany(mappedBy = "appRolesEntities")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<AppUserEntity> appUserEntities;
}
