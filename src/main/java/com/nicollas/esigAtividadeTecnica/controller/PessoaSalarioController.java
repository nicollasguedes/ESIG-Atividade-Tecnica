package com.nicollas.esigAtividadeTecnica.controller;


import com.nicollas.esigAtividadeTecnica.dto.pessoaSalario.PessoaSalarioRequestDTO;
import com.nicollas.esigAtividadeTecnica.dto.pessoaSalario.PessoaSalarioResponseDTO;
import com.nicollas.esigAtividadeTecnica.model.PessoaSalarioModel;
import com.nicollas.esigAtividadeTecnica.service.impl.PessoaSalarioServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@CrossOrigin(origins = "http://localhost:3000")
@RestController()
@RequestMapping("/pessoa-salario")
public class PessoaSalarioController {
    private final PessoaSalarioServiceImpl pessoasalarioService;


    @Autowired
    public PessoaSalarioController(PessoaSalarioServiceImpl pessoasalarioService) {
        this.pessoasalarioService = pessoasalarioService;
    }

    @ApiOperation("save pessoa data using a login.")
    @PostMapping("save/{pessoaId}")
    public ResponseEntity<PessoaSalarioResponseDTO> savePessoaByLogin(
            @PathVariable BigInteger pessoaId, @RequestBody PessoaSalarioRequestDTO pessoaSalarioRequestDTO
    ) {
        PessoaSalarioModel pessoa = this.pessoasalarioService.savePessoaSalarioByPessoaId(pessoaId, pessoaSalarioRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(PessoaSalarioResponseDTO.convertToDto(pessoa));
    }

    @ApiOperation("update pessoa by Id.")
    @PatchMapping("update/{pessoaId}")
    public ResponseEntity<PessoaSalarioResponseDTO> updatePessoaByPessoaId(
            @PathVariable BigInteger pessoaId, @RequestBody PessoaSalarioRequestDTO pessoaSalarioRequestDTO
    ) {
        PessoaSalarioModel pessoa = this.pessoasalarioService.updatePessoaSalarioByPessoaId(pessoaId, pessoaSalarioRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(PessoaSalarioResponseDTO.convertToDto(pessoa));
    }


    @ApiOperation("get pessoa by Id.")
    @GetMapping("/{pessoaId}")
    public ResponseEntity<PessoaSalarioResponseDTO> listPessoaById(
            @PathVariable BigInteger pessoaId
    ) {
        PessoaSalarioModel pessoa = this.pessoasalarioService.listPessoaSalarioByPessoaId(pessoaId);
        return ResponseEntity.status(HttpStatus.OK).body(PessoaSalarioResponseDTO.convertToDto(pessoa));
    }

    @ApiOperation("delete pessoa by Id.")
    @DeleteMapping("/delete/{pessoaId}")
    public ResponseEntity<Boolean> deletePessoaByPessoaId(
            @PathVariable BigInteger pessoaId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(this.pessoasalarioService.deletePessoaSalarioByPessoaId(pessoaId));
    }

}