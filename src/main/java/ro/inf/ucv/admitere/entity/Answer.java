package ro.inf.ucv.admitere.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Answer implements Serializable {

    private static final long serialVersionUID = 2491329840625081173L;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "question", nullable = false)
    private Question question;

    @ManyToOne(optional = false)
    @JoinColumn(name = "survey", nullable = false)
    private SurveyData survey;

    @ManyToMany(mappedBy = "answers", fetch = FetchType.LAZY)
    private Set<AnswerOption> answerOptions = new HashSet<AnswerOption>();

    public Answer() {

    }

    public Answer(User user, Question question, SurveyData survey, List<AnswerOption> options) {
        this.user = user;
        this.question = question;
        this.survey = survey;
        this.answerOptions = new HashSet<AnswerOption>(options);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public SurveyData getSurvey() {
        return survey;
    }

    public void setSurvey(SurveyData survey) {
        this.survey = survey;
    }

    public Set<AnswerOption> getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(Set<AnswerOption> answerOptions) {
        this.answerOptions = answerOptions;
    }
}
