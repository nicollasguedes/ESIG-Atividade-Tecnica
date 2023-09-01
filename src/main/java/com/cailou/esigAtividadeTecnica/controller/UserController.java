package com.cailou.esigAtividadeTecnica.controller;

import com.cailou.esigAtividadeTecnica.dto.user.*;
import com.cailou.esigAtividadeTecnica.model.UserModel;
import com.cailou.esigAtividadeTecnica.service.impl.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController()
@RequestMapping("/user")
public class UserController {
    private final UserServiceImpl userService;


    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @ApiOperation("This method is used to save a user.")
    @PostMapping()
    public ResponseEntity<UserResponseDTO> saveUser(@RequestBody @Valid UserRequestDTO user) {
        UserModel userSaved = this.userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponseDTO.convertToDto(userSaved));
    }

    @ApiOperation("This method is used to list a logged user's data.")
    @GetMapping()
    public ResponseEntity<UserResponseDTO> listUser() {
        var userIdLogged = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserModel user = this.userService.listUser(userIdLogged.toString());
        return ResponseEntity.status(HttpStatus.OK).body(UserResponseDTO.convertToDto(user));
    }

    @ApiOperation("This method is used to update a user password")
    @PatchMapping("update-password/{userId}")
    public ResponseEntity<UserResponseDTO> updatePassword(@PathVariable String userId,
                                                          @RequestBody @Valid PasswordRequestDTO passwordRequestDTO) {
        UserModel user = this.userService.updateUserPassword(userId, passwordRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(UserResponseDTO.convertToDto(user));
    }

    @ApiOperation("This method is used to check a user password")
    @PatchMapping("check-password/{userId}")
    public ResponseEntity<Boolean> updatePassword(@PathVariable String userId,
                                                  @RequestBody @Valid CheckPasswordRequestDTO checkPasswordRequestDTO) {
        boolean equals = this.userService.checkPassword(userId, checkPasswordRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(equals);
    }

    @ApiOperation("This method is used to list a user's data by Id.")
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> listUserById(
            @PathVariable String userId
    ) {
        UserModel user = this.userService.listUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(UserResponseDTO.convertToDto(user));
    }

    @ApiOperation("This method is used to switch the active status of a user.")
    @GetMapping("/switchactivity/{userId}")
    public ResponseEntity<UserResponseDTO> switchActiveUser(
            @PathVariable String userId
    ) {
        UserModel user = this.userService.switchUserActivity(userId);
        return ResponseEntity.status(HttpStatus.OK).body(UserResponseDTO.convertToDto(user));
    }
}