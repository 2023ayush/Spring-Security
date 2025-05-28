package com.example.security.Controller;

import com.example.security.ApiResponse;
import com.example.security.Dto.LoginDto;
import com.example.security.Dto.UserDto;
import com.example.security.Service.AuthService;
import com.example.security.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@RequestBody UserDto dto){
        ApiResponse<String> response = authService.register(dto);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

//    @PostMapping("/login")
//    public ResponseEntity<ApiResponse<String>> loginCheck(@RequestBody LoginDto loginDto){
//
//        ApiResponse<String> response = new ApiResponse<>();
//
//        UsernamePasswordAuthenticationToken token =
//                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
//
//        try {
//            Authentication authenticate = authenticationManager.authenticate(token);
//
//            if(authenticate.isAuthenticated()) {
//                response.setMessage("Login Sucessful");
//                response.setStatus(200);
//                response.setData("User has logged");
//                return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        response.setMessage("Failed");
//        response.setStatus(401);
//        response.setData("Un-Authorized Access");
//        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
//    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> loginCheck(@RequestBody LoginDto loginDto) {
        ApiResponse<String> response = new ApiResponse<>();

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        try {
            Authentication authenticate = authenticationManager.authenticate(token);
            if (authenticate.isAuthenticated()) {
                String jwtToken = jwtService.generateToken(loginDto.getUsername(),
                        authenticate.getAuthorities().iterator().next().getAuthority());

                response.setMessage("Login Successful");
                response.setStatus(200);
                response.setData(jwtToken);  // return JWT
                return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setMessage("Failed");
        response.setStatus(401);
        response.setData("Unauthorized");
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
    }
}
