package com.nicollas.esigAtividadeTecnica.controller;

import com.nicollas.esigAtividadeTecnica.dto.pessoa.PessoaResponseDTO;
import com.nicollas.esigAtividadeTecnica.dto.user.CheckPasswordRequestDTO;
import com.nicollas.esigAtividadeTecnica.dto.user.PasswordRequestDTO;
import com.nicollas.esigAtividadeTecnica.dto.user.UserRequestDTO;
import com.nicollas.esigAtividadeTecnica.dto.user.UserResponseDTO;
import com.nicollas.esigAtividadeTecnica.model.UserModel;
import com.nicollas.esigAtividadeTecnica.service.impl.PessoaServiceImpl;
import com.nicollas.esigAtividadeTecnica.service.impl.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.text.ParseException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController()
@RequestMapping("/user")
public class UserController {
    private final UserServiceImpl userService;
    private final PessoaServiceImpl pessoaService;


    @Autowired
    public UserController(UserServiceImpl userService, PessoaServiceImpl pessoaService) {
        this.userService = userService;
        this.pessoaService = pessoaService;
    }


    @ApiOperation("save user.")
    @PostMapping()
    public ResponseEntity<UserResponseDTO> saveUser(@RequestBody @Valid UserRequestDTO user) throws ParseException {
        UserModel userSaved = this.userService.saveUser(user);
        UserResponseDTO userResponseDTO = UserResponseDTO.convertToDto(userSaved);
        PessoaResponseDTO pessoaResponseDTO = PessoaResponseDTO.convertToDto(pessoaService.listPessoaByLogin(userSaved.getLogin()));
        userResponseDTO.setPessoa(pessoaResponseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
    }

    @ApiOperation("list a logged user's data.")
    @GetMapping()
    public ResponseEntity<UserResponseDTO> listUser() {
        var userIdLogged = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserModel user = this.userService.listUser(new BigInteger(userIdLogged.toString()));
        UserResponseDTO userResponseDTO = UserResponseDTO.convertToDto(user);
        PessoaResponseDTO pessoaResponseDTO = PessoaResponseDTO.convertToDto(pessoaService.listPessoaByLogin(user.getLogin()));
        userResponseDTO.setPessoa(pessoaResponseDTO);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDTO);
    }

    @ApiOperation("update user password")
    @PatchMapping("update-password/{userId}")
    public ResponseEntity<UserResponseDTO> updatePassword(@PathVariable BigInteger userId,
                                                          @RequestBody @Valid PasswordRequestDTO passwordRequestDTO) {
        UserModel user = this.userService.updateUserPassword(userId, passwordRequestDTO);
        UserResponseDTO userResponseDTO = UserResponseDTO.convertToDto(user);
        PessoaResponseDTO pessoaResponseDTO = PessoaResponseDTO.convertToDto(pessoaService.listPessoaByLogin(user.getLogin()));
        userResponseDTO.setPessoa(pessoaResponseDTO);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDTO);
    }

    @ApiOperation("check user password")
    @GetMapping("check-password/{userId}")
    public ResponseEntity<Boolean> updatePassword(@PathVariable BigInteger userId,
                                                  @RequestBody @Valid CheckPasswordRequestDTO checkPasswordRequestDTO) {
        boolean equals = this.userService.checkPassword(userId, checkPasswordRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(equals);
    }

    @ApiOperation("get user by Id.")
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> listUserById(
            @PathVariable BigInteger userId
    ) {
        UserModel user = this.userService.listUser(userId);
        UserResponseDTO userResponseDTO = UserResponseDTO.convertToDto(user);
        PessoaResponseDTO pessoaResponseDTO = PessoaResponseDTO.convertToDto(pessoaService.listPessoaByLogin(user.getLogin()));
        userResponseDTO.setPessoa(pessoaResponseDTO);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDTO);
    }

    @ApiOperation("switch user active flag.")
    @GetMapping("/switchactivity/{userId}")
    public ResponseEntity<UserResponseDTO> switchActiveUser(
            @PathVariable BigInteger userId
    ) {
        UserModel user = this.userService.switchUserActive(userId);
        UserResponseDTO userResponseDTO = UserResponseDTO.convertToDto(user);
        PessoaResponseDTO pessoaResponseDTO = PessoaResponseDTO.convertToDto(pessoaService.listPessoaByLogin(user.getLogin()));
        userResponseDTO.setPessoa(pessoaResponseDTO);
        return ResponseEntity.status(HttpStatus.OK).body(UserResponseDTO.convertToDto(user));
    }
    @ApiOperation("delete user.")
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Boolean> deleteUser(
            @PathVariable BigInteger userId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(userId));
    }
}