package affichage;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class Tableau extends DefaultTableModel {
	
		public static Boolean aValue;
		private int row;
		private int col;
		private boolean ImInLoop=false;
          public Tableau() {
        	  	col=this.getColumnCount();
        		row=this.getRowCount();
        		
          }
          

          @Override
          public Class<?> getColumnClass(int columnIndex) {
            Class cl = String.class;
            switch (columnIndex) {
              case 0:
                cl = Boolean.class;
                break;
              case 2:
                cl = JScrollPane.class;
                break;
            }
            return cl;
          }

          //Tableau non modifiable 
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false except 1st one
		       return column==0;
		    }
		    
		    //Value of checkbox
		    @Override
		    public void setValueAt(Object aValue, int row, int column) {
		      if (aValue instanceof Boolean && column == 0) {
		    	  if (!ImInLoop) {
                      ImInLoop = true;
                      Boolean bol = (Boolean) aValue;
                      super.setValueAt(aValue, row, column);
                      for (int i = 0; i < this.getRowCount(); i++) {
                        //  if (i != rowIndex) {
                              super.setValueAt(false, i, column);
                          }
                      }
                      ImInLoop = false;
                  }
                         
		     super.setValueAt(aValue, row, column);
		        System.out.println(aValue);
		        Vector rowData = (Vector)getDataVector().get(row);
		        rowData.set(0, aValue);
		        fireTableCellUpdated(row, column);
		        Tableau.aValue=(Boolean) aValue;
		    }		    
	  };


