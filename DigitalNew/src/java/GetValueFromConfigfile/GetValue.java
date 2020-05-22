/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GetValueFromConfigfile;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author thang
 */
public class GetValue {

    public String getValue(String txt) {
        try {
            InitialContext init = new InitialContext();
            Context cont = (Context) init.lookup("java:/comp/env");
            return (String) cont.lookup(txt);
        } catch (NamingException ex) {
            System.out.println(ex);
        }
        return null;
    }
}
