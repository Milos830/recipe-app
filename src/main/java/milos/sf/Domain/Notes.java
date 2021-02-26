package milos.sf.Domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne// dont need Cascade coz if we delete Note, we dont wont to delete and Recipe
    private Recipe recipe;

    @Lob
    private String recipeNote;

}
