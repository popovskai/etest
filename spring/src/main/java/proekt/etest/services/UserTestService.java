package proekt.etest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import proekt.etest.models.domain.Test;
import proekt.etest.models.domain.User;
import proekt.etest.models.domain.UserTest;
import proekt.etest.repositories.UserRepo;
import proekt.etest.repositories.UserTestRepo;

@Service
public class UserTestService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserTestRepo userTestRepo;
    @Autowired
    private TestService testService;

    public UserTest createTest(String userName, Long testId) {
        Test test = testService.findTestById(testId);
        User user = userRepo.findByUsername(userName);
        UserTest userTest = new UserTest();
        userTest.setUserT(user);
        userTest.setTestT(test);
        userTest.setNameUser(userName);
        userTest.setUser_score(0);
        return userTestRepo.save(userTest);
    }
    public UserTest findUserTestById(Long user_tesId){
        UserTest userTest = userTestRepo.findById(user_tesId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, null));
        return userTest;
    }

    public int getScore(Long user_testId) {
        UserTest userTest = findUserTestById(user_testId);
        return userTest.getUser_score();
    }
}
