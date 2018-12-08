/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.service;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import com.udea.dao.ClimaDAO;
import com.udea.persistencia.Clima;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author FABIAN
 */
@WebService(serviceName = "ClimaWS")
@XmlSeeAlso({Clima.class})
public class ClimaWS {
    
    @WebMethod(operationName = "ingresarRegistro")
    public String ingresarRegistro(@WebParam(name = "ciudad") String ciudad,
            @WebParam(name = "clima") String clima,
            @WebParam(name = "centigrados") int centigrados,
            @WebParam(name = "fahrenheit") int fahrenheit,
            @WebParam(name = "nubosidad") int nubosidad,
            @WebParam(name = "porcentaje_humedad") int porcentaje_humedad,
            @WebParam(name = "prob_precipitacion") int prob_precipitacion,
            @WebParam(name = "prediccion") String prediccion) {
        //TODO write your implementation code here:
        Clima c = new Clima(ciudad, clima, centigrados, fahrenheit, nubosidad, porcentaje_humedad,prob_precipitacion,prediccion);
        ClimaDAO climadao = new ClimaDAO();
        climadao.ingresarRegistro(c);
        return "Registro ingresado";
    }
    
    @WebMethod(operationName = "buscarRegistro")
    public String buscarRegistro(@WebParam(name = "codigo") int codigo) {
        //TODO write your implementation code here:
        ClimaDAO climaDAO = new ClimaDAO();
        return climaDAO.consultarRegistro(codigo);       
   
    }
    @WebMethod(operationName = "consultarRegistro")
    public List<Clima> consultarRegistro() {
        ClimaDAO climaDAO = new ClimaDAO();
        List<Clima> listaClima = climaDAO.verRegistro();
        return listaClima;
    }
    /**
     * Web service operation
     */
    @WebMethod(operationName = "buscarCiudad")
    public String buscarCiudad(@WebParam(name = "ciudad") String ciudad) {
        //TODO write your implementation code here:
        ClimaDAO climaDAO = new ClimaDAO();
        return climaDAO.consultarCiudad(ciudad); 
    }
}
