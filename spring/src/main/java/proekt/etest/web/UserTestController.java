package proekt.etest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proekt.etest.models.domain.UserTest;
import proekt.etest.services.UserTestService;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/testing")
public class UserTestController {

    @Autowired
    private UserTestService userTestService;

    @PostMapping("/{testId}")
    public ResponseEntity<?> postUserTest(Principal principal,
                                          @PathVariable Long testId){

        UserTest userTest = userTestService.createTest(principal.getName(), testId);
        return new ResponseEntity<UserTest>(userTest, HttpStatus.CREATED);
    }

    @GetMapping("/score")
    public int getScore(@RequestParam Long user_testId){
        return userTestService.getScore(user_testId);
    }
}