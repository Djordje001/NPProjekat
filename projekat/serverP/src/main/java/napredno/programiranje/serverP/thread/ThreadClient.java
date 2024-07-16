/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.serverP.thread;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


import org.zajednickiP.transfer.Request;
import org.zajednickiP.transfer.Response;
import org.zajednickiP.transfer.util.Operation;
import org.zajednickiP.transfer.util.ResponseStatus;
import org.serverP.controller.ServerController;
import org.zajednickiP.domain.Administrator;
import org.zajednickiP.domain.Knjiga;
import org.zajednickiP.domain.Kupac;
import org.zajednickiP.domain.Porudzbina;
import org.zajednickiP.domain.Proizvod;
import org.serverP.controller.*;



/**
 *
 * @author Lenovo
 */
public class ThreadClient extends Thread {

    private Socket socket;

    ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request request = (Request) in.readObject();
                Response response = handleRequest(request);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request request) {
        Response response = new Response(null, null, ResponseStatus.Success);
        try {
            switch (request.getOperation()) {
            case Operation.LOGIN:
                Administrator administrator = (Administrator) request.getData();
                Administrator ulogovani = ServerController.getInstance().login(administrator);
                response.setData(ulogovani);
                break;
                
                
            case Operation.ADD_KUPAC:
                ServerController.getInstance().addKupac((Kupac) request.getData());
                break;
            case Operation.ADD_PROIZVOD:
                ServerController.getInstance().addProizvod((Proizvod) request.getData());
                break;
            case Operation.ADD_PORUDZBINA:
                ServerController.getInstance().addPorudzbina((Porudzbina) request.getData());
                break;
            
      
                
            case Operation.DELETE_PROIZVOD:
                ServerController.getInstance().deleteProizvod((Proizvod) request.getData());
                break;
            case Operation.DELETE_PORUDZBINA:
                ServerController.getInstance().deletePorudzbina((Porudzbina) request.getData());
                break;
            
                
          
            case Operation.UPDATE_PROIZVOD:
                ServerController.getInstance().updateProizvod((Proizvod) request.getData());
                break;
            case Operation.UPDATE_PORUDZBINA:
            	Porudzbina p=(Porudzbina)request.getData();
            	System.out.println(p.getStavkePorudzbine());
                ServerController.getInstance().updatePorudzbina((Porudzbina) request.getData());
                break;

                
                
            case Operation.FIND_ALL_PROIZVOD:
                response.setData(ServerController.getInstance().findAllProizvod((Proizvod) request.getData()));
                break;
            case Operation.FIND_ALL_PORUDZBINA:
            	Porudzbina porudzbina=(Porudzbina) request.getData();
                response.setData(ServerController.getInstance().findAllPorudzbina(porudzbina));
                break;    
            case Operation.FIND_ALL_STAVKA_PORUDZBINE:
                response.setData(ServerController.getInstance().findAllStavkaPorudzbine((Porudzbina) request.getData()));
                break;
            case Operation.FIND_ALL_AUTOR:
                response.setData(ServerController.getInstance().findAllAutor((Knjiga) request.getData()));
                break;

         
            case Operation.GET_ALL_ADMINISTRATOR:
                response.setData(ServerController.getInstance().getAllAdministrator());
                break;   
            case Operation.GET_ALL_KUPAC:
                response.setData(ServerController.getInstance().getAllKupac());
                break;
            case Operation.GET_ALL_PISAC:
                response.setData(ServerController.getInstance().getAllPisac());
                break;
            case Operation.GET_ALL_PROIZVOD:
                response.setData(ServerController.getInstance().getAllProizvod());
                break;
            case Operation.GET_ALL_PORUDZBINA:
                response.setData(ServerController.getInstance().getAllPorudzbina());
                break;
         
            
            
                default:
                    return null;
            }
        } catch (Exception e) {
            response.setResponseStatus(ResponseStatus.Error);
            response.setException(e);
        }
        return response;
    }

}
