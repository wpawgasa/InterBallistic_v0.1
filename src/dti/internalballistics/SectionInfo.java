/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dti.internalballistics;

import dti.internalballistics.cad.CAD;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileFilter;
import org.apache.batik.swing.JSVGCanvas;
import org.w3c.dom.svg.SVGDocument;

/**
 *
 * @author amabird
 */
public class SectionInfo {

    private int rowNo = 0;

    private Double newInnerDiameter;
    private Double newOuterDiameter;
    private boolean isCircle = false;
    private Double cx;
    private Double cy;


    private Double diameterSection;
    private Double lengthSection;
    private Double innerPortSection;
    private Double xPosition;
    private Double yPosition;
    //CADPanel cADPanel = new CADPanel(); 
    public SVGDocument CADDoc;

    private List<PropellantLayer> layers = new ArrayList<PropellantLayer>();

    private String section_id;
    private String innerPort_id;

    public SectionInfo(double outer, double inner, String id, String innerPort_id, Double lengthSection, Double xPosition, Double yPostion) {
        rowNo = 1;
        PropellantLayer layer = new PropellantLayer();
        layer.setLayerId(rowNo);
        layer.setLayerName("");
        layer.setLayerMaterial("");
        layer.setBurningRate(0.0);
        layer.setBurningConst(0.0);
        layer.setDensity(0.0);
        layer.setGasConst(0.0);
        layer.setGasTemp(0.0);
        layer.setHeatRatio(0.0);
        layer.setPressureExponent(0.0);
        layer.setMaxBurningDistance(outer);
        layers.add(layer);
        newOuterDiameter = outer;
        newInnerDiameter = inner;
        section_id = id;
        this.innerPort_id = innerPort_id;
        this.lengthSection = lengthSection;
        this.xPosition = xPosition;
        this.yPosition = yPostion;
    }

    public List<PropellantLayer> getLayers() {
        return layers;
    }

    public PropellantLayer addNewLayer() {
        setRowNo(getRowNo() + 1);
        PropellantLayer layer = new PropellantLayer();
        layer.setLayerId(getRowNo());
        layer.setLayerName("");
        layer.setLayerMaterial("");
        layer.setBurningRate(0.0);
        layer.setBurningConst(0.0);
        layer.setDensity(0.0);
        layer.setGasConst(0.0);
        layer.setGasTemp(0.0);
        layer.setHeatRatio(0.0);
        layer.setPressureExponent(0.0);
        layer.setMaxBurningDistance(getNewOuterDiameter());
        layers.add(layer);
        return layer;
    }

    public void removeLayer(PropellantLayer layer) {
        setRowNo(getRowNo() - 1);
        layers.remove(layer);
        int i = 1;
        for (PropellantLayer propellantLayer : layers) {
            propellantLayer.setLayerId(i);
            i++;
        }
    }

    public SVGDocument getCADDoc() {
        return CADDoc;
    }

    public void setCADDoc(SVGDocument CADDoc) {
        this.CADDoc = CADDoc;
    }

    public String getSection_id() {
        return section_id;
    }

    public void setSection_id(String section_id) {
        this.section_id = section_id;
    }

    /**
     * @return the innerPort_id
     */
    public String getInnerPort_id() {
        return innerPort_id;
    }

    /**
     * @param innerPort_id the innerPort_id to set
     */
    public void setInnerPort_id(String innerPort_id) {
        this.innerPort_id = innerPort_id;
    }

    /**
     * @return the diameterSection
     */

    /**
     * @return the lengthSection
     */
    public Double getLengthSection() {
        return lengthSection;
    }

    /**
     * @param lengthSection the lengthSection to set
     */
    public void setLengthSection(Double lengthSection) {
        this.lengthSection = lengthSection;
    }

    /**
     * @return the newInnerDiameter
     */
    public Double getNewInnerDiameter() {
        return newInnerDiameter;
    }

    /**
     * @param newInnerDiameter the newInnerDiameter to set
     */
    public void setNewInnerDiameter(Double newInnerDiameter) {
        this.newInnerDiameter = newInnerDiameter;
    }

    /**
     * @return the newOuterDiameter
     */
    public Double getNewOuterDiameter() {
        return newOuterDiameter;
    }

    /**
     * @param newOuterDiameter the newOuterDiameter to set
     */
    public void setNewOuterDiameter(Double newOuterDiameter) {
        this.newOuterDiameter = newOuterDiameter;
    }

    /**
     * @return the rowNo
     */
    public int getRowNo() {
        return rowNo;
    }

    /**
     * @param rowNo the rowNo to set
     */
    public void setRowNo(int rowNo) {
        this.rowNo = rowNo;
    }

    /**
     * @return the xPosition
     */
    public Double getxPosition() {
        return xPosition;
    }

    /**
     * @param xPosition the xPosition to set
     */
    public void setxPosition(Double xPosition) {
        this.xPosition = xPosition;
    }

    /**
     * @return the cx
     */
    public Double getCx() {
        return cx;
    }

    /**
     * @param cx the cx to set
     */
    public void setCx(Double cx) {
        this.cx = cx;
    }

    /**
     * @return the cy
     */
    public Double getCy() {
        return cy;
    }

    /**
     * @param cy the cy to set
     */
    public void setCy(Double cy) {
        this.cy = cy;
    }

    /**
     * @return the yPosition
     */
    public Double getyPosition() {
        return yPosition;
    }

    /**
     * @param yPosition the yPosition to set
     */
    public void setyPosition(Double yPosition) {
        this.yPosition = yPosition;
    }



}
