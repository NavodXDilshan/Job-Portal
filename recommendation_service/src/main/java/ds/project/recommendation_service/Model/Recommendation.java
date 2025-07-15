package ds.project.recommendation_service.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "recommendations")
public class Recommendation {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String userId;
    private String name;
    private String email;

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Recommendation{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", qualifications=" + qualifications +
                ", description='" + description + '\'' +
                '}';
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<String> qualifications) {
        this.qualifications = qualifications;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Recommendation(String id, String userId, String name, String email, List<String> qualifications, String description) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.qualifications = qualifications;
        this.description = description;
    }

    private List<String> qualifications;
    private String description;
}
