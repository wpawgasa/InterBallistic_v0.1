/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dti.internalballistics.cad;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.html.HTMLEditorKit;
import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.util.XMLResourceDescriptor;
import org.kabeja.batik.tools.SAXPNGSerializer;
import org.kabeja.dxf.DXFCircle;
import org.kabeja.dxf.DXFColor;
import org.kabeja.dxf.DXFConstants;
import org.kabeja.dxf.DXFDocument;
import org.kabeja.dxf.DXFLayer;
import org.kabeja.parser.DXFParseException;
import org.kabeja.parser.DXFParser;
import org.kabeja.parser.Parser;
import org.kabeja.parser.ParserBuilder;
import org.kabeja.svg.SVGGenerator;
import org.kabeja.xml.SAXGenerator;
import org.kabeja.xml.SAXSerializer;
import org.kabeja.xslt.SAXXMLSerializer;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.svg.SVGDocument;

/**
 *
 * @author amabird
 */
public class CAD {

    public DXFDocument dXFDocument;
    public DXFLayer dXFLayer;
    public DXFColor dXFColor;
    public ByteArrayOutputStream byteArrayOutputStream;
    public DXFCircle dXFCircle;
    public InputStream inputStream;
    public Parser dxfParser;
    public SVGDocument sVGDocument;

    public CAD() {
    }

