package com.cailou.esigAtividadeTecnica.controller;


import com.cailou.esigAtividadeTecnica.dto.pessoa.PessoaRequestDTO;
import com.cailou.esigAtividadeTecnica.dto.pessoa.PessoaResponseDTO;
import com.cailou.esigAtividadeTecnica.model.PessoaModel;
import com.cailou.esigAtividadeTecnica.service.impl.PessoaServiceImpl;
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


    @Autowired
    public PessoaController(PessoaServiceImpl pessoaService) {
        this.pessoaService = pessoaService;
    }

    @ApiOperation("save pessoa data using a login.")
    @PostMapping("save/{login}")
    public ResponseEntity<PessoaResponseDTO> savePessoaByLogin(
            @PathVariable String login, @RequestBody PessoaRequestDTO pessoaRequestDTO
    ) throws ParseException {
        PessoaModel pessoa = this.pessoaService.savePessoaByLogin(pessoaRequestDTO, login);
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