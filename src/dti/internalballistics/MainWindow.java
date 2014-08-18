/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dti.internalballistics;

import com.apple.eawt.Application;
import dti.internalballistics.cad.CAD;
import dti.internalballistics.cad.OnClickAction;
import dti.internalballistics.cad.OnLoadAction;
import dti.internalballistics.cad.Point;
import dti.internalballistics.cad.SvgOnHoverAction;
import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.IAxis;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.traces.Trace2DLtd;
import info.monitorenter.gui.chart.views.ChartPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.NumberFormatter;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.swing.svg.SVGLoadEventDispatcherAdapter;
import org.apache.batik.swing.svg.SVGLoadEventDispatcherEvent;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.svg.SVGElement;
import sun.lwawt.LWToolkit;

/**
 *
 * @author amabird
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {

        //Application.getApplication().setDockIconImage(new ImageIcon(getClass().getResource("/dti/icon/InternalBallisticNew-Logo.png")).getImage());
        this.setIconImage(new ImageIcon(getClass().getResource("/dti/icon/InternalBallisticNew-Logo.png")).getImage());
        initComponents();
        Chart2D chart1 = initChart("Thrust, N", "Time, s");
        traceOutputThrust = new Trace2DLtd(200);
        traceCompareThrust = new Trace2DLtd(200);
        addTrace(traceOutputThrust, "Output Thrust", Color.BLUE, chart1);
        addTrace(traceCompareThrust, "Compare Thrust", Color.RED, chart1);
        addChartToPanel(chart1, chartPanelThrust);

        Chart2D chart2 = initChart("Pressure, PSI", "Time, s");
        traceOutputPressure = new Trace2DLtd(200);
        traceComparePressure = new Trace2DLtd(200);
        addTrace(traceOutputPressure, "Output Pressure", Color.BLUE, chart2);
        addTrace(traceComparePressure, "Compare Pressure", Color.RED, chart2);
        addChartToPanel(chart2, chartPanelPressure);

        setSpinner(rocketDiameterSp);
        setSpinner(rocketLengthSp);
        setSpinner(guessPSI);
        setSpinner(outerDiameterSpinner);
        setSpinner(innerDiameterSpinner);
        setButtonGroup();
        drawCanvas.setDocumentState(JSVGCanvas.ALWAYS_DYNAMIC);
        drawCanvas.addSVGLoadEventDispatcherListener(new SVGLoadEventDispatcherAdapter() {
            public void svgLoadEventDispatchStarted(SVGLoadEventDispatcherEvent e) {

                //System.out.println("sssssss");
            }
        });

        setCanvas();

    }

    public void registerListeners(String id) {
        // Gets an element from the loaded document.
        Element elt = document.getElementById(id);

        EventTarget t = (EventTarget) elt;

        // Adds oldLengthSection 'onload' listener
        t.addEventListener("SVGLoad", new OnLoadAction(), false);

        // Adds oldLengthSection 'onclick' listener
        t.addEventListener("click", new EventListener() {

            @Override
            public void handleEvent(Event event) {
                Element target = (Element) event.getCurrentTarget();
                String sectionNo = target.getAttribute("id");

                for (SectionInfo section : sectionList) {

                    if (section.getSection_id().equalsIgnoreCase(sectionNo)) {
                        selectedSection = section;
                        target.setAttribute("stroke-width", "2");
                        selectedSomething = true;
                    } else {
                        Element elm = document.getElementById(section.getSection_id());
                        elm.setAttribute("stroke-width", "1");
                    }
                }
                setSectionInfoView();

            }
        }, false);

        //System.out
        //System.out.println("def");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        leftGeoPanel = new javax.swing.JPanel();
        rigthGeoPanel = new javax.swing.JPanel();
        mainTabbedPanel = new javax.swing.JTabbedPane();
        geometricTab = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        addSectionBT = new javax.swing.JButton();
        removeSectionBT = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        rocketDiameterSp = new javax.swing.JSpinner();

        rocketLengthSp = new javax.swing.JSpinner();
        //drawCanvas = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        ignitorTime = new javax.swing.JSpinner();
        ignitorMass = new javax.swing.JSpinner();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        sectionPropertiesTabbedPanel = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        propellantTable = new javax.swing.JTable();
        addPropellantBt = new javax.swing.JButton();

        removePropellantBt = new javax.swing.JButton();
        propellantPanel = new javax.swing.JPanel();
        leftPanel = new javax.swing.JPanel();
        burningRateLB = new javax.swing.JLabel();
        burningRateTB = new javax.swing.JTextField();

        jLabel19 = new javax.swing.JLabel();
        pressureExponentLB = new javax.swing.JLabel();
        pressureExponentTB = new javax.swing.JTextField();
        densityLB = new javax.swing.JLabel();
        densityTB = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        alphaConstLB = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        alphaConstTB = new javax.swing.JTextField();
        gasConstLB = new javax.swing.JLabel();
        gasConstTB = new javax.swing.JTextField();
        gasTempLB = new javax.swing.JLabel();
        gasTempTB = new javax.swing.JTextField();
        heatRatioLB = new javax.swing.JLabel();
        heatRatioTB = new javax.swing.JTextField();
        maxBurntLB = new javax.swing.JLabel();
        maxBurntTB = new javax.swing.JTextField();
        savePropertiesBT = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        outerDiameterSpinner = new javax.swing.JSpinner();
        jPanel3 = new javax.swing.JPanel();
        circleToggleButton = new javax.swing.JToggleButton();
        wheelToggleButton = new javax.swing.JToggleButton();
        starToggleButton = new javax.swing.JToggleButton();
        hexaToggleButton = new javax.swing.JToggleButton();
        pentaToggleButton = new javax.swing.JToggleButton();
        eightStarToggleButton = new javax.swing.JToggleButton();
        //cadPanelShape = new javax.swing.JPanel();
        diameterInner = new javax.swing.JLabel();
        innerDiameterSpinner = new javax.swing.JSpinner();
        mmLabel = new javax.swing.JLabel();
        mmLabel1 = new javax.swing.JLabel();
        simulationTab = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        precisionLabel = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        runSimulateBtn = new javax.swing.JButton();
        abortSimulateBtn = new javax.swing.JButton();
        guessPSI = new javax.swing.JSpinner();
        specificImp = new javax.swing.JSpinner();
        jSpinner5 = new javax.swing.JSpinner();
        stoptime = new javax.swing.JSpinner();
        numSegments = new javax.swing.JSpinner();
        outputPrecision = new javax.swing.JSpinner();
        mainMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        loadConfItem = new javax.swing.JMenuItem();
        saveConfItem = new javax.swing.JMenuItem();
        exitItem = new javax.swing.JMenuItem();
        lengthLB = new javax.swing.JLabel();
        lengthSectionSpinner = new javax.swing.JSpinner();
        mmLengthLabel = new javax.swing.JLabel();
        startBurningDistanceLb = new javax.swing.JLabel();
        startBurningDistanceTb = new javax.swing.JTextField();
        zoomInPropellantBt = new javax.swing.JButton();
        zoomOutPropellantBt = new javax.swing.JButton();

        viewControlPanel = new javax.swing.JPanel();
        arrowUpButton = new javax.swing.JButton();
        arrowRightButton = new javax.swing.JButton();
        arrowLeftButton = new javax.swing.JButton();
        arrowDownButton = new javax.swing.JButton();
        centerButton = new javax.swing.JButton();

        diameterSpinnerNumberModel = new SpinnerNumberModel(0.0, -1000.0, 1000.0, 0.1);
        lengthSpinnerNumberModel = new SpinnerNumberModel(0.0, -1000.0, 1000.0, 0.1);
        rocketDiameterSp = new JSpinner(diameterSpinnerNumberModel);
        rocketLengthSp = new JSpinner(lengthSpinnerNumberModel);

        diameterSectionSpinnerNumberModel = new SpinnerNumberModel(0.0, -1000.0, 1000.0, 0.1);
        lengthSectionSpinnerNumberModel = new SpinnerNumberModel(0.0, -1000.0, 1000.0, 0.1);
        innerDiameterSectionSpinnerNumberModel = new SpinnerNumberModel(0.0, -1000.0, 1000.0, 0.1);
        outerDiameterSpinner = new JSpinner(diameterSectionSpinnerNumberModel);
        innerDiameterSpinner = new JSpinner(innerDiameterSectionSpinnerNumberModel);
        lengthSectionSpinner = new JSpinner(lengthSectionSpinnerNumberModel);

        burningDistancePanel = new javax.swing.JPanel();
        burningDistanceScrollPanel = new javax.swing.JScrollPane();
        burningDistanceTable = new javax.swing.JTable();
        addBurningRowBt = new javax.swing.JButton();
        removeBurningRowBt = new javax.swing.JButton();
        loadPropDataBt = new javax.swing.JButton();
        savePropDataBt = new javax.swing.JButton();
        savePropDataBt.setText("Save propellant geometric data");

        chartPanel = new javax.swing.JPanel();
        chartPanelPressure = new javax.swing.JPanel();
        chartPanelThrust = new javax.swing.JPanel();
        browseCompareButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Rocket Diameter :");

        jLabel2.setText("Rocket Length :");

        addSectionBT.setText("Add Section");
        addSectionBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addSectionBTMouseClicked(evt);
            }
        });
        addSectionBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSectionBTActionPerformed(evt);
            }
        });

        removeSectionBT.setText("Remove Section");

        removeSectionBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeSectionBTActionPerformed(evt);
            }
        });
        jLabel3.setText("mm");

        jLabel4.setText("mm");

        rocketDiameterSp.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rocketDiameterSpStateChanged(evt);
            }
        });

        rocketLengthSp.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rocketLengthSpStateChanged(evt);
            }
        });

        javax.swing.GroupLayout drawCanvasLayout = new javax.swing.GroupLayout(drawCanvas);
        drawCanvas.setLayout(drawCanvasLayout);
        drawCanvasLayout.setHorizontalGroup(
                drawCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 650, Short.MAX_VALUE)
        );
        drawCanvasLayout.setVerticalGroup(
                drawCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel14.setText("Igniter Mass :");

        jLabel15.setText("Igniter burn time :");

        ignitorTime.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ignitorTimeStateChanged(evt);
            }
        });

        ignitorMass.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ignitorMassStateChanged(evt);
            }
        });

        jLabel16.setText("kg");

        jLabel17.setText("s");

        propellantTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Layer", "Name", "Material"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        propellantTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                propellantTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(propellantTable);

        addPropellantBt.setText("Add");
        addPropellantBt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addPropellantBtMouseClicked(evt);
            }
        });

        removePropellantBt.setText("Remove");

        removePropellantBt.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeSectionBTActionPerformed(evt);
            }
        });
        burningRateLB.setText("Burning rate (m/s) ");

        jLabel19.setText("/1000 ");

        pressureExponentLB.setText("Pressure Exponent");

        densityLB.setText("Density (kg/m^3)");

        jLabel22.setText("*1000 ");

        alphaConstLB.setText("Alpha erosive burning const.");

        jLabel24.setText("/10^7");

        gasConstLB.setText("Individual gas const. (J/(kg*K))");

        gasTempLB.setText("Gas temperature (K)");

        heatRatioLB.setText("Heat Capacity Ratio");

        maxBurntLB.setText("Max. Burning Distance (mm)");

        startBurningDistanceLb.setText("Start Burning Distance (mm)");

        javax.swing.GroupLayout propellantPanelLayout = new javax.swing.GroupLayout(propellantPanel);
        propellantPanel.setLayout(propellantPanelLayout);
        propellantPanelLayout.setHorizontalGroup(
                propellantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(propellantPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(propellantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, propellantPanelLayout.createSequentialGroup()
                                        .addComponent(heatRatioLB)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(heatRatioTB, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, propellantPanelLayout.createSequentialGroup()
                                        .addComponent(gasConstLB)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(gasConstTB, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(propellantPanelLayout.createSequentialGroup()
                                        .addComponent(gasTempLB)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(gasTempTB, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(propellantPanelLayout.createSequentialGroup()
                                        .addComponent(maxBurntLB)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(maxBurntTB, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(propellantPanelLayout.createSequentialGroup()
                                        .addComponent(startBurningDistanceLb)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(startBurningDistanceTb, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, propellantPanelLayout.createSequentialGroup()
                                        .addComponent(pressureExponentLB)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(pressureExponentTB, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, propellantPanelLayout.createSequentialGroup()
                                        .addComponent(burningRateLB)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(burningRateTB, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, propellantPanelLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(densityTB, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(propellantPanelLayout.createSequentialGroup()
                                        .addComponent(densityLB)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, propellantPanelLayout.createSequentialGroup()
                                        .addComponent(alphaConstLB)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(alphaConstTB, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(propellantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel22)
                                .addComponent(jLabel19)
                                .addComponent(jLabel24))
                        .addGap(0, 0, Short.MAX_VALUE))
        );
        propellantPanelLayout.setVerticalGroup(
                propellantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(propellantPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(propellantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(burningRateTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel19)
                                .addComponent(burningRateLB))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(propellantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(pressureExponentTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(pressureExponentLB))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(propellantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(densityLB)
                                .addComponent(densityTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(propellantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(alphaConstLB)
                                .addComponent(alphaConstTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(propellantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(gasTempLB)
                                .addComponent(gasTempTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(propellantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(gasConstLB)
                                .addComponent(gasConstTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(propellantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(heatRatioLB)
                                .addComponent(heatRatioTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(propellantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(maxBurntTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(maxBurntLB))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(propellantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(startBurningDistanceTb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(startBurningDistanceLb))
                        .addContainerGap(12, Short.MAX_VALUE))
        );

        savePropertiesBT.setText("Save");
        savePropertiesBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                savePropertiesBTMouseClicked(evt);
            }
        });
        savePropertiesBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savePropertiesBTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(addPropellantBt)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(removePropellantBt))
                                .addComponent(propellantPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(savePropertiesBT))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, 150, 200, 250)
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(addPropellantBt)
                                .addComponent(removePropellantBt))
                        .addGap(15, 15, 15)
                        .addComponent(propellantPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(savePropertiesBT)
                        .addGap(0, 6, Short.MAX_VALUE))
        );

        sectionPropertiesTabbedPanel.addTab("Propellant Properties", jPanel1);

        jLabel28.setText("Outer Diameter :");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Shape :"));

        circleToggleButton.setBackground(new java.awt.Color(0, 0, 0));
        circleToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dti/icon/DTICircleIcon.png"))); // NOI18N
        circleToggleButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                circleToggleButtonItemStateChanged(evt);
            }
        });

        wheelToggleButton.setBackground(new java.awt.Color(0, 0, 0));
        wheelToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dti/icon/DTIWheelIcon.png"))); // NOI18N
        wheelToggleButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                wheelToggleButtonItemStateChanged(evt);
            }
        });

        starToggleButton.setBackground(new java.awt.Color(0, 0, 0));
        starToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dti/icon/DTIStarIcon.png"))); // NOI18N
        starToggleButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                starToggleButtonItemStateChanged(evt);
            }
        });

        hexaToggleButton.setBackground(new java.awt.Color(0, 0, 0));
        hexaToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dti/icon/DTIHexagonIcon.png"))); // NOI18N
        hexaToggleButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                hexaToggleButtonItemStateChanged(evt);
            }
        });

        pentaToggleButton.setBackground(new java.awt.Color(0, 0, 0));
        pentaToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dti/icon/DTIPentagonIcon.png"))); // NOI18N
        pentaToggleButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                pentaToggleButtonItemStateChanged(evt);
            }
        });

        eightStarToggleButton.setBackground(new java.awt.Color(0, 0, 0));
        eightStarToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dti/icon/DTIEightStarIcon.png"))); // NOI18N
        eightStarToggleButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                eightStarToggleButtonItemStateChanged(evt);
            }
        });

        zoomInPropellantBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomInButtonActionPerformed(evt);
            }
        });
        zoomOutPropellantBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomOutButtonActionPerformed(evt);
            }
        });
        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(pentaToggleButton)
                                .addComponent(circleToggleButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(wheelToggleButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(starToggleButton))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(hexaToggleButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(eightStarToggleButton)))
                        .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(wheelToggleButton)
                                .addComponent(circleToggleButton)
                                .addComponent(starToggleButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(pentaToggleButton)
                                .addComponent(hexaToggleButton)
                                .addComponent(eightStarToggleButton))
                        .addContainerGap(16, Short.MAX_VALUE))
        );

        diameterInner.setText("Inner Diameter:");

        innerDiameterSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                innerDiameterSpinStateChanged(evt);
            }
        });

        outerDiameterSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                outerDiameterSpinStateChanged(evt);
            }
        });

        lengthSectionSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                lengthSpinStateChanged(evt);
            }
        });

        mmLabel.setText("mm");

        mmLabel1.setText("mm");

        lengthLB.setText("Length :");

        mmLengthLabel.setText("mm");

        arrowUpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dti/icon/arrow-up.png"))); // NOI18N
        arrowUpButton.setToolTipText("");
        arrowUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arrowUpButtonActionPerformed(evt);
            }
        });

        arrowRightButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dti/icon/arrow-right.png"))); // NOI18N
        arrowRightButton.setToolTipText("");
        arrowRightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arrowRightButtonActionPerformed(evt);
            }
        });

        arrowLeftButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dti/icon/arrow-left.png"))); // NOI18N
        arrowLeftButton.setToolTipText("");
        arrowLeftButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arrowLeftButtonActionPerformed(evt);
            }
        });

        arrowDownButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dti/icon/arrow-down.png"))); // NOI18N
        arrowDownButton.setToolTipText("");
        arrowDownButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arrowDownButtonActionPerformed(evt);
            }
        });

        centerButton.setToolTipText("");
        centerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                centerButtonActionPerformed(evt);
            }
        });

        zoomInPropellantBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dti/icon/zoom_in.png"))); // NOI18N
        zoomInPropellantBt.setToolTipText("");

        zoomOutPropellantBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dti/icon/zoom_out.png"))); // NOI18N
        zoomOutPropellantBt.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel28)
                                        .addGap(13, 13, 13)
                                        .addComponent(outerDiameterSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(mmLabel1)
                                        .addGap(13, 13, 13)
                                        .addComponent(diameterInner)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(innerDiameterSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(mmLabel)
                                        .addGap(13, 13, 13).addComponent(lengthLB)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lengthSectionSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(mmLengthLabel))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(cadPanelShape, 350, 350, 350)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(viewControlPanel, 190, 190, 190))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel28)
                                .addComponent(outerDiameterSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(diameterInner)
                                .addComponent(innerDiameterSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(mmLabel)
                                .addComponent(mmLabel1)
                                .addComponent(lengthLB)
                                .addComponent(lengthSectionSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(mmLengthLabel))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cadPanelShape, 320, 320, 320)
                                .addComponent(viewControlPanel, 320, 320, 320))
                        .addGap(10, 10, 10)
                        .addContainerGap(10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout viewControlPanelLayout = new javax.swing.GroupLayout(viewControlPanel);
        viewControlPanel.setLayout(viewControlPanelLayout);
        viewControlPanelLayout.setHorizontalGroup(
                viewControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(viewControlPanelLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(viewControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(viewControlPanelLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(zoomInPropellantBt)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(zoomOutPropellantBt)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(viewControlPanelLayout.createSequentialGroup()
                                        .addComponent(arrowLeftButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(viewControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(viewControlPanelLayout.createSequentialGroup()
                                                        .addComponent(centerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(arrowRightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(31, 31, 31))
                                                .addGroup(viewControlPanelLayout.createSequentialGroup()
                                                        .addGroup(viewControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(arrowDownButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(arrowUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        viewControlPanelLayout.setVerticalGroup(
                viewControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(viewControlPanelLayout.createSequentialGroup()
                        .addComponent(arrowUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(viewControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(viewControlPanelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(arrowLeftButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(viewControlPanelLayout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addGroup(viewControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(arrowRightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(centerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(1, 1, 1)
                        .addComponent(arrowDownButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(viewControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(zoomInPropellantBt)
                                .addComponent(zoomOutPropellantBt))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        sectionPropertiesTabbedPanel.addTab("Propellant Geometric", jPanel2);

        addBurningRowBt.setText("Add new row");
        addBurningRowBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBurningRowBtActionPerformed(evt);
            }
        });

        removeBurningRowBt.setText("Remove row");
        removeBurningRowBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBurningRowBtActionPerformed(evt);
            }
        });

        loadPropDataBt.setText("Load propellant geometric data from file");
        loadPropDataBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadPropDataBtActionPerformed(evt);
            }
        });

        savePropDataBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savePropDataBtActionPerformed(evt);
            }
        });

        propellantTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Layer", "Name", "Material"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        burningDistanceTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Distance (mm)", "Peripheral (mm)", "Port Area (mm^2)"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        burningDistanceScrollPanel.setViewportView(burningDistanceTable);

        javax.swing.GroupLayout burningDistancePanelLayout = new javax.swing.GroupLayout(burningDistancePanel);
        burningDistancePanel.setLayout(burningDistancePanelLayout);
        burningDistancePanelLayout.setHorizontalGroup(
                //                burningDistancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                //                .addComponent(burningDistanceScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                burningDistancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(burningDistancePanelLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(addBurningRowBt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeBurningRowBt)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, burningDistancePanelLayout.createSequentialGroup()
                        .addComponent(burningDistanceScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, burningDistancePanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(savePropDataBt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loadPropDataBt, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
        );
        burningDistancePanelLayout.setVerticalGroup(
                //                burningDistancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                //                .addGroup(burningDistancePanelLayout.createSequentialGroup()
                //                        .addComponent(burningDistanceScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                //                        .addGap(0, 0, Short.MAX_VALUE))
                burningDistancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(burningDistancePanelLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(burningDistancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(savePropDataBt)
                                .addComponent(loadPropDataBt))
                        .addGap(18, 18, 18)
                        .addComponent(burningDistanceScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(burningDistancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(addBurningRowBt)
                                .addComponent(removeBurningRowBt))
                        .addContainerGap(58, Short.MAX_VALUE))
        );

        sectionPropertiesTabbedPanel.addTab("Burning Distance", burningDistancePanel);

        javax.swing.GroupLayout geometricTabLayout = new javax.swing.GroupLayout(geometricTab);
        geometricTab.setLayout(geometricTabLayout);
        geometricTabLayout.setHorizontalGroup(
                geometricTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(geometricTabLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(leftGeoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rigthGeoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
        );
        geometricTabLayout.setVerticalGroup(
                geometricTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, geometricTabLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(geometricTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(leftGeoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rigthGeoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
        );

        mainTabbedPanel.addTab("Geometric", geometricTab);

        jLabel5.setText("Guess Pressure :");

        jLabel6.setText("Specific Impulse :");

        jLabel7.setText("Stop Pressure :");

        jLabel8.setText("Stop Time :");

        jLabel9.setText("Number of Segments :");

        precisionLabel.setText("Precision :");

        jLabel10.setText("PSI");

        jLabel11.setText("s");

        jLabel12.setText("PSI");

        jLabel13.setText("s");

        runSimulateBtn.setText("Simulate");
        runSimulateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runSimulateBtnActionPerformed(evt);
            }
        });

        abortSimulateBtn.setText("Abort");
        abortSimulateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abortSimulateBtnActionPerformed(evt);
            }
        });
        abortSimulateBtn.setEnabled(false);

        chartPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Output"));
        browseCompareButton.setText("Browse Compared Data");
        browseCompareButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseCompareButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout chartPanelThrustLayout = new javax.swing.GroupLayout(chartPanelThrust);
        chartPanelThrust.setLayout(chartPanelThrustLayout);
        chartPanelThrustLayout.setHorizontalGroup(
                chartPanelThrustLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 471, Short.MAX_VALUE)
        );
        chartPanelThrustLayout.setVerticalGroup(
                chartPanelThrustLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout chartPanelPressureLayout = new javax.swing.GroupLayout(chartPanelPressure);
        chartPanelPressure.setLayout(chartPanelPressureLayout);
        chartPanelPressureLayout.setHorizontalGroup(
                chartPanelPressureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 244, Short.MAX_VALUE)
        );
        chartPanelPressureLayout.setVerticalGroup(
                chartPanelPressureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 378, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout chartPanelLayout = new javax.swing.GroupLayout(chartPanel);
        chartPanel.setLayout(chartPanelLayout);
        chartPanelLayout.setHorizontalGroup(
                chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(chartPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(chartPanelLayout.createSequentialGroup()
                                        .addComponent(chartPanelPressure, 600, 650, 700)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(chartPanelThrust, 600, 650, 700))
                                .addComponent(browseCompareButton))
                        .addContainerGap(61, Short.MAX_VALUE))
        );
        chartPanelLayout.setVerticalGroup(
                chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, chartPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(browseCompareButton)
                        .addGroup(chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(chartPanelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                                        .addComponent(chartPanelThrust, 400, 500, 600)
                                        .addGap(7, 7, 7))
                                .addGroup(chartPanelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(chartPanelPressure, 400, 500, 600)
                                        .addContainerGap())))
        );

        javax.swing.GroupLayout simulationTabLayout = new javax.swing.GroupLayout(simulationTab);
        simulationTab.setLayout(simulationTabLayout);
        simulationTabLayout.setHorizontalGroup(
                simulationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(simulationTabLayout.createSequentialGroup()
                        .addGroup(simulationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(simulationTabLayout.createSequentialGroup()
                                        .addComponent(chartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        ))
                .addGroup(simulationTabLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(simulationTabLayout.createSequentialGroup()
                                .addGroup(simulationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(simulationTabLayout.createSequentialGroup()
                                                .addGroup(simulationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel5)
                                                        .addComponent(jLabel6)
                                                        .addComponent(jLabel7)
                                                        .addComponent(jLabel8)
                                                        .addComponent(jLabel9)
                                                        .addComponent(precisionLabel))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                                                .addGroup(simulationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(specificImp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(guessPSI, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jSpinner5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(stoptime, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(numSegments, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(outputPrecision, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(simulationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel12)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel13)))
                        .addGroup(simulationTabLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(runSimulateBtn)
                                .addComponent(abortSimulateBtn))
                        .addContainerGap(798, Short.MAX_VALUE))
        );
        simulationTabLayout.setVerticalGroup(
                simulationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(simulationTabLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(simulationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(jLabel10)
                                .addComponent(guessPSI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(simulationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(jLabel11)
                                .addComponent(specificImp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(simulationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(jLabel12)
                                .addComponent(jSpinner5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(simulationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(jLabel13)
                                .addComponent(stoptime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(simulationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(numSegments, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(simulationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(precisionLabel)
                                .addComponent(outputPrecision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(simulationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(runSimulateBtn)
                                .addComponent(abortSimulateBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(simulationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(chartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(539, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout leftGeoPanelLayout = new javax.swing.GroupLayout(leftGeoPanel);
        leftGeoPanel.setLayout(leftGeoPanelLayout);

        leftGeoPanelLayout.setHorizontalGroup(
                leftGeoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(leftGeoPanelLayout.createSequentialGroup()
                        .addGroup(leftGeoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(leftGeoPanelLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(leftGeoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel1)
                                                .addComponent(jLabel2)
                                                .addComponent(addSectionBT))
                                        .addGap(18, 18, 18)
                                        .addGroup(leftGeoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(removeSectionBT)
                                                .addGroup(leftGeoPanelLayout.createSequentialGroup()
                                                        .addGroup(leftGeoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(leftGeoPanelLayout.createSequentialGroup()
                                                                        .addComponent(rocketLengthSp, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(jLabel4))
                                                                .addGroup(leftGeoPanelLayout.createSequentialGroup()
                                                                        .addComponent(rocketDiameterSp, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(jLabel3)))
                                                        .addGap(62, 62, 62)
                                                        .addGroup(leftGeoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel14)
                                                                .addComponent(jLabel15))
                                                        .addGap(18, 18, 18)
                                                        .addGroup(leftGeoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(leftGeoPanelLayout.createSequentialGroup()
                                                                        .addComponent(ignitorTime, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(jLabel17))
                                                                .addGroup(leftGeoPanelLayout.createSequentialGroup()
                                                                        .addComponent(ignitorMass, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(jLabel16))))))
                                .addGroup(leftGeoPanelLayout.createParallelGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(drawCanvas, 650, 650, 650)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        leftGeoPanelLayout.setVerticalGroup(
                leftGeoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(leftGeoPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(leftGeoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(leftGeoPanelLayout.createSequentialGroup()
                                        .addGroup(leftGeoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel1)
                                                .addComponent(jLabel3)
                                                .addComponent(rocketDiameterSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(leftGeoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel2)
                                                .addComponent(jLabel4)
                                                .addComponent(rocketLengthSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(leftGeoPanelLayout.createSequentialGroup()
                                        .addGroup(leftGeoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel14)
                                                .addComponent(jLabel16)
                                                .addComponent(ignitorMass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(leftGeoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel15)
                                                .addComponent(jLabel17)
                                                .addComponent(ignitorTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        )
                        .addGap(18, 18, 18)
                        .addGroup(leftGeoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(addSectionBT)
                                .addComponent(removeSectionBT))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(drawCanvas, 600, 600, 600)
                        .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout rightGeoPanelLayout = new javax.swing.GroupLayout(rigthGeoPanel);
        rigthGeoPanel.setLayout(rightGeoPanelLayout);
        rightGeoPanelLayout.setHorizontalGroup(
                rightGeoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(rightGeoPanelLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(sectionPropertiesTabbedPanel, 480, 630, 780)
                        .addContainerGap(79, Short.MAX_VALUE))
        );
        rightGeoPanelLayout.setVerticalGroup(
                rightGeoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(rightGeoPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(sectionPropertiesTabbedPanel, 600, 720, 750)
                        .addContainerGap(10, Short.MAX_VALUE))
        );

        mainTabbedPanel.addTab("Simulation", simulationTab);

        fileMenu.setText("File");

        loadConfItem.setText("Load Configuration");
        loadConfItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadConfItemActionPerformed(evt);
            }
        });
        fileMenu.add(loadConfItem);

        saveConfItem.setText("Save Configuration");
        saveConfItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveConfItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveConfItem);

        exitItem.setText("Exit");
        exitItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitItem);

        mainMenuBar.add(fileMenu);

        setJMenuBar(mainMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(mainTabbedPanel)
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(mainTabbedPanel)
                        .addContainerGap())
        );

        enableComponents(sectionPropertiesTabbedPanel, false);
        pack();
    }

    public void setButtonGroup() {

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(circleToggleButton);
        buttonGroup.add(wheelToggleButton);
        buttonGroup.add(starToggleButton);
        buttonGroup.add(pentaToggleButton);
        buttonGroup.add(hexaToggleButton);
        buttonGroup.add(eightStarToggleButton);

    }

    public void setCanvas() {
        DOMImplementation dOMImplementation = SVGDOMImplementation.getDOMImplementation();

        document = dOMImplementation.createDocument(svgNS, "svg", null);

        svgRoot = document.getDocumentElement();

        svgRoot.setAttributeNS(null, "width", "1000");
        svgRoot.setAttributeNS(null, "height", "450");
        String rocketDiameterStr = String.valueOf(rocketDiameter);
        String rocketLengthStr = String.valueOf(rocketDiameter);
        String xPositionStr = String.valueOf(xPosition);
        String yPositionStr = String.valueOf(yPosition);
        String yMotorPositionStr = String.valueOf(yMotorPosition);

        Element motorCase = document.createElementNS(svgNS, "rect");
        motorCase.setAttributeNS(null, "x", "50");
        motorCase.setAttributeNS(null, "y", yMotorPositionStr);
        motorCase.setAttributeNS(null, "width", rocketLengthStr);
        motorCase.setAttributeNS(null, "height", rocketDiameterStr);
        motorCase.setAttributeNS(null, "stroke", "black");
        motorCase.setAttributeNS(null, "fill", "#CCCCCC");
        motorCase.setAttribute("id", "motorCase");

        Element igniter = document.createElementNS(svgNS, "rect");
        igniter.setAttributeNS(null, "x", xPositionStr);
        igniter.setAttributeNS(null, "y", yPositionStr);
        igniter.setAttributeNS(null, "width", "30");
        igniter.setAttributeNS(null, "height", "20");
        igniter.setAttributeNS(null, "stroke", "black");
        igniter.setAttributeNS(null, "fill", "red");
        igniter.setAttribute("id", "igniter");

        svgRoot.appendChild(motorCase);
        svgRoot.appendChild(igniter);
        registerListeners("motorCase");
        drawCanvas.setDocument(document);

        //Number diameterNumb = Double.valueOf(motorCase.getAttribute("width").toString());
//        Number lengthNumb = Double.valueOf(motorCase.getAttribute("height").toString());
        rocketDiameterSp.setValue(rocketDiameter);
        rocketLengthSp.setValue(rocketLength);

    }

    private void setSpinner(JSpinner spinner) {
        JComponent comp = spinner.getEditor();
        JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);
        DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();
        formatter.setCommitsOnValidEdit(true);

//        spinner.addChangeListener(new ChangeListener() {
//
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                
//            }
//        });
    }

    private void loadConfItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadConfItemActionPerformed
        JFileChooser chooserLoad = new JFileChooser();

        FileFilter loadFilter = new ExtensionFileFilter("Text Document(*.txt)", new String[]{"TXT"});
        chooserLoad.setFileFilter(loadFilter);

        int returnVal = chooserLoad.showOpenDialog(MainWindow.this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooserLoad.getSelectedFile();
            readFromFile(file.getPath());
            //This is where oldLengthSection real application would open the file.
            //log.append("Opening: " + file.getName() + "." + newline);
        } else {
            //log.append("Open command cancelled by user." + newline);
        }
        //log.setCaretPosition(log.getDocument().getLength());

    }//GEN-LAST:event_loadConfItemActionPerformed

    void saveToFile() throws Exception {
        BufferedWriter outfile = new BufferedWriter(new FileWriter(yourFile + ".txt"));
        outfile.write("Rocket_Diameter=" + Double.parseDouble(rocketDiameterSp.getValue().toString()) + "\n");
        outfile.write("Rocket_Length=" + Double.parseDouble(rocketLengthSp.getValue().toString()) + "\n");
        System.out.println(rocketLengthSp.getValue().toString());
        outfile.close();
    }

    void readFromFile(String readingFilePath) {
        FileInputStream fis;
        String rocketDiameter;
        String rocketLength;

        try {
            fis = new FileInputStream(readingFilePath);
            Scanner scanner = new Scanner(fis);

            do {
                rocketDiameter = scanner.nextLine();
                rocketLength = scanner.nextLine();
            } while (scanner.hasNextLine());

            String rocketDiameterSub = rocketDiameter.substring(rocketDiameter.lastIndexOf("=") + 1);
            String rocketLengthSub = rocketLength.substring(rocketLength.lastIndexOf("=") + 1);
            rocketDiameterSp.setValue(new Integer(rocketDiameterSub));
            rocketLengthSp.setValue(new Integer(rocketLengthSub));
            scanner.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//Read more: http://javarevisited.blogspot.com/2012/07/read-file-line-by-line-java-example-scanner.html#ixzz32pEnNHAw
//        try (BufferedReader br = new BufferedReader(new FileReader(readingFilePath))) {
//
//            String rocketDiameter;
//            String rocketLength;
//String sCurrentLine[];
//            while ((sCurrentLine = br.readLine()) != null) {
//				System.out.println(sCurrentLine);
//			}
//            do{
//                rocketDiameter = br.readLine();
//                rocketLength = br.readLine();
//            }
//            while (br.readLine().isEmpty()); {
//                
//            }
//            rocketDiameterSp.setValue(rocketDiameter.substring(rocketDiameter.lastIndexOf("=") + 1));
//            rocketLengthSp.setValue(rocketLength.substring(rocketLength.lastIndexOf("=") + 1));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    private void saveConfItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveConfItemActionPerformed
        JFileChooser chooserSave = new JFileChooser();
        FileFilter saveFilter = new ExtensionFileFilter("Text Document (*.txt)", new String[]{"TXT"});
        chooserSave.setFileFilter(saveFilter);
        int returnVal = chooserSave.showSaveDialog(MainWindow.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            yourFile = chooserSave.getSelectedFile();
            try {
                saveToFile();
                //This is where oldLengthSection real application would save the file.
                //log.append("Saving: " + file.getName() + "." + newline);

            } catch (Exception ex) {
                Logger.getLogger(MainWindow.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            //log.append("Save command cancelled by user." + newline);
        }
        //log.setCaretPosition(log.getDocument().getLength());
    }//GEN-LAST:event_saveConfItemActionPerformed

    private void exitItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitItemActionPerformed
        //System.exit(0);
        dispose();
    }//GEN-LAST:event_exitItemActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void runSimulateBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

    }

    private void abortSimulateBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

    }
    private void rocketDiameterSpStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rocketDiameterSpStateChanged
//        rocketDiameter = (Double)rocketDiameterSp.getValue();
//        String rocketDiameterStr = String.valueOf(rocketDiameter);
        String rocketDiameter = rocketDiameterSp.getValue().toString();
        Double diam = (Double) rocketDiameterSp.getValue();
        Element motorCase = document.getElementById("motorCase");
        Double oldDiam = Double.valueOf(motorCase.getAttribute("height"));
        motorCase.setAttribute("height", rocketDiameter);
        yPosition = yMotorPosition + diam / 2 - 10;
        String yPositionStr = String.valueOf(yPosition);
        Element igniter = document.getElementById("igniter");
        igniter.setAttribute("y", yPositionStr);

        for (SectionInfo section : sectionList) {
            Double oldYPosition = section.getyPosition();
            Double newYPosition = yMotorPosition + diam / 2 - section.getNewOuterDiameter() / 2;

            Element target = document.getElementById(section.getSection_id());
            target.setAttribute("y", newYPosition.toString());
            section.setyPosition(newYPosition);

            Double newInnerPortYPosition = newYPosition = yMotorPosition + diam / 2 - section.getNewInnerDiameter() / 2;
            String innerPortName = "innerPort" + section.getSection_id().substring(section.getSection_id().length() - 1);

            Element targetInnerPort = document.getElementById(innerPortName);
            targetInnerPort.setAttribute("y", String.valueOf(newInnerPortYPosition.toString()));

        }
        drawCanvas.setDocument(document);
    }//GEN-LAST:event_rocketDiameterSpStateChanged

    private void rocketLengthSpStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rocketLengthSpStateChanged
        String rocketLength = rocketLengthSp.getValue().toString();
        Element motorCase = document.getElementById("motorCase");
        motorCase.setAttribute("width", rocketLength);
        drawCanvas.setDocument(document);
    }//GEN-LAST:event_rocketLengthSpStateChanged

    private void addSectionBTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addSectionBTMouseClicked


    }//GEN-LAST:event_addSectionBTMouseClicked

    private void removeSectionBTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeSectionBTMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_removeSectionBTMouseClicked

    private void addSectionBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSectionBTActionPerformed
        //String diameterSectionStr;
        //String lengthSectionStr;
        final AddSectionPopup addSectionPopup = new AddSectionPopup();

        addSectionPopup.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println(addSectionPopup.isOK);
                if (addSectionPopup.isOK) {

                    String diameterSectionStr = addSectionPopup.getDiameterSectionStr();
                    String lengthSectionStr = addSectionPopup.getLengthSectionStr();
                    String innerPortSectionString = addSectionPopup.getInnerPortSectionStr();

                    enableComponents((Container) sectionPropertiesTabbedPanel, true);
                    Double totalLength = 0.0;
                    for (SectionInfo section : sectionList) {
                        totalLength += section.getLengthSection();

                    }
                    lengthSection = Double.valueOf(lengthSectionStr);
                    String sectionName = "section" + sectionNo;
                    String innerPortName = "innerPort" + sectionNo;
                    sectionNo++;
                    Double diam = (Double) rocketDiameterSp.getValue();

                    //String xPositionSectionStr = String.valueOf(xPosition + totalLength);
                    //svgRoot = document.getElementsByTagName('svg');
                    String xPositionSectionStr = "";
                    String no = "";
                    int idx = sectionList.size();
                    if (selectedSomething != true) {
//                         if (selectedSection == null ) {
                        xPositionSectionStr = String.valueOf(xPosition + totalLength);
//                        }
//                         else{
//                        no = selectedSection.getSection_id().substring(selectedSection.getSection_id().length() - 1);
//                        xPositionSectionStr = String.valueOf(xPosition + totalLength);
//                         }
                    } else {
                        //idx = 0;
                        Double totalLengthBefore = 0.0;
                        Double currX = selectedSection.getxPosition();
                        Double currLength = selectedSection.getLengthSection();
                        xPositionSectionStr = String.valueOf(currX + currLength);
//                        for (SectionInfo thisSection : sectionList) {
//                            Double length = thisSection.getLengthSection();
//                            String sectionId = thisSection.getSection_id();
//                            
//                            totalLengthBefore += length;
//                            if (thisSection.getSection_id() == selectedSection.getSection_id()) {
//                                idx = sectionList.indexOf(thisSection);
//                                
//                                xPositionSectionStr = String.valueOf(xPosition + totalLengthBefore);
//                                break;
//                            }
//    
//                        }
                        for (SectionInfo thisSection : sectionList) {
                            if (thisSection.getSection_id() == selectedSection.getSection_id()) {
                                idx = sectionList.indexOf(thisSection) + 1;

                                break;
                            }
                        }
                        for (int i = idx; i < sectionList.size(); i++) {
                            SectionInfo thisSection = sectionList.get(i);
                            Element next_section = document.getElementById(thisSection.getSection_id());
                            String thisInnerPortName = "innerPort" + (thisSection.getSection_id().substring(thisSection.getSection_id().length() - 1));
                            System.out.println(thisSection.getSection_id());
                            System.out.println(innerPortName);
                            Double oldX = thisSection.getxPosition();

                            next_section.setAttribute("x", String.valueOf(oldX + lengthSection));

                            Element next_innerPort = document.getElementById(thisInnerPortName);
                            next_innerPort.setAttribute("x", String.valueOf(oldX + lengthSection));
                            thisSection.setxPosition(oldX + lengthSection);
                        }
                    }

                    String yPositionSectionStr = String.valueOf(yMotorPosition + (diam / 2.0) - (Double.valueOf(diameterSectionStr) / 2.0));
                    String yInnerPortPosition = String.valueOf((diam / 2.0) - (Double.valueOf(innerPortSectionString) / 2.0) + yMotorPosition);

                    int randomColor = new Random().nextInt(colors.length);

                    Element section = document.createElementNS(svgNS, "rect");
                    section.setAttributeNS(null, "x", xPositionSectionStr);
                    section.setAttributeNS(null, "y", yPositionSectionStr);
                    section.setAttributeNS(null, "width", lengthSectionStr);
                    section.setAttributeNS(null, "height", diameterSectionStr);
                    section.setAttributeNS(null, "stroke", "black");
                    section.setAttributeNS(null, "fill", colors[randomColor]);
                    section.setAttributeNS(null, "fill-opacity", "0.5");
                    section.setAttribute("id", sectionName);
                    //Node sectionNode = (Node) section;

                    //     xAddMoreSection = xAddMoreSection + lengthSection;
                    Element innerPort = document.createElementNS(svgNS, "rect");
                    innerPort.setAttributeNS(null, "x", xPositionSectionStr);
                    innerPort.setAttributeNS(null, "y", yInnerPortPosition);
                    innerPort.setAttributeNS(null, "width", lengthSectionStr);
                    innerPort.setAttributeNS(null, "height", innerPortSectionString);
                    innerPort.setAttributeNS(null, "stroke", "black");
                    innerPort.setAttributeNS(null, "fill", "grey");
                    innerPort.setAttributeNS(null, "fill-opacity", "0.8");
                    innerPort.setAttribute("id", innerPortName);
                    svgRoot.appendChild(section);
                    svgRoot.appendChild(innerPort);
                    drawCanvas.setDocument(document);
                    SectionInfo newSection = new SectionInfo(Double.valueOf(diameterSectionStr), Double.valueOf(innerPortSectionString), sectionName, innerPortName, lengthSection, Double.valueOf(xPositionSectionStr), Double.valueOf(yPositionSectionStr));
                    defaultDrawCircle(newSection);

                    //if(selectedSection==null) {
                    selectedSection = newSection;
                    sectionList.add(idx, newSection);
                    circleToggleButton.setSelected(true);
                    selectedSomething = true;
                    Element target = document.getElementById(selectedSection.getSection_id());
                    target.setAttribute("stroke-width", "2");
                    for (SectionInfo sec : sectionList) {

                        if (!sec.getSection_id().equalsIgnoreCase(selectedSection.getSection_id())) {

                            Element elm = document.getElementById(sec.getSection_id());
                            elm.setAttribute("stroke-width", "1");
                        }
                    }

                    setSectionInfoView();
                    DefaultTableModel model = (DefaultTableModel) burningDistanceTable.getModel();
                    model.setRowCount(0);

                    //}
                    registerListeners(sectionName);
                    registerListeners(innerPortName);
                    System.out.println("inner port " + innerPortSectionString);

                    //selectedSomething = false;
                }
                addSectionPopup.dispose();
            }

        });

        addSectionPopup.setVisible(true);
        //System.out.println(diameterSectionStr);
    }//GEN-LAST:event_addSectionBTActionPerformed

    private void removeSectionBTActionPerformed(java.awt.event.ActionEvent evt) {
        String sectionId = selectedSection.getSection_id();
        String innerPortID = selectedSection.getInnerPort_id();
        Double lengthRemoveSection = selectedSection.getLengthSection();
        Element targetSection = document.getElementById(sectionId);
        Node targetSectionNode = (Node) targetSection;

        Element targetInnerPort = document.getElementById(innerPortID);
        Node targetInnerPortNode = (Node) targetInnerPort;

        targetSectionNode.getParentNode().removeChild(targetSectionNode);
        targetInnerPortNode.getParentNode().removeChild(targetInnerPortNode);
        drawCanvas.setDocument(document);

        int idx = 0;
        for (SectionInfo section : sectionList) {

            if (section.getSection_id() == selectedSection.getSection_id()) {
                idx = sectionList.indexOf(section);

            }

        }

        for (int i = idx + 1; i < sectionList.size(); i++) {
            SectionInfo section = sectionList.get(i);
            Element next_section = document.getElementById(section.getSection_id());

            String innerPortName = "innerPort" + section.getSection_id().substring(section.getSection_id().length() - 1);
            System.out.println(section.getSection_id());
            System.out.println(innerPortName);
            Double oldX = section.getxPosition();
            next_section.setAttribute("x", String.valueOf(oldX - lengthRemoveSection));

            Element next_innerPort = document.getElementById(innerPortName);
            next_innerPort.setAttribute("x", String.valueOf(oldX - lengthRemoveSection));
            section.setxPosition(oldX - lengthRemoveSection);
        }
        sectionList.remove(selectedSection);
    }

    private void findCenter(SectionInfo section) {
        SVGDocument document = section.getCADDoc();
        Element circle = document.getElementById("ID_8E0");
        section.setCx(Double.valueOf(circle.getAttribute("cx")));
        section.setCy(Double.valueOf(circle.getAttribute("cy")));
        Point center = new Point();
        center.setX(Double.valueOf(circle.getAttribute("cx")));
        center.setY(Double.valueOf(circle.getAttribute("cy")));
        section.setCenter(center);
    }

    private void setSectionInfoView() {
        DefaultTableModel model = (DefaultTableModel) propellantTable.getModel();
        model.setRowCount(0);
        List<PropellantLayer> layers = selectedSection.getLayers();
        //System.out.println(layers.size());
        for (int i = 0; i < layers.size(); i++) {
            PropellantLayer layer = layers.get(i);
            model.addRow(new Object[]{layer.getLayerId(), layer.getLayerName(), layer.getLayerMaterial()});
        }
        loadPropDataTable();
        System.out.println(selectedSection.getNewOuterDiameter());
        outerDiameterSpinner.setValue(selectedSection.getNewOuterDiameter());
        innerDiameterSpinner.setValue(selectedSection.getNewInnerDiameter());
        lengthSectionSpinner.setValue(selectedSection.getLengthSection());

        propellantTable.setRowSelectionInterval(0, 0);
        setPropertiesView(layers.get(0));
        cadPanelShape.setDocument(selectedSection.getCADDoc());

    }

    private void setPropertiesView(PropellantLayer layer) {

        burningRateTB.setText(String.valueOf(layer.getBurningRate()));
        pressureExponentTB.setText(String.valueOf(layer.getPressureExponent()));
        densityTB.setText(String.valueOf(layer.getDensity()));
        alphaConstTB.setText(String.valueOf(layer.getBurningConst()));
        gasTempTB.setText(String.valueOf(layer.getGasTemp()));
        gasConstTB.setText(String.valueOf(layer.getGasConst()));
        heatRatioTB.setText(String.valueOf(layer.getHeatRatio()));
        maxBurntTB.setText(String.valueOf(layer.getMaxBurningDistance()));

        selectedLayer = layer;
    }

    private void rocketLengthSp1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rocketLengthSp1StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_rocketLengthSp1StateChanged
    private void ignitorTimeStateChanged(javax.swing.event.ChangeEvent evt) {
        // TODO add your handling code here:
    }
    private void rocketDiameterSp1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rocketDiameterSp1StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_rocketDiameterSp1StateChanged
    private void ignitorMassStateChanged(javax.swing.event.ChangeEvent evt) {
        // TODO add your handling code here:
    }
    private void propellantTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_propellantTableMouseClicked

        DefaultTableModel model = (DefaultTableModel) propellantTable.getModel();
        int selectedRow = propellantTable.getSelectedRow();

        if (selectedRow != -1) {
            int selectedId = (int) model.getValueAt(selectedRow, 0);
            List<PropellantLayer> layers = selectedSection.getLayers();
            PropellantLayer layer = null;
            for (PropellantLayer propellantLayer : layers) {
                if (propellantLayer.getLayerId() == selectedId) {
                    layer = propellantLayer;
                }
            }

            setPropertiesView(layer);

        }
    }//GEN-LAST:event_propellantTableMouseClicked

    private void addPropellantBtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPropellantBtMouseClicked
        PropellantLayer layer = selectedSection.addNewLayer();

        DefaultTableModel model = (DefaultTableModel) propellantTable.getModel();
        model.addRow(new Object[]{layer.getLayerId(), layer.getLayerName(), layer.getLayerMaterial()});

        setPropertiesView(layer);
    }//GEN-LAST:event_addPropellantBtMouseClicked

    private void removePropellantBtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removePropellantBtMouseClicked
        selectedSection.removeLayer(selectedLayer);
        DefaultTableModel model = (DefaultTableModel) propellantTable.getModel();
        int selectedRow = propellantTable.getSelectedRow();
        if (selectedRow != -1) {
            sectionList.remove(propellantTable.getSelectedRow());
            model.removeRow(propellantTable.getSelectedRow());

        }
        int totalRow = propellantTable.getRowCount();

        for (int countRow = 0; countRow < totalRow; countRow++) {
            model.setValueAt(countRow + 1, countRow, 0);

        }
    }//GEN-LAST:event_removePropellantBtMouseClicked

    private void savePropertiesBTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_savePropertiesBTMouseClicked
        selectedLayer.setBurningRate(Double.parseDouble(burningRateTB.getText()));
        selectedLayer.setPressureExponent(Double.parseDouble(pressureExponentTB.getText()));
        selectedLayer.setDensity(Double.parseDouble(densityTB.getText()));
        selectedLayer.setBurningConst(Double.parseDouble(alphaConstTB.getText()));
        selectedLayer.setGasTemp(Double.parseDouble(gasTempTB.getText()));
        selectedLayer.setGasConst(Double.parseDouble(gasConstTB.getText()));
        selectedLayer.setHeatRatio(Double.parseDouble(heatRatioTB.getText()));
        selectedLayer.setMaxBurningDistance(Double.parseDouble(maxBurntTB.getText()));
        selectedLayer.setBurningStartDistance(Double.parseDouble(startBurningDistanceTb.getText()));
    }//GEN-LAST:event_savePropertiesBTMouseClicked

    private void savePropertiesBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savePropertiesBTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_savePropertiesBTActionPerformed

    private void circleToggleButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_circleToggleButtonItemStateChanged
        String path = "dti/image/DTICircle.dxf";

        if (circleToggleButton.isSelected()) {
            setCADShape(path);
            showSVG(cadPanelShape);
            findCenter(selectedSection);
            selectedSection.setIsCircle(true);
            cad.setInnerShape(selectedSection.getNewInnerDiameter(), cadPanelShape, selectedSection.getCADDoc());
            cad.setOuterShape(selectedSection.getNewOuterDiameter(), cadPanelShape, selectedSection.getCADDoc());
            selectedSection.zoomLevel = 1.0;
    }//GEN-LAST:event_circleToggleButtonItemStateChanged
    }

    private void defaultDrawCircle(SectionInfo section) {
        String path = "dti/image/DTICircle.dxf";

        setCADShape(path);
        section.setCADDoc(cad.outputSVG());
        findCenter(section);
        section.setIsCircle(true);
        cad.setInnerShape(section.getNewInnerDiameter(), cadPanelShape, section.getCADDoc());
        cad.setOuterShape(section.getNewOuterDiameter(), cadPanelShape, section.getCADDoc());
        section.zoomLevel = 1.0;
    }
    private void wheelToggleButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_wheelToggleButtonItemStateChanged
        String path = "dti/image/DTIWheel.dxf";
        setCADShape(path);

        showSVG(cadPanelShape);
        findCenter(selectedSection);
        cad.extractInnerPort(selectedSection.getCADDoc(), selectedSection.getPoints());
        selectedSection.setIsCircle(false);
        selectedSection.zoomLevel = 1.0;
    }//GEN-LAST:event_wheelToggleButtonItemStateChanged

    private void starToggleButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_starToggleButtonItemStateChanged
        String path = "dti/image/DTIStar.dxf";
        setCADShape(path);
        showSVG(cadPanelShape);
        findCenter(selectedSection);
        cad.rearrangePath(selectedSection.getCADDoc());
        cad.extractInnerPort(selectedSection.getCADDoc(), selectedSection.getPoints());

        cad.resizeInnerPort(selectedSection.getCenter(), selectedSection.getNewInnerDiameter() / 2, selectedSection.getPoints(), selectedSection.getCADDoc());
        cad.setOuterShape(selectedSection.getNewOuterDiameter(), cadPanelShape, selectedSection.getCADDoc());

        cadPanelShape.setSVGDocument(selectedSection.getCADDoc());
        selectedSection.setIsCircle(false);
        selectedSection.zoomLevel = 1.0;
    }//GEN-LAST:event_starToggleButtonItemStateChanged

    private void hexaToggleButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_hexaToggleButtonItemStateChanged
        String path = "dti/image/DTIHexagon.dxf";
        setCADShape(path);
        showSVG(cadPanelShape);
        findCenter(selectedSection);
        selectedSection.setIsCircle(false);
        selectedSection.zoomLevel = 1.0;
    }//GEN-LAST:event_hexaToggleButtonItemStateChanged

    private void pentaToggleButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_pentaToggleButtonItemStateChanged
        String path = "dti/image/DTIPentagon.dxf";
        setCADShape(path);
        showSVG(cadPanelShape);
        findCenter(selectedSection);
        selectedSection.setIsCircle(false);
        selectedSection.zoomLevel = 1.0;
    }//GEN-LAST:event_pentaToggleButtonItemStateChanged

    private void eightStarToggleButtonItemStateChanged(java.awt.event.ItemEvent evt) {
        String path = "dti/image/DTIEightStar.dxf";
        setCADShape(path);
        showSVG(cadPanelShape);
        findCenter(selectedSection);
        selectedSection.setIsCircle(false);
        selectedSection.zoomLevel = 1.0;
    }

    private void zoomInButtonActionPerformed(java.awt.event.ActionEvent evt) {

        cad.zoom(cadPanelShape, selectedSection.getCADDoc(), selectedSection.zoomLevel + 0.1, selectedSection.panX, selectedSection.panY);
        selectedSection.zoomLevel = selectedSection.zoomLevel + 0.1;
    }

    private void zoomOutButtonActionPerformed(java.awt.event.ActionEvent evt) {
        cad.zoom(cadPanelShape, selectedSection.getCADDoc(), selectedSection.zoomLevel - 0.1, selectedSection.panX, selectedSection.panY);
        selectedSection.zoomLevel = selectedSection.zoomLevel - 0.1;
    }

    private void arrowUpButtonActionPerformed(java.awt.event.ActionEvent evt) {
        selectedSection.panY = selectedSection.panY - 5;
        cad.zoom(cadPanelShape, selectedSection.getCADDoc(), selectedSection.zoomLevel, selectedSection.panX, selectedSection.panY);

    }

    private void arrowDownButtonActionPerformed(java.awt.event.ActionEvent evt) {
        selectedSection.panY = selectedSection.panY + 5;
        cad.zoom(cadPanelShape, selectedSection.getCADDoc(), selectedSection.zoomLevel, selectedSection.panX, selectedSection.panY);

    }

    private void arrowRightButtonActionPerformed(java.awt.event.ActionEvent evt) {
        selectedSection.panX = selectedSection.panX + 5;
        cad.zoom(cadPanelShape, selectedSection.getCADDoc(), selectedSection.zoomLevel, selectedSection.panX, selectedSection.panY);

    }

    private void arrowLeftButtonActionPerformed(java.awt.event.ActionEvent evt) {
        selectedSection.panX = selectedSection.panX - 5;
        cad.zoom(cadPanelShape, selectedSection.getCADDoc(), selectedSection.zoomLevel, selectedSection.panX, selectedSection.panY);

    }

    private void centerButtonActionPerformed(java.awt.event.ActionEvent evt) {
        cad.zoom(cadPanelShape, selectedSection.getCADDoc(), selectedSection.zoomLevel, 0, 0);
        selectedSection.panX = 0;
        selectedSection.panY = 0;
    }

    private void innerDiameterSpinStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_innerDiameterSpinStateChanged
        // newInnerDiameter = (Integer)innerDiameterSpinner.getValue();
        Double newInnerDiameter = (Double) innerDiameterSpinner.getValue();
        String newInnerDiameterStr = innerDiameterSpinner.getValue().toString();
        Double diam = (Double) rocketDiameterSp.getValue();
        String yInnerPortPosition = String.valueOf((diam / 2.0) - (Double.valueOf(newInnerDiameterStr) / 2.0) + yMotorPosition);

        Element innerPort = document.getElementById(selectedSection.getInnerPort_id());
        innerPort.setAttribute("height", newInnerDiameterStr);
        innerPort.setAttribute("y", yInnerPortPosition);

        drawCanvas.setDocument(document);
        selectedSection.setNewInnerDiameter(newInnerDiameter);
        if (circleToggleButton.isSelected()) {
            cad.setInnerShape(newInnerDiameter, cadPanelShape, selectedSection.getCADDoc());
        } else if (starToggleButton.isSelected()) {
            cad.resizeInnerPort(selectedSection.getCenter(), selectedSection.getNewInnerDiameter() / 2, selectedSection.getPoints(), selectedSection.getCADDoc());
        }
    }//GEN-LAST:event_innerDiameterSpinStateChanged

    private void outerDiameterSpinStateChanged(javax.swing.event.ChangeEvent evt) {

        Double newOuterDiameter = (Double) outerDiameterSpinner.getValue();
        String newOuterDiameterStr = outerDiameterSpinner.getValue().toString();
        Double diam = (Double) rocketDiameterSp.getValue();
        String ySectionPosition = String.valueOf(yMotorPosition + (diam / 2.0) - (Double.valueOf(newOuterDiameterStr) / 2.0));
        Element section = document.getElementById(selectedSection.getSection_id());

        section.setAttributeNS(null, "height", newOuterDiameterStr);
        section.setAttribute("y", ySectionPosition);

        drawCanvas.setDocument(document);
        selectedSection.setNewOuterDiameter(newOuterDiameter);
        //if (circleToggleButton.isSelected()) {
        cad.setOuterShape(newOuterDiameter, cadPanelShape, selectedSection.getCADDoc());
        //}
    }

    private void lengthSpinStateChanged(javax.swing.event.ChangeEvent evt) {

        Double newLengthSection = (Double) lengthSectionSpinner.getValue();
        Double oldLengthSection = selectedSection.getLengthSection();
        Double diff = newLengthSection - oldLengthSection;
        selectedSection.setLengthSection(newLengthSection);
        String newLengthSectionStr = selectedSection.getLengthSection().toString();

        Double diam = (Double) rocketDiameterSp.getValue();

        // String yPositionSectionStr = String.valueOf(yMotorPosition + (diam / 2.0) - (Double.valueOf(newOuterDiameterStr) / 2.0));
        //String newX = String.valueOf(selectedSection.getxPosition() + diff);
        Element thisSection = document.getElementById(selectedSection.getSection_id());
        thisSection.setAttribute("width", newLengthSectionStr);

        String thisInnerPortName = "innerPort" + selectedSection.getSection_id().substring(selectedSection.getSection_id().length() - 1);
        Element this_innerPort = document.getElementById(thisInnerPortName);
        this_innerPort.setAttribute("width", newLengthSectionStr);
        //int rowNo = selectedSection.getRowNo();
        int idx = 0;
        for (SectionInfo section : sectionList) {

            if (section.getSection_id() == selectedSection.getSection_id()) {
                idx = sectionList.indexOf(section);

            }

        }
        for (int i = idx + 1; i < sectionList.size(); i++) {
            SectionInfo section = sectionList.get(i);
            Element next_section = document.getElementById(section.getSection_id());
            String innerPortName = "innerPort" + section.getSection_id().substring(section.getSection_id().length() - 1);
            System.out.println(section.getSection_id());
            System.out.println(innerPortName);
            Double oldX = section.getxPosition();
            next_section.setAttribute("x", String.valueOf(oldX + diff));

            Element next_innerPort = document.getElementById(innerPortName);
            next_innerPort.setAttribute("x", String.valueOf(oldX + diff));
            section.setxPosition(oldX + diff);
        }

        //selectedSection.setLengthSection(lengthSection);
        drawCanvas.setDocument(document);
    }
    private void innerDiameterSpinInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_innerDiameterSpinInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_innerDiameterSpinInputMethodTextChanged

    private void loadPropDataBtActionPerformed(java.awt.event.ActionEvent evt) {

        JFileChooser fc = new JFileChooser();
        FileFilter ff = new FileNameExtensionFilter("Data Files", new String[]{"dat", "csv"});
        fc.setFileFilter(ff);
        fc.addChoosableFileFilter(ff);
        //fc.setAcceptAllFileFilterUsed(false);
        int retVal = fc.showOpenDialog(fc);
        DefaultTableModel model = (DefaultTableModel) burningDistanceTable.getModel();
        if (retVal == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fc.getSelectedFile();
                //This is where an application would open the file.
                //JOptionPane.showMessageDialog(this, "Opening: " + file.getName() + ".\n" );
                InputStream fis;
                BufferedReader br;
                String line;
                fis = new FileInputStream(file);
                br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
                try {

                    model.setRowCount(0);
                    while ((line = br.readLine()) != null) {
                        // Deal with the line
                        //System.out.println(line);//debug code
                        String[] parts = line.split(",", 3);
                        Double x1d_A = Double.parseDouble(parts[0]);
                        Double portArea1_A = Double.parseDouble(parts[1]);
                        Double periphery1_A = Double.parseDouble(parts[2]);

                        model.addRow(new Object[]{x1d_A, portArea1_A, periphery1_A});

                    }
                } catch (IOException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        Vector data = model.getDataVector();
        selectedSection.setBurningList(data);
    }

    private void savePropDataBtActionPerformed(java.awt.event.ActionEvent evt) {

        List<BurningDistance> burningList = new ArrayList<BurningDistance>();
        DefaultTableModel model = (DefaultTableModel) burningDistanceTable.getModel();
        Vector data = model.getDataVector();

        selectedSection.setBurningList(data);

        String outfilename = "GLRS.csv";
        StringBuilder out = new StringBuilder();
        // out.append("Distance (mm),Peripheral (mm),Peripheral (mm)");
        // out.append(System.getProperty("line.separator"));

        for (int i = 0; i < model.getRowCount(); i++) {
            out.append(model.getValueAt(i, 0));
            out.append(",");
            out.append(model.getValueAt(i, 1));
            out.append(",");
            out.append(model.getValueAt(i, 2));
            out.append(System.getProperty("line.separator"));
        }

        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setSelectedFile(new File(outfilename));
        int retVal = jFileChooser.showSaveDialog(jFileChooser);
        if (retVal == JFileChooser.APPROVE_OPTION) {
            try {
                File file = jFileChooser.getSelectedFile();
                FileOutputStream fop = new FileOutputStream(file);

                if (!file.exists()) {
                    file.createNewFile();
                }

                byte[] contentInBytes = out.toString().getBytes();

                fop.write(contentInBytes);
                fop.flush();
                fop.close();

                JOptionPane.showMessageDialog(this, "Save output successfully.");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error: Can't save to file");
        }
    }

    private void loadPropDataTable() {

        Vector<String> columnNames = new Vector<String>();
        columnNames.add("Distance (mm)");
        columnNames.add("Peripheral (mm)");
        columnNames.add("Peripheral (mm)");
        Vector burning = selectedSection.getBurningList();
        DefaultTableModel model = new DefaultTableModel(burning, columnNames);
        burningDistanceTable.setModel(model);

    }

    private void addBurningRowBtActionPerformed(java.awt.event.ActionEvent evt) {
        // BurningDistance burningDistance = selectedSection.addNewBurning();
        DefaultTableModel model = (DefaultTableModel) burningDistanceTable.getModel();
        int selectedRow = burningDistanceTable.getSelectedRow();
        if (selectedRow != -1) {
            model.insertRow(selectedRow + 1, new Object[]{"", "", ""});
            //model.addRow(new Object[]{" ", " ", " "});
        } else {
            model.addRow(new Object[]{"", "", ""});
        }
    }

    private void removeBurningRowBtActionPerformed(java.awt.event.ActionEvent evt) {

        DefaultTableModel model = (DefaultTableModel) burningDistanceTable.getModel();
        int selectedRow = burningDistanceTable.getSelectedRow();
        if (selectedRow != -1) {
            // sectionList.remove(burningDistanceTable.getSelectedRow());
            model.removeRow(burningDistanceTable.getSelectedRow());
        }
        int totalRow = burningDistanceTable.getRowCount();
        for (int countRow = 0; countRow < totalRow; countRow++) {
            model.setValueAt(countRow + 1, countRow, 0);
        }
    }

    public void setCADShape(String path) {
        cad.parseFile(path);

    }

    public void showSVG(JSVGCanvas canvas) {
        selectedSection.setCADDoc(cad.outputSVG());
        canvas.setSVGDocument(selectedSection.getCADDoc());
    }

    public void enableComponents(Container container, boolean enable) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            component.setEnabled(enable);
            if (component instanceof Container) {
                enableComponents((Container) component, enable);
            }
        }
    }

    private void browseCompareButtonActionPerformed(java.awt.event.ActionEvent evt) {

        JFileChooser fc = new JFileChooser();
        FileFilter ff = new FileNameExtensionFilter("Data Files", new String[]{"dat", "csv"});
        fc.setFileFilter(ff);
        fc.addChoosableFileFilter(ff);
        //fc.setAcceptAllFileFilterUsed(false);
        int retVal = fc.showOpenDialog(fc);

        if (retVal == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fc.getSelectedFile();
                //This is where an application would open the file.
                //JOptionPane.showMessageDialog(this, "Opening: " + file.getName() + ".\n" );
                InputStream fis;
                BufferedReader br;
                String line;
                fis = new FileInputStream(file);
                br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
                try {

                    String firstLine = br.readLine();
                    while ((line = br.readLine()) != null) {
                        // Deal with the line

                        String[] parts = line.split(",", 12);
                        Double time = Double.parseDouble(parts[0]);
                        Double pressure = Double.parseDouble(parts[10]);
                        Double thrust = Double.parseDouble(parts[11]);

                        //traceComparePressure = new Trace2DLtd();
                        traceComparePressure.addPoint(time, pressure);
                        //traceCompareThrust = new Trace2DLtd();
                        traceCompareThrust.addPoint(time, thrust);

                        ArrayList<Double> browseArray = new ArrayList<Double>();
                        browseArray.add(pressure);
                        browseArray.add(thrust);
                        //model.addRow(new Object[]{x1d_A, portArea1_A, periphery1_A});

                    }
                } catch (IOException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

//        Vector data = model.getDataVector();
//        selectedSection.setBurningList(data);
    }

    private Chart2D initChart(String yAxisName, String xAxisName) {
        Chart2D chart = new Chart2D();
        IAxis yAxis = chart.getAxisY();
        IAxis xAxis = chart.getAxisX();
        IAxis.AxisTitle yTitle = new IAxis.AxisTitle(yAxisName);
        yAxis.setAxisTitle(yTitle);
        IAxis.AxisTitle xTitle = new IAxis.AxisTitle(xAxisName);
        xAxis.setAxisTitle(xTitle);
        xAxis.setPaintGrid(true);
        yAxis.setPaintGrid(true);

        return chart;

    }

    private void addChartToPanel(Chart2D chart, JPanel panel) {
        ChartPanel cp = new ChartPanel(chart);
        cp.setSize(800, 300);
        panel.add(cp);

    }

    private void addTrace(ITrace2D trace, String tracename, Color tracecolor, Chart2D chart) {

        // Create an ITrace: 
        // Note that dynamic charts need limited amount of values!!! 
        //trace = new Trace2DLtd(200);
        trace.setColor(tracecolor);
        trace.setName(tracename);
        // Add the traceOutputThrust to the chart. This has to be done before adding points (deadlock prevention): 
        chart.addTrace(trace);

    }

    public class calThread extends Thread {

        public volatile boolean isAborted = false;

        @Override
        public void run() {

            // Calculate Thrust profile
            abortSimulateBtn.setEnabled(true);

            double Dt = Double.parseDouble(rocketDiameterSp.getValue().toString()) / 1000;   //Throat diameter (m)
            double At = (Math.PI) * Math.pow(Dt, 2) / 4;                     //Throat cross section area (m^2)
            double Lt = Double.parseDouble(rocketLengthSp.getValue().toString());        //Rocket Length (m)
//            double D1A = Double.parseDouble(propGeoWin.innerGrainA.getText())/1000; //Inner grain of section A where 2nd propellant start to burnt
//            double D1B = Double.parseDouble(propGeoWin.innerGrainB.getText())/1000; //Inner grain of section A where 2nd propellant start to burnt
            int N = (int) numSegments.getValue();             //Number of Segment
//            int Nch = Integer.parseInt(segmentChange.getText());        //Segment where port starts to change shape
            double Ln = Lt / N;                                           //Segment Length (m)
//
//            //1st Propellant parameters
//            double rb0_1 = Double.parseDouble(burnrate1.getText())/1000;//Burn rate of 1st propellant (m/s)
//            double n1 = Double.parseDouble(pressureExp1.getText());     //Pressure Exponent
//            double rho1 = Double.parseDouble(density1.getText())*1000;  //Density (kg/m^3)
//            double alpha1 = Double.parseDouble(alphaConst1.getText())/(Math.pow(10,7));   //Erosive burning constant
//            double T1 = Double.parseDouble(gastemp1.getText());         //Gas Temperature (K)
//            double R1 = Double.parseDouble(gasconst1.getText());        //Gas Constant (J/(kg*K))
//            double gamma1 = Double.parseDouble(heatcap1.getText());     //Heat Capacity Ratio
//
//            //2nd Propellant parameters
//            double rb0_2 = Double.parseDouble(burnrate2.getText())/1000;//Burn rate of 2nd propellant (m/s)
//            double n2 = Double.parseDouble(pressureExp2.getText());     //Pressure Exponent
//            double rho2 = Double.parseDouble(density2.getText())*1000;  //Density (kg/m^3)
//            double alpha2 = Double.parseDouble(alphaConst2.getText())/(Math.pow(10,7));   //Erosive burning constant
//            double T2 = Double.parseDouble(gastemp2.getText());         //Gas Temperature (K)
//            double R2 = Double.parseDouble(gasconst2.getText());        //Gas Constant (J/(kg*K))
//            double gamma2 = Double.parseDouble(heatcap2.getText());     //Heat Capacity Ratio
//
//
            double Pg = ((double) guessPSI.getValue()) * 6895;    //Guess pressure (N/m^2)
            double Isp = (double) specificImp.getValue();     //Specific Impulse (s)
//
            double P0 = 1000 * 6895;                                      //Reference pressure (N/m^2)
//            double a1 = rb0_1/(Math.pow(P0,n1));                        //pre-exponential factor of 1st propellant
//            double a2 = rb0_2/(Math.pow(P0,n2));                        //pre-exponential factor of 1st propellant
//
            double tdelta = 0.01;                                       //time step (s)
            double climit = 0.00001;                                    //convergence limit
//
//            double[] rb1 = new double[N+1];                               //burning rate of the 1st propellant at time t of each segment
//            double[] rb2 = new double[N+1];                               //burning rate of the 2nd propellant at time t of each segment
//
//            double[] x1 = new double[N+1];                                //distance burnt of the 1st propellant at time t of each segment
//            double[] x2 = new double[N+1];                                //distance burnt of the 2nd propellant at time t of each segment
//
//            double[] Ap = new double[N+1];                               //Port area  at time t of each segment
//
//            double[] Peri1 = new double[N+1];                             //Periphery of the 1st propellant at time t of each segment
//            double[] Peri2 = new double[N+1];                             //Periphery of the 2nd propellant at time t of each segment
//
//            double[] rb_m0_1 = new double[N+1];                           //Burning rate at zero mass flow of the 1st propellant at time t of each segment
//            double[] rb_m0_2 = new double[N+1];                           //Burning rate at zero mass flow of the 2nd propellant at time t of each segment
//
//            double[] mdot = new double[N+1];                             //Mass flow rate at time t at each segment (kg/s)
//            double[] mdotA = new double[N+1];                             //Dummy Mass flow rate at time t at each segment (kg/s)
//
//            double[] mach = new double[N+1];                             //Mach at time t of each segment 
//            double[] machA = new double[N+1];                             //dummy mach
//
//            double[] rb1a = new double[N+1];                               //dummy burning rate of the 1st propellant at time t of each segment
//            double[] rb2a = new double[N+1];                               //dummy burning rate of the 2nd propellant at time t of each segment
//
            int outprecision = (int) outputPrecision.getValue();
            double ts = (double) stoptime.getValue();              //simulation time limit
            double CF = 0;
//
            //initialize
            double t = 0;                                               //start time (s)
            double tprint = 0;
            double P = Pg;                                              //Set pressure to guess pressure

            //for each segment, set start burning distance for each layer to 0 and burning rate for each layer in each segment
            double runningLength = 0;
            Segment[] segments = new Segment[N + 1];

            for (int i = 1; i <= N; i++) {
                runningLength = Ln * i;
                double totalSectionL = 0;
                double startSeg = runningLength - Ln;
                double endSeg = runningLength;
                for (int j = 0; j < sectionList.size(); j++) {
                    SectionInfo section = sectionList.get(j);
                    for (int k = 0; k < section.getLayers().size(); k++) {
                        PropellantLayer l = section.getLayers().get(k);
                        l.setX(0);
                        double rb0 = l.getBurningRate();
                        double n = l.getPressureExponent();
                        l.setA_factor(rb0 / (Math.pow(P0, n)));
                        double a_factor = l.getA_factor();
                        l.setRb(a_factor * Math.pow(Pg, n));
                        l.setGas(0);
                    }
                    totalSectionL = totalSectionL + section.getLengthSection();
                    segments[i].getSections().add(section);
                    if (runningLength <= totalSectionL) {
                        segments[i].getSectionL().add(endSeg - startSeg);
                        break;
                    } else {
                        segments[i].getSectionL().add(totalSectionL - startSeg);
                        startSeg = totalSectionL;

                    }
                }

//                x1[i] = 0;                                              //Set burnt distance to 0
//                x2[i] = 0;
//                rb1[i] = a1*Math.pow(Pg, n1);                           
//                rb2[i] = a2*Math.pow(Pg, n2);
            }
            //segments[0].setSections(segments[1].getSections());
            while (!isAborted) {
                boolean isConverged = false;
                do {
//                   System.out.println(P); 
//                   double gas1 = 0;
//                   double gas2 = 0;
//                   double gasfrac1 = 0;
//                   double gasfrac2 = 0;
                    double totalGas = 0;
                    for (int i = 1; i <= N; i++) {
                        //CALC CURRENT PERIPHERIES AND PORT AREAS
                        for (int j = 0; j < segments[i].getSections().size(); j++) {
                            SectionInfo section = segments[i].getSections().get(j);
                            Geom(section);
                            for (int k = 0; k < section.getLayers().size(); k++) {
                                PropellantLayer l = section.getLayers().get(k);
                                double n = l.getPressureExponent();
                                double a_factor = l.getA_factor();
                                l.setRb_m0(a_factor * Math.pow(P, n));
                                double rb = l.getRb();
                                double gas = l.getGas();
                                double peri = l.getPeri();
                                l.setGas(rb * peri);
                                totalGas = totalGas + rb * peri;
                            }
                        }
//                       if(i < Nch) {
//                           GeomA(i, x1, x2, Peri1, Peri2, Ap);
//                       } else {
//                           GeomB(i, x1, x2, Peri1, Peri2, Ap);
//                       }
//                       //CALC BASE MASS BURN RATE
//                       rb_m0_1[i] = a1*Math.pow(P, n1);
//                       rb_m0_2[i] = a2*Math.pow(P, n2);
//                       //FIND MIXTURE RATIO OF COMBUSTION GAS FROM THE TWO PROPELLANTS
//                       gas1 = gas1 + rb1[i]*Peri1[i];
//                       gas2 = gas2 + rb2[i]*Peri2[i];
                    }
                    segments[0].setSections(segments[1].getSections());
                    segments[0].setSectionL(segments[1].getSectionL());
                    //FIND MIXTURE RATIO OF COMBUSTION GAS FROM THE TWO PROPELLANTS
                    double Rmix = 0;
                    double Tmix = 0;
                    double Gammix = 0;
                    for (int i = 1; i <= N; i++) {

                        for (int j = 0; j < segments[i].getSections().size(); j++) {
                            SectionInfo section = segments[i].getSections().get(j);

                            for (int k = 0; k < section.getLayers().size(); k++) {
                                PropellantLayer l = section.getLayers().get(k);
                                double gas = l.getGas();
                                double R = l.getGasConst();
                                double T = l.getGasTemp();
                                double Gamma = l.getHeatRatio();
                                l.setGasFrac(gas / totalGas);
                                Rmix = Rmix + R * gas / totalGas;
                                Tmix = Tmix + T * gas / totalGas;
                                Gammix = Gammix + Gamma * gas / totalGas;
                            }
                        }
                    }
//                   if(gas1==0 && gas2==0) {
//                       gasfrac1 = 1;
//                       gasfrac2 = 0;
//                   } else {
//                       gasfrac1 = gas1/(gas1+gas2);
//                       gasfrac2 = gas2/(gas1+gas2);
//                   }
//                   double Rmix = R1*gasfrac1 + R2*gasfrac2;
//                   double Tmix = T1*gasfrac1 + T2*gasfrac2;
//                   double Gammix = gamma1*gasfrac1 + gamma2*gasfrac2;
                    //calculate m0
                    double mdot0 = IgnitionMassFlow(t);
                    double AP0 = 0;
                    double Peri0 = 0;
                    for (int i = 0; i < segments[0].getSections().get(0).getLayers().size(); i++) {
                        AP0 = AP0 + segments[0].getSections().get(0).getLayers().get(i).getAp();
                        Peri0 = Peri0 + segments[0].getSections().get(0).getLayers().get(i).getPeri();
                    }
                    double mach0 = mdot0 / P / AP0 * Math.sqrt(Rmix * Tmix / Gammix);
                    segments[0].getSections().get(0).setMdot(mdot0);
                    segments[0].getSections().get(0).setMach(mach0);
//                   mdot[0] = IgnitionMassFlow(t);
//                   mach[0] = mdot[0]/P/Ap[0]*Math.sqrt(Rmix*Tmix/Gammix);
//                   //System.out.println("mdot0: "+mdot[0]+",R:"+Rmix+",T:"+Tmix+",Gam:"+Gammix);
                    double G0 = 0.0;
                    double D0 = 0.0;
                    double B0 = 0.0;

                    if (mdot0 == 0) {
//                       rb1[0] = rb_m0_1[0];
//                       rb2[0] = rb_m0_2[0];
                        for (int i = 0; i < segments[0].getSections().get(0).getLayers().size(); i++) {
                            segments[0].getSections().get(0).getLayers().get(i).setRb(segments[0].getSections().get(0).getLayers().get(i).getRb_m0());

                        }
                    } else {
                        G0 = mdot0 / AP0;
                        D0 = 4 * AP0 / Peri0;
                        for (int i = 0; i < segments[0].getSections().get(0).getLayers().size(); i++) {
                            PropellantLayer l = segments[0].getSections().get(0).getLayers().get(i);
                            B0 = 53 * l.getRb() * l.getDensity() / G0;
                            double rb_m0 = l.getRb_m0();
                            double rb = l.getRb();
                            double alpha = l.getBurningConst() / Math.pow(10, 7);
                            rb = rb_m0 + alpha * (Math.pow(G0, 0.8)) * (Math.pow(D0, -0.2)) * Math.exp(-1 * B0);
                            l.setRb(rb); //set value for rb[0] in each layer 
                        }
//                       B0 = 53*rb1[0]*rho1/G0;
//                       rb1[0] = rb_m0_1[0]+alpha1*(Math.pow(G0, 0.8))*(Math.pow(D0,-0.2))*Math.exp(-1*B0);
//                       rb2[0] = rb_m0_2[0]+alpha2*(Math.pow(G0, 0.8))*(Math.pow(D0,-0.2))*Math.exp(-1*B0);
                    }
//                   //System.out.println("Ap:"+Ap[0]+"Peri1:"+Peri1[0]+"Peri2:"+Peri2[0]+"G0: "+G0+",B0:"+B0+",D0:"+D0);
//                   //System.out.println("rb1_0: "+asz0]+",rb2_0:"+rb2[0]+",rbm0_1:"+rb_m0_1[0]+",rbm0_2:"+rb_m0_2[0]);
//                   //calculate m1,...,mN
                    double GA = 0.0;
                    double DA = 0.0;
                    double BA = 0.0;
//                   double BA2 = 0.0;
                    double G = 0.0;
                    double D = 0.0;
                    double B = 0.0;
//                   double B2 = 0.0;

                    for (int i = 1; i <= N; i++) {
                        for (int j = 0; j < segments[i].getSections().size(); i++) {
                            SectionInfo section = segments[i].getSections().get(j);
                            SectionInfo lastSection = segments[i - 1].getSections().get(segments[i - 1].getSections().size() - 1);
                            if (j > 0) {
                                lastSection = segments[i].getSections().get(j - 1);
                            }
                            double curPeri = 0;
                            double curAp = 0;
                            for (int k = 0; k < section.getLayers().size(); k++) {
                                PropellantLayer l = section.getLayers().get(k);
                                curPeri = curPeri + l.getPeri();
                                curAp = curAp + l.getAp();
                            }
                            if (curPeri > 0) {
                                // Calculate dummy mass flow rate
                                double cur_mdot = section.getMdot();
                                double cur_mach = section.getMach();
                                double cur_mdotA = section.getMdotA();
                                double last_mdot = lastSection.getMdot();
                                double cur_machA = section.getMachA();
                                double producedM = 0;
                                double Ls = segments[i].getSectionL().get(j);
                                if (j > 0) {
                                    G0 = last_mdot / curAp;
                                    D0 = 4 * curAp / curPeri;
                                    for (int k = 0; k < section.getLayers().size(); k++) {
                                        PropellantLayer l = segments[i].getSections().get(j).getLayers().get(k);
                                        B0 = 53 * l.getRb() * l.getDensity() / G0;
                                        double rb_m0 = l.getRb_m0();
                                        double rb = l.getRb();
                                        double alpha = l.getBurningConst() / Math.pow(10, 7);
                                        rb = rb_m0 + alpha * (Math.pow(G0, 0.8)) * (Math.pow(D0, -0.2)) * Math.exp(-1 * B0);
                                        l.setRb(rb); //set value for rb[0] in each layer 
                                        producedM = producedM + l.getRb() * (l.getPeri() + l.getPeri()) / 2 * Ls * l.getDensity();

                                    }
                                } else {
                                    for (int k = 0; k < lastSection.getLayers().size(); k++) {
                                        PropellantLayer l = lastSection.getLayers().get(k);
                                        PropellantLayer cl = section.getLayers().get(k);

                                        producedM = producedM + l.getRb() * (l.getPeri() + cl.getPeri()) / 2 * Ls * l.getDensity();

                                    }
                                }

                                cur_mdotA = last_mdot + producedM;
                                cur_machA = (cur_mdotA / P / AP0) * Math.sqrt(Rmix * Tmix / Gammix);
                                GA = cur_mdotA / curAp;
                                DA = 4 * curAp / curPeri;
                                // BURNING RATE USING MASS FLOW RATE FROM LAST SEGMENT
                                cur_mdot = last_mdot;
                                for (int k = 0; k < section.getLayers().size(); k++) {
                                    PropellantLayer l = section.getLayers().get(k);
                                    if(GA==0) {
                                        BA = Math.pow(10, 11);
                                        
                                    } else {
                                        BA = 53 * l.getRb() * l.getDensity() / GA;
                                        
                                    }
                                    double rb_m0 = l.getRb_m0();
                                    double rb_a = l.getRbA();
                                    
                                    double alpha = l.getBurningConst() / Math.pow(10, 7);
                                    rb_a = rb_m0+alpha*(Math.pow(GA, 0.8))*(Math.pow(DA,-0.2))*Math.exp(-1*BA);
                                    l.setRbA(rb_a);
                                    cur_mdot = cur_mdot; 
                                    //                           mdot[i] = mdot[i-1]+(((rb1[i-1]*Peri1[i-1]+rb1a[i]*Peri1[i])/2)*Ln*rho1 + ((rb2[i-1]*Peri2[i-1]+rb2a[i]*Peri2[i])/2)*Ln*rho2);
//                           mach[i] = (mdot[i]/P/Ap[0])*Math.sqrt(Rmix*Tmix/Gammix);

                                }
                            }
                        }
                    }
//                       if(Peri1[i]+Peri2[i]>0) {
//                           // Calculate dummy mass flow rate
//                           mdotA[i] = mdot[i-1]+((rb1[i-1]*(Peri1[i-1]+Peri1[i])/2)*Ln*rho1 + (rb2[i-1]*(Peri2[i-1]+Peri2[i])/2)*Ln*rho2);
//                           machA[i] = (mdotA[i]/P/Ap[0])*Math.sqrt(Rmix*Tmix/Gammix);
//                           GA = mdotA[i]/Ap[i];
//                           DA = 4*Ap[i]/(Peri1[i]+Peri2[i]);
//                           if(GA==0) {
//                               BA1 = Math.pow(10, 11);
//                               BA2 = Math.pow(10, 11);
//                           } else {
//                               BA1 = 53*rb1[i]*rho1/GA;
//                               BA2 = 53*rb2[i]*rho2/GA;
//                           }
//                           // BURNING RATE USING MASS FLOW RATE FROM LAST SEGMENT
//                           rb1a[i] = rb_m0_1[i]+alpha1*(Math.pow(GA, 0.8))*(Math.pow(DA,-0.2))*Math.exp(-1*BA1);
//                           rb2a[i] = rb_m0_2[i]+alpha2*(Math.pow(GA, 0.8))*(Math.pow(DA,-0.2))*Math.exp(-1*BA2);
//                           mdot[i] = mdot[i-1]+(((rb1[i-1]*Peri1[i-1]+rb1a[i]*Peri1[i])/2)*Ln*rho1 + ((rb2[i-1]*Peri2[i-1]+rb2a[i]*Peri2[i])/2)*Ln*rho2);
//                           mach[i] = (mdot[i]/P/Ap[0])*Math.sqrt(Rmix*Tmix/Gammix);
//
//                           G = mdot[i]/Ap[i];
//                           D = 4*Ap[i]/(Peri1[i]+Peri2[i]);
//                           B1 = 53*rb1[i]*rho1/G;
//                           B2 = 53*rb2[i]*rho2/G;
//                           // correct burning rate
//                           rb1[i] = rb_m0_1[i]+alpha1*(Math.pow(G, 0.8))*(Math.pow(D,-0.2))*Math.exp(-1*B1);
//                           rb2[i] = rb_m0_2[i]+alpha2*(Math.pow(G, 0.8))*(Math.pow(D,-0.2))*Math.exp(-1*B2);
//                       } else {
//                           mdotA[i] = mdot[i-1];
//                           mdot[i] = mdot[i-1];
//                           rb1a[i] = 0;
//                           rb2a[i] = 0;
//                           rb1[i] = 0;
//                           rb2[i] = 0;
//                       }
//                       //System.out.println("mdotI: "+mdot[i]+",mdotA: "+mdotA[i]+",RBM01: "+rb_m0_1[i]+",RB1A: "+rb1a[i]+",GA: "+GA+",DA: "+DA+",ApI: "+Ap[i]+",peri1I: "+Peri1[i]+",peri2I: "+Peri2[i]+",L: "+Ln);
//                        }
//                    }
//                   if(Peri1[0]+Peri2[0]==0) {
//                       rb1[0] = 0;
//                       rb2[0] = 0;
//                   }
//
//                   if(mdot[N]==0) {
//                       break;
//                   }
//                   double k = 2*Math.pow(Gammix,2)/(Gammix-1);
//                   double k1 = Math.pow(2/(Gammix+1),(Gammix+1)/(Gammix-1));
//                   double k2 = 1-Math.pow(101356.5 / P,(Gammix-1)/Gammix);
//                   CF = Math.sqrt(k*k1*k2);
//                   double CStar = Isp * 9.80665 / CF;
//                   System.out.println("mdotN: "+mdot[N]+", C*: "+CStar+", At: "+ At);
//                   P = mdot[N] * CStar / At; 
//                   if(Math.abs((Pg-P)/P)<climit) {
//                       isConverged = true;
//                   } else {
//                       Pg = P;
//                   }
//
                } while (!isConverged);

//                System.out.println("Converged Pressure: "+P); 
//                if(mdot[N]==0) {
//                    isAborted = true;
//                }
//
//                for(int i = 0;i <= N;i++) {
//                    x1[i] = x1[i] + rb1[i]*tdelta;
//                    if(i < Nch) {
//                        if(x1[i] < (D1A-Dt)/2) {
//                            x2[i] = 0;
//                        } else {
//                            x2[i] = x2[i] + rb2[i]*tdelta;
//                        }
//                    } else {
//                        if(x1[i] < (D1B-Dt)/2) {
//                            x2[i] = 0;
//                        } else {
//                            x2[i] = x2[i] + rb2[i]*tdelta;
//                        }
//                    }
//                }
//
//                t = t + tdelta;
//                double delta_tprint = 0.1;
//                if(t>=tprint) {
//
//                    double out_t = (double)Math.round(tprint*Math.pow(10,precision))/Math.pow(10,precision);
//                    double out_x1_head = (double)Math.round(x1[0]*1000*Math.pow(10,precision))/Math.pow(10,precision);
//                    double out_x1_mid = (double)Math.round(x1[Math.round(N/2)]*1000*Math.pow(10,precision))/Math.pow(10,precision);
//                    double out_x1_tail8 = (double)Math.round(x1[N-8]*1000*Math.pow(10,precision))/Math.pow(10,precision);
//                    double out_x1_tail6 = (double)Math.round(x1[N-6]*1000*Math.pow(10,precision))/Math.pow(10,precision);
//                    double out_x1_tail4 = (double)Math.round(x1[N-4]*1000*Math.pow(10,precision))/Math.pow(10,precision);
//                    double out_x1_tail3 = (double)Math.round(x1[N-3]*1000*Math.pow(10,precision))/Math.pow(10,precision);
//                    double out_x1_tail2 = (double)Math.round(x1[N-2]*1000*Math.pow(10,precision))/Math.pow(10,precision);
//                    double out_x1_tail1 = (double)Math.round(x1[N-1]*1000*Math.pow(10,precision))/Math.pow(10,precision);
//                    double out_x1_tail = (double)Math.round(x1[N]*1000*Math.pow(10,precision))/Math.pow(10,precision);
//                    double out_P = (double)Math.round(P/6895);
//                    double out_thrust = (double)Math.round(P * At * CF / 9.8 * 2.204*Math.pow(10,precision))/Math.pow(10,precision);
//                    DefaultTableModel outTableModel = (DefaultTableModel) outputWin.outputTable.getModel();
//                    outTableModel.addRow(new Object[]{out_t,out_x1_head,out_x1_mid,out_x1_tail8,out_x1_tail6,out_x1_tail4,out_x1_tail3,out_x1_tail2,out_x1_tail1,out_x1_tail,out_P,out_thrust});
//                    outputWin.trace.addPoint(tprint, out_thrust);
//                    tprint = tprint + delta_tprint;
//                }
//
//                //P < 50 * 6895
//                if(t>delta_tprint && (P < 50 * 6895 || t >= ts)) {
//                    isAborted = true;
//                }
//
//            } 
//        
                //            outputWin.saveOutputBtn.setEnabled(true);
                //            outputWin.abortBtn.setEnabled(false);
            }

        }

        private double IgnitionMassFlow(double T) {

            double Tig = 0.3;        //igniter burn time (s) default 0.3
            double Mig = 0.02;          //igniter mass (kg)  default 0.02

            if (T <= Tig) {
                return Mig / Tig;
            } else {
                return 0;
            }
        }

        private void Geom(SectionInfo section) {
            List<BurningDistance> distanceLayer = section.getBurningList();
            List<PropellantLayer> propLayers = section.getLayers();
//            DefaultTableModel table1 = (DefaultTableModel) propGeoWin.jTable1.getModel();
//            DefaultTableModel table2 = (DefaultTableModel) propGeoWin.jTable2.getModel();
//            int numrows = Integer.parseInt(propGeoWin.numrowsA.getText());
//            int rows1 = table1.getRowCount();
//            int rows2 = table2.getRowCount();
//            double [] x1d = new double[numrows+1];
//            double [] x2d = new double[numrows+1];
//            double [] peri1d = new double[numrows+1];
//            double [] peri2d = new double[numrows+1];
//            double ap1 = 0;
//            double ap2 = 0;
//            double [] ap1d = new double[numrows+1];
//            double [] ap2d = new double[numrows+1];
//            for(int i=0;i<=numrows;i++) {
//                if(i<rows1) {
//                    x1d[i] = (Double) table1.getValueAt(i, 0)/1000;
//                    peri1d[i] = (Double) table1.getValueAt(i, 2)/1000;
//                    ap1d[i] = (Double) table1.getValueAt(i, 1)/1000000;
//                } else {
//                    x1d[i] = 0.0;
//                    peri1d[i] = 0;
//                    ap1d[i] = 0;
//                }
//                if(i<rows2) {
//                    x2d[i] = (Double) table2.getValueAt(i, 0)/1000;
//                    peri2d[i] = (Double) table2.getValueAt(i, 2)/1000;
//                    ap2d[i] = (Double) table2.getValueAt(i, 1)/1000000;
//                } else {
//                    x2d[i] = 0;
//                    peri2d[i] = 0;
//                    ap2d[i] = 0;
//                }
//            }
            BurningDistance lastLayer = distanceLayer.get(distanceLayer.size() - 1);

            for (int i = 0; i < propLayers.size(); i++) {
                PropellantLayer propLayer = propLayers.get(i);
                double curX = propLayer.getX(); //get current burnt distance of this layer
                double maxX = propLayer.getMaxBurningDistance(); //get max burnt distance of this layer

                if (curX >= maxX) {
                    propLayer.setPeri(lastLayer.getPeripheral());
                    propLayer.setAp(lastLayer.getPortArea());
                } else {
                    for (int j = 0; j < distanceLayer.size() - 1; j++) {
                        BurningDistance bdp = distanceLayer.get(j);
                        BurningDistance bdn = distanceLayer.get(j + 1);
                        if (curX >= bdp.getDistance() && curX < bdn.getDistance()) {
                            double AP = bdp.getPortArea() + (bdn.getPortArea() - bdp.getPortArea()) / (bdn.getDistance() - bdp.getDistance()) * (curX - bdp.getDistance());
                            double Peri = bdp.getPeripheral() + (bdn.getPeripheral() - bdp.getPeripheral()) / (bdn.getDistance() - bdp.getDistance()) * (curX - bdp.getDistance());
                            propLayer.setAp(AP);
                            propLayer.setPeri(Peri);
                        }
                    }
                }
            }
//            for(int i=0;i<numrows;i++) {
//                if(x1[idx]>=x1d[i] && x1[idx]<x1d[i+1]) {
//                    ap1 = ap1d[i]+(ap1d[i+1]-ap1d[i])/(x1d[i+1]-x1d[i])*(x1[idx]-x1d[i]);
//                    Ap[idx] = ap1;
//                    Peri1[idx] = peri1d[i]+(peri1d[i+1]-peri1d[i])/(x1d[i+1]-x1d[i])*(x1[idx]-x1d[i]);
//                }
//            }
//            for(int i=0;i<numrows;i++) {
//                if(x2[idx]>=x2d[i] && x2[idx]<x2d[i+1]) {
//                    ap2 = ap2d[i]+(ap2d[i+1]-ap1d[i])/(x2d[i+1]-x2d[i])*(x2[idx]-x2d[i]);
//                    Ap[idx] = ap1+ap2;
//                    Peri2[idx] = peri2d[i]+(peri2d[i+1]-peri2d[i])/(x2d[i+1]-x2d[i])*(x2[idx]-x2d[i]);
//                }
//            }
//
//            if(x1[idx] >= Double.parseDouble(propGeoWin.maxBurntD1_A.getText())) {
//                Ap[idx] = Double.parseDouble(propGeoWin.finalAP_A.getText());
//                Peri1[idx] = Double.parseDouble(propGeoWin.finalPeri_A.getText());
//            }
//
//
//
//
//
        }
//
//        private void GeomB(int idx, double [] x1, double [] x2, double [] Peri1, double [] Peri2, double [] Ap) {
//
//            DefaultTableModel table1 = (DefaultTableModel) propGeoWin.jTable3.getModel();
//            DefaultTableModel table2 = (DefaultTableModel) propGeoWin.jTable4.getModel();
//            int numrows = Integer.parseInt(propGeoWin.numrowsB.getText());
//            int rows1 = table1.getRowCount();
//            int rows2 = table2.getRowCount();
//            double [] x1d = new double[numrows+1];
//            double [] x2d = new double[numrows+1];
//            double [] peri1d = new double[numrows+1];
//            double [] peri2d = new double[numrows+1];
//            double ap1 = 0;
//            double ap2 = 0;
//            double [] ap1d = new double[numrows+1];
//            double [] ap2d = new double[numrows+1];
//            for(int i=0;i<=numrows;i++) {
//                if(i<rows1) {
//                    x1d[i] = (Double) table1.getValueAt(i, 0)/1000;
//                    peri1d[i] = (Double) table1.getValueAt(i, 2)/1000;
//                    ap1d[i] = (Double) table1.getValueAt(i, 1)/1000000;
//                } else {
//                    x1d[i] = 0;
//                    peri1d[i] = 0;
//                    ap1d[i] = 0;
//                }
//                if(i<rows2) {
//                    x2d[i] = (Double) table2.getValueAt(i, 0)/1000;
//                    peri2d[i] = (Double) table2.getValueAt(i, 2)/1000;
//                    ap2d[i] = (Double) table2.getValueAt(i, 1)/1000000;
//                } else {
//                    x2d[i] = 0;
//                    peri2d[i] = 0;
//                    ap2d[i] = 0;
//                }
//            }
//
//            for(int i=0;i<numrows;i++) {
//                if(x1[idx]>=x1d[i] && x1[idx]<x1d[i+1]) {
//                    ap1 = ap1d[i]+(ap1d[i+1]-ap1d[i])/(x1d[i+1]-x1d[i])*(x1[idx]-x1d[i]);
//                    Ap[idx] = ap1;
//                    Peri1[idx] = peri1d[i]+(peri1d[i+1]-peri1d[i])/(x1d[i+1]-x1d[i])*(x1[idx]-x1d[i]);
//                }
//            }
//            for(int i=0;i<numrows;i++) {
//                if(x2[idx]>=x2d[i] && x2[idx]<x2d[i+1]) {
//                    ap2 = ap2d[i]+(ap2d[i+1]-ap1d[i])/(x2d[i+1]-x2d[i])*(x2[idx]-x2d[i]);
//                    Ap[idx] = ap1+ap2;
//                    Peri2[idx] = peri2d[i]+(peri2d[i+1]-peri2d[i])/(x2d[i+1]-x2d[i])*(x2[idx]-x2d[i]);
//                }
//            }
//
//            if(x1[idx] >= Double.parseDouble(propGeoWin.maxBurntD1_B.getText())) {
//                Ap[idx] = Double.parseDouble(propGeoWin.finalAP_B.getText());
//                Peri1[idx] = Double.parseDouble(propGeoWin.finalPeri_B.getText());
//            }
//
//
//
//
//
//        }
//        

        public void kill() {
            isAborted = true;
        }

    }

    class ExtensionFileFilter extends FileFilter {

        String description;

        String extensions[];

        public ExtensionFileFilter(String description, String extension) {
            this(description, new String[]{extension});
        }

        public ExtensionFileFilter(String description, String extensions[]) {
            if (description == null) {
                this.description = extensions[0];
            } else {
                this.description = description;
            }
            this.extensions = (String[]) extensions.clone();
            toLower(this.extensions);
        }

        private void toLower(String array[]) {
            for (int i = 0, n = array.length; i < n; i++) {
                array[i] = array[i].toLowerCase();
            }
        }

        public String getDescription() {
            return description;
        }

        public boolean accept(File file) {
            if (file.isDirectory()) {
                return true;
            } else {
                String path = file.getAbsolutePath().toLowerCase();
                for (int i = 0, n = extensions.length; i < n; i++) {
                    String extension = extensions[i];
                    if ((path.endsWith(extension) && (path.charAt(path.length() - extension.length() - 1)) == '.')) {
                        return true;
                    }
                }
            }
            return false;
        }

    }

    /**
     * @param args the command line arguments
     */
    File yourFile;
    SpinnerNumberModel diameterSpinnerNumberModel;
    SpinnerNumberModel lengthSpinnerNumberModel;

    SpinnerNumberModel diameterSectionSpinnerNumberModel;
    SpinnerNumberModel lengthSectionSpinnerNumberModel;
    SpinnerNumberModel innerDiameterSectionSpinnerNumberModel;
    public JSVGCanvas drawCanvas = new JSVGCanvas();
    public Document document;
    public String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
    Element svgRoot;
    Double rocketDiameter = 50.0;
    Double rocketLength = 100.0;
    Double xPosition = 50.0;
    Double yPosition = 65.0;
    Double yMotorPosition = 50.0;
    Double diameterSection = 0.0;
    Double lengthSection = 0.0;
    Double xAddMoreSection = 0.0;
    int sectionNo = 0;
    AddSectionPopup addSectionPopup;

    Boolean selectedSomething = false;
    double newInnerDiameter;

    List<SectionInfo> sectionList = new ArrayList<SectionInfo>();
    //List<SizeOfShape> sizeOfShapesList = new ArrayList<SizeOfShape>();
    ByteArrayInputStream inputStream;
    SpinnerNumberModel outerSpinnerNumberModel;
    SpinnerNumberModel innerSpinnerNumberModel;
    private CAD cad = new CAD();
    byte[] data;
    private SectionInfo selectedSection;
    private PropellantLayer selectedLayer;

    // Variables declaration - do not modify
    private javax.swing.JButton addPropellantBt;
    private javax.swing.JButton addSectionBT;
    private javax.swing.JTextField alphaConstTB;
    private javax.swing.JTextField burningRateTB;
    private JSVGCanvas cadPanelShape = new JSVGCanvas();
    private javax.swing.JToggleButton circleToggleButton;
    private javax.swing.JTextField densityTB;
    private javax.swing.JLabel diameterInner;
    //private javax.swing.JPanel drawCanvas;
    private javax.swing.JMenuItem exitItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JTextField gasConstTB;
    private javax.swing.JTextField gasTempTB;
    private javax.swing.JPanel geometricTab;
    private javax.swing.JTextField heatRatioTB;
    private javax.swing.JTextField maxBurntTB;
    private javax.swing.JToggleButton hexaToggleButton;
    private javax.swing.JSpinner innerDiameterSpinner;
    private javax.swing.JButton runSimulateBtn;
    private javax.swing.JButton abortSimulateBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel burningRateLB;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel pressureExponentLB;
    private javax.swing.JLabel densityLB;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel alphaConstLB;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel gasConstLB;
    private javax.swing.JLabel gasTempLB;
    private javax.swing.JLabel heatRatioLB;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel maxBurntLB;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel precisionLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner outerDiameterSpinner;
    private javax.swing.JSpinner guessPSI;
    private javax.swing.JSpinner specificImp;
    private javax.swing.JSpinner jSpinner5;
    private javax.swing.JSpinner stoptime;
    private javax.swing.JSpinner numSegments;
    private javax.swing.JSpinner outputPrecision;
    private javax.swing.JTabbedPane sectionPropertiesTabbedPanel;
    private javax.swing.JMenuItem loadConfItem;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JTabbedPane mainTabbedPanel;
    private javax.swing.JLabel mmLabel;
    private javax.swing.JLabel mmLabel1;
    private javax.swing.JToggleButton pentaToggleButton;
    private javax.swing.JTextField pressureExponentTB;
    private javax.swing.JPanel propellantPanel;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JTable propellantTable;
    private javax.swing.JButton removePropellantBt;
    private javax.swing.JButton removeSectionBT;
    private javax.swing.JSpinner rocketDiameterSp;
    private javax.swing.JSpinner ignitorMass;
    private javax.swing.JSpinner rocketLengthSp;
    private javax.swing.JSpinner ignitorTime;
    private javax.swing.JMenuItem saveConfItem;
    private javax.swing.JButton savePropertiesBT;
    private javax.swing.JPanel simulationTab;
    private javax.swing.JToggleButton starToggleButton;
    private javax.swing.JToggleButton wheelToggleButton;
    private javax.swing.JToggleButton eightStarToggleButton;
    private javax.swing.JLabel lengthLB;
    private javax.swing.JLabel mmLengthLabel;
    private javax.swing.JSpinner lengthSectionSpinner;
    private javax.swing.JLabel startBurningDistanceLb;
    private javax.swing.JTextField startBurningDistanceTb;
    private javax.swing.JButton zoomInPropellantBt;
    private javax.swing.JButton zoomOutPropellantBt;

    private javax.swing.JButton arrowDownButton;
    private javax.swing.JButton arrowLeftButton;
    private javax.swing.JButton arrowRightButton;
    private javax.swing.JButton arrowUpButton;
    private javax.swing.JButton centerButton;
    private javax.swing.JPanel viewControlPanel;

    private javax.swing.JPanel leftGeoPanel;
    private javax.swing.JPanel rigthGeoPanel;
    // End of variables declaration    
    String[] colors = {"blue", "green", "yellow", "pink"};

    private javax.swing.JPanel burningDistancePanel;
    private javax.swing.JScrollPane burningDistanceScrollPanel;
    private javax.swing.JTable burningDistanceTable;
    private javax.swing.JButton addBurningRowBt;
    private javax.swing.JButton loadPropDataBt;
    private javax.swing.JButton removeBurningRowBt;
    private javax.swing.JButton savePropDataBt;
    public ITrace2D traceOutputThrust;
    public ITrace2D traceCompareThrust;
    public ITrace2D traceOutputPressure;
    public ITrace2D traceComparePressure;
    private javax.swing.JPanel chartPanelThrust;
    private javax.swing.JPanel chartPanelPressure;

    private javax.swing.JButton browseCompareButton;
    private javax.swing.JPanel chartPanel;
}
