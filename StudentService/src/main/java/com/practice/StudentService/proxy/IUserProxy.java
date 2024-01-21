package com.practice.StudentService.proxy;

import com.practice.StudentService.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "UserService", url = "localhost:8081")
public interface IUserProxy {
    @PostMapping("/api/user/register")
    ResponseEntity<?> register(@RequestBody User user);

    @PutMapping("/api/user/update")
    ResponseEntity<?> updateUser(@RequestBody User user);

    @DeleteMapping("/api/user/delete/{email}")
    ResponseEntity<?> deleteUser(@PathVariable String email);
}
