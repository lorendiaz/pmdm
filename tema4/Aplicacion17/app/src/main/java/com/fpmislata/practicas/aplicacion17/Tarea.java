package com.fpmislata.practicas.aplicacion17;

/**
 * Created by loren on 30/09/15.
 */
public class Tarea{


        private String nombre;
        private String hora;
        private int categoria;

        public Tarea(String nombre, String hora, int categoria){
            this.nombre = nombre;
            this.hora = hora;
            this.categoria = categoria;
        }

        public void setNombre(String nombre){
            this.nombre = nombre;
        }

        public void setHora(String hora){
            this.hora = hora;
        }

        public void setCategoria(int categoria){
            this.categoria=categoria;
        }

        public String getNombre(){
            return nombre;
        }
        public String getHora(){
            return hora;
        }
        public int getCategoria(){
            return categoria;
        }
}