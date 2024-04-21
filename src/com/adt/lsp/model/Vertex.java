package com.adt.lsp.model;

import java.util.Objects;

public class Vertex implements Comparable<Vertex> {
    public int id;
    public double x;
    public double y;

    public double d;
    public double h;

    public Vertex parent;

    public Vertex(int id){
        this.id=id;
    }

    public Vertex(Vertex v){
        this.id= v.id;
        this.x=v.x;
        this.y=v.y;
        this.parent=null;
    }


    public Vertex(int id, double x, double y) {
        this.id=id;
        this.x = x;
        this.y = y;
        this.parent=null;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public int getId() {
        return id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex vertex)) return false;
        return id == vertex.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.valueOf("{" + "id="+id+
                ", D="+ d +
                ", H="+ h +
                //", parent="+parent+
                '}');
    }

    @Override
    public int compareTo(Vertex other) {
        if(this.d<other.d){
            return -1;
        }
        else{
            if(this.d==other.d){
                return 0;
            }
            else{
                return 1;
            }
        }
    }

}
