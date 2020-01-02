/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import classes.pagamento;
import classes.produto;
import conexao.Conecta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class DaoPagamento {
       private Connection con;
    private ResultSet rs;
    public int status;
    
     public DaoPagamento(){
       this.con=new Conecta().getConnection(); 
    }
    
    public void cadastrar(pagamento pag){
     try{
        PreparedStatement p=con.prepareStatement
        ("insert into pagamento(Tipopagamento,Datapagamento,Valorecebido,Troco)values(?,?,?,?)");
        p.setString(1,pag.getTipopagamento());
        p.setString(2,pag.getDatapagamento());
        p.setString(3,pag.getValorrecebido());
        p.setString(4,pag.getTroco());
        p.execute();
        p.close();       
        
     }
     catch(SQLException erro){
         throw new RuntimeException(erro);
         
     }       
  
    }
     public void editar(pagamento pag){
     try{
        PreparedStatement p=con.prepareStatement
        ("update pagamento set Tipopagamento=?,Datapagamento=?,Valorecebido=?,troco=? where idpag='"+pag.getIdpagamento()+"';");
        p.setString(1,pag.getTipopagamento());
        p.setString(2,pag.getDatapagamento());
        p.setString(3,pag.getValorrecebido()); 
        p.setString(4,pag.getTroco());
        p.execute();
        p.close();       
        
     }
     catch(SQLException erro){
         throw new RuntimeException(erro);
         
     }       
        
    }
      public void buscarpag(pagamento pag){
    
    try{
       PreparedStatement p=con.prepareStatement
        ("select * from estoque where nome = '"+pag.getIdpagamento()+"';");
        
       rs=p.executeQuery();
       
       if(rs.first()){
     pag.setIdpagamento(rs.getInt("idpag"));
     pag.setTipopagamento(rs.getString("tipopag"));
     pag.setDatapagamento(rs.getString("datapag"));
     pag.setValorrecebido(rs.getString("valoreceb"));
     pag.setTroco(rs.getString("troco"));
       }else{
           JOptionPane.showMessageDialog(null,"Pagamento não encontrado");
       }     
      
       p.close();
    }
    catch(SQLException erro){
        throw new RuntimeException(erro);
    }    
}    
}
