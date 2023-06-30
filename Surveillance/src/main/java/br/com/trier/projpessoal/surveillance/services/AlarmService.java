package br.com.trier.projpessoal.surveillance.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.trier.projpessoal.surveillance.domain.Alarm;

@Service
public interface AlarmService {

	Alarm insert(Alarm alarm);

	List<Alarm> listAll();

	Alarm findById(Integer id);

	Alarm update(Alarm alarm);

	void delete(Integer id);

	String getProductName(Integer alarmId);


}
