/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dti.internalballistics;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class Segment {
    private List<SectionInfo> sections = new ArrayList<SectionInfo>();
    private List<Double> sectionL = new ArrayList<Double>();
    
    
    public Segment() {
    }

    public List<SectionInfo> getSections() {
        return sections;
    }

    public void setSections(List<SectionInfo> sections) {
        this.sections = sections;
    }

    
    public List<Double> getSectionL() {
        return sectionL;
    }

    public void setSectionL(List<Double> sectionL) {
        this.sectionL = sectionL;
    }
    
    
    
}
