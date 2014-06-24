/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dti.internalballistics.cad;

/**
 *
 * @author wpawgasa
 */
public class Arc {

    private Point coord1, coord2;
    private Point center;
    private double radius, empieza, acaba;
    private double bulge;
    private double d, dd, aci;
    private Point coordAux;

    public Arc() {

    }

    public void ArcFromBulge(Point p1, Point p2, double bulge) {
        this.bulge = bulge;
        if (bulge < 0.0) {
            coord1 = p2;
            coord2 = p1;
        } else {
            coord1 = p1;
            coord2 = p2;
        }
        calParams();
    }

    private void calParams() {
        d = Math.sqrt((coord2.getX() - coord1.getX()) * (coord2.getX() - coord1.getX()) + (coord2.getY() - coord1.getY()) * (coord2.getY() - coord1.getY()));
        coordAux = new Point();
        coordAux.setX((coord1.getX() + coord2.getX()) / 2.0);
        coordAux.setY((coord1.getY() + coord2.getY()) / 2.0);
        double b = Math.abs(bulge);
        double beta = Math.atan(b);
        double alfa = beta * 4.0;
        double landa = alfa / 2.0;
        dd = (d / 2.0) / (Math.tan(landa));
        radius = (d / 2.0) / (Math.sin(landa));
        aci = Math.atan((coord2.getX() - coord1.getX()) / (coord2.getY() - coord1.getY()));
        double aciDegree = aci * 180.0 / Math.PI;
        if (coord2.getY() > coord1.getY()) {
            aci += Math.PI;
            aciDegree = aci * 180.0 / Math.PI;
        }
        center = new Point();
        center.setX(coordAux.getX() + dd * Math.sin(aci + (Math.PI / 2.0)));
        center.setY(coordAux.getY() + dd * Math.cos(aci + (Math.PI / 2.0)));
        calEA(alfa);
    }

    private void calEA(double alfa) {
        empieza = Math.atan2(coord1.getY() - center.getY(), coord1.getX() - center.getX());
        acaba = (empieza + alfa);
        empieza = empieza * 180.0 / Math.PI;
        acaba = acaba * 180.0 / Math.PI;
    }

    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    public double getBulge() {
        return bulge;
    }

}
