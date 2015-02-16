/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.jecommons.testdataprocessing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.measure.unit.Unit;
import org.jevis.api.JEVisDataSource;
import org.jevis.api.JEVisException;
import org.jevis.api.JEVisObject;
import org.jevis.api.JEVisSample;
import org.jevis.api.JEVisType;
import org.jevis.api.JEVisUnit;
import org.jevis.jecommons.dataprocessing.DataCompareTime;
import org.joda.time.DateTime;
import org.joda.time.Period;

/**
 *
 * @author gf
 */
public class JEVisAttributeTest implements org.jevis.api.JEVisAttribute{

    List<JEVisSample> _samples = new ArrayList<JEVisSample>();

    public JEVisAttributeTest(List<JEVisSample> samples) {
        _samples.addAll(samples);
    }
    
    
    
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public JEVisType getType() throws JEVisException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public JEVisObject getObject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<JEVisSample> getAllSamples() {
        return _samples;
    }

    public List<JEVisSample> getSamples(DateTime from, DateTime to) {
        List<JEVisSample> cuted = new ArrayList<JEVisSample>();
        for (JEVisSample row : _samples) {
            try {
            boolean before = row.getTimestamp().isBefore(to) || row.getTimestamp().equals(to);
            boolean after = row.getTimestamp().isAfter(from) || row.getTimestamp().equals(from);
            if (before && after) {
                cuted.add(buildSample(row.getTimestamp(), row.getValue()));
            }
            } catch (JEVisException ex) {
                    Logger.getLogger(JEVisAttributeTest.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        DataCompareTime comparator = new DataCompareTime();
        Collections.sort(cuted, comparator);
        return cuted;
    }

    public int addSamples(List<JEVisSample> samples) throws JEVisException {
        _samples.addAll(samples);
        return 1;
    }

    public JEVisSample buildSample(DateTime ts, Object value) throws JEVisException {
        return new JEVisSampleTest(ts,Double.parseDouble(value.toString()));
    }

    public JEVisSample buildSample(DateTime ts, Object value, String note) throws JEVisException {
        return new JEVisSampleTest(ts,Double.parseDouble(value.toString()),note);
    }

    public JEVisSample getLatestSample() {
        return _samples.get(_samples.size()-1);
    }

    public int getPrimitiveType() throws JEVisException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean hasSample() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DateTime getTimestampFromFirstSample() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DateTime getTimestampFromLastSample() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean deleteAllSample() throws JEVisException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean deleteSamplesBetween(DateTime from, DateTime to) throws JEVisException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Unit getUnit() throws JEVisException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setUnit(Unit unit) throws JEVisException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getAlternativSymbol() throws JEVisException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setAlternativSymbol(String symbol) throws JEVisException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Period getPeriod() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isType(JEVisType type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public long getSampleCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public JEVisDataSource getDataSource() throws JEVisException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int compareTo(org.jevis.api.JEVisAttribute o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public JEVisSample buildSample(DateTime ts, double value, JEVisUnit unit) throws JEVisException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public JEVisSample buildSample(DateTime ts, double value, String note, JEVisUnit unit) throws JEVisException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public JEVisUnit getDisplayUnit() throws JEVisException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setDisplayUnit(JEVisUnit unit) throws JEVisException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public JEVisUnit getInputUnit() throws JEVisException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setInputUnit(JEVisUnit unit) throws JEVisException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Period getDisplaySampleRate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Period getInputSampleRate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setInputSampleRate(Period period) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setDisplaySampleRate(Period period) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void commit() throws JEVisException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void rollBack() throws JEVisException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean hasChanged() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
