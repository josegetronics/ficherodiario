package com.getronics.ficherodiario.models.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(schema = "snsalud", name = "usuarios")
public class SnsSalud_Usuarios {
	
	
	@Id
	@NotNull
	public String cod_usuario_sns;   
	public Integer cod_estado;
	public Integer cod_prestacion_servicio;
	public Integer cod_origen_alta;
	public Date fecha_alta_reg;
	public Date fecha_ult_actualizacion;
	public String protegida;
	
	/* Get and Set*/
	
	public String getCod_usuario_sns() {
		return cod_usuario_sns;
	}
	public void setCod_usuario_sns(String cod_usuario_sns) {
		this.cod_usuario_sns = cod_usuario_sns;
	}
	public Integer getCod_estado() {
		return cod_estado;
	}
	public void setCod_estado(Integer cod_estado) {
		this.cod_estado = cod_estado;
	}
	public Integer getCod_prestacion_servicio() {
		return cod_prestacion_servicio;
	}
	public void setCod_prestacion_servicio(Integer cod_prestacion_servicio) {
		this.cod_prestacion_servicio = cod_prestacion_servicio;
	}
	public Integer getCod_origen_alta() {
		return cod_origen_alta;
	}
	public void setCod_origen_alta(Integer cod_origen_alta) {
		this.cod_origen_alta = cod_origen_alta;
	}
	public Date getFecha_alta_reg() {
		return fecha_alta_reg;
	}
	public void setFecha_alta_reg(Date fecha_alta_reg) {
		this.fecha_alta_reg = fecha_alta_reg;
	}
	public Date getFecha_ult_actualizacion() {
		return fecha_ult_actualizacion;
	}
	public void setFecha_ult_actualizacion(Date fecha_ult_actualizacion) {
		this.fecha_ult_actualizacion = fecha_ult_actualizacion;
	}
	public String getProtegida() {
		return protegida;
	}
	public void setProtegida(String protegida) {
		this.protegida = protegida;
	} 
	
	
	

}
