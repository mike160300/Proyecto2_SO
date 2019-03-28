/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa_transporte;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Miguelito
 */
public class Interfaz2 extends javax.swing.JFrame {
    //ATRIBUTOS
    int o,r;
    int[][] asignados;
    int[][] Necesario;
    private DefaultTableModel Tabla;
    private DefaultTableModel Tabla2;
    Matriz matriz;
    Grafica grafica;
    //FIN ATRIBUTOS
    
    //CONSTRUCTOR
    public Interfaz2(int o,int r,int[][] asignados,int[][] Necesario,Matriz matriz,Grafica grafica) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.o = o;
        this.r = r;
        this.asignados = asignados;
        this.Necesario = Necesario;
        this.matriz = matriz;
        this.grafica = grafica;
        Tabla = (DefaultTableModel) jTable1.getModel();
        Tabla2 = (DefaultTableModel) jTable2.getModel();
        Object[] array2 = new Object[this.o];

        //ASIGNAR LAS COLUMNAS A LAS TABLAS
        for (int i = 0; i < this.r; i++) {

            Tabla.addColumn("Recurso " + i);
            Tabla2.addColumn("Recurso " + i);
        }

        //ASIGNAR LAS FILAS A LAS TABLAS
        for (int j = 0; j < this.o; j++) {
            Tabla.addRow(array2);
            Tabla2.addRow(array2);
        }
        
        //METER LOS DATOS DE LA MATRIZ NECESARIO A LA TABLA
        for (int i = 0; i < this.o; i++) {
            for (int j = 0; j < this.r; j++) {
                Object x = (Integer) this.Necesario[i][j];
                this.jTable1.getModel().setValueAt(x, i, j);
            }
        }

        //METER LOS DATOS DE LA MATRIZ NECESARIO A LA TABLA
        for (int i = 0; i < this.o; i++) {
            for (int j = 0; j < this.r; j++) {
                Object x = (Integer) this.asignados[i][j];
                this.jTable2.getModel().setValueAt(x, i, j);
            }
        }
    }
    //FIN CONSTRUCTOR
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 552, 188));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 390, 552, 188));

        jButton1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton1.setText("Listo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 610, 120, 30));

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setText("Matriz Necesarios");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 350, 155, 26));

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel4.setText("Matriz Asignados");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 155, 26));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setText("Matrices faltantes");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 307, 44));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //BOTON LISTO
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.matriz.estadoSeguro();
        this.grafica.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed
    //FIN BOTON LISTO

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
