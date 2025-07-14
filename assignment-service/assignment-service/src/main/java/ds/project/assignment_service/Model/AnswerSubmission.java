package ds.project.assignment_service.Model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "answers")
public class AnswerSubmission {
    private String id;
    private String jobId;
    private String applicationId; // Reference to Application
    private String questionId;
    private int selectedOptionIndex;

    @Override
    public String toString() {
        return "AnswerSubmission{" +
                "id='" + id + '\'' +
                ", jobId='" + jobId + '\'' +
                ", applicationId='" + applicationId + '\'' +
                ", questionId='" + questionId + '\'' +
                ", selectedOptionIndex=" + selectedOptionIndex +
                '}';
    }

    public AnswerSubmission() {
    }

    public AnswerSubmission(String jobId, String applicationId, String questionId, int selectedOptionIndex) {
        this.jobId = jobId;
        this.applicationId = applicationId;
        this.questionId = questionId;
        this.selectedOptionIndex = selectedOptionIndex;
    }

    public String getId() {
        return id;
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

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public int getSelectedOptionIndex() {
        return selectedOptionIndex;
    }

    public void setSelectedOptionIndex(int selectedOptionIndex) {
        this.selectedOptionIndex = selectedOptionIndex;
    }
}
