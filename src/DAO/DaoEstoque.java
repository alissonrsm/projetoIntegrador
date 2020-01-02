/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import classes.estoque;
import classes.produto;
import conexao.Conecta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DaoEstoque {
    private Connection con;
    private ResultSet rs;
    public int status;
    
     public DaoEstoque(){
       this.con=new Conecta().getConnection(); 
    }
    
    public void cadastrar(estoque esto){
     try{
        PreparedStatement p=con.prepareStatement
        ("insert into estoque(Modelo,Quantidade,Valor)values(?,?,?)");
        p.setString(1,esto.getModelo());
        p.setString(2,esto.getQuantidade());
        p.setString(3,esto.getValor()); 
        p.execute();
        p.close();       
        
     }
     catch(SQLException erro){
         throw new RuntimeException(erro);
         
     }       
  
    }
     public void editar(estoque esto){
     try{
        PreparedStatement p=con.prepareStatement
        ("update estoque set Modelo=?,Quantidade=?,Valor=? where idesto='"+esto.getIdestoque()+"';");
        p.setString(1,esto.getModelo());
        p.setString(2,esto.getQuantidade());
        p.setString(3,esto.getValor()); 
        p.execute();
        p.close();       
        
     }
     catch(SQLException erro){
         throw new RuntimeException(erro);
         
     }       
        
    }
      public void buscarprod(estoque esto){
    
    try{
       PreparedStatement p=con.prepareStatement
        ("select * from estoque where nome = '"+esto.getIdestoque()+"';");
        
       rs=p.executeQuery();
       
       if(rs.first()){
     esto.setIdestoque(rs.getInt("idestoque"));
     esto.setModelo(rs.getString("modelo"));
     esto.setQuantidade(rs.getString("quantidade"));
     esto.setValor(rs.getString("valor"));
       }else{
           JOptionPane.showMessageDialog(null,"Produto não encontrado no estoque");
       }     
      
       p.close();
    }
    catch(SQLException erro){
        throw new RuntimeException(erro);
    }    
}    
}
