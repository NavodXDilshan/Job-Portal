package ds.project.assignment_service.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="questions")
public class Assignment {
    private String id;
    private String jobId;
    private String questionText;
    private List<String> options;
    private int correctOptionIndex;

    public Assignment() {
    }

    public Assignment(String jobId, String questionText, List<String> options, int correctOptionIndex) {
        this.jobId = jobId;
        this.questionText = questionText;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "id='" + id + '\'' +
                ", jobId='" + jobId + '\'' +
                ", questionText='" + questionText + '\'' +
                ", options=" + options +
                ", correctOptionIndex=" + correctOptionIndex +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }

    public void setCorrectOptionIndex(int correctOptionIndex) {
        this.correctOptionIndex = correctOptionIndex;
    }
}
