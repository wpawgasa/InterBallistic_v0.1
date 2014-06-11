/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dti.internalballistics.cad;

import org.w3c.dom.Element;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

/**
 *
 * @author amabird
 */
public class SvgOnHoverAction implements EventListener {

    @Override
    public void handleEvent(Event evt) {
        System.out.println("*****");
        Element target = (Element) evt.getCurrentTarget();
        System.out.println("ID:"+target.getAttribute("id"));
    }
    
}
