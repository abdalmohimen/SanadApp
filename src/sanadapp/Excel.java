/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanadapp;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import static sanadapp.Operation.conn_local;

/**
 *
 * @author abd almohimen
 */
public class Excel {
    
    public static void print_as_excel(String query,String name) throws SQLException
    {
        Workbook workbook = new HSSFWorkbook();
               
               
                Sheet sheet = workbook.createSheet("Sanad");
               
                
                Statement st=conn_local.createStatement();
                ResultSet rs=st.executeQuery(query);
                int i=1;
                ResultSetMetaData rsmd=rs.getMetaData();
                System.out.println(rsmd.getColumnCount());
                Cell [] cell =new Cell[rsmd.getColumnCount()];
                for(int j=0;j<rsmd.getColumnCount();j++)
                    {
                        cell[j]=sheet.createRow(0).createCell(j);
                    }
              
                for(int j=0;j<rsmd.getColumnCount();j++)
                    {
                        cell[j].setCellValue(ClassesNames.getArabicCol(rsmd.getColumnLabel(j+1)));
                    }
                
                while(rs.next())
                {
                    
                    for(int j=0;j<rsmd.getColumnCount();j++)
                    {
                        cell[j]=sheet.createRow(i).createCell(j);
                    }
                    for(int j=0;j<rsmd.getColumnCount();j++)
                    {
                        cell[j].setCellValue(rs.getString(j+1));
                    }
                    i++;
                }
               
                try {   
                       
                        name+=LocalDate.now().toString();
                        name+=".xls";
                        name.trim();
                        String desktop=System.getProperty("user.home");
                        File f=new File(desktop+"/Desktop", name);
                        FileOutputStream output = new FileOutputStream(f);
                        workbook.write(output);
                        output.close();
                } catch(Exception e) {
                        e.printStackTrace();
                }
        }
    }
    
