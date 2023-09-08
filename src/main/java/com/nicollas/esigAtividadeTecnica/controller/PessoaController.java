package com.nicollas.esigAtividadeTecnica.controller;


import com.nicollas.esigAtividadeTecnica.dto.pessoa.PessoaRequestDTO;
import com.nicollas.esigAtividadeTecnica.dto.pessoa.PessoaResponseDTO;
import com.nicollas.esigAtividadeTecnica.model.PessoaModel;
import com.nicollas.esigAtividadeTecnica.service.impl.PessoaServiceImpl;
import com.nicollas.esigAtividadeTecnica.service.impl.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.text.ParseException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController()
@RequestMapping("/pessoa")
public class PessoaController {
    private final PessoaServiceImpl pessoaService;
    private final UserServiceImpl userService;


    @Autowired
    public PessoaController(PessoaServiceImpl pessoaService, UserServiceImpl userService) {
        this.pessoaService = pessoaService;
        this.userService = userService;
    }

    @ApiOperation("save pessoa data using a userId.")
    @PostMapping("save/{userId}")
    public ResponseEntity<PessoaResponseDTO> savePessoaByUserId(
            @PathVariable String userId, @RequestBody PessoaRequestDTO pessoaRequestDTO
    ) throws ParseException {
        var user = userService.listUser(userId);
        PessoaModel pessoa = this.pessoaService.savePessoaByUser(pessoaRequestDTO, user);

        return ResponseEntity.status(HttpStatus.OK).body(PessoaResponseDTO.convertToDto(pessoa));
    }

    @ApiOperation("update pessoa by Id.")
    @PatchMapping("update/{pessoaId}")
    public ResponseEntity<PessoaResponseDTO> updatePessoaByPessoaId(
            @PathVariable BigInteger pessoaId, @RequestBody PessoaRequestDTO pessoaRequestDTO
    ) throws ParseException {
        PessoaModel pessoa = this.pessoaService.updatePessoaByPessoaId(pessoaId, pessoaRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(PessoaResponseDTO.convertToDto(pessoa));
    }


    @ApiOperation("get pessoa by Id.")
    @GetMapping("/{pessoaId}")
    public ResponseEntity<PessoaResponseDTO> listPessoaById(
            @PathVariable BigInteger pessoaId
    ) {
        PessoaModel pessoa = this.pessoaService.listPessoa(pessoaId);
        return ResponseEntity.status(HttpStatus.OK).body(PessoaResponseDTO.convertToDto(pessoa));
    }

    @ApiOperation("delete pessoa by Id.")
    @DeleteMapping("/delete/{pessoaId}")
    public ResponseEntity<Boolean> deletePessoaByPessoaId(
            @PathVariable BigInteger pessoaId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(this.pessoaService.deletePessoaByPessoaId(pessoaId));
    }

}