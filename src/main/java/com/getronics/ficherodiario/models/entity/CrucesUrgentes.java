package com.getronics.ficherodiario.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(schema="dbpro12d", name = "Z_INSS_MOV_SEP_25")
public class CrucesUrgentes implements Serializable {
	
	@Id
	private String cod_tipo_asegurado;      
	
	private String tipo_movimiento;         
	
	private String ipf;            
	
	private String dni_nie;                     
	
	private String pasaporte;                   
	
	private String naf;                  
	
	private String naf_sec1;                    
	
	private String naf_sec2;                    
	
	private String naf_sec3;                   
	
	private String naf_sec4;                    
	
	private String naf_sec5;                    
	
	private String naf_sec6;                    
	
	private String naf_sec7;                    
	
	private String naf_sec8;                    
	
	private String naf_sec9;                    
	
	private String indicativo_nombre;           
	
	private String apellidos_nombre;          
	
	private String apellido1;           
	
	private String apellido2;                  
	
	private String nombre;                  
	
	private String nacionalidad;                
	
//	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fecha_nacimiento;              
	
	private int sexo;             
	
	private String indicativo_domicilio;      
	
	private String domicilio;
	
	private String tipo_aseguramiento;          
	
	private String cod_indicador_de_farmacia;   
	
	private String cod_subindicador_de_farmacia;
	
	private String cod_situacion;
	
	private String fecha_efecto_situacion;      
	
	private int cod_tipo_beneficiario;	
	
	private String ipf_titular;
	
	private String naf_titular;
	
	private int numero_secuencia;
	
	private String fecha_nacimiento_raw;
	
	private String ipf_anterior;
	
	private String cod_usuario_sns;
	
	private String codigo_badas;
	
	private String motivo_baja;
	
	private String protegida;
	
	private String indicador_doble_cobertura;
	
	private String cip_mutualista;
	
	private String cip_mutualista_titular;      
	
	private String prestadora_privada;
	
	private String indicador_convenio_rural;

	
	
	/*Get and Set */
	public String getCod_tipo_asegurado() {
		return cod_tipo_asegurado;
	}

	public void setCod_tipo_asegurado(String cod_tipo_asegurado) {
		this.cod_tipo_asegurado = cod_tipo_asegurado;
	}

	public String getTipo_movimiento() {
		return tipo_movimiento;
	}

	public void setTipo_movimiento(String tipo_movimiento) {
		this.tipo_movimiento = tipo_movimiento;
	}

	public String getIpf() {
		return ipf;
	}

	public void setIpf(String ipf) {
		this.ipf = ipf;
	}

	public String getDni_nie() {
		return dni_nie;
	}

	public void setDni_nie(String dni_nie) {
		this.dni_nie = dni_nie;
	}

	public String getPasaporte() {
		return pasaporte;
	}

	public void setPasaporte(String pasaporte) {
		this.pasaporte = pasaporte;
	}

	public String getNaf() {
		return naf;
	}

	public void setNaf(String naf) {
		this.naf = naf;
	}

	public String getNaf_sec1() {
		return naf_sec1;
	}

	public void setNaf_sec1(String naf_sec1) {
		this.naf_sec1 = naf_sec1;
	}

	public String getNaf_sec2() {
		return naf_sec2;
	}

	public void setNaf_sec2(String naf_sec2) {
		this.naf_sec2 = naf_sec2;
	}

	public String getNaf_sec3() {
		return naf_sec3;
	}

	public void setNaf_sec3(String naf_sec3) {
		this.naf_sec3 = naf_sec3;
	}

	public String getNaf_sec4() {
		return naf_sec4;
	}

	public void setNaf_sec4(String naf_sec4) {
		this.naf_sec4 = naf_sec4;
	}

	public String getNaf_sec5() {
		return naf_sec5;
	}

	public void setNaf_sec5(String naf_sec5) {
		this.naf_sec5 = naf_sec5;
	}

	public String getNaf_sec6() {
		return naf_sec6;
	}

	public void setNaf_sec6(String naf_sec6) {
		this.naf_sec6 = naf_sec6;
	}

	public String getNaf_sec7() {
		return naf_sec7;
	}

	public void setNaf_sec7(String naf_sec7) {
		this.naf_sec7 = naf_sec7;
	}

	public String getNaf_sec8() {
		return naf_sec8;
	}

	public void setNaf_sec8(String naf_sec8) {
		this.naf_sec8 = naf_sec8;
	}

	public String getNaf_sec9() {
		return naf_sec9;
	}

	public void setNaf_sec9(String naf_sec9) {
		this.naf_sec9 = naf_sec9;
	}

	public String getIndicativo_nombre() {
		return indicativo_nombre;
	}

	public void setIndicativo_nombre(String indicativo_nombre) {
		this.indicativo_nombre = indicativo_nombre;
	}

