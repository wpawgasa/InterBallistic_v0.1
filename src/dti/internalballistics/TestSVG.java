/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dti.internalballistics;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.util.XMLResourceDescriptor;
import org.kabeja.dxf.DXFDocument;
import org.kabeja.parser.DXFParseException;
import org.kabeja.parser.DXFParser;
import org.kabeja.parser.Parser;
import org.kabeja.parser.ParserBuilder;
import org.kabeja.svg.SVGGenerator;
import org.kabeja.xml.SAXGenerator;
import org.kabeja.xml.SAXSerializer;
import org.kabeja.xslt.SAXXMLSerializer;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;

/**
 *
 * @author amabird
 */
public class TestSVG {

    public JSVGCanvas canvas;

    public static void main(String[] args) {
        InputStream is = null;
        try {
            TestSVG t = new TestSVG();
            t.canvas = new JSVGCanvas();
            JFrame f = new JFrame();
            JPanel p = new JPanel();
            p.add(t.canvas);
            p.setSize(400,400);
            f.getContentPane().add(p);

            f.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
            f.setSize(400, 400);
            f.setVisible(true);
            is = t.getClass().getClassLoader().getResource("dti/image/DTIStar.dxf").openStream();
            Parser dxfParser = ParserBuilder.createDefaultParser();
            dxfParser.parse(is, DXFParser.DEFAULT_ENCODING);
            DXFDocument dXFDocument = dxfParser.getDocument();
            SAXGenerator sAXGenerator = new SVGGenerator();
            SAXSerializer sAXSerializer = new SAXXMLSerializer();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            sAXSerializer.setOutput(byteArrayOutputStream);

            sAXGenerator.setProperties(new HashMap());

            sAXGenerator.generate(dXFDocument, sAXSerializer);
            //return byteArrayOutputStream;
            byte[] data = byteArrayOutputStream.toByteArray();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
            String svgParser = XMLResourceDescriptor.getXMLParserClassName();
            SAXSVGDocumentFactory sAXSVGDocumentFactory = new SAXSVGDocumentFactory(svgParser);
            SVGDocument sVGDocument = sAXSVGDocumentFactory.createSVGDocument(null, byteArrayInputStream);
            Element root = sVGDocument.getDocumentElement();
            System.out.println(root.getAttribute("width"));
            t.canvas.setSVGDocument(sVGDocument);

        } catch (IOException ex) {
            Logger.getLogger(TestSVG.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DXFParseException ex) {
            Logger.getLogger(TestSVG.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(TestSVG.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
