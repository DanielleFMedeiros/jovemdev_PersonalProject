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

    private final AlarmRepository alarmRepository;

    @Autowired
    public AlarmServiceImpl(AlarmRepository alarmRepository) {
        this.alarmRepository = alarmRepository;
    }

    @Override
    public Alarm insert(Alarm alarm) {
        return alarmRepository.save(alarm);
    }

    @Override
    public List<Alarm> listAll() {
        return alarmRepository.findAll();
    }

    @Override
    public Alarm findById(Integer id) {
        return alarmRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFound("Alarme não encontrado com o ID: " + id));
    }

    @Override
    public Alarm update(Alarm alarm) {
        if (!alarmRepository.existsById(alarm.getId())) {
            throw new ObjectNotFound("Alarm não encontrado com o ID: " + alarm.getId());
        }
        return alarmRepository.save(alarm);
    }

    @Override
    public void delete(Integer id) {
        if (!alarmRepository.existsById(id)) {
            throw new ObjectNotFound("Alarm não encontrado com o ID: " + id);
        }
        alarmRepository.deleteById(id);
    }


    @Override
    public String getProductName(Integer alarmId) {
        Alarm alarm = findById(alarmId);
        return alarm.getProduct().getName();
    }

}
