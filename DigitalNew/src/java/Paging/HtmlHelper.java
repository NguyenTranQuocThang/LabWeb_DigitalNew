/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Paging;

/**
 *
 * @author thang
 */
public class HtmlHelper {
    public static String hyperlink(String label){
        //return "<a href='search?page="+label+"' >"+label+"</a>";
        return "<input type ='submit' name='page' value='"+label+"' />";
    }
    public static String pager(int pageindex, int pagecount){
        // show all page
        String result ="";
        for (int i = 1; i <= pagecount; i++) {
            if(i == pageindex){
                result += i;
            }else{
                result += hyperlink(""+(i));
            }
        }
        return result;
    }
}
