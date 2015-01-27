/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.jecommons.testdataprocessing;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.jevis.api.JEVisAttribute;
import org.jevis.api.JEVisException;
import org.jevis.api.JEVisSample;
import org.jevis.jecommons.dataprocessing.DataCalc;
import org.jevis.jecommons.dataprocessing.sql.DataCalcSQL;
import org.jevis.jecommons.dataprocessing.sql.App;
import org.joda.time.DateTime;

/**
 *
 * @author gf
 */
public class TestFunction {
    TestCompare comp=new TestCompare();
    public void testAddition(JEVisAttribute attribute,Double value,JEVisAttribute exp) throws Exception {
        System.out.println("Addition1");

        for (JEVisSample sample : attribute.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        System.out.println("*******************");
        System.out.println(value);
        
       DataCalc calc = new DataCalcSQL();

        System.out.println("Addition1 Result:");
        App.output(calc.addition(attribute.getAllSamples(), value));
//        output(exp.getAllSamples());
        comp.compareJEVis(exp, calc.addition(attribute.getAllSamples(), value));
    }
    
    public void testAddition(JEVisAttribute attribute1,JEVisAttribute attribute2,JEVisAttribute exp) throws Exception {
        System.out.println("addition2");
        
        for (JEVisSample sample : attribute1.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        
        System.out.println("******************");
        
        for (JEVisSample sample : attribute2.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        
        DataCalc calc = new DataCalcSQL();
        
        System.out.println("Addition2 Result:");
        App.output(((DataCalcSQL)calc).addition(attribute1.getAllSamples(), attribute2.getAllSamples()));
        comp.compareJEVis(exp, ((DataCalcSQL)calc).addition(attribute1.getAllSamples(), attribute2.getAllSamples()));
    }
    
    public void testAddition(List<List<JEVisSample>> attributes,JEVisAttribute exp) throws Exception {
        System.out.println("addition3");
        
        for (int i = 0; i <= attributes.size() - 1; i++) {
            System.out.println("******************data row" + (i+1));
            for (JEVisSample sample : attributes.get(i)) {
                System.out.println(sample.getTimestamp() + ";" + sample.getValue());
            }
        }
    
        DataCalc calc = new DataCalcSQL();
        
        System.out.println("Addition3 Result:");
        App.output(calc.addition(attributes));
        comp.compareJEVis(exp, calc.addition(attributes));
    }
    
    public void testBoundaryFilter(JEVisAttribute attribute1,double up,double low,JEVisAttribute exp) throws Exception {
        System.out.println("BoundaryFilter");
        
        for (JEVisSample sample : attribute1.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        DataCalc calc=new DataCalcSQL();
        System.out.println("BoundaryFilter Result:");
        App.output(calc.boundaryFilter(attribute1.getAllSamples(), up, low,false));
        comp.compareJEVis(exp, calc.boundaryFilter(attribute1.getAllSamples(), up, low,false));
    }

    public void testCumulativeDifferentialConverter(JEVisAttribute attribute1,JEVisAttribute exp) throws Exception {
        System.out.println("CumulativeDifferentialConverter");
        for (JEVisSample sample : attribute1.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        DataCalc calc = new DataCalcSQL();
        System.out.println("CumulativeDifferentialConverter Result:");
        App.output(calc.cumulativeDifferentialConverter(attribute1.getAllSamples()));
        comp.compareJEVis(exp, calc.cumulativeDifferentialConverter(attribute1.getAllSamples()));
    }

    public void testHighPassFilter(JEVisAttribute attribute1,double boundary,JEVisAttribute exp) throws Exception {
        System.out.println("highPassFilter1");
        for (JEVisSample sample : attribute1.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        DataCalc calc=new DataCalcSQL();
        System.out.println("HighPassFilter1 Result:");
        App.output(calc.highPassFilter(attribute1.getAllSamples(),boundary));
        comp.compareJEVis(exp, calc.highPassFilter(attribute1.getAllSamples(),boundary));
        
    }

    public void testHighPassFilter(JEVisAttribute attribute1,double boundary,double fill_value,JEVisAttribute exp) throws Exception {
        System.out.println("highPassFilter2");
        for (JEVisSample sample : attribute1.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        DataCalc calc=new DataCalcSQL();
        System.out.println("HighPassFilter2 Result:");
        App.output(calc.highPassFilter(attribute1.getAllSamples(),boundary,fill_value));
        comp.compareJEVis(exp, calc.highPassFilter(attribute1.getAllSamples(),boundary,fill_value));
        
    }

    public void testIntegration(JEVisAttribute attribute1,double exp) throws Exception {
        System.out.println("integration1");
        for (JEVisSample sample : attribute1.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        DataCalc calc = new DataCalcSQL();
        
        Double result = calc.integration(attribute1.getAllSamples());
        System.out.println("integration1 Result:");
        System.out.println(result);
        comp.compareValue(exp, result);
    }
    
    //
    public void testIntegration(JEVisAttribute attribute1,DateTime from,DateTime to,double exp) throws Exception {
        System.out.println("integration2");
        for (JEVisSample sample : attribute1.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        DataCalc calc = new DataCalcSQL();
        
        Double result = calc.integration(attribute1.getSamples(from, to));
        System.out.println("integration2 Result:");
        System.out.println(result);
        comp.compareValue(exp, result);
    }

    public void testIntervalAlignment(JEVisAttribute attribute1,DateTime begin_time,int period_s,int deviation_s,JEVisAttribute exp) throws Exception {
        System.out.println("intervalAlignment");
        for (JEVisSample sample : attribute1.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        DataCalc calc=new DataCalcSQL();
        System.out.println("intervalAlignment Result:");
        App.output(calc.intervalAlignment(attribute1.getAllSamples(),begin_time,period_s,deviation_s));//begin,
        comp.compareJEVis(exp, calc.intervalAlignment(attribute1.getAllSamples(),begin_time,period_s,deviation_s));//begin,
        
    }

    public void testLinearInterpolation(JEVisAttribute attribute1,int insert_num,JEVisAttribute exp) throws Exception {
        System.out.println("linearInterpolation1");
        for (JEVisSample sample : attribute1.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        DataCalc calc = new DataCalcSQL();
        System.out.println("linearInterpolation1 Result:");
        App.output(calc.linearInterpolation(attribute1.getAllSamples(),insert_num));
        comp.compareJEVis(exp, calc.linearInterpolation(attribute1.getAllSamples(),insert_num));
        
    }
    //
    public void testLinearInterpolation(JEVisAttribute attribute1,DateTime from,DateTime to,int insert_num,JEVisAttribute exp) throws Exception {
        System.out.println("linearInterpolation2");
        for (JEVisSample sample : attribute1.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        DataCalc calc = new DataCalcSQL();
        System.out.println("linearInterpolation2 Result:");
        App.output(((DataCalcSQL)calc).linearInterpolation(attribute1,from, to,insert_num));
        comp.compareJEVis(exp, ((DataCalcSQL)calc).linearInterpolation(attribute1,from, to,insert_num));
        
    }

    public void testLinearScaling(JEVisAttribute attribute1,double proportion,double b,JEVisAttribute exp) throws Exception {
        System.out.println("linearScaling");
        for (JEVisSample sample : attribute1.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        DataCalc calc = new DataCalcSQL();
        System.out.println("linearScaling Result:");
        App.output(calc.linearScaling(attribute1.getAllSamples(),proportion,b));
        comp.compareJEVis(exp, calc.linearScaling(attribute1.getAllSamples(),proportion,b));
        
    }

    public void testMedian(JEVisAttribute attribute1,double exp) throws Exception {
        System.out.println("median");
        for (JEVisSample sample : attribute1.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        DataCalc calc = new DataCalcSQL();
        Double result = calc.median(attribute1.getAllSamples());
        System.out.println("median Result:");
        System.out.println(result);
        comp.compareValue(exp, result);
    }

    public void testMergeValues(JEVisAttribute attribute1,DateTime begin,int period_s,int meg_num,JEVisAttribute exp) throws Exception {
        System.out.println("mergeValues");
        for (JEVisSample sample : attribute1.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        DataCalc calc = new DataCalcSQL();
        System.out.println("mergeValues Result:");
        App.output(calc.mergeValues(attribute1.getAllSamples(),begin,period_s,meg_num));
        comp.compareJEVis(exp, calc.mergeValues(attribute1.getAllSamples(),begin,period_s,meg_num));
        
    }

    public void testPrecisionFilter(JEVisAttribute attribute1,double percent,JEVisAttribute exp) throws Exception {
        System.out.println("precisionFilter");
        for (JEVisSample sample : attribute1.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        DataCalc calc = new DataCalcSQL();
        System.out.println("precisionFilter Result:");
        App.output(calc.precisionFilter(attribute1.getAllSamples(),percent));
        comp.compareJEVis(exp, calc.precisionFilter(attribute1.getAllSamples(),percent));
        
    }

    
    public void testSortByTime(JEVisAttribute attribute1,int order,JEVisAttribute exp) throws JEVisException {
        System.out.println("sortByTime");
        for (JEVisSample sample : attribute1.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        System.out.println("******************");
        System.out.println(order);
        
        DataCalc calc = new DataCalcSQL();

        System.out.println("sortByTime Result:");
        App.output(calc.sortByTime(attribute1.getAllSamples(), order));
        comp.compareJEVis(exp, calc.sortByTime(attribute1.getAllSamples(), order));
    }

    
    public void testSortByValue(JEVisAttribute attribute1,int order,JEVisAttribute exp) throws JEVisException {
        System.out.println("sortByValue");
        for (JEVisSample sample : attribute1.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        System.out.println("******************");
        System.out.println(order);
        
        DataCalc calc = new DataCalcSQL();

        System.out.println("sortByValue Result:");
        App.output(calc.sortByValue(attribute1.getAllSamples(), order));
        comp.compareJEVis(exp, calc.sortByValue(attribute1.getAllSamples(), order));
    }

    public void testSplitValues(JEVisAttribute attribute1,int period_s,int seg_num,JEVisAttribute exp) throws Exception {
        System.out.println("splitValues");
        for (JEVisSample sample : attribute1.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        DataCalc calc = new DataCalcSQL();
        System.out.println("splitValues Result:");
        App.output(calc.splitValues(attribute1.getAllSamples(),period_s,seg_num));
        comp.compareJEVis(exp, calc.splitValues(attribute1.getAllSamples(),period_s,seg_num));
    }

    public void testSubtraction(JEVisAttribute attribute1,Double value,JEVisAttribute exp) throws Exception {
        System.out.println("subtraction1");
        for (JEVisSample sample : attribute1.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        System.out.println("******************");
        System.out.println(value);
        
        DataCalc calc = new DataCalcSQL();

        System.out.println("subtraction1 Result:");
        App.output(calc.subtraction(attribute1.getAllSamples(), value));
        comp.compareJEVis(exp, calc.subtraction(attribute1.getAllSamples(), value));
    }

    public void testSubtraction(JEVisAttribute attribute1,JEVisAttribute attribute2,JEVisAttribute exp) throws Exception {
        System.out.println("subtraction2");
        for (JEVisSample sample : attribute1.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        
        System.out.println("******************");
        
        for (JEVisSample sample : attribute2.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        
        DataCalc calc = new DataCalcSQL();
        
        System.out.println("subtraction2 Result:");
        App.output(calc.subtraction(attribute1.getAllSamples(), attribute2.getAllSamples()));
        comp.compareJEVis(exp, calc.subtraction(attribute1.getAllSamples(), attribute2.getAllSamples()));
        
    }

    public void testValueAllMinimum(JEVisAttribute attribute1,JEVisAttribute exp) throws Exception {
        System.out.println("valueAllMinimum");
        for (JEVisSample sample : attribute1.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        DataCalc calc = new DataCalcSQL();
        System.out.println("AllMinimum Result:");
        App.output(calc.valueAllMinimum(attribute1.getAllSamples()));
        comp.compareJEVis(exp,calc.valueAllMinimum(attribute1.getAllSamples()));
        
    }
    
    public void testValueMinimum(JEVisAttribute attribute1,double exp) throws Exception {
        System.out.println("valueMinimum1");
        for (JEVisSample sample : attribute1.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        DataCalc calc = new DataCalcSQL();
        
        double result = calc.valueMinimum(attribute1.getAllSamples());
        System.out.println("valueMinimum1 Result:");
        System.out.println(result);
        comp.compareValue(exp, result);
    }
    
    public void testValueMinimum(double v1,double v2,double exp) throws Exception {
        System.out.println("valueMinimum2");
        System.out.println(v1+"\n"+v2);
        
        DataCalc calc = new DataCalcSQL();
        System.out.println("valueMinimum2 Result:");
        System.out.println(Math.min(v1, v2));
        comp.compareValue(exp, Math.min(v1, v2));
    }
    
    public void testValueMinimum(List<List<JEVisSample>> attributes,double exp) throws Exception {
        System.out.println("valueMinimum3");
        
        DataCalc calc = new DataCalcSQL();
//        List<List<JEVisSample>> myAtts=new ArrayList<List<JEVisSample>>();
//        myAtts.add(attribute1.getAllSamples());
//        myAtts.add(attribute2.getAllSamples());
        double result = calc.valueMinimumMore(attributes);
        System.out.println("valueMinimum3 Result:");
        System.out.println(result);
        comp.compareValue(exp, result);
    }

    public void testValueMinimum(List<List<JEVisSample>> attributes,double value,double exp) throws Exception {
        System.out.println("valueMinimum4");
        
        DataCalc calc = new DataCalcSQL();
//        List<List<JEVisSample>> myAtts=new ArrayList<List<JEVisSample>>();
//        myAtts.add(attribute1.getAllSamples());
//        myAtts.add(attribute2.getAllSamples());
        double result = calc.valueMinimumMore(attributes, value);
        System.out.println("valueMinimum4 Result:");
        System.out.println(result);
        comp.compareValue(exp, result);
    }
    
    public void testFindGap(JEVisAttribute attribute1,DateTime begin_time,int period_s,int deviation_s,List<DateTime> exp) throws JEVisException {
        System.out.println("findGap");
        
        for (JEVisSample sample : attribute1.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        System.out.println("******************");
        System.out.println(period_s);
        System.out.println(deviation_s);
        
        DataCalc calc = new DataCalcSQL();

        System.out.println("findGap Result:");
        for (DateTime gaptime : ((DataCalcSQL)calc).findGap(attribute1.getAllSamples(),begin_time, period_s, deviation_s)) {
            System.out.println(gaptime);
        }
        
        comp.compareTime(exp, ((DataCalcSQL)calc).findGap(attribute1.getAllSamples(),begin_time, period_s,deviation_s));
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void testMultiplication(JEVisAttribute samples1,JEVisAttribute samples2,JEVisAttribute exp) throws JEVisException, ParseException {
        System.out.println("Multiplication");
        
        for (JEVisSample sample : samples1.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        System.out.println("******************");
        
        for (JEVisSample sample : samples2.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        System.out.println("******************");
        
        DataCalc calc = new DataCalcSQL();

        System.out.println("Multiplication Result:");
        for (JEVisSample result : calc.multiplication(samples1,samples2)) {
            System.out.println(result.getTimestamp() + ";" + result.getValue());
        }
        
        comp.compareJEVis(exp, calc.multiplication(samples1,samples2));
    }
    
    public void testDivision(JEVisAttribute samples1,JEVisAttribute samples2,JEVisAttribute exp) throws JEVisException, ParseException {
        System.out.println("Division");
        
        for (JEVisSample sample : samples1.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        System.out.println("******************");
        
        for (JEVisSample sample : samples2.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        System.out.println("******************");
        
        DataCalc calc = new DataCalcSQL();

        System.out.println("Division Result:");
        for (JEVisSample result : calc.division(samples1,samples2)) {
            System.out.println(result.getTimestamp() + ";" + result.getValue());
        }
        
        comp.compareJEVis(exp, calc.division(samples1,samples2));
    }
    
    public void testAverageValue(JEVisAttribute samples1,double exp) throws JEVisException, ParseException {
        System.out.println("AverageValue");
        
        for (JEVisSample sample : samples1.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        System.out.println("******************");
       
        DataCalc calc = new DataCalcSQL();

        System.out.println("AverageValue:");
        
        System.out.println(calc.getAverageValue(samples1));
        
        comp.compareValue(exp, calc.getAverageValue(samples1));
    }
    
    public void testMaxValue(JEVisAttribute samples1,double exp) throws JEVisException, ParseException {
        System.out.println("MaxValue");
        
        for (JEVisSample sample : samples1.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        System.out.println("******************");
       
        DataCalc calc = new DataCalcSQL();

        System.out.println("MaxValue Result:");
        
        System.out.println(calc.getMaxValue(samples1));
        
        comp.compareValue(exp, calc.getMaxValue(samples1));
    }
    
    public void testMeanDeviation(JEVisAttribute samples1,double exp) throws JEVisException, ParseException {
        System.out.println("MeanDeviation");
        
        for (JEVisSample sample : samples1.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        System.out.println("******************");
       
        DataCalc calc = new DataCalcSQL();

        System.out.println("MeanDeviation Result:");
        
        System.out.println(calc.meanDeviation(samples1));
        
        comp.compareValue(exp, calc.meanDeviation(samples1));
    }
    
    public void testShiftTime(JEVisAttribute samples1,int shiftTime,JEVisAttribute exp) throws JEVisException, ParseException {
        System.out.println("ShiftTime");
        
        for (JEVisSample sample : samples1.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        System.out.println("******************");
        
        System.out.println("shift time: "+shiftTime);
        
        System.out.println("******************");
        
        DataCalc calc = new DataCalcSQL();

        System.out.println("ShiftTime Result:");
        for (JEVisSample result : calc.addShiftTime(samples1, shiftTime)) {
            System.out.println(result.getTimestamp() + ";" + result.getValue());
        }
        
        comp.compareJEVis(exp, calc.addShiftTime(samples1, shiftTime));
    }
    
    public void testLowPassFilter(JEVisAttribute samples1,double lowerlimit,JEVisAttribute exp) throws JEVisException, ParseException {
        System.out.println("LowPassFilter");
        
        for (JEVisSample sample : samples1.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        System.out.println("******************");
        
        System.out.println("Lower limit: "+lowerlimit);
        
        System.out.println("******************");
        
        DataCalc calc = new DataCalcSQL();

        System.out.println("LowPassFilter Result:");
        for (JEVisSample result : calc.lowPassFilter(samples1, lowerlimit)) {
            System.out.println(result.getTimestamp() + ";" + result.getValue());
        }
        
        comp.compareJEVis(exp, calc.lowPassFilter(samples1, lowerlimit));
    }
    
    public void testDerivation_JEVisAttribute_Int(JEVisAttribute samples1,int period_s,JEVisAttribute exp) throws JEVisException, ParseException {
        System.out.println("Derivation");
        
        for (JEVisSample sample : samples1.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        System.out.println("******************");
        System.out.println("period_s: "+period_s);
       
        DataCalc calc = new DataCalcSQL();

        System.out.println("Derivation Result:");
        for (JEVisSample result : calc.derivation(samples1,period_s)) {
            System.out.println(result.getTimestamp() + ";" + result.getValue());
        }
        
        comp.compareJEVis(exp, calc.derivation(samples1,period_s));
    }
    
    public void testDifferentialCumulativeConverter(JEVisAttribute samples1,JEVisAttribute exp) throws JEVisException, ParseException {
        System.out.println("DCConverter");
        
        for (JEVisSample sample : samples1.getAllSamples()) {
            System.out.println(sample.getTimestamp() + ";" + sample.getValue());
        }
        System.out.println("******************");
       
        DataCalc calc = new DataCalcSQL();

        System.out.println("DCConverter Result:");
        for (JEVisSample result : calc.differentialCumulativeConverter(samples1)) {
            System.out.println(result.getTimestamp() + ";" + result.getValue());
        }
        
        comp.compareJEVis(exp, calc.differentialCumulativeConverter(samples1));
    }
}