	public String getApellidos_nombre() {
		return apellidos_nombre;
	}

	public void setApellidos_nombre(String apellidos_nombre) {
		this.apellidos_nombre = apellidos_nombre;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public int getSexo() {
		return sexo;
	}

	public void setSexo(int sexo) {
		this.sexo = sexo;
	}

	public String getIndicativo_domicilio() {
		return indicativo_domicilio;
	}

	public void setIndicativo_domicilio(String indicativo_domicilio) {
		this.indicativo_domicilio = indicativo_domicilio;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTipo_aseguramiento() {
		return tipo_aseguramiento;
	}

	public void setTipo_aseguramiento(String tipo_aseguramiento) {
		this.tipo_aseguramiento = tipo_aseguramiento;
	}

	public String getCod_indicador_de_farmacia() {
		return cod_indicador_de_farmacia;
	}

	public void setCod_indicador_de_farmacia(String cod_indicador_de_farmacia) {
		this.cod_indicador_de_farmacia = cod_indicador_de_farmacia;
	}

	public String getCod_subindicador_de_farmacia() {
		return cod_subindicador_de_farmacia;
	}

	public void setCod_subindicador_de_farmacia(String cod_subindicador_de_farmacia) {
		this.cod_subindicador_de_farmacia = cod_subindicador_de_farmacia;
	}

	public String getCod_situacion() {
		return cod_situacion;
	}

	public void setCod_situacion(String cod_situacion) {
		this.cod_situacion = cod_situacion;
	}

	public String getFecha_efecto_situacion() {
		return fecha_efecto_situacion;
	}

	public void setFecha_efecto_situacion(String fecha_efecto_situacion) {
		this.fecha_efecto_situacion = fecha_efecto_situacion;
	}

	public int getCod_tipo_beneficiario() {
		return cod_tipo_beneficiario;
	}

	public void setCod_tipo_beneficiario(int cod_tipo_beneficiario) {
		this.cod_tipo_beneficiario = cod_tipo_beneficiario;
	}

	public String getIpf_titular() {
		return ipf_titular;
	}

	public void setIpf_titular(String ipf_titular) {
		this.ipf_titular = ipf_titular;
	}

	public String getNaf_titular() {
		return naf_titular;
	}

	public void setNaf_titular(String naf_titular) {
		this.naf_titular = naf_titular;
	}

	public int getNumero_secuencia() {
		return numero_secuencia;
	}

	public void setNumero_secuencia(int numero_secuencia) {
		this.numero_secuencia = numero_secuencia;
	}

	public String getFecha_nacimiento_raw() {
		return fecha_nacimiento_raw;
	}

	public void setFecha_nacimiento_raw(String fecha_nacimiento_raw) {
		this.fecha_nacimiento_raw = fecha_nacimiento_raw;
	}

	public String getIpf_anterior() {
		return ipf_anterior;
	}

	public void setIpf_anterior(String ipf_anterior) {
		this.ipf_anterior = ipf_anterior;
	}

	public String getCod_usuario_sns() {
		return cod_usuario_sns;
	}

	public void setCod_usuario_sns(String cod_usuario_sns) {
		this.cod_usuario_sns = cod_usuario_sns;
	}

	public String getCodigo_badas() {
		return codigo_badas;
	}

	public void setCodigo_badas(String codigo_badas) {
		this.codigo_badas = codigo_badas;
	}

	public String getMotivo_baja() {
		return motivo_baja;
	}

	public void setMotivo_baja(String motivo_baja) {
		this.motivo_baja = motivo_baja;
	}

	public String getProtegida() {
		return protegida;
	}

	public void setProtegida(String protegida) {
		this.protegida = protegida;
	}

	public String getIndicador_doble_cobertura() {
		return indicador_doble_cobertura;
	}

	public void setIndicador_doble_cobertura(String indicador_doble_cobertura) {
		this.indicador_doble_cobertura = indicador_doble_cobertura;
	}

	public String getCip_mutualista() {
		return cip_mutualista;
	}

	public void setCip_mutualista(String cip_mutualista) {
		this.cip_mutualista = cip_mutualista;
	}

	public String getCip_mutualista_titular() {
		return cip_mutualista_titular;
	}

	public void setCip_mutualista_titular(String cip_mutualista_titular) {
		this.cip_mutualista_titular = cip_mutualista_titular;
	}

	public String getPrestadora_privada() {
		return prestadora_privada;
	}

	public void setPrestadora_privada(String prestadora_privada) {
		this.prestadora_privada = prestadora_privada;
	}

	public String getIndicador_convenio_rural() {
		return indicador_convenio_rural;
	}

	public void setIndicador_convenio_rural(String indicador_convenio_rural) {
		this.indicador_convenio_rural = indicador_convenio_rural;
	}    

	
	private static final long serialVersionUID = 1L;

	
	
	
}
