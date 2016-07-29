package ro.inf.ucv.admitere.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Survey implements Serializable {

    private static final long serialVersionUID = 4628558822870521763L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = false, nullable = false)
    private String title;

    private Boolean active;

    private Boolean latest;

    @ManyToOne(optional = false)
    @JoinColumn(name = "parentTemplate", nullable = false)
    private SurveyTemplate parentTemplate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author", nullable = false)
    private User author;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "survey", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Question> questions = new HashSet<Question>();

    public Survey() {

    }

    public Survey(String title, Boolean active, SurveyTemplate parentTemplate, User author) {
        this.title = title;
        this.active = active;
        this.parentTemplate = parentTemplate;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public SurveyTemplate getParentTemplate() {
        return parentTemplate;
    }

    public void setParentTemplate(SurveyTemplate parentTemplate) {
        this.parentTemplate = parentTemplate;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Boolean getLatest() {
        return latest;
    }

    public void setLatest(Boolean latest) {
        this.latest = latest;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}
