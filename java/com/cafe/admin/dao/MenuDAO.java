package com.cafe.admin.dao;
import java.sql.*;
import java.util.*;
import com.cafe.admin.model.MenuItem;

public class MenuDAO {
    private String url="jdbc:sqlite:cafe.db";

    public MenuDAO(){
        try(Connection c=DriverManager.getConnection(url)){
            c.createStatement().execute("CREATE TABLE IF NOT EXISTS menu(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, price REAL)");
        }catch(Exception e){e.printStackTrace();}
    }

    public List<MenuItem> getAll(){
        List<MenuItem> list=new ArrayList<>();
        try(Connection c=DriverManager.getConnection(url)){
            ResultSet rs=c.createStatement().executeQuery("SELECT * FROM menu");
            while(rs.next()){
                list.add(new MenuItem(rs.getInt("id"),rs.getString("name"),rs.getDouble("price")));
            }
        }catch(Exception e){e.printStackTrace();}
        return list;
    }

    public void add(String name,double price){
        try(Connection c=DriverManager.getConnection(url)){
            PreparedStatement p=c.prepareStatement("INSERT INTO menu(name,price) VALUES(?,?)");
            p.setString(1,name); p.setDouble(2,price);
            p.executeUpdate();
        }catch(Exception e){e.printStackTrace();}
    }
}
