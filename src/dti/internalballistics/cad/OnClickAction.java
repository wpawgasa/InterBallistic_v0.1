/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dti.internalballistics.cad;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

/**
 *
 * @author wichai.p
 */
public class OnClickAction implements EventListener {

    @Override
    public void handleEvent(Event event) {
        System.out.println("Click*****");
        Element target = (Element) event.getCurrentTarget();
        System.out.println("ID:"+target.getAttribute("id"));
        Node parent = target.getParentNode();
        NodeList childs = parent.getChildNodes();
        for (int i = 0; i < childs.getLength(); i++) {
            Node child = childs.item(i);
            Element childElt = (Element) child;
            childElt.setAttribute("stroke", "black");
        }
        target.setAttribute("stroke", "red");
        target.setAttribute("stroke-width", "1");
        
        
    }
    
}
