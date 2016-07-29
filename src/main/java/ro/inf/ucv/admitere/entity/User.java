package ro.inf.ucv.admitere.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 9093304836112847216L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlAttribute(name = "id")
    private Long id;

    @XmlElement(name = "token")
    private String token;

    @XmlElement(name = "username")
    private String username;

    @XmlElement(name = "email")
    private String email;

    @JsonIgnore
    private String password;

    @XmlElement(name = "enabled")
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    @XmlTransient
    private List<Role> roles;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author", fetch = FetchType.LAZY)
    private Set<SurveyTemplate> templates = new HashSet<SurveyTemplate>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author", fetch = FetchType.LAZY)
    private Set<Survey> publishedSurveys = new HashSet<Survey>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_surveys", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "survey_id", referencedColumnName = "id")})
    private Set<SurveyData> surveys = new HashSet<SurveyData>();

    public Set<SurveyTemplate> getTemplates() {
        return templates;
    }

    public void setTemplates(Set<SurveyTemplate> templates) {
        this.templates = templates;
    }

    public Set<Survey> getPublishedSurveys() {
        return publishedSurveys;
    }

    public void setPublishedSurveys(Set<Survey> publishedSurveys) {
        this.publishedSurveys = publishedSurveys;
    }

    public Set<SurveyData> getSurveys() {
        return surveys;
    }

    public void setSurveys(Set<SurveyData> surveys) {
        this.surveys = surveys;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setReset(String token) {
        this.token = token;
    }

}
