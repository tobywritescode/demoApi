package com.example.demoapi.api.controller;

import com.example.demoapi.exception.AgeCanNotBeLowerThanZero;
import com.example.demoapi.exception.UsersCanNotBeNullException;
import com.example.demoapi.model.entity.people.UserAgeGroups;
import com.example.demoapi.model.entity.people.Users;
import com.example.demoapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
@AllArgsConstructor
public class PeopleController {

    private final UserService userService;

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @PostMapping("/getUsersOver/{age}")
    public ResponseEntity<Users> getUsersOver(@RequestBody Users users, @PathVariable Integer age) throws AgeCanNotBeLowerThanZero, UsersCanNotBeNullException {
        if(users == null || users.userInfoList == null || users.userInfoList.isEmpty()) {
            throw new UsersCanNotBeNullException("Users can not be null");
        }else if(age <= 0){
            throw new AgeCanNotBeLowerThanZero("Age can not be lower than zero.");
        }
        return ResponseEntity.ok(userService.getUsersOverAge(users, age));

    }

    @PostMapping("/getUsersInAgeGroups")
    public ResponseEntity<UserAgeGroups> getUsersInAgeGroups(@RequestBody Users users) throws UsersCanNotBeNullException {
        if(users == null || users.userInfoList == null || users.userInfoList.isEmpty()) {
            throw new UsersCanNotBeNullException("Users can not be null");
        }
        return ResponseEntity.ok(userService.putUsersIntoAgeGroups(users));
    }

    @GetMapping(value = "/startJobOne")
    public ResponseEntity<String> startJob() {


        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("ExampleParameter", 1L, true)
                .toJobParameters();
        try {
            jobLauncher.run(job, jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Could not start job.");
        }

        return ResponseEntity.accepted().body("Job successfully started.");
    }

}