    public void parseFile(String sourceFile) {
        try {

            inputStream = this.getClass().getClassLoader().getResource(sourceFile).openStream();
            //inputStream = new FileInputStream(sourceFile);
            dxfParser = ParserBuilder.createDefaultParser();
            dxfParser.parse(inputStream, DXFParser.DEFAULT_ENCODING);
            dXFDocument = dxfParser.getDocument();

            dXFLayer = dXFDocument.getDXFLayer("11");

        } catch (DXFParseException dXFParseException) {
            dXFParseException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SVGDocument outputSVG() {

        SAXGenerator sAXGenerator = new SVGGenerator();
        SAXSerializer sAXSerializer = new SAXXMLSerializer();
        byteArrayOutputStream = new ByteArrayOutputStream();
        sAXSerializer.setOutput(byteArrayOutputStream);

        sAXGenerator.setProperties(new HashMap());

        sAXGenerator.generate(dXFDocument, sAXSerializer);
        //return byteArrayOutputStream;
        byte[] data = byteArrayOutputStream.toByteArray();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        String svgParser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory sAXSVGDocumentFactory = new SAXSVGDocumentFactory(svgParser);
        SVGDocument svgDoc = null;
        try {
            svgDoc = sAXSVGDocumentFactory.createSVGDocument(null, byteArrayInputStream);
            Element x = svgDoc.getElementById("ID_8E0");
            x.setAttribute("stroke", "red");
            
            NodeList nodes = svgDoc.getElementsByTagName("svg");
            Node path0 = nodes.item(0);
            Element elm = (Element) path0;
            //elm.setAttribute("viewBox", "0 0 200 200");
            elm.setAttribute("width", "300");
            elm.setAttribute("hight", "300");
            elm.setAttribute("preserveAspectRatio", "xMidYMid meet");
                    
        } catch (IOException ex) {
            Logger.getLogger(CAD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return svgDoc;
    }

    public void resizeShape() {

    }

    public void convertToSVG(ByteArrayOutputStream byteArrayOutputStream, JSVGCanvas jSVGCanvas) {
        byte[] data = byteArrayOutputStream.toByteArray();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        String svgParser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory sAXSVGDocumentFactory = new SAXSVGDocumentFactory(svgParser);
        SVGDocument sVGDocument;
        try {
            sVGDocument = sAXSVGDocumentFactory.createSVGDocument(null, byteArrayInputStream);
            jSVGCanvas.setSVGDocument(sVGDocument);
        } catch (IOException ex) {
            Logger.getLogger(CAD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Double getOuterRadius() {
        Element radius = sVGDocument.getElementById("ID_8E0");
        //Double oldOuterradius = Double.parseDouble(radius.getAttribute("r"));
        return Double.parseDouble(radius.getAttribute("r"));
    }

    public Double getinnerValue() {
        Element radius = sVGDocument.getElementById("ID_8ED");
        //Double oldOuterradius = Double.parseDouble(radius.getAttribute("r"));
        return Double.parseDouble(radius.getAttribute("r"));
    }

    public void setOuterRadius(double newRadius, JSVGCanvas jSVGCanvas) {

        // Element tag = sVGDocument.getElementById("draft");
        // tag.setAttribute("stroke-width", "10%");
        //Element layer = sVGDocument.getElementById("ID_0");
        // layer.setAttribute("color", "rgb(239,13,54)");
        Element radius = sVGDocument.getElementById("ID_8E0");
        radius.setAttribute("r", String.valueOf(newRadius / 2));
        jSVGCanvas.setDocument(sVGDocument);
//            Element circle = (Element)layer.getElementsByTagName("circle").item(0);
//            circle.setAttribute("r", String.valueOf(newRadius/2));
//            jSVGCanvas.setDocument(sVGDocument);

        //dXFCircle = (DXFCircle)circle.get(1);
        //dXFCircle.setRadius(newInnerRadius*2);
    }

    public void setOuterShape(double newRadius, JSVGCanvas jSVGCanvas, SVGDocument svgDoc) {
//        Element radius = svgDoc.getElementById("ID_8E0");
        Node node0 = (Node) svgDoc.getElementById("ID_0");
        NodeList nodes = node0.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node n = nodes.item(i);
            Element elm = (Element) n;
            if (elm.getTagName().equalsIgnoreCase("circle") && elm.getAttribute("id").equalsIgnoreCase("ID_8E0")) {
                System.out.println("outer " + newRadius);
                elm.setAttribute("r", String.valueOf(newRadius / 2));
            }
        }

        jSVGCanvas.setDocument(svgDoc);
    }

    public void setInnerShape(double newRadius, JSVGCanvas jSVGCanvas, SVGDocument svgDoc) {
//        Element radius = svgDoc.getElementById("ID_8E0");
        Node node0 = (Node) svgDoc.getElementById("ID_0");
        NodeList nodes = node0.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node n = nodes.item(i);
            Element elm = (Element) n;
            if (elm.getTagName().equalsIgnoreCase("circle") && elm.getAttribute("id").equalsIgnoreCase("ID_8ED")) {
                System.out.println("inner " + newRadius);
                elm.setAttribute("r", String.valueOf(newRadius / 2));
            }
        }

        jSVGCanvas.setDocument(svgDoc);
    }

    public void extractInnerPort(SVGDocument svgDoc, List<Point> points) {
        Element elm0 = svgDoc.getElementById("ID_0");
        NodeList nodes = elm0.getElementsByTagName("path");
        points.clear();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node n = nodes.item(i);
            Element elm = (Element) n;
            String d = elm.getAttribute("d");
            String[] dPoints = d.split("\\s+");
            for (int j = 0; j < dPoints.length; j++) {
                Point p = new Point();
                if (dPoints[j].equalsIgnoreCase("m") || dPoints[j].equalsIgnoreCase("l")) {
                    p.setInstruction(dPoints[j]);
                    p.setX(Double.valueOf(dPoints[++j]));
                    p.setY(Double.valueOf(dPoints[++j]));

                } else if (dPoints[j].equalsIgnoreCase("a")) {

                    p.setInstruction(dPoints[j]);
                    p.setRx(Double.valueOf(dPoints[j + 1]));
                    p.setRy(Double.valueOf(dPoints[j + 2]));
                    p.setXrotate(Integer.valueOf(dPoints[j + 3]));
                    p.setLargearcflag(Integer.valueOf(dPoints[j + 4]));
                    p.setSweepflag(Integer.valueOf(dPoints[j + 5]));

                    p.setX(Double.valueOf(dPoints[j + 6]));
                    p.setY(Double.valueOf(dPoints[j + 7]));
                    j = j + 7;

                }

                if (j < dPoints.length - 1 && !dPoints[j + 1].equalsIgnoreCase("a")) {
                    p.setBulge(0.0);
                } else if (j < dPoints.length - 1 && dPoints[j + 1].equalsIgnoreCase("a")) {
                    Point p2 = new Point();
                    p2.setX(Double.valueOf(dPoints[j + 7]));
                    p2.setY(Double.valueOf(dPoints[j + 8]));
                    double r = Double.valueOf(dPoints[j + 2]);
                    double b = calculateBulge(p, p2, r);
                    p.setBulge(b);
                    Arc arc = new Arc();
                    arc.ArcFromBulge(p, p2, p.getBulge());
                    p.setArcCx(arc.getCenter().getX());
                    p.setArcCy(arc.getCenter().getY());
                    p.setArcR(arc.getRadius());
                }
                points.add(p);
            }
        }

    }

    public double calculateBulge(Point p1, Point p2, double radius) {
        double bulge = 0.0;
        double x1 = p1.getX();
        double y1 = p1.getY();
        double x2 = p2.getX();
        double y2 = p2.getY();
        double chord_length = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        bulge = Math.tan(0.5 * Math.asin(chord_length / (2 * radius)));
        return bulge;
    }

    public void rearrangePath(SVGDocument svgDoc) {
        Element el = svgDoc.getElementById("ID_0");
        NodeList nodes = el.getElementsByTagName("path");
        Node path0 = nodes.item(0);
        Element elm0 = (Element) path0;
        String d0 = elm0.getAttribute("d");
        String[] dPoints0 = d0.split("\\s+");
        Point firstP = new Point();
        firstP.setX(Double.valueOf(dPoints0[1]));
        firstP.setY(Double.valueOf(dPoints0[2]));
        Point lastP = new Point();
        lastP.setX(Double.valueOf(dPoints0[dPoints0.length - 2]));
        lastP.setY(Double.valueOf(dPoints0[dPoints0.length - 1]));

        for (int i = 1; i < nodes.getLength(); i++) {
            dPoints0 = d0.split("\\s+");
            lastP = new Point();
            lastP.setX(Double.valueOf(dPoints0[dPoints0.length - 2]));
            lastP.setY(Double.valueOf(dPoints0[dPoints0.length - 1]));

            Node path = nodes.item(i);
            Element elm = (Element) path;
            String d = elm.getAttribute("d");
            String [] dPoints = d.split("\\s+");
            Point nP = new Point();
            nP.setX(Double.valueOf(dPoints[1]));
            nP.setY(Double.valueOf(dPoints[2]));
            BigDecimal p1x = new BigDecimal(nP.getX());
            p1x = p1x.setScale(8, RoundingMode.FLOOR);
            BigDecimal p1y = new BigDecimal(nP.getY());
            p1y = p1y.setScale(8, RoundingMode.FLOOR);
            BigDecimal p2x = new BigDecimal(lastP.getX());
            p2x = p2x.setScale(8, RoundingMode.FLOOR);
            BigDecimal p2y = new BigDecimal(lastP.getY());
            p2y = p2y.setScale(8, RoundingMode.FLOOR);
            if(p1x.compareTo(p2x)==0&&p1y.compareTo(p2y)==0) {
                for(int j=3;j<dPoints.length;j++) {
                    d0 = d0 + " " + dPoints[j];
                }
                el.removeChild(path);
                nodes = el.getElementsByTagName("path");
                i=0;
            }
        }
        
        BigDecimal p1x = new BigDecimal(firstP.getX());
            p1x = p1x.setScale(8, RoundingMode.FLOOR);
            BigDecimal p1y = new BigDecimal(firstP.getY());
            p1y = p1y.setScale(8, RoundingMode.FLOOR);
            BigDecimal p2x = new BigDecimal(lastP.getX());
            p2x = p2x.setScale(8, RoundingMode.FLOOR);
            BigDecimal p2y = new BigDecimal(lastP.getY());
            p2y = p2y.setScale(8, RoundingMode.FLOOR);
            
        if(p1x.compareTo(p2x)==0&&p1y.compareTo(p2y)==0) {
                
                    d0 = d0 + " L " + dPoints0[1] + " " + dPoints0[2];
                
        }
        elm0.setAttribute("d", d0);
        
        //return svgDoc;
    }

    public SVGDocument resizeInnerPort(double newRadius, SVGDocument svgDoc) {
        Element elm0 = svgDoc.getElementById("ID_0");
        NodeList nodes = elm0.getElementsByTagName("path");
        for (int i = 0; i < nodes.getLength(); i++) {
            Node n = nodes.item(i);
            Element elm = (Element) n;

        }
        return svgDoc;
    }

    public void zoomIn(JSVGCanvas jSVGCanvas, SVGDocument svgDoc, Double cx, Double cy) {
        
       Element resizeInner = svgDoc.getElementById("ID_0");
    
       resizeInner.setAttribute("transform", "translate(-50,-50) scale(2)");
            NodeList nodes = svgDoc.getElementsByTagName("svg");
            Node path0 = nodes.item(0);
            Element elm = (Element) path0;
        elm.setAttribute("preserveAspectRatio", "xMidYMid meet");
//  

        jSVGCanvas.setDocument(svgDoc);
//        
////        Element resizeInner = svgDoc.getElementById("ID_0");
////        resizeInner.setAttribute("transform", "scale(1.5)");
////        resizeInner.setAttribute("cx", cx.toString());
////        resizeInner.setAttribute("cy", cy.toString());
//        NodeList nodes = svgDoc.getElementsByTagName("svg");
//            Node path0 = nodes.item(0);
//            Element elm = (Element) path0;
//            String vb = elm.getAttribute("viewBox");
//            String [] vbVal = vb.split("\\s+");
//            String newVb = "";
//            for(int i=0;i<vbVal.length;i++) {
//                double val = Double.valueOf(vbVal[i]);
//                val = val*1.5;
//                newVb = newVb + String.valueOf(val) + " ";
//                        
//                
//            }
//            elm.setAttribute("viewBox", newVb);
////            elm.setAttribute("width", "450");
////            elm.setAttribute("hight", "450");
//            elm.setAttribute("preserveAspectRatio", "xMidYMid meet");
//        jSVGCanvas.setDocument(svgDoc);
    }
}
