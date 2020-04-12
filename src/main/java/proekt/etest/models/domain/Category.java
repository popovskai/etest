package proekt.etest.models.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable = false, unique = true)
    @NotNull(message = "Идентификувачки код за категоријата е задолжителен")
    @Size(min = 4, max = 5, message = "Ве молам внесете код од 4 до 5 карактери")
    private String categoryIdentifier;
    @NotNull(message = "Името на катеогријата е задолжително")
    private String name;
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "category", orphanRemoval = true)
    private List<Test> tests = new ArrayList<>();
}
