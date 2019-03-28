/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa_transporte;

import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Miguelito
 */
public class Matriz {
    //ATRIBUTOS
    int r, o;
    Grafica grafica;
    int[][] disponibles; //RELLENADA POR EL USUARIO
    int[][] asignados;  //RELLENADA MEDIANTE UN RANDOM
    int[][] maxNecesario;  //RELLENADA POR EL USUARIO
    int[][] Necesario; //CALCULADA 
    //FIN DE ATRIBUTOS
    
    //CONSTRUCTOR    
    public Matriz(int o,int r,Grafica grafica,int[][] disponibles,int[][]maxNecesarios) {
        this.r=r;
        this.o=o;
        this.grafica = grafica;
        this.disponibles = disponibles;
        this.maxNecesario = maxNecesarios;
    }
    //FIN CONSTRUCTOR
    
    //GETTERS Y SETTERS
    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getO() {
        return o;
    }

    public void setO(int o) {
        this.o = o;
    }

    public int[][] getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(int[][] disponibles) {
        this.disponibles = disponibles;
    }

    public int[][] getAsignados() {
        return asignados;
    }

    public void setAsignados(int[][] asignados) {
        this.asignados = asignados;
    }

    public int[][] getMaxNecesario() {
        return maxNecesario;
    }

    public void setMaxNecesario(int[][] maxNecesario) {
        this.maxNecesario = maxNecesario;
    }

    public int[][] getNecesario() {
        return Necesario;
    }

    public void setNecesario(int[][] Necesario) {
        this.Necesario = Necesario;
    }

    public Grafica getGrafica() {
        return grafica;
    }
    //FIN GETTERS Y SETTERS
    
    //METODOS
    //METODO PARA LLENAR LAS MATRICES ASIGNADOS Y NECESARIOS
    public void llenar(){
        System.out.println("Rutas= " + this.r + "\nOrdenes= " + this.o);
        this.asignados = new int[this.o][this.r];
        this.Necesario = new int[this.o][this.r]; 
        int n=0;
        
        //LLENADO DE MATRIZ ASIGNADOS MEDIANTE UN RANDOM
        for (int i = 0; i < this.o; i++) {
            for (int j = 0; j < this.r; j++) {
                n = (int) (Math.random() * this.maxNecesario[i][j]);
                
                if (n <= disponibles[0][j]) {
                    this.asignados[i][j] = n;
                    this.disponibles[0][j] = this.disponibles[0][j] - n;
                } else {
                    this.asignados[i][j] = 0;
                }
                
            }
        }
        System.out.println(" ");

        //LLENADO DE MATRIZ NECESARIO MEDIANTE UN CALCULO
        for (int i = 0; i < this.o; i++) {
            for (int j = 0; j < this.r; j++) 
            {
                this.Necesario[i][j] = this.maxNecesario[i][j] - this.asignados[i][j];
            }
        }
        Interfaz2 interfaz = new Interfaz2(this.o,this.r,this.asignados,this.Necesario,this,this.grafica);
        interfaz.setVisible(true);
    }

    //METODO QUE REVISA SI LOS RECURSOS PUEDEN SER ASIGNADOS A UN PROCESO
    private boolean check(int i) {
        for (int j = 0; j < this.r; j++) {
            if (this.disponibles[0][j] < this.Necesario[i][j]) {
                return false;
            }
        }
        return true;
    }
    
    //METODO PARA CALCULAR EL ESTADO SEGURO
    public void estadoSeguro(){
        boolean done[] = new boolean[this.o];
        int j = 0;

        while (j < this.o) {
            boolean asignado = false;
            for (int i = 0; i < this.o; i++) {
                if (!done[i] && this.check(i)) {
                    for (int k = 0; k < this.r; k++) {
                        this.disponibles[0][k] = this.disponibles[0][k] - this.Necesario[i][k] + this.maxNecesario[i][k];
                    }
                    System.out.println("Proceso asignado: " + i);
                    this.grafica.resultado(i);
                    asignado = done[i] = true;
                    j++;
                }
            }
            if (!asignado) {
                break;  //EL PROCESO NO ESTA ASIGNADO
            }
        }

        if (j == this.o){
        //SI TODOS LOS PROCESOS ESTAN ASIGNADOS
            System.out.println("Asignado de forma segura");
            JOptionPane.showMessageDialog(null, "Asignado de forma segura");
        } else {
        //SI NO TODOS LOS PROCESOS ESTAN ASIGNADOS
            System.out.println("No se pueden asginar todos los procesos de forma segura");
            JOptionPane.showMessageDialog(null,"No se pueden asignar todos los procesos de forma segura");
            this.grafica.resultadoMalo();
        }
    }
    //FIN METODOS
}
