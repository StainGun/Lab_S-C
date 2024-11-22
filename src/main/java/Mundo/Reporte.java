package Mundo;

import java.util.concurrent.atomic.AtomicInteger;

public class Reporte {
    private static final AtomicInteger idGenerator = new AtomicInteger(1);
    private int id;
    private int totalPacientes;
    private int pacientesConCita;
    private int doctoresDiferentes;
    private int totalCitas;
    private int citasReservadas;
    private int especialidadesDiferentes;

    public Reporte(int totalPacientes, int pacientesConCita, int doctoresDiferentes, int totalCitas, int citasReservadas, int especialidadesDiferentes) {
        this.id = idGenerator.getAndIncrement();
        this.totalPacientes = totalPacientes;
        this.pacientesConCita = pacientesConCita;
        this.doctoresDiferentes = doctoresDiferentes;
        this.totalCitas = totalCitas;
        this.citasReservadas = citasReservadas;
        this.especialidadesDiferentes = especialidadesDiferentes;
    }

    public int getId() {
        return id;
    }

    public int getTotalPacientes() {
        return totalPacientes;
    }

    public int getPacientesConCita() {
        return pacientesConCita;
    }

    public int getDoctoresDiferentes() {
        return doctoresDiferentes;
    }

    public int getTotalCitas() {
        return totalCitas;
    }

    public int getCitasReservadas() {
        return citasReservadas;
    }

    public int getEspecialidadesDiferentes() {
        return especialidadesDiferentes;
    }
}
