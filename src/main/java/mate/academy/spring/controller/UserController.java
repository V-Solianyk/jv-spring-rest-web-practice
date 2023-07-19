package mate.academy.spring.controller;

import mate.academy.spring.mapper.impl.request.UserRequestMapper;
import mate.academy.spring.mapper.impl.response.UserResponseMapper;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.response.UserResponseDto;
import mate.academy.spring.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserRequestMapper userRequestMapper;
    private final UserResponseMapper userResponseMapper;

    public UserController(UserService userService, UserRequestMapper userRequestMapper,
                          UserResponseMapper userResponseMapper) {
        this.userService = userService;
        this.userRequestMapper = userRequestMapper;
        this.userResponseMapper = userResponseMapper;
    }

    @GetMapping("/by-email")
    public UserResponseDto getUserByEmail(@RequestParam String email) {
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Does not exist the user in a database"
                        + " for this email: " + email));
        return userResponseMapper.toDto(user);
    }
}