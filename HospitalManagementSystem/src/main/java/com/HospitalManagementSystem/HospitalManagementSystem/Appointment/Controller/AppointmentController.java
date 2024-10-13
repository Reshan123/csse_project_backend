package com.HospitalManagementSystem.HospitalManagementSystem.Appointment.Controller;

import com.HospitalManagementSystem.HospitalManagementSystem.Appointment.Model.Appointment;
import com.HospitalManagementSystem.HospitalManagementSystem.Appointment.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/getAll")
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/getById/{aptNo}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable String aptNo) {
        Optional<Appointment> appointment = appointmentService.getAppointmentById(aptNo);
        return appointment.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PostMapping("/addAppointment")
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return appointmentService.addAppointment(appointment);
    }

    @PutMapping("/updateAppointment/{aptNo}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable String aptNo, @RequestBody Appointment appointmentDetails) {
        Appointment updatedAppointment = appointmentService.updateAppointment(aptNo, appointmentDetails);
        if (updatedAppointment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedAppointment);
    }

    @DeleteMapping("/delete/{aptNo}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable String aptNo) {
        appointmentService.deleteAppointment(aptNo);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
