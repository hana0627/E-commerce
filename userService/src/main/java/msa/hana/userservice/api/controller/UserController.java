package msa.hana.userservice.api.controller;

import msa.hana.userservice.api.dto.request.UserCreate;
import msa.hana.userservice.api.dto.response.UserResponse;
import msa.hana.userservice.api.service.UserService;
import msa.hana.userservice.global.dto.Greeting;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    private final Greeting greeting;
    private final UserService userService;

    public UserController(Greeting greeting, UserService userService) {
        this.greeting = greeting;
        this.userService = userService;
    }

    @GetMapping("/health_check")
    public String status() {
        return "It's Working in User Service";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Validated UserCreate requestDto) {
        Long id = userService.createUser(requestDto);

        UserResponse userResponse = UserResponse.of("UserCreated Success!", id);

//        return new ResponseEntity<>(userResponse , HttpStatus.CREATED);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userResponse);
    }

}
