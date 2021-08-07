
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class studentlist {
    
    public void insertUpdateDeleteStudent(char operation, Integer studentid, String name, String phone, String department)
    {
        
        Connection con = mysqlconnector.getConnection();
        PreparedStatement ps;
        
        if(operation == 'i')
        {
            try {
                ps = con.prepareStatement("INSERT INTO studentlist(student_id,name,phone_no,department) VALUES (?,?,?,?)");
                ps.setInt(1, studentid);
                ps.setString(2, name);
                ps.setString(3, phone);
                ps.setString(4, department);
              
                
                if(ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null,"New Student Added");
                }
            
            
            } catch (SQLException ex) {
                Logger.getLogger(studentlist.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(operation == 'e')//e for edit
        {
            try {
                ps = con.prepareStatement("UPDATE studentlist SET `name`=?,`phone_no`=?,`department`=? WHERE `student_id`=?");
                ps.setInt(1, studentid);
                ps.setString(2, name);
                ps.setString(3, phone);
                ps.setString(4, department);
                
              
                
                if(ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null,"Student Data Updated");
                }
            
            
            } catch (SQLException ex) {
                Logger.getLogger(studentlist.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
         if(operation == 'd')//d for delete
        {
            try {
                ps = con.prepareStatement("DELETE FROM `studentlist` WHERE `student_id`=?");
                ps.setInt(1, studentid);
               
                
              
                
                if(ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null,"Student Data Deleted");
                }
            
            
            } catch (SQLException ex) {
                Logger.getLogger(studentlist.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    void insertUpdateDeleteStudent(char c, Object object, int studentid, String name, String phone, String department) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void fillStudentJtable(JTable table,String valueToSearch){
        
        Connection con = mysqlconnector.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM `studentlist` WHERE CONCAT(`student_id`,`name`,`phone_no`,`department`)LIKE ?");
            ps.setString(1,"%"+valueToSearch+"%");
            
            ResultSet rs= ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            
            Object[] row;
            while(rs.next()){
                row = new Object[4];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                
                model.addRow(row);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(studentlist.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
