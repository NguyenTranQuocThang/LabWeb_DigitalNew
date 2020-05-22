/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import GetValueFromConfigfile.GetValue;
import Model.Digital;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author thang
 */
public class DB {

    DBContext dbc;
    GetValue gv = new GetValue();
    public DB() {
        dbc = new DBContext();
    }

    public int countPostById(int id) throws Exception {
        Connection cnn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Digital digital = new Digital();
        try {
            String sql = "Select count (*) from digital where id = ?";
            cnn = dbc.getConnection();
            statement = cnn.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {                
                return rs.getInt(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            dbc.closeConnection(rs, statement, cnn);
        }
        return -1;
    }

    public Digital getPost(int id) throws Exception {
        Connection cnn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Digital digital = new Digital();
        try {
            String sql = "select * from digital\n"
                    + "where id = ?";
            cnn = dbc.getConnection();
            statement = cnn.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                digital.setId(rs.getInt("id"));
                digital.setTitle(rs.getString("title"));
                digital.setImage(gv.getValue("image") +"/"+rs.getString("image"));
                digital.setAuthor(rs.getString("author"));
                digital.setShortDes(rs.getString("shortDes"));
                digital.setDescription(rs.getString("description"));
                digital.setTimePost(rs.getTimestamp("timePost"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            dbc.closeConnection(rs, statement, cnn);
        }

        return digital;
    }

    public Digital getPost() throws Exception {
        Connection cnn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Digital digital = new Digital();
        try {

            String sql = "select * from digital\n"
                    + " where timePost = ( select MAX(timePost) from digital );";
            cnn = dbc.getConnection();
            statement = cnn.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                digital.setId(rs.getInt("id"));
                digital.setTitle(rs.getString("title"));
                digital.setImage(gv.getValue("image") +"/"+rs.getString("image"));
                digital.setAuthor(rs.getString("author"));
                digital.setShortDes(rs.getString("shortDes"));
                digital.setDescription(rs.getString("description"));
                digital.setTimePost(rs.getTimestamp("timePost"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            dbc.closeConnection(rs, statement, cnn);
        }
        return digital;
    }

    public ArrayList<Digital> getTop5Post() throws Exception {
        Connection cnn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        ArrayList<Digital> digitalList = new ArrayList<>();

        String sql = "select top 5 * from digital where timePost not in(select max(timepost) from digital)order by timePost desc";
        try {
            cnn = dbc.getConnection();
            statement = cnn.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                Digital digital = new Digital();
                digital.setId(rs.getInt("id"));
                digital.setTitle(rs.getString("title"));
                digital.setImage(gv.getValue("image") +"/"+rs.getString("image"));
                digital.setAuthor(rs.getString("author"));
                digital.setShortDes(rs.getString("shortDes"));
                digital.setDescription(rs.getString("description"));
                digital.setTimePost(rs.getTimestamp("timePost"));
                digitalList.add(digital);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            dbc.closeConnection(rs, statement, cnn);
        }
        return digitalList;
    }

    public int pageCount(String title) throws Exception {
        Connection cnn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sql = "select count(*) as rownum from digital where title like '%'+?+'%'";
        try {
            cnn = dbc.getConnection();
            statement = cnn.prepareStatement(sql);
            statement.setString(1,  title);
            rs = statement.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            dbc.closeConnection(rs, statement, cnn);
        }
        return -1;
    }

    public ArrayList<Digital> getAllPost(String title, int pageindex, int pagesize) throws Exception {
        Connection cnn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        ArrayList<Digital> digitalList = new ArrayList<>();
        String sqlF = "Select *,ROW_NUMBER() over (ORDER BY ID ASC) as row_num from Digital where title like '%' + ? + '%'";
        String sql = "Select * from ( " + sqlF + ") titlePost where row_num >= (?-1)*?+1 and row_num <= ? * ? ";
        try {
            cnn = dbc.getConnection();
            statement = cnn.prepareStatement(sql);
            statement.setString(1, title);
            statement.setInt(2, pageindex);
            statement.setInt(3, pagesize);
            statement.setInt(4, pageindex);
            statement.setInt(5, pagesize);
            rs = statement.executeQuery();
            while (rs.next()) {
                Digital digital = new Digital();
                digital.setId(rs.getInt("id"));
                digital.setTitle(rs.getString("title"));
                digital.setImage(gv.getValue("image") +"/"+rs.getString("image"));
                digital.setAuthor(rs.getString("author"));
                digital.setShortDes(rs.getString("shortDes"));
                digital.setDescription(rs.getString("description"));
                digital.setTimePost(rs.getTimestamp("timePost"));
                digitalList.add(digital);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            dbc.closeConnection(rs, statement, cnn);
        }
        return digitalList;
    }
}
