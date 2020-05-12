package proekt.etest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proekt.etest.models.domain.UserRespond;
import proekt.etest.services.UserRespondService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/respond")
public class UserRespondController {

    @Autowired
    private UserRespondService userRespondService;

    @PostMapping("/{user_testId}")
    public ResponseEntity<?> postRespond(@RequestParam Long questionId,
                                         @RequestParam Long respondId,
                                         @PathVariable Long user_testId){
        UserRespond respond = userRespondService.addRespond(user_testId, questionId, respondId);
        return new ResponseEntity<UserRespond>(respond, HttpStatus.CREATED);
    }
}
