/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dti.internalballistics.cad;

/**
 *
 * @author amabird
 */
public class Point {
    
    private double x;
    private double y;
    private String instruction;
    private double c1x;
    private double c1y;
    private double c2x;
    private double c2y;
    private double rx;
    private double ry;
    private int xrotate;
    private int largearcflag;
    private int sweepflag;
    private double bulge;
    private double arcCx;
    private double arcCy;
    private double arcR;

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
    }

    public double getC1x() {
        return c1x;
    }

    public void setC1x(double c1x) {
        this.c1x = c1x;
    }

    public double getC1y() {
        return c1y;
    }

    public void setC1y(double c1y) {
        this.c1y = c1y;
    }

    public double getC2x() {
        return c2x;
    }

    public void setC2x(double c2x) {
        this.c2x = c2x;
    }

    public double getC2y() {
        return c2y;
    }

    public void setC2y(double c2y) {
        this.c2y = c2y;
    }

    public double getRx() {
        return rx;
    }

    public void setRx(double rx) {
        this.rx = rx;
    }

    public double getRy() {
        return ry;
    }

    public void setRy(double ry) {
        this.ry = ry;
    }

    public int getXrotate() {
        return xrotate;
    }

    public void setXrotate(int xrotate) {
        this.xrotate = xrotate;
    }

    public int getLargearcflag() {
        return largearcflag;
    }

    public void setLargearcflag(int largearcflag) {
        this.largearcflag = largearcflag;
    }

    public int getSweepflag() {
        return sweepflag;
    }

    public void setSweepflag(int sweepflag) {
        this.sweepflag = sweepflag;
    }

    public double getBulge() {
        return bulge;
    }

    public void setBulge(double bulge) {
        this.bulge = bulge;
    }

    public double getArcCx() {
        return arcCx;
    }

    public void setArcCx(double arcCx) {
        this.arcCx = arcCx;
    }

    public double getArcCy() {
        return arcCy;
    }

    public void setArcCy(double arcCy) {
        this.arcCy = arcCy;
    }

    public double getArcR() {
        return arcR;
    }

    public void setArcR(double arcR) {
        this.arcR = arcR;
    }
    
    
    
    
}
