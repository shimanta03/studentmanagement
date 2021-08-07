
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class resultlist {
     public void insertUpdateDeleteResult(char operation, Integer studentid, String name, String trimester, Double cgpa)
    {
        
        Connection con = mysqlconnector.getConnection();
        PreparedStatement pst;
        
        if(operation == 'a')
        {
            try {
                pst = con.prepareStatement("INSERT INTO studentinfo(student_id, name, trimester, cgpa) VALUES (?,?,?,?)");
                pst.setInt(1, studentid);
                pst.setString(2, name);
                pst.setString(3, trimester);
                pst.setDouble(4, cgpa );
              
                
                if(pst.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null,"Result Added");
                }
            
            
            } catch (SQLException ex) {
                Logger.getLogger(resultlist.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         if(operation == 'r')//d for delete
        {
            try {
                pst = con.prepareStatement("DELETE FROM `studentinfo` WHERE `student_id`= ?");
                pst.setInt(1, studentid);
               
                
              
                
                if(pst.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null,"Student Data Deleted");
                }
            
            
            } catch (SQLException ex) {
                Logger.getLogger(resultlist.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    /**
     *
     * @param table
     * @param valuesearch
     */
    public void fillResultJtable(JTable table,String valuesearch){
        
        Connection con = mysqlconnector.getConnection();
        PreparedStatement pst;
        try {
            pst = con.prepareStatement("SELECT * FROM `studentinfo` WHERE CONCAT (`student_id`,`name`,`trimester`,`cgpa`) LIKE ?");
            pst.setString(1,"%"+valuesearch+"%");
            
            ResultSet rst= pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            
            Object[] row;
            while(rst.next()){
                row = new Object[4];
                row[0] = rst.getInt(1);
                row[1] = rst.getString(2);
                row[2] = rst.getString(3);
                row[3] = rst.getDouble(4);
                
                model.addRow(row);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(resultlist.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     }

    

   
    
 
     
