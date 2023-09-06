package com.nicollas.esigAtividadeTecnica.bean;

import com.nicollas.esigAtividadeTecnica.model.PessoaModel;
import com.nicollas.esigAtividadeTecnica.repository.PessoaRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;


@Component
@ViewScoped
@ManagedBean
@Data
@NoArgsConstructor(force = true)
public class PessoaBean implements Serializable {


    private List<PessoaModel> pessoaList;
    @Autowired
    private final PessoaRepository pessoaRepository;


    @PostConstruct
    public void init() {
        pessoaList = pessoaRepository.findAll();
    }
}
