/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import classes.os;
import classes.produto;
import conexao.Conecta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DaoOS {
     private Connection con;
    private ResultSet rs;
    public int status;
    
     public DaoOS(){
       this.con=new Conecta().getConnection(); 
    }
    
    public void cadastrar(os os){
     try{
        PreparedStatement p=con.prepareStatement
        ("insert into os(Nomecliente,Dataos,Descricao,Status)values(?,?,?,?)");
        p.setString(1,os.getNomecliente());
        p.setString(2,os.getDataos());
        p.setString(3,os.getDescricao()); 
        p.setString(4,os.getStatus());
        p.execute();
        p.close();       
        
     }
     catch(SQLException erro){
         throw new RuntimeException(erro);
         
     }       
  
    }
     public void editar(os os){
     try{
        PreparedStatement p=con.prepareStatement
        ("update os set Nomecliente=?,Dataos=?,Descricao=?,Status=? where idos='"+os.getIdos()+"';");
        p.setString(1,os.getNomecliente());
        p.setString(2,os.getDataos());
        p.setString(3,os.getDescricao()); 
        p.setString(4,os.getStatus());
        p.execute();
        p.close();       
        
     }
     catch(SQLException erro){
         throw new RuntimeException(erro);
         
     }       
        
    }
      public void buscaros(os os){
    
    try{
       PreparedStatement p=con.prepareStatement
        ("select * from os where nome = '"+os.getNomecliente()+"';");
        
       rs=p.executeQuery();
       
       if(rs.first()){
           os.setIdos(rs.getInt("idos"));
           os.setNomecliente(rs.getString("nome"));
           os.setDataos(rs.getString("data"));
           os.setDescricao(rs.getString("descricao"));
           os.setStatus(rs.getString("status"));
       }else{
           JOptionPane.showMessageDialog(null,"Ordem de Serviço não encontrada!");
       }     
      
       p.close();
    }
    catch(SQLException erro){
        throw new RuntimeException(erro);
    }    
}
}
