package ds.project.assignment_service.Client;

import com.dsproject.application_service.Model.Application;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(name = "application-service", url = "${application.service.url}")
public interface ApplicationClient {
    @GetMapping("/application/{id}")
    Optional<Application> findById(@PathVariable("id") String id);

    @PostMapping("/application")
    Application save(@RequestBody Application application);
}