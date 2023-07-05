package br.com.trier.projpessoal.surveillance.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.trier.projpessoal.surveillance.domain.Alarm;
import br.com.trier.projpessoal.surveillance.repositories.AlarmRepository;
import br.com.trier.projpessoal.surveillance.services.AlarmService;
import br.com.trier.projpessoal.surveillance.services.exceptions.ObjectNotFound;

@Service
public class AlarmServiceImpl implements AlarmService {

    private final AlarmRepository repository;

    @Autowired
    public AlarmServiceImpl(AlarmRepository repository) {
        this.repository = repository;
    }

    @Override
    public Alarm insert(Alarm alarm) {
        return repository.save(alarm);
    }

    @Override
    public List<Alarm> listAll() {
        return repository.findAll();
    }

    @Override
    public Alarm findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFound("Alarme não encontrado com o ID: " + id));
    }

    @Override
    public Alarm update(Alarm alarm) {
        if (!repository.existsById(alarm.getId())) {
            throw new ObjectNotFound("Alarm não encontrado com o ID: " + alarm.getId());
        }
        return repository.save(alarm);
    }

    @Override
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new ObjectNotFound("Alarm não encontrado com o ID: " + id);
        }
        repository.deleteById(id);
    }


    @Override
    public String getProductName(Integer alarmId) {
        Alarm alarm = findById(alarmId);
        return alarm.getProduct().getName();
    }

}
