package br.com.trier.projpessoal.surveillance.resources;

import br.com.trier.projpessoal.surveillance.domain.Alarm;
import br.com.trier.projpessoal.surveillance.services.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alarms")
public class AlarmResource {

    private final AlarmService alarmService;

    @Autowired
    public AlarmResource(AlarmService alarmService) {
        this.alarmService = alarmService;
    }

    @PostMapping
    public Alarm insertAlarm(@RequestBody Alarm alarm) {
        return alarmService.insert(alarm);
    }

    @GetMapping
    public List<Alarm> getAllAlarms() {
        return alarmService.listAll();
    }

    @GetMapping("/{id}")
    public Alarm getAlarmById(@PathVariable Integer id) {
        return alarmService.findById(id);
    }

    @PutMapping("/{id}")
    public Alarm updateAlarm(@PathVariable Integer id, @RequestBody Alarm alarm) {
        alarm.setId(id);
        return alarmService.update(alarm);
    }

    @DeleteMapping("/{id}")
    public void deleteAlarm(@PathVariable Integer id) {
        alarmService.delete(id);
    }
}
