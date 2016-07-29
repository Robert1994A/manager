package ro.inf.ucv.admitere.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"description", "survey_template"}))
public class QuestionTemplate implements Serializable {

    private static final long serialVersionUID = 2623997428809055306L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = false)
    private String description;

    @Column(nullable = false)
    private String answerType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "survey_template", nullable = false)
    private SurveyTemplate survey;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<AnswerOptionTemplate> answerOptions = new HashSet<AnswerOptionTemplate>();

    public QuestionTemplate() {

    }

    public QuestionTemplate(String description, String answerType, SurveyTemplate survey,
                            List<AnswerOptionTemplate> answerOptions) {
        this.description = description;
        this.answerType = answerType;
        this.answerOptions = new HashSet<AnswerOptionTemplate>(answerOptions);
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

    public Set<AnswerOptionTemplate> getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(Set<AnswerOptionTemplate> answerOptions) {
        this.answerOptions = answerOptions;
    }

    public SurveyTemplate getSurvey() {
        return survey;
    }

    public void setSurvey(SurveyTemplate survey) {
        this.survey = survey;
    }
}
