/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.dao;

import com.udea.persistencia.Clima;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
/**
 *
 * @author FABIAN
 */
public class ClimaDAO {    
    public void ingresarRegistro  (Clima c)
    {
        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            sf = HibernateUtil.getSessionFactory();
            session=sf.openSession();
            tx = session.beginTransaction();
            session.save(c);
            tx.commit();
            session.close();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException("No se pudo guardar el producto");
        }
        
    }
    public String consultarRegistro (int codigo)
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Clima c = (Clima)session.get(Clima.class, codigo);
        session.close();
        if (c!=null) {
            return "La ciudad correspondiente al codigo: " +c.getCodigo()
                    + " es " +c.getCiudad()
                    +"  su clima actual es " + c.getClima()
                    + " su temperatura en grados centigrados es "
                    + c.getCentigrados()+"°"+ " en fahrenheit "+c.getFahrenheit()+"°"
                    + ", sus porcentajes de nubosidad,"+ " humedad y la probabilidad de precipitación son: "
                    + c.getNubosidad()+", "
                    + c.getPorcentajeHumedad() +", "
                    + c.getProbPrecipitacion() + " respectivamente."
                    + " La predicción para los proximos 3 dias es: "
                    +c.getPrediccion();                      
            
        } else {
            return "La ciudad con código " + c.getCodigo() +" no existe o no ha sido registrada";
        }
    }
    public List <Clima> verRegistro(){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Query query = session.createQuery("from Clima");
        List<Clima> lista  = query.list();
        
        return lista;    
    }    
    
   public String consultarCiudad (String ciudad)
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Query query = session.createQuery("from Clima where ciudad='"+ciudad+"'");
        Query query2 = session.createQuery("from Clima");        
        List<Clima> lista  = query.list();
        List<Clima> lista2  = query2.list();
        if (lista!=null) {
            for (Clima clima : lista2) {
                if (clima.getCiudad().equals(ciudad)) {
                    return "Para la ciudad:  "+ciudad+ " se reportan los siguientes estados de clima para los proximos 3 dias: "
                            +clima.getPrediccion();
                }
            }
            return "La ciudad no existe o no ha sido registrada";
        } 
        else 
        {
            return "Lista vacia"; 
        }        
    }
    
}

