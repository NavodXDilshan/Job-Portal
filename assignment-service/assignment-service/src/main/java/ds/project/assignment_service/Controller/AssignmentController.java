package ds.project.assignment_service.Controller;

import com.dsproject.application_service.Model.Application;
import com.dsproject.joblisting_service.Model.Job;

import ds.project.assignment_service.Client.ApplicationClient;
import ds.project.assignment_service.Client.JobClient;
import ds.project.assignment_service.Model.AnswerSubmission;
import ds.project.assignment_service.Model.Assignment;
import ds.project.assignment_service.Repository.AnswerSubmissionRepository;
import ds.project.assignment_service.Repository.AssignmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/assignment")
public class AssignmentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AssignmentController.class);

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private AnswerSubmissionRepository answerSubmissionRepository;

    @Autowired
    private JobClient jobClient;

    @Autowired
    private ApplicationClient applicationClient;

    // Create a question for a job
    @PostMapping("/job/{jobId}")
    public Assignment addQuestion(@PathVariable("jobId") String jobId, @RequestBody Assignment assignment) {
        LOGGER.info("Adding question for jobId: {}", jobId);
        // Validate job exists
        Optional<Job> job = jobClient.findById(jobId);
        if (job.isEmpty()) {
            throw new IllegalArgumentException("Job not found: " + jobId);
        }
        assignment.setJobId(jobId);
        return assignmentRepository.save(assignment);
    }

    // Get all questions for a job
    @GetMapping("/job/{jobId}")
    public List<Assignment> findByJobId(@PathVariable("jobId") String jobId) {
        LOGGER.info("Finding questions for jobId: {}", jobId);
        return assignmentRepository.findByJobId(jobId);
    }



    // Submit answers and calculate score
    @PostMapping("/submit/{applicationId}")
    public Application submitAnswers(@PathVariable("applicationId") String applicationId,
                                     @RequestBody List<AnswerSubmission> submissions) {
        LOGGER.info("Submitting answers for applicationId: {}", applicationId);
        // Validate application exists
        Optional<Application> applicationOpt = applicationClient.findById(applicationId);
        if (applicationOpt.isEmpty()) {
            throw new IllegalArgumentException("Application not found: " + applicationId);
        }
        Application application = applicationOpt.get();

        // Calculate score
        int totalQuestions = submissions.size();
        int correctAnswers = 0;

        for (AnswerSubmission submission : submissions) {
            Optional<Assignment> questionOpt = assignmentRepository.findById(submission.getQuestionId());
            if (questionOpt.isPresent()) {
                Assignment question = questionOpt.get();
                if (submission.getSelectedOptionIndex() == question.getCorrectOptionIndex()) {
                    correctAnswers++;
                }
                submission.setApplicationId(applicationId);
                submission.setJobId(application.jobId());
                answerSubmissionRepository.save(submission);
            }
        }

        // Calculate score as percentage
        int score = totalQuestions > 0 ? (correctAnswers * 100) / totalQuestions : 0;

        // Update application with score
        Application updatedApplication = new Application(
                application.id(),
                application.jobId(),
                application.name(),
                application.email(),
                score,
                application.userId()
        );
        return applicationClient.save(updatedApplication);
    }

    @DeleteMapping("/{assignmentId}")
    public void deleteAssignment(@PathVariable("assignmentId") String assignmentId) {
        LOGGER.info("Deleting assignment with assignmentId: {}", assignmentId);
        // Validate assignment exists
        Optional<Assignment> assignment = assignmentRepository.findById(assignmentId);
        if (assignment.isEmpty()) {
            throw new IllegalArgumentException("Assignment not found: " + assignmentId);
        }

        // Delete associated answer submissions
        List<AnswerSubmission> submissions = answerSubmissionRepository.findByQuestionId(assignmentId);
        if (!submissions.isEmpty()) {
            answerSubmissionRepository.deleteAll(submissions);
            LOGGER.info("Deleted {} answer submissions for assignmentId: {}", submissions.size(), assignmentId);
        }

        // Delete the assignment
        assignmentRepository.deleteById(assignmentId);
        LOGGER.info("Assignment deleted successfully: {}", assignmentId);
    }
}