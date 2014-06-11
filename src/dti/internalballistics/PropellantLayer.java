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
    
    
    
    
}
