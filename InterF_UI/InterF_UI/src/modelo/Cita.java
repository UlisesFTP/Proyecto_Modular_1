package modelo;

import java.util.Date;
import java.sql.Time;
import java.time.LocalDate;

public class Cita {

    private int id;
    private LocalDate fecha;
    private Time hora;
    private String cliente;
    private String servicio;
    private String barbero;
    private boolean cumplida;
    private boolean cancelda;

    public Cita(int id, LocalDate fecha, Time hora, String cliente, String servicio, String barbero, boolean cumplida, boolean cancelda) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.cliente = cliente;
        this.servicio = servicio;
        this.barbero = barbero;
        this.cumplida = cumplida;
        this.cancelda = cancelda;
    }

    public Cita(LocalDate fecha, Time hora, String cliente, String servicio, String barbero, boolean cumplida, boolean cancelda) {
        this.fecha = fecha;
        this.hora = hora;
        this.cliente = cliente;
        this.servicio = servicio;
        this.barbero = barbero;
        this.cumplida = cumplida;
        this.cancelda = cancelda;
    }

    public Cita() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getBarbero() {
        return barbero;
    }

    public void setBarbero(String barbero) {
        this.barbero = barbero;
    }

    public boolean isCumplida() {
        return cumplida;
    }

    public void setCumplida(boolean cumplida) {
        this.cumplida = cumplida;
    }

    public boolean isCancelda() {
        return cancelda;
    }

    public void setCancelda(boolean cancelda) {
        this.cancelda = cancelda;
    }

    @Override
    public String toString() {
        return "Cita{" + "id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", cliente=" + cliente + ", servicio=" + servicio + ", barbero=" + barbero + ", cumplida=" + cumplida + ", cancelda=" + cancelda + '}';
    }

}
