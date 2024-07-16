/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zajednickiP.transfer.util;

/**
 *
 * @author Lenovo
 */
public interface Operation {

    public static final int LOGIN = 0;

    public static final int ADD_KUPAC = 1;
    public static final int ADD_PROIZVOD=2;
    public static final int ADD_PORUDZBINA = 3;
    
    public static final int DELETE_PROIZVOD = 4;
    public static final int DELETE_PORUDZBINA = 5;
   
    public static final int UPDATE_PROIZVOD = 6;
    public static final int UPDATE_PORUDZBINA = 7;
    
    public static final int FIND_ALL_PROIZVOD=8;
    public static final int FIND_ALL_PORUDZBINA=9;
    public static final int FIND_ALL_STAVKA_PORUDZBINE = 10; //pomocna
    public static final int FIND_ALL_AUTOR=11;//pomocna
    
    public static final int GET_ALL_ADMINISTRATOR=12;//pomocna
    public static final int GET_ALL_KUPAC = 13; //pomocna
    public static final int GET_ALL_PISAC = 14; //pomocna
    public static final int GET_ALL_PROIZVOD = 15; //pomocna
    public static final int GET_ALL_PORUDZBINA = 16; //pomocna



}