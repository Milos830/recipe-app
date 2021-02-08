package milos.sf.Domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
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
