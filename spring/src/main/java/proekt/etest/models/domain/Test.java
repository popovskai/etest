package proekt.etest.models.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Името на тестот е задолжително")
    private String name;

    @JsonFormat(pattern = "d/M/yyyy")
    private Date createdAt;
    @JsonFormat(pattern = "d-m-yyyy")
    private Date updatedAt;
    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "test", orphanRemoval = true)
    @JsonIgnore
    private List<Question> questions = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testT", orphanRemoval = true)
    @JsonIgnore
    private List<UserTest> userTestsT;

    @PrePersist
    protected  void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate protected void onUpdate(){
        this.updatedAt = new Date();
    }
}
