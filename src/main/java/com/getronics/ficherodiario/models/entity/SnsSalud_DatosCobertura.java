package com.getronics.ficherodiario.models.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(schema = "snsalud", name = "datos_cobertura")
public class SnsSalud_DatosCobertura {

	@Id
	@NotNull
	public String cod_usuario_sns;        
	public String cod_usuario_sns_titular;
	public Integer cod_titulo;            
	public Integer cod_titulo_heredable;  
	public Integer cod_aseguradora;       
	public Integer cod_gestora;           
	public Integer cod_colaboradora;      
	public Integer cod_proveedor;         
	public Integer cod_situacion;         
	public String naf;                    
	public Integer flagssocial;           
	public String naf_titular;            
	public String interno;                
	public Integer cod_proveedor_ap;      
	public Integer cod_proveedor_ae;      
	public Integer cod_proveedor_far;     
	public Integer cod_proveedor_urg;
	
	/*Get and Set*/
	
	public String getCod_usuario_sns() {
		return cod_usuario_sns;
	}
	public void setCod_usuario_sns(String cod_usuario_sns) {
		this.cod_usuario_sns = cod_usuario_sns;
	}
	public String getCod_usuario_sns_titular() {
		return cod_usuario_sns_titular;
	}
	public void setCod_usuario_sns_titular(String cod_usuario_sns_titular) {
		this.cod_usuario_sns_titular = cod_usuario_sns_titular;
	}
	public Integer getCod_titulo() {
		return cod_titulo;
	}
	public void setCod_titulo(Integer cod_titulo) {
		this.cod_titulo = cod_titulo;
	}
	public Integer getCod_titulo_heredable() {
		return cod_titulo_heredable;
	}
	public void setCod_titulo_heredable(Integer cod_titulo_heredable) {
		this.cod_titulo_heredable = cod_titulo_heredable;
	}
	public Integer getCod_aseguradora() {
		return cod_aseguradora;
	}
	public void setCod_aseguradora(Integer cod_aseguradora) {
		this.cod_aseguradora = cod_aseguradora;
	}
	public Integer getCod_gestora() {
		return cod_gestora;
	}
	public void setCod_gestora(Integer cod_gestora) {
		this.cod_gestora = cod_gestora;
	}
	public Integer getCod_colaboradora() {
		return cod_colaboradora;
	}
	public void setCod_colaboradora(Integer cod_colaboradora) {
		this.cod_colaboradora = cod_colaboradora;
	}
	public Integer getCod_proveedor() {
		return cod_proveedor;
	}
	public void setCod_proveedor(Integer cod_proveedor) {
		this.cod_proveedor = cod_proveedor;
	}
	public Integer getCod_situacion() {
		return cod_situacion;
	}
	public void setCod_situacion(Integer cod_situacion) {
		this.cod_situacion = cod_situacion;
	}
	public String getNaf() {
		return naf;
	}
	public void setNaf(String naf) {
		this.naf = naf;
	}
	public Integer getFlagssocial() {
		return flagssocial;
	}
	public void setFlagssocial(Integer flagssocial) {
		this.flagssocial = flagssocial;
	}
	public String getNaf_titular() {
		return naf_titular;
	}
	public void setNaf_titular(String naf_titular) {
		this.naf_titular = naf_titular;
	}
	public String getInterno() {
		return interno;
	}
	public void setInterno(String interno) {
		this.interno = interno;
	}
	public Integer getCod_proveedor_ap() {
		return cod_proveedor_ap;
	}
	public void setCod_proveedor_ap(Integer cod_proveedor_ap) {
		this.cod_proveedor_ap = cod_proveedor_ap;
	}
	public Integer getCod_proveedor_ae() {
		return cod_proveedor_ae;
	}
	public void setCod_proveedor_ae(Integer cod_proveedor_ae) {
		this.cod_proveedor_ae = cod_proveedor_ae;
	}
	public Integer getCod_proveedor_far() {
		return cod_proveedor_far;
	}
	public void setCod_proveedor_far(Integer cod_proveedor_far) {
		this.cod_proveedor_far = cod_proveedor_far;
	}
	public Integer getCod_proveedor_urg() {
		return cod_proveedor_urg;
	}
	public void setCod_proveedor_urg(Integer cod_proveedor_urg) {
		this.cod_proveedor_urg = cod_proveedor_urg;
	}     

	

	
}
