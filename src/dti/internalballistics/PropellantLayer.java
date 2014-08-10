/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dti.internalballistics;

/**
 *
 * @author wichai.p
 */
public class PropellantLayer {
    private int layerId;
    private String layerName;
    private String layerMaterial;
    private double burningRate;
    private double pressureExponent;
    private double density;      
    private double burningConst; 
    private double gasTemp;
    private double gasConst;
    private double heatRatio;
    private double maxBurningDistance;
    private double burningStartDistance;
    private double rb; //burnining rate at time t
    private double rb_m0; //burnining rate at time t
    private double x;  //burning distance at time t
    private double Ap; //burning port area at time t
    private double peri; //periphery at time t
    private double rbA; //Dummy burning rate at time t
    private double a_factor; //pre-exponent factor
    private double gas;
    private double gasFrac;
    
    

    public PropellantLayer() {
    }

    public int getLayerId() {
        return layerId;
    }

    public void setLayerId(int layerId) {
        this.layerId = layerId;
    }

    public String getLayerName() {
        return layerName;
    }

    public void setLayerName(String layerName) {
        this.layerName = layerName;
    }

    public String getLayerMaterial() {
        return layerMaterial;
    }

    public void setLayerMaterial(String layerMaterial) {
        this.layerMaterial = layerMaterial;
    }

    public double getBurningRate() {
        return burningRate;
    }

    public void setBurningRate(double burningRate) {
        this.burningRate = burningRate;
    }

    public double getPressureExponent() {
        return pressureExponent;
    }

    public void setPressureExponent(double pressureExponent) {
        this.pressureExponent = pressureExponent;
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public double getBurningConst() {
        return burningConst;
    }

    public void setBurningConst(double burningConst) {
        this.burningConst = burningConst;
    }

    public double getGasTemp() {
        return gasTemp;
    }

    public void setGasTemp(double gasTemp) {
        this.gasTemp = gasTemp;
    }

    public double getGasConst() {
        return gasConst;
    }

    public void setGasConst(double gasConst) {
        this.gasConst = gasConst;
    }

    public double getHeatRatio() {
        return heatRatio;
    }

    public void setHeatRatio(double heatRatio) {
        this.heatRatio = heatRatio;
    }

    public double getMaxBurningDistance() {
        return maxBurningDistance;
    }

    public void setMaxBurningDistance(double maxBurningDistance) {
        this.maxBurningDistance = maxBurningDistance;
    }

    public double getBurningStartDistance() {
        return burningStartDistance;
    }

    public void setBurningStartDistance(double burningStartDistance) {
        this.burningStartDistance = burningStartDistance;
    }

    public double getRb() {
        return rb;
    }

    public void setRb(double rb) {
        this.rb = rb;
    }

    public double getRb_m0() {
        return rb_m0;
    }

    public void setRb_m0(double rb_m0) {
        this.rb_m0 = rb_m0;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getPeri() {
        return peri;
    }

    public void setPeri(double peri) {
        this.peri = peri;
    }

    public double getRbA() {
        return rbA;
    }

    public void setRbA(double rbA) {
        this.rbA = rbA;
    }

    public double getA_factor() {
        return a_factor;
    }

    public void setA_factor(double a_factor) {
        this.a_factor = a_factor;
    }

    public double getAp() {
        return Ap;
    }

    public void setAp(double Ap) {
        this.Ap = Ap;
    }

    public double getGas() {
        return gas;
    }

    public void setGas(double gas) {
        this.gas = gas;
    }

    public double getGasFrac() {
        return gasFrac;
    }

    public void setGasFrac(double gasFrac) {
        this.gasFrac = gasFrac;
    }
    
    
    
    
}
