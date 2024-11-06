package com.misael.bank.services;

import com.misael.bank.entities.Chave;
import com.misael.bank.entities.Person;
import com.misael.bank.entities.Transferencia;
import com.misael.bank.entities.dto.TransferenciaRequestDto;
import com.misael.bank.entities.dto.TransferenciaResponseDto;
import com.misael.bank.exceptions.UsuarioNaoEncontradoException;
import com.misael.bank.repositories.ChaveRepository;
import com.misael.bank.repositories.PersonRepository;
import com.misael.bank.repositories.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransferenciaService {


    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ChaveRepository chaveRepository;



    public TransferenciaResponseDto saveTransferencia(TransferenciaRequestDto dto){

        Optional<Person> findPersonDepositante = personRepository.findById(dto.depositanteId());
        Optional<Chave> findByChave = chaveRepository.findByChave(dto.chaveRecebedor());

        if(findPersonDepositante.isPresent() && findByChave.isPresent()){
            Person depositante = findPersonDepositante.get();
            Chave chave = findByChave.get();
            //verificar pela chave, qual pessoa está relacionada a.
            Person recebedor =  chave.getPerson(); //READY?


            depositante.setWallet(depositante.getWallet() - dto.valor());
            personRepository.save(depositante);

            recebedor.setWallet(recebedor.getWallet() + dto.valor());
            personRepository.save(recebedor);

            Transferencia transferencia = Transferencia.builder()
                    .valor(dto.valor())
                    .depositante(depositante)
                    .recebedor(recebedor)
                    .localDateTime(LocalDateTime.now())
                    .build();

            transferenciaRepository.save(transferencia);

            TransferenciaResponseDto transferenciaResponseDto = TransferenciaResponseDto.builder()
                    .valor(dto.valor())
                    .nomePagador(depositante.getCompleteName())
                    .nomeRecebedor(recebedor.getCompleteName())
                    .localDateTime(transferencia.getLocalDateTime())
                    .build();

            return transferenciaResponseDto;

        }else{
             throw new UsuarioNaoEncontradoException();
        }
    }




}
