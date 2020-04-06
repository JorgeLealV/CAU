/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.lib01;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author cgarias
 */
public class DTODocumento implements Serializable {
    
    /**
	 * Propiedad que realiza la serialización de la CLASE en tiempo de ejecución.
	 */
	private static final long serialVersionUID = -6663667588650255135L;
	private Integer idDocumento;
    private Integer idTipodoc;
    private String tipodocdesc;
    private String docNombreOrig;
    private String docNombre;
    private String docRuta;
    private String sysStatus;
    private Date sysFechaAlta;
    private Date sysFechaMovimiento;
    private Integer sysIdUsuario;
    private boolean nuevo;
    private boolean modificado;
    private boolean borrado;
    protected boolean cargado;
    protected byte[] contenido;
    private String observaciones;
    private String obserCriterios;
    

    public Integer getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public Integer getIdTipodoc() {
        return idTipodoc;
    }

    public void setIdTipodoc(Integer idTipodoc) {
        this.idTipodoc = idTipodoc;
    }

    public String getDocNombreOrig() {
        return docNombreOrig;
    }

    public void setDocNombreOrig(String docNombreOrig) {
        this.docNombreOrig = docNombreOrig;
    }

    public String getDocNombre() {
        return docNombre;
    }

    public void setDocNombre(String docNombre) {
        this.docNombre = docNombre;
    }

    public String getDocRuta() {
        return docRuta;
    }

    public void setDocRuta(String docRuta) {
        this.docRuta = docRuta;
    }

    public String getSysStatus() {
        return sysStatus;
    }

    public void setSysStatus(String sysStatus) {
        this.sysStatus = sysStatus;
    }

    public Date getSysFechaAlta() {
        return sysFechaAlta;
    }

    public void setSysFechaAlta(Date sysFechaAlta) {
        this.sysFechaAlta = sysFechaAlta;
    }

    public Date getSysFechaMovimiento() {
        return sysFechaMovimiento;
    }

    public void setSysFechaMovimiento(Date sysFechaMovimiento) {
        this.sysFechaMovimiento = sysFechaMovimiento;
    }

    public Integer getSysIdUsuario() {
        return sysIdUsuario;
    }

    public void setSysIdUsuario(Integer sysIdUsuario) {
        this.sysIdUsuario = sysIdUsuario;
    }

    public boolean isNuevo() {
        return nuevo;
    }

    public void setNuevo(boolean nuevo) {
        this.nuevo = nuevo;
    }

    public boolean isModificado() {
        return modificado;
    }

    public void setModificado(boolean modificado) {
        this.modificado = modificado;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public boolean isCargado() {
        return cargado;
    }

    public void setCargado(boolean cargado) {
        this.cargado = cargado;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getObserCriterios() {
        return obserCriterios;
    }

    public void setObserCriterios(String obserCriterios) {
        this.obserCriterios = obserCriterios;
    }

    /**
     * @return the tipodocdesc
     */
    public String getTipodocdesc() {
        return tipodocdesc;
    }

    /**
     * @param tipodocdesc the tipodocdesc to set
     */
    public void setTipodocdesc(String tipodocdesc) {
        this.tipodocdesc = tipodocdesc;
    }
}
