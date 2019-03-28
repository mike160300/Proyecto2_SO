package empresa_transporte;

import java.awt.Image;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Miguelito
 */
public class Insertar extends javax.swing.JFrame {
    //ATRIBUTOS
    DefaultTableModel tabla;
    DefaultTableModel tabla2;
    int ordenes, rutas;
    int[][] disponibles;
    int[][] maxNecesario;
    boolean error = false , seguir = true;
    //FIN ATRIBUTOS
    
    //CONSTRUCTOR
    public Insertar(int rutas, int ordenes) {
        //INSTANCIO
        initComponents();
        this.tabla = (DefaultTableModel) this.jTable1.getModel();
        this.tabla2 = (DefaultTableModel) this.jTable2.getModel();
        this.ordenes = ordenes;
        this.rutas = rutas;
        this.disponibles = new int[1][this.rutas];
        this.maxNecesario = new int[this.ordenes][this.rutas];
        Object[] vector = new Object[this.ordenes];
        
        //ASIGNAR LAS FILAS A LAS TABLAS 
        for (int i = 0; i < this.ordenes ; i++) {
            this.tabla.addRow(vector);
        }
        this.tabla2.addRow(vector);
        
        //ASIGNAR LAS COLUMNAS A LAS TABLAS
        for (int j = 0; j < this.rutas ; j++) {
            this.tabla.addColumn("Recurso " + (j));
            this.tabla2.addColumn("Recurso " + (j));
        }
    }
    //FIN CONSTRUCTOR
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setText("Insertar valores matriz de recursos:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 57, 307, 44));

        jButton3.setText("Calcular secuencia");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(621, 523, 141, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 298, 552, 188));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 159, 552, 58));

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setText("Matriz Maximo Necesarios");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 254, 183, 26));

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setText("Matriz Disponibles");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 155, 26));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //BOTON CALCULAR SECUENCIA
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //LLENADO DE LA MATRIZ DISPONIBLE
        for (int j = 0; j < this.rutas ; j++) {
            Object o = this.tabla2.getValueAt(0,j);
            String x = String.valueOf(o);
            int n = 0;
            try{
                n =  Integer.parseInt(x);
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Dato introducido en los camiones disponibles no es un numero");
                this.error = true;
                this.seguir = false;
            }
            
            if (!this.error) {
                if (n <= 0) {
                    this.seguir = false;
                    JOptionPane.showMessageDialog(null, "Los camiones disponibles no pueden ser menores o iguales a 0");
                } else {
                    this.disponibles[0][j] = n;  //MATRIZ MÁXIMA
                }
            }
        }  
        
        //LLENADO DE LA MATRIZ MAXNECESARIO
        for (int i = 0; i < this.ordenes; i++) {
            for (int j = 0; j < this.rutas; j++) {
                Object o2 = this.tabla.getValueAt(i,j);
                String x2 = String.valueOf(o2);
                int n2 =  0;
                try{
                    n2 =  Integer.parseInt(x2);
                }catch(NumberFormatException e){
                    this.error = true;
                    this.seguir = false;
                    JOptionPane.showMessageDialog(null, "Dato introducido en los camiones maximos no es un numero");
                }
                
                if(!this.error){
                    if (n2 < 0 || n2 > this.disponibles[0][j]) {
                        this.seguir = false;
                        JOptionPane.showMessageDialog(null, "Los camiones maximos no pueden ser menores a 0 ni mayores a los disponibles");
                    } else {
                        this.maxNecesario[i][j] = n2;
                    }
                }
            }        
        }
        
        
        if(this.seguir){
        //SI TODO VA BIEN SE PROCEDE A LLENAR
            Grafica grafica = new Grafica();
            Matriz matriz = new Matriz(this.ordenes,this.rutas,grafica,this.disponibles,this.maxNecesario);
            matriz.llenar();
        } else {
        //SI NO SE REINICIA EL ATRIBUTO ERROR Y EL SEGUIR PARA QUE EL USUARIO CAMBIE O ARREGLE SU ERROR
            this.error = false;
            this.seguir = true;
        }
    }//GEN-LAST:event_jButton3ActionPerformed
    //FIN BOTON
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
