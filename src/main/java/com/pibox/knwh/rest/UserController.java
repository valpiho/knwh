package com.pibox.knwh.rest;

import com.pibox.knwh.entity.DTO.UserDTO;
import com.pibox.knwh.entity.HttpResponse;
import com.pibox.knwh.service.UserService;
import com.pibox.knwh.utils.MapValidationErrorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
public class UserController {

    private final UserService userService;
    private final MapValidationErrorService mapValidationErrorService;

    public UserController(UserService userService, MapValidationErrorService mapValidationErrorService) {
        this.userService = userService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        UserDTO userDTO = userService.getUserById( id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/users")
    public List<UserDTO> getAllUsers(@RequestParam(value = "companyId", required = false) Long companyTitle) {
        if (companyTitle == null) {
            return userService.getAllUsers();
        }
        return userService.getAllUsers(companyTitle);
    }

    @PostMapping("/users/add")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDTO userDTO, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if (errorMap != null) {
            return errorMap;
        }
        userService.addUser(userDTO);
        return response(HttpStatus.CREATED, "User has been added");
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id,
                                        @Valid @RequestBody UserDTO userDTO, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if (errorMap != null) {
            return errorMap;
        }
        return new ResponseEntity<>(userService.updateUser(id, userDTO), HttpStatus.OK);

    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<HttpResponse> deleteUser(@PathVariable ("id") Long id) {
        userService.deleteUser(id);
        return response(HttpStatus.OK, "User with ID: " + id + " has been deleted");
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, message), httpStatus);
    }
}
