package msa.hana.userservice.api.controller;

import msa.hana.userservice.api.domain.UserAccount;
import msa.hana.userservice.api.dto.request.UserCreate;
import msa.hana.userservice.api.dto.response.UserResponse;
import msa.hana.userservice.api.dto.response.UserResponseData;
import msa.hana.userservice.api.service.UserService;
import msa.hana.userservice.global.dto.Greeting;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user-service/")
public class UserController {

    private final Greeting greeting;
    private final UserService userService;
    private final Environment env;

    public UserController(Greeting greeting, UserService userService, Environment env) {
        this.greeting = greeting;
        this.userService = userService;
        this.env = env;
    }

    @GetMapping("/health_check")
    public String status() {
        return "It's Working in User Service on PORT " + env.getProperty("local.server.port");
    }

    @GetMapping("/welcome")
    public String welcome() {
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponseData> createUser(@RequestBody @Validated UserCreate requestDto) {
        Long id = userService.createUser(requestDto);

        UserResponseData userResponseData = UserResponseData.of("UserCreated Success!", id);

//        return new ResponseEntity<>(userResponse , HttpStatus.CREATED);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userResponseData);
    }


    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> users() {

        List<UserResponse> result = userService.getUsers();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> users(@PathVariable("userId") String userId) {

        UserResponse result = userService.findUser(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }


}
