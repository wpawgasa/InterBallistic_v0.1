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
    
    //CADPanel cADPanel = new CADPanel(); 
    public SVGDocument CADDoc;
    
    private List<PropellantLayer> layers = new ArrayList<PropellantLayer>();
    
    private String section_id;

    public SectionInfo(double outer, double inner, String id) {
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
    }

    public List<PropellantLayer> getLayers() {
        return layers;
    }
    
    public PropellantLayer addNewLayer() {
        rowNo++;
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
        layer.setMaxBurningDistance(newOuterDiameter);
        layers.add(layer);
        return layer;
    }
    
    public void removeLayer(PropellantLayer layer) {
        rowNo--;
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
    
    
    
    
    
    

    

    
    
    
    
   
    
}
