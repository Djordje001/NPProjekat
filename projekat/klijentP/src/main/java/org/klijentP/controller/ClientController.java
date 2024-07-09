/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.klijentP.controller;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;

import org.klijentP.session.Session;
import org.zajednickiP.domain.Administrator;
import org.zajednickiP.domain.Autor;
import org.zajednickiP.domain.Knjiga;
import org.zajednickiP.domain.Kupac;
import org.zajednickiP.domain.Pisac;
import org.zajednickiP.domain.Porudzbina;
import org.zajednickiP.domain.Proizvod;
import org.zajednickiP.domain.StavkaPorudzbine;
import org.zajednickiP.transfer.Request;
import org.zajednickiP.transfer.Response;
import org.zajednickiP.transfer.util.Operation;
import org.zajednickiP.transfer.util.ResponseStatus;



/**
 *
 * @author Lenovo
 */
public class ClientController {

    private static ClientController instance;

    private ClientController() {
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public Administrator login(Administrator administrator) throws Exception {
        return (Administrator) sendRequest(Operation.LOGIN, administrator);
    }
    
    

    public void addKupac(Kupac kupac) throws Exception {
        sendRequest(Operation.ADD_KUPAC, kupac);
    }
    public void addProizvod(Proizvod proizvod) throws Exception {
        sendRequest(Operation.ADD_PROIZVOD, proizvod);
    }

    public void addPorudzbina(Porudzbina porudzbina) throws Exception {
        sendRequest(Operation.ADD_PORUDZBINA, porudzbina);
    }

    

    public void deleteProizvod(Proizvod p) throws Exception {
        sendRequest(Operation.DELETE_PROIZVOD, p);
    }
    public void deletePorudzbina(Porudzbina porudzbina) throws Exception {
        sendRequest(Operation.DELETE_PORUDZBINA, porudzbina);
    }
    
    
   
    public void updateProizvod(Proizvod proizvod) throws Exception {
        sendRequest(Operation.UPDATE_PROIZVOD, proizvod);
    }
    public void updatePorudzbina(Porudzbina porudzbina) throws Exception {
        sendRequest(Operation.UPDATE_PORUDZBINA, porudzbina);
    }
    
    
    
    public ArrayList<Proizvod> findAllProizvod(Proizvod p) throws Exception {
        return (ArrayList<Proizvod>) sendRequest(Operation.FIND_ALL_PROIZVOD, p);
    }
    public ArrayList<Porudzbina> findAllPorudzbina(Porudzbina p) throws Exception {
        return (ArrayList<Porudzbina>) sendRequest(Operation.FIND_ALL_PORUDZBINA, p);
    }
    public ArrayList<StavkaPorudzbine> findAllStavkaPorudzbine(Porudzbina p) throws Exception {
        return (ArrayList<StavkaPorudzbine>) sendRequest(Operation.FIND_ALL_STAVKA_PORUDZBINE, p);
    }
    public ArrayList<Autor> findAllAutor(Knjiga k) throws Exception {
        return (ArrayList<Autor>) sendRequest(Operation.FIND_ALL_AUTOR, k);
    }
    

    
    public ArrayList<Administrator> getAllAdministrator() throws Exception {
        return (ArrayList<Administrator>) sendRequest(Operation.GET_ALL_ADMINISTRATOR, null);
    }
    public ArrayList<Kupac> getAllKupac() throws Exception {
        return (ArrayList<Kupac>) sendRequest(Operation.GET_ALL_KUPAC, null);
    }
    public ArrayList<Pisac> getAllPisac() throws Exception {
        return (ArrayList<Pisac>) sendRequest(Operation.GET_ALL_PISAC, null);
    }
    public ArrayList<Proizvod> getAllProizvod() throws Exception {
        return (ArrayList<Proizvod>) sendRequest(Operation.GET_ALL_PROIZVOD, null);
    }
    public ArrayList<Porudzbina> getAllPorudzbina() throws Exception {
        return (ArrayList<Porudzbina>) sendRequest(Operation.GET_ALL_PORUDZBINA, null);
    }


  
    private Object sendRequest(int operation, Object data) throws Exception {
        Request request = new Request(operation, data);

        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(request);

        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response response = (Response) in.readObject();

        if (response.getResponseStatus().equals(ResponseStatus.Error)) {
            throw response.getException();
        } else {
            return response.getData();
        }
    }
}
