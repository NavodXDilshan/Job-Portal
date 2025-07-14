package ds.project.assignment_service.Client;

import com.dsproject.joblisting_service.Model.Job;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "joblisting-service", url = "${job.service.url}")
public interface JobClient {
    @GetMapping("/job/{id}")
    Optional<Job> findById(@PathVariable("id") String id);
}