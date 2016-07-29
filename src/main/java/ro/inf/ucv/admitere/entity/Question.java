package ro.inf.ucv.admitere.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"description", "survey"}))
public class Question implements Serializable {

    private static final long serialVersionUID = 7872898223016885190L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = false)
    private String description;

    @Column(nullable = false)
    private String answerType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "survey", nullable = false)
    private Survey survey;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<AnswerOption> answerOptions = new HashSet<AnswerOption>();

    public Question() {

    }

    public Question(String description, String answerType, Survey survey, List<AnswerOption> answerOptions) {
        this.description = description;
        this.answerType = answerType;
        this.answerOptions = new HashSet<AnswerOption>(answerOptions);
        this.survey = survey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public Set<AnswerOption> getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(Set<AnswerOption> answerOptions) {
        this.answerOptions = answerOptions;
    }
}
