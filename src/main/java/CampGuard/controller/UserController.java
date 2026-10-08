package CampGuard.controller;

import CampGuard.entity.User;
import CampGuard.security.JwtUtil;
import CampGuard.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {

        try {
            String username = loginData.get("username");
            String password = loginData.get("password");

            User user = userService.login(username, password);

            String token = JwtUtil.generateToken(
                    user.getUsername(),
                    user.getRole()
            );

            return ResponseEntity.ok(
                    Map.of(
                            "message", "Login successful",
                            "username", user.getUsername(),
                            "role", user.getRole(),
                            "token", token
                    )
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .status(401)
                    .body(Map.of(
                            "message", "Invalid username or password"
                    ));
        }
    }
}