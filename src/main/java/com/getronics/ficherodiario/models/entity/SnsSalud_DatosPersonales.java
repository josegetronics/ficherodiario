package com.getronics.ficherodiario.models.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(schema = "snsalud", name = "datos_personales")
public class SnsSalud_DatosPersonales {

	@Id
	@NotNull
	public String cod_usuario_sns;    
	public String cod_pais;           
	public Integer cod_sexo;          
	public Integer cod_comunidad;     
	public String nombre;             
	public String apellido1;          
	public String apellido2;          
	public Date fecha_nac;            
	public String dni_nie;            
	public Integer flaq_dni_duplicado;
	public Integer flag_extranjero;   
	public String pasaporte;          
	public String raiz;               
	public String tarjeta_identidad;  
	public String cod_nacionalidad;
	
	
	/* Get and Set*/
	public String getCod_usuario_sns() {
		return cod_usuario_sns;
	}
	public void setCod_usuario_sns(String cod_usuario_sns) {
		this.cod_usuario_sns = cod_usuario_sns;
	}
	public String getCod_pais() {
		return cod_pais;
	}
	public void setCod_pais(String cod_pais) {
		this.cod_pais = cod_pais;
	}
	public Integer getCod_sexo() {
		return cod_sexo;
	}
	public void setCod_sexo(Integer cod_sexo) {
		this.cod_sexo = cod_sexo;
	}
	public Integer getCod_comunidad() {
		return cod_comunidad;
	}
	public void setCod_comunidad(Integer cod_comunidad) {
		this.cod_comunidad = cod_comunidad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public Date getFecha_nac() {
		return fecha_nac;
	}
	public void setFecha_nac(Date fecha_nac) {
		this.fecha_nac = fecha_nac;
	}
	public String getDni_nie() {
		return dni_nie;
	}
	public void setDni_nie(String dni_nie) {
		this.dni_nie = dni_nie;
	}
	public Integer getFlaq_dni_duplicado() {
		return flaq_dni_duplicado;
	}
	public void setFlaq_dni_duplicado(Integer flaq_dni_duplicado) {
		this.flaq_dni_duplicado = flaq_dni_duplicado;
	}
	public Integer getFlag_extranjero() {
		return flag_extranjero;
	}
	public void setFlag_extranjero(Integer flag_extranjero) {
		this.flag_extranjero = flag_extranjero;
	}
	public String getPasaporte() {
		return pasaporte;
	}
	public void setPasaporte(String pasaporte) {
		this.pasaporte = pasaporte;
	}
	public String getRaiz() {
		return raiz;
	}
	public void setRaiz(String raiz) {
		this.raiz = raiz;
	}
	public String getTarjeta_identidad() {
		return tarjeta_identidad;
	}
	public void setTarjeta_identidad(String tarjeta_identidad) {
		this.tarjeta_identidad = tarjeta_identidad;
	}
	public String getCod_nacionalidad() {
		return cod_nacionalidad;
	}
	public void setCod_nacionalidad(String cod_nacionalidad) {
		this.cod_nacionalidad = cod_nacionalidad;
	}   


	
	
}
