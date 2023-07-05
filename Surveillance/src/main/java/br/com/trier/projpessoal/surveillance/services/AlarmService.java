package br.com.trier.projpessoal.surveillance.services;

import java.util.List;

import br.com.trier.projpessoal.surveillance.domain.Alarm;

public interface AlarmService {

	Alarm insert(Alarm alarm);

	List<Alarm> listAll();

	Alarm findById(Integer id);

	Alarm update(Alarm alarm);

	void delete(Integer id);

	String getProductName(Integer alarmId);


}
