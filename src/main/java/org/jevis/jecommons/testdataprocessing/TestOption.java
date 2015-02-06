/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.jecommons.testdataprocessing;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;
import org.jevis.api.JEVisAttribute;
import org.jevis.api.JEVisDataSource;
import org.jevis.api.JEVisObject;
import org.jevis.api.JEVisSample;
import org.jevis.api.sql.JEVisDataSourceSQL;
import org.joda.time.DateTime;

/**
 *
 * @author Ulrich
 */
public class TestOption {

    @SuppressWarnings("empty-statement")
    public void option(String[] command) throws Exception{

        Options opts = new Options();
        opts.addOption("help", false, "Print help for this application");
        opts.addOption("addition1", true, "add two data rows");
        opts.addOption("addition2", true, "add one value to all vaules of one data row");
        opts.addOption("addition3", true, "add many data rows");
        opts.addOption("boundaryF", true, "limt the value between upper limit and low limit");
        opts.addOption("cdConv", true, "convert a cumulative data row to a differential data row");
        opts.addOption("highpassF1", true, "limt the value under upper limit, replace the unappropriate value with upper limit");
        opts.addOption("highpassF2", true, "limt the value under upper limit, replace the unappropriate value with any one value");
        opts.addOption("integration1", true, "calculate the integration of the values");
        opts.addOption("integration2", true, "calculate the integration of the values in a range of times,time horizon can be choosed");
        opts.addOption("intervalalignment", true, "eliminate the deviation of the timestamps of one data row");
        opts.addOption("interpolation1", true, "add some vaules between every two timepunkts,according to linear method");
        opts.addOption("interpolation2", true, "add some vaules between every two timepunkts in a range of times,time horizon can be choosed, according to linear method");
        opts.addOption("linearscaling", true, "calculate every value of a data row according to \"y=a*x+b\"");
        opts.addOption("median", true, "calculate the median of one data row");
        opts.addOption("mergevalues", true, "merge the values at some timestamps to one timestamp, the values will be added");
        opts.addOption("precisionfilter", true, "delete the value,that is not bigger or smaller than it's previous value in one percentage");
        opts.addOption("sortbytime", true, "sort the whole data row according to time");
        opts.addOption("sortbyvalue", true, "sort the whole data row according to value");
        opts.addOption("splitvalues", true, "divide each original value equally into more than one value");
        opts.addOption("subtraction1", true, "every value of a data row subtract one value");
        opts.addOption("subtraction2", true, "the values of one data row subtract the values with the same time of another data row");
        opts.addOption("allmin", true, "find all minimum values with the timestamp from one data row");
        opts.addOption("min1", true, "find the minimum value from one data row(only the value)");
        opts.addOption("min2", true, "find the minimum value from two values");
        opts.addOption("min3", true, "find the minimum value from infinite many data rows(only the values)");
        opts.addOption("min4", true, "find the minimum value from infinite many data rows and a single value(only the values)");
        opts.addOption("findgap", true, "find, whether a data row has gaps. If yes, put the begin time and end time of gaps into a List, every two times is a pair.");
        opts.addOption("dcConv", true, "convert a differential data row to a cumulative data row");
        opts.addOption("max", true, "find the maximum value of one data row");
        opts.addOption("average", true, "calculate the median of one data row");
        opts.addOption("shifttime", true, "migrate the time of one data row with certain seconds");
        opts.addOption("meandeviation", true, "");
        opts.addOption("multiplication", true, "calculate the product of values, which come from different data rows but have the same time");
        opts.addOption("division", true, "calculate the consult of values, which come from two data rows but have the same time");
        opts.addOption("lowpassF", true, "filter the data row with a certain lower limit value,replace the unappropriate value with lower limit");
        opts.addOption("derivation", true, "calculate the slope between every two values of the data row");


//        opts.addOption("Help", false, "To test Max Function");
//        opts.addOption("0", false, "Format of Online Function");
//        opts.addOption("1", false, "Format of Local Function");
//        opts.addOption("2", false, "Online Multiplication Test");
//        opts.addOption("3", false, "Local Multiplication Test");
//        opts.addOption("4", true, "Online Division Test");
//        opts.addOption("5", true, "Local Division Test");
//        opts.addOption("6", true, "Online Max Value Test");
//        opts.addOption("7", false, "Local Max Value Test");
//        opts.addOption("8", false, "Online Mean-Deviation Test");
//        opts.addOption("9", false, "Local Mean-Deviation Test");
//        opts.addOption("10", false, "Online Average Value Test");
//        opts.addOption("11", false, "Local Average Value Test");
//        opts.addOption("12", false, "Online Shift-Time Test");
//        opts.addOption("13", false, "Local Shift-Time Test");
//        opts.addOption("14", false, "Online Low-Pass-Filter Test");
//        opts.addOption("15", false, "Local Low-Pass-Filter Test");
//        opts.addOption("16", false, "Online Derivation Test");
//        opts.addOption("17", false, "Local Derivation Test");
//        opts.addOption("18", false, "Online Split Value Test");
//        opts.addOption("19", false, "Local Split Value Test");
//        opts.addOption("20", false, "Online Interpolation Test");
//        opts.addOption("21", false, "Local Interpolation Test");
//        opts.addOption("22", false, "ID of Online Functions");
//        opts.addOption("Multiplication2Test", false, "To test Multiplication2 Function");
//        opts.addOption("Multiplication1TestWithID", false, "To test Multiplication Function");


        
        BasicParser parser = new BasicParser();
        CommandLine cl;
        try{
        cl = parser.parse(opts, command);
        }catch(UnrecognizedOptionException err){
            System.out.println(err.getMessage());
            String[] help=new String[1];
            help[0]="-help";
            cl = parser.parse(opts, help);
        }
        
        if (cl.hasOption("help")) {
            HelpFormatter hf = new HelpFormatter();
            hf.printHelp("OptionsTip", opts);
        } else {
//            xmlLesen xml2 = new xmlLesen();
            xmlLesenAcAtt xml = new xmlLesenAcAtt();
            
            JEVisAttribute in_param1;
            JEVisAttribute in_param2;
            JEVisAttribute in_result;
            List<Double> in_value;
            List<Double> in_resultv;
            List<DateTime> in_time;
            List<DateTime> result_time;
            
            TestFunction calc = new TestFunction();

            if (cl.hasOption("addition1")) {
                in_param1 = (JEVisAttribute)xml.paserXML(cl.getOptionValue("addition1"), "addition1", "1");
//                System.out.println(xml.paserXML(cl.getOptionValue("addition1"), "addition1", "1","JEVis").getClass());
                in_value = (List<Double>) xml.paserXML(cl.getOptionValue("addition1"), "addition1", "2");
                in_result = (JEVisAttribute) xml.paserXML(cl.getOptionValue("addition1"), "addition1", "expResult");
                if (in_param1 != null && in_value != null && in_result != null) {
                    calc.testAddition(in_param1, in_value.get(0), in_result);
                } else {
                    isNull(in_param1, in_value, in_result);
                }
            }
            if (cl.hasOption("addition2")) {
                in_param1 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("addition2"), "addition2", "1");
                in_param2 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("addition2"), "addition2", "2");
                in_result = (JEVisAttribute) xml.paserXML(cl.getOptionValue("addition2"), "addition2", "expResult");

                if (in_param1 != null && in_param2 != null && in_result != null) {
                    calc.testAddition(in_param1, in_param2, in_result);
                } else {
                    isNull(in_param1, in_param2, in_result);
                    
                }
            }
            if (cl.hasOption("addition3")) {
                in_param1 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("addition3"), "addition2", "1");
                in_param2 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("addition3"), "addition2", "2");
                in_result = (JEVisAttribute) xml.paserXML(cl.getOptionValue("addition3"), "addition2", "expResult");

                if (in_param1 != null && in_param2 != null && in_result != null) {
                    List<List<JEVisSample>> l = new ArrayList<List<JEVisSample>>();
                    l.add(in_param1.getAllSamples());
                    l.add(in_param2.getAllSamples());
                    calc.testAddition(l, in_result);
                } else {
                    isNull(in_param1, in_param2, in_result);
                    
                }
            }
            if (cl.hasOption("boundaryF")) {
                in_param1 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("boundaryF"), "BoundaryFilter", "1");
                in_value = (List<Double>) xml.paserXML(cl.getOptionValue("boundaryF"), "BoundaryFilter", "2");
                List<Boolean> delete=new ArrayList<Boolean>();
                delete=(List<Boolean>)xml.paserXML(cl.getOptionValue("boundaryF"), "BoundaryFilter", "3");
                in_result = (JEVisAttribute) xml.paserXML(cl.getOptionValue("boundaryF"), "BoundaryFilter", "expResult");
                
                if (in_param1 != null && in_value != null && in_result != null) {
                    calc.testBoundaryFilter(in_param1, in_value.get(0), in_value.get(1),delete.get(0), in_result);
                } else {
                    isNull(in_param1, in_value, delete, in_result);
                    
                }
            }
            if (cl.hasOption("cdConv")) {
                in_param1 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("cdConv"), "cdConv", "1");
                in_result = (JEVisAttribute) xml.paserXML(cl.getOptionValue("cdConv"), "cdConv", "expResult");
                if (in_param1 != null && in_result != null) {
                    calc.testCumulativeDifferentialConverter(in_param1, in_result);
                } else {
                    isNull(in_param1, in_result);
                    
                }
            }
            if (cl.hasOption("highpassF1")) {
                in_param1 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("highpassF1"), "highpassF1", "1");
                in_value = (List<Double>) xml.paserXML(cl.getOptionValue("highpassF1"), "highpassF1", "2");
                in_result = (JEVisAttribute) xml.paserXML(cl.getOptionValue("highpassF1"), "highpassF1", "expResult");
                if (in_param1 != null && in_value != null && in_result != null) {
                    calc.testHighPassFilter(in_param1, in_value.get(0), in_result);
                } else {
                    isNull(in_param1, in_value, in_result);
                    
                }
            }
            if (cl.hasOption("highpassF2")) {
                in_param1 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("highpassF2"), "highpassF2", "1");
                in_value = (List<Double>) xml.paserXML(cl.getOptionValue("highpassF2"), "highpassF2", "2");
                in_result = (JEVisAttribute) xml.paserXML(cl.getOptionValue("highpassF2"), "highpassF2", "expResult");
                if (in_param1 != null && in_value != null && in_result != null) {
                    calc.testHighPassFilter(in_param1, in_value.get(0), in_value.get(1), in_result);
                } else {
                    isNull(in_param1, in_value, in_result);
                    
                }
            }
            if (cl.hasOption("integration1")) {
                in_param1 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("integration1"), "integration1", "1");
                in_value = (List<Double>) xml.paserXML(cl.getOptionValue("integration1"), "integration1", "expResult");
                if (in_param1 != null && in_value != null) {
                    calc.testIntegration(in_param1, in_value.get(0));
                } else {
                    isNull(in_param1, in_value);
                    
                }
            }
            if (cl.hasOption("integration2")) {
                in_param1 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("integration2"), "integration2", "1");
                in_time = (List<DateTime>) xml.paserXML(cl.getOptionValue("integration2"), "integration2", "2");//
                in_value = (List<Double>) xml.paserXML(cl.getOptionValue("integration2"), "integration2", "expResult");
                if (in_param1 != null && in_value != null && in_time != null) {
                    calc.testIntegration(in_param1, in_time.get(0), in_time.get(1), in_value.get(0));
                } else {
                    isNull(in_param1, in_time, in_value);
                   
                }
            }
            if (cl.hasOption("intervalalignment")) {
                in_param1 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("intervalalignment"), "intervalalignment", "1");
                in_value = (List<Double>) xml.paserXML(cl.getOptionValue("intervalalignment"), "intervalalignment", "2");
                in_time=(List<DateTime>)xml.paserXML(cl.getOptionValue("intervalalignment"), "intervalalignment","3");
                in_result = (JEVisAttribute) xml.paserXML(cl.getOptionValue("intervalalignment"), "intervalalignment", "expResult");
                if (in_param1 != null && in_value != null && in_result != null) {
                    double v1 = in_value.get(0);
                    double v2 = in_value.get(1);
                    calc.testIntervalAlignment(in_param1,in_time.get(0),(int) v1, (int) v2, in_result);
                } else {
                    isNull(in_param1, in_value, in_time, in_result);
                    
                }
            }
            if (cl.hasOption("interpolation1")) {
                in_param1 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("interpolation1"), "interpolation1", "1");
                in_value = (List<Double>) xml.paserXML(cl.getOptionValue("interpolation1"), "interpolation1", "2");
                in_result = (JEVisAttribute) xml.paserXML(cl.getOptionValue("interpolation1"), "interpolation1", "expResult");

                if (in_param1 != null && in_value != null && in_result != null) {
                    double v1 = in_value.get(0);
                    calc.testLinearInterpolation(in_param1, (int) v1, in_result);
                } else {
                    isNull(in_param1, in_value, in_result);
                    
                }
            }
            if (cl.hasOption("interpolation2")) {
                in_param1 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("interpolation2"), "interpolation2", "1");
                in_value = (List<Double>) xml.paserXML(cl.getOptionValue("interpolation2"), "interpolation2", "2");
                in_time = (List<DateTime>) xml.paserXML(cl.getOptionValue("interpolation2"), "interpolation2", "3");//
                in_result = (JEVisAttribute) xml.paserXML(cl.getOptionValue("interpolation2"), "interpolation2", "expResult");
                if (in_param1 != null && in_value != null && in_result != null) {
                    double v1 = in_value.get(0);
                    calc.testLinearInterpolation(in_param1, in_time.get(0), in_time.get(1), (int) v1, in_result);
                } else {
                    isNull(in_param1, in_value, in_time, in_result);
                   
                }
            }
            if (cl.hasOption("linearscaling")) {
                in_param1 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("linearscaling"), "linearscaling", "1");
                in_value = (List<Double>) xml.paserXML(cl.getOptionValue("linearscaling"), "linearscaling", "2");
                in_result = (JEVisAttribute) xml.paserXML(cl.getOptionValue("linearscaling"), "linearscaling", "expResult");
                if (in_param1 != null && in_value != null && in_result != null) {
                    calc.testLinearScaling(in_param1, in_value.get(0), in_value.get(1), in_result);
                } else {
                    isNull(in_param1, in_value, in_result);
                    
                }
            }
            if (cl.hasOption("median")) {
                in_param1 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("median"), "median", "1");
                in_value = (List<Double>) xml.paserXML(cl.getOptionValue("median"), "median", "expResult");
                if (in_param1 != null && in_value != null) {
                    calc.testMedian(in_param1, in_value.get(0));
                } else {
                    isNull(in_param1, in_value);
                    
                }
            }
            if (cl.hasOption("mergevalues")) {
                in_param1 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("mergevalues"), "mergevalues", "1");
                in_value = (List<Double>) xml.paserXML(cl.getOptionValue("mergevalues"), "mergevalues", "2");
                in_time = (List<DateTime>) xml.paserXML(cl.getOptionValue("mergevalues"), "mergevalues", "3");
                in_result = (JEVisAttribute) xml.paserXML(cl.getOptionValue("mergevalues"), "mergevalues", "expResult");

                if (in_param1 != null && in_value != null && in_time != null && in_result != null) {
                    double v1 = in_value.get(0);
                    double v2 = in_value.get(1);
                    calc.testMergeValues(in_param1, in_time.get(0), (int) v1, (int) v2, in_result);
                } else {
                    isNull(in_param1, in_value, in_time, in_result);
                    
                }
            }
            if (cl.hasOption("precisionfilter")) {
                in_param1 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("precisionfilter"), "precisionfilter", "1");
                in_value = (List<Double>) xml.paserXML(cl.getOptionValue("precisionfilter"), "precisionfilter", "2");
                in_result = (JEVisAttribute) xml.paserXML(cl.getOptionValue("precisionfilter"), "precisionfilter", "expResult");

                if (in_param1 != null && in_value != null && in_result != null) {
                    calc.testPrecisionFilter(in_param1, in_value.get(0), in_result);
                } else {
                    isNull(in_param1, in_value, in_result);
                    
                }
            }
            if (cl.hasOption("sortbytime")) {
                in_param1 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("sortbytime"), "SortByTime", "1");
                in_value = (List<Double>) xml.paserXML(cl.getOptionValue("sortbytime"), "SortByTime", "2");
                in_result = (JEVisAttribute) xml.paserXML(cl.getOptionValue("sortbytime"), "SortByTime", "expResult");
                if (in_param1 != null && in_value != null && in_result != null) {
                    double v1 = in_value.get(0);
                    calc.testSortByTime(in_param1, (int) v1, in_result);
                } else {
                    isNull(in_param1, in_value, in_result);
                    
                }
            }
            if (cl.hasOption("sortbyvalue")) {
                in_param1 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("sortbyvalue"), "SortByValue", "1");
                in_value = (List<Double>) xml.paserXML(cl.getOptionValue("sortbyvalue"), "SortByValue", "2");
                in_result = (JEVisAttribute) xml.paserXML(cl.getOptionValue("sortbyvalue"), "SortByValue", "expResult");
                if (in_param1 != null && in_value != null && in_result != null) {
                    double v1 = in_value.get(0);
                    calc.testSortByValue(in_param1, (int) v1, in_result);
                } else {
                    isNull(in_param1, in_value, in_result);
                    
                }
            }
            if (cl.hasOption("splitvalues")) {
                in_param1 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("splitvalues"), "SplitValues", "1");
                in_value = (List<Double>) xml.paserXML(cl.getOptionValue("splitvalues"), "SplitValues", "2");
                List<Boolean> backward=new ArrayList<Boolean>();
                backward=(List<Boolean>)xml.paserXML(cl.getOptionValue("splitvalues"), "SplitValues", "3");
                in_result = (JEVisAttribute) xml.paserXML(cl.getOptionValue("splitvalues"), "SplitValues", "expResult");
                
                if (in_param1 != null && in_value != null && in_result != null && backward != null) {
                    double v1 = in_value.get(0);
                    double v2 = in_value.get(1);
                    calc.testSplitValues(in_param1, (int) v1, (int) v2,backward.get(0), in_result);
                } else {
                    isNull(in_param1, in_value, backward, in_result);
                    
                }
            }
            if (cl.hasOption("subtraction1")) {
                in_param1 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("subtraction1"), "subtraction1", "1");
                in_value = (List<Double>) xml.paserXML(cl.getOptionValue("subtraction1"), "subtraction1", "2");
                in_result = (JEVisAttribute) xml.paserXML(cl.getOptionValue("subtraction1"), "subtraction1", "expResult");
                if (in_param1 != null && in_value != null && in_result != null) {
                    calc.testSubtraction(in_param1, in_value.get(0), in_result);
                } else {
                    isNull(in_param1, in_value, in_result);
                    
                }
            }
            if (cl.hasOption("subtraction2")) {
                in_param1 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("subtraction2"), "subtraction2", "1");
                in_param2 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("subtraction2"), "subtraction2", "2");
                in_result = (JEVisAttribute) xml.paserXML(cl.getOptionValue("subtraction2"), "subtraction2", "expResult");
                if (in_param1 != null && in_param2 != null && in_result != null) {
                    calc.testSubtraction(in_param1, in_param2, in_result);
                } else {
                    isNull(in_param1, in_param2, in_result);
                    
                }
            }
            if (cl.hasOption("allmin")) {
                in_param1 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("allmin"), "allmin", "1");
                in_result = (JEVisAttribute) xml.paserXML(cl.getOptionValue("allmin"), "allmin", "expResult");
                if (in_param1 != null && in_result != null) {
                    calc.testValueAllMinimum(in_param1, in_result);
                } else {
                    isNull(in_param1,in_result);
                    
                }
            }
            if (cl.hasOption("min1")) {
                in_param1 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("min1"), "min1", "1");
                in_value = (List<Double>) xml.paserXML(cl.getOptionValue("min1"), "min1", "expResult");
                if (in_param1 != null && in_value != null) {
                    calc.testValueMinimum(in_param1, in_value.get(0));
                } else {
                    isNull(in_param1, in_value);
                    
                }
            }
//            if (cl.hasOption("min2")) {
//                in_value = (List<Double>) xml.paserXML(cl.getOptionValue("min2"), "min2", "1");
//                List<Double> in_value3 = (List<Double>) xml.paserXML(cl.getOptionValue("min2"), "min2", "2");
//                in_resultv = (List<Double>) xml.paserXML(cl.getOptionValue("min2"), "min2", "expResult");
//                if (in_value != null) {
//                    calc.testValueMinimum(in_value.get(0), in_value3.get(0), in_resultv.get(0));
//                } else {
//                    isNull(in_value,in_value3);
//                }
//            }
            if (cl.hasOption("min3")) {
                in_param1 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("min3"), "min3", "1");
                in_param2 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("min3"), "min3", "2");
                in_value = (List<Double>) xml.paserXML(cl.getOptionValue("min3"), "min3", "expResult");
                if (in_param1 != null && in_param2 != null && in_value != null) {
                    List<List<JEVisSample>> attributes=new ArrayList<List<JEVisSample>>();
                    attributes.add(in_param1.getAllSamples());
                    attributes.add(in_param2.getAllSamples());
                    calc.testValueMinimum(attributes, in_value.get(0));
                } else {
                    isNull(in_param1, in_param2, in_value);
                    
                }
            }
            if (cl.hasOption("min4")) {
                in_param1 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("min4"), "min4", "1");
                in_param2 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("min4"), "min4", "2");
                in_value = (List<Double>) xml.paserXML(cl.getOptionValue("min4"), "min4", "3");
                in_resultv = (List<Double>) xml.paserXML(cl.getOptionValue("min4"), "min4", "expResult");
                if (in_param1 != null && in_param2 != null && in_value != null && in_resultv != null) {
                    List<List<JEVisSample>> attributes=new ArrayList<List<JEVisSample>>();
                    attributes.add(in_param1.getAllSamples());
                    attributes.add(in_param2.getAllSamples());
                    calc.testValueMinimum(attributes, in_value.get(0), in_resultv.get(0));
                } else {
                    isNull(in_param1, in_param2, in_value, in_resultv);
                    
                }
            }
            if (cl.hasOption("findgap")) {
                in_param1 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("findgap"), "FindGap", "1");
                in_value = (List<Double>) xml.paserXML(cl.getOptionValue("findgap"), "FindGap", "2");
                in_time=(List<DateTime>)xml.paserXML(cl.getOptionValue("findgap"), "FindGap","3");
                result_time = (List<DateTime>) xml.paserXML(cl.getOptionValue("findgap"), "FindGap", "expResult");
                if (in_param1 != null && in_time != null && in_value != null) {
                    double v1 = in_value.get(0);
                    double v2 = in_value.get(1);
                    calc.testFindGap(in_param1,in_time.get(0) ,(int) v1, (int) v2, result_time);
                } else {
                    isNull(in_param1, in_value, in_time, result_time);
                    
                }
            }
            if (cl.hasOption("dcConv")) {
                in_param1 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("dcConv"), "dcConv", "1");
                in_result = (JEVisAttribute) xml.paserXML(cl.getOptionValue("dcConv"), "dcConv", "expResult");
                if (in_param1 != null) {
                    calc.testDifferentialCumulativeConverter(in_param1,in_result);
                } else {
                    isNull(in_param1, in_result);
                    
                }
            }
            if (cl.hasOption("max")) {
                in_param1 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("max"), "MaxValue", "1");
                in_resultv = (List<Double>) xml.paserXML(cl.getOptionValue("max"), "MaxValue", "expResult");
                if (in_param1 != null && in_resultv != null) {
                    calc.testMaxValue(in_param1,in_resultv.get(0));
                } else {
                    isNull(in_param1, in_resultv);
                    
                }
            }
            if (cl.hasOption("average")) {
                in_param1 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("average"), "AverageValue", "1");
                in_resultv = (List<Double>) xml.paserXML(cl.getOptionValue("average"), "AverageValue", "expResult");
                if (in_param1 != null && in_resultv != null) {
                    calc.testAverageValue(in_param1,in_resultv.get(0));
                } else {
                    isNull(in_param1, in_resultv);
                    
                }
            }
            if (cl.hasOption("shifttime")) {
                in_param1 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("shifttime"), "ShiftTime", "1");
                in_value = (List<Double>) xml.paserXML(cl.getOptionValue("shifttime"), "ShiftTime", "2");
                in_result = (JEVisAttribute) xml.paserXML(cl.getOptionValue("shifttime"), "ShiftTime", "expResult");
                if (in_param1 != null && in_value!=null && in_result != null) {
                    double v1 = in_value.get(0);
                    calc.testShiftTime(in_param1, (int) v1,in_result);
                } else {
                    isNull(in_param1, in_value, in_result);
                    
                }
            }
            if (cl.hasOption("meandeviation")) {
                in_param1 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("meandeviation"), "MeanDeviation", "1");
                in_resultv = (List<Double>) xml.paserXML(cl.getOptionValue("meandeviation"), "MeanDeviation", "expResult");
                if (in_param1 != null && in_resultv != null) {
                    calc.testMeanDeviation(in_param1,in_resultv.get(0));
                } else {
                    isNull(in_param1, in_resultv);
                    
                }
            }
            if (cl.hasOption("multiplication")) {
                in_param1 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("multiplication"), "Multiplication", "1");
                in_param2 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("multiplication"), "Multiplication", "2");
                in_result = (JEVisAttribute) xml.paserXML(cl.getOptionValue("multiplication"), "Multiplication", "expResult");
                if (in_param1 != null && in_param2 != null && in_result != null) {
                    calc.testMultiplication(in_param1, in_param2, in_result);
                } else {
                    isNull(in_param1, in_param2, in_result);
                    
                }
            }
            if (cl.hasOption("division")) {
                in_param1 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("division"), "Division", "1");
                in_param2 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("division"), "Division", "2");
                in_result = (JEVisAttribute) xml.paserXML(cl.getOptionValue("division"), "Division", "expResult");
                if (in_param1 != null && in_param2 != null && in_result != null) {
                    calc.testDivision(in_param1, in_param2, in_result);
                } else {
                    isNull(in_param1, in_param2, in_result);
                    
                }
            }
            if (cl.hasOption("lowpassF")) {
                in_param1 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("lowpassF"), "LowPassFilter", "1");
                in_value = (List<Double>) xml.paserXML(cl.getOptionValue("lowpassF"), "LowPassFilter", "2");
                in_result = (JEVisAttribute) xml.paserXML(cl.getOptionValue("lowpassF"), "LowPassFilter", "expResult");
                if (in_param1 != null && in_value != null && in_result != null) {
                    calc.testLowPassFilter(in_param1, in_value.get(0), in_result);
                } else {
                    isNull(in_param1, in_value, in_result);
                    
                }
            }
            if (cl.hasOption("derivation")) {
                in_param1 = (JEVisAttribute) xml.paserXML(cl.getOptionValue("derivation"), "Derivation", "1");
                in_value = (List<Double>) xml.paserXML(cl.getOptionValue("derivation"), "Derivation", "2");
                in_result = (JEVisAttribute) xml.paserXML(cl.getOptionValue("derivation"), "Derivation", "expResult");
                if (in_param1 != null && in_value != null && in_result != null) {
                    double v1 = in_value.get(0);
                    calc.testDerivation_JEVisAttribute_Int(in_param1, (int) v1, in_result);
                } else {
                    isNull(in_param1, in_value, in_result);
                    
                }
            }




//            if (cl.hasOption("Help")) {
//                System.out.println(" Here are the options that you can input, please choose a number:");
//                System.out.println("  0. Format of Online Function         1. Format of Local Function");
//                System.out.println("  2. Online Multiplication Test        3. Local Multiplication Test                    ");
//                System.out.println("  4. Online Division Test              5. Local Division Test");
//                System.out.println("  6. Online Max Value Test             7. Local Max Value Test");
//                System.out.println("  8. Online Mean-Deviation Test       9. Local Mean-Deviation Test");
//                System.out.println(" 10. Online Average Value Test        11. Local Average Value Test");
//                System.out.println(" 12. Online Shift-Time Test           13. Local Shift-Time Test");
//                System.out.println(" 14. Online Low-Pass-Filter Test      15. Local Low-Pass-Filter Test");
//                System.out.println(" 16. Online Derivation Test           17. Local Derivation Test");
//                System.out.println(" 18. Online Split Value Test          19. Local Split Value Test ");
//                System.out.println(" 20. Online Interpolation Test        21. Local Interpolation Test");
//                System.out.println(" 22. ID of Online Functions                                        ");
//            }
//            if (cl.hasOption("0")) {
//                System.out.println(" Online Multiplication Test  ---->  Two data row multiplied by each other. ");
//                System.out.println("                     Format  ---->  Datarow1's ID  Datarow2's ID  Expected-result's ID");
//                System.out.println();
//                System.out.println(" Online MaxValue Test        ---->  Find the maximum value of the data row. ");
//                System.out.println("                     Format  ---->  Datarow's ID  Expected-result's ID");
//                System.out.println();
//                System.out.println(" Online Mean Deviation Test  ---->  Calculate mean deviation of the data row     ");
//                System.out.println("                     Format  ---->  Datarow's ID  Expected-result's ID");
//                System.out.println();
//                System.out.println(" Online Average Value Test   ---->  Calculate average value of the data row    ");
//                System.out.println("                     Format  ---->  Datarow's ID  Expected-result's ID");
//                System.out.println();
//                System.out.println(" Online Shift-Time Test      ---->  Shift the time axis ");
//                System.out.println("                     Format  ---->  Datarow's ID  Shift-time(seconds)  Expected-result's ID");
//                System.out.println();
//                System.out.println(" Online Low-Pass-Filter Test ---->  Filter the data row with a certain value    ");
//                System.out.println("                     Format  ---->  Datarow's ID  Filter-value  Expected-result's ID");
//                System.out.println();
//                System.out.println(" Online Division Test        ---->  One datarow divided by another datarow.    ");
//                System.out.println("                     Format  ---->  Datarow1's ID  Datarow2's ID  Expected-result's ID");
//                System.out.println();
//                System.out.println(" Online Derivation Test      ---->  Calculate the derivation of the datarow    ");
//                System.out.println("                     Format  ---->  Datarow's ID  Expected-result's ID");
//                System.out.println();
//                System.out.println(" Online SplitValues Test     ---->  Split the data row");
//                System.out.println("                     Format  ---->  Datarow's ID  Split-Quantity  Expected-result's ID");
//                System.out.println();
//                // there is a problem?
//                System.out.println(" Online Interpolation Test   ---->  Interpolate certaion quantities of values    ");
//                System.out.println("                     Format  ---->  Datarow's ID  Interpolation-Quantity  Expected-result's ID");
//                System.out.println();
//
//            }
//
//            if (cl.hasOption("1")) {
//                System.out.println(" Local Multiplication Test  ---->  Two data row multiplied by each other. ");
//                System.out.println("                     Format ---->  Datarow1's path  Datarow2's path  Expected-result's path");
//                System.out.println();
//                System.out.println(" Local MaxValue Test        ---->  Find the maximum value of the data row. ");
//                System.out.println("                     Format ---->  Datarow's path  Expected-result's path");
//                System.out.println();
//                System.out.println(" Local Mean Deviation Test  ---->  Calculate mean deviation of the data row     ");
//                System.out.println("                     Format ---->  Datarow's path  Expected-result's path");
//                System.out.println();
//                System.out.println(" Local Average Value Test   ---->  Calculate average value of the data row    ");
//                System.out.println("                     Format ---->  Datarow's path  Expected-result's path");
//                System.out.println();
//                System.out.println(" Local Shift-Time Test      ---->  Shift the time axis ");
//                System.out.println("                     Format ---->  Datarow's path  Shift-time(seconds)  Expected-result's path");
//                System.out.println();
//                System.out.println(" Local Low-Pass-Filter Test ---->  Filter the data row with a certain value    ");
//                System.out.println("                     Format ---->  Datarow's path  Filter-value  Expected-result's path");
//                System.out.println();
//                System.out.println(" Local Division Test        ---->  One datarow divided by another datarow.    ");
//                System.out.println("                     Format ---->  Datarow1's path  Datarow2's ID  Expected-result's path");
//                System.out.println();
//                System.out.println(" Local Derivation Test      ---->  Calculate the derivation of the datarow    ");
//                System.out.println("                     Format ---->  Datarow's path  Expected-result's path");
//                System.out.println();
//                System.out.println(" Local SplitValues Test     ---->  Split the data row");
//                System.out.println("                     Format ---->  Datarow's path  Split-Quantity  Expected-result's path");
//                System.out.println();
//                // there is a problem?
//                System.out.println(" Local Interpolation Test   ---->  Interpolate certaion quantities of values    ");
//                System.out.println("                     Format ---->  Datarow's path  Interpolation-Quantity  Expected-result's path");
//                System.out.println();
//
//            }
//            if (cl.hasOption("22")) {
//
//                System.out.println("Here are the IDs of different functions:               ");
//                System.out.println(" MultiplicationTest   ---->  factor1: 1028, factor2: 1029,expected result: 1095 ");
//                System.out.println(" MaxValue Test         ---->  datarow :994 , expected max value: 15.103               ");
//                System.out.println(" Mean Deviation Test   ---->  datarow :1252, expected result:1253                                        ");
//                System.out.println(" Average Value Test    ---->  datarow :1248, expected average value: -1.1481                                             ");
//                System.out.println(" Shift-Time Test       ---->  datarow :1042, shift time in seconds: 900  expected result:1130         ");
//                System.out.println(" Low-Pass-Filter       ---->  datarow :1123, filter :value: 10  expected result:1124       ");
//                System.out.println(" Division Test         ---->  dividend:1036, divider: 1035,expected result: 1119  ");
//                System.out.println(" Derivation Test       ---->  dividend:1116, expected result: 1117       ");
//                System.out.println(" SplitValues Test      ---->  datarow :978 , split number:2, expected result:1134                   ");
//                System.out.println(" Interpolation Test    ---->  datarow :1047, expected result: 1121                   ");
//
//            }
//
//            if (cl.hasOption("2")) {
//                //Multiplication by ID to test
//                JEVisDataSource ds = new JEVisDataSourceSQL("192.168.2.55", "3306", "jevis", "jevis", "jevistest",null,null);
//                ds.connect("Sys Admin", "jevis");
//
//                JEVisObject object1 = ds.getObject(Long.parseLong(command[1]));
//                JEVisObject object2 = ds.getObject(Long.parseLong(command[2]));
//                JEVisObject expectedObj = ds.getObject(Long.parseLong(command[3]));
//                JEVisAttribute factor1 = object1.getAttribute("Value");
//                JEVisAttribute factor2 = object2.getAttribute("Value");
//                JEVisAttribute expectedResult = expectedObj.getAttribute("Value");
//                TestFunction2 TestMultiplication1 = new TestFunction2();
//                System.out.println("Next we will test our Multiplication Function");
//                TestMultiplication1.multiplicationTestOnline(factor1, factor2, expectedResult);
//            }
//
//
//            // Multiplication by path to test      
//            if (cl.hasOption("3")) {
//
//                TestFunction2 TestMultiplication1 = new TestFunction2();
//                System.out.println("Next we will test our Multiplication Function");
//                TestMultiplication1.multiplicationTestLocal(command[1], command[2], command[3]);
//
//            }
//
//
//
//            //division by ID test
//            if (cl.hasOption("4")) {
//
//                JEVisDataSource ds = new JEVisDataSourceSQL("192.168.2.55", "3306", "jevis", "jevis", "jevistest",null,null);
//                ds.connect("Sys Admin", "jevis");
//
//                JEVisObject object1 = ds.getObject(Long.parseLong(command[1]));
//                JEVisObject object2 = ds.getObject(Long.parseLong(command[2]));
//                JEVisObject expectedObj = ds.getObject(Long.parseLong(command[3]));
//                JEVisAttribute dividend = object1.getAttribute("Value");
//                JEVisAttribute divider = object2.getAttribute("Value");
//                JEVisAttribute expectedResult = expectedObj.getAttribute("Value");
//                TestFunction2 DivisionTest = new TestFunction2();
//                System.out.println("Next we will test our Division Function");
//                DivisionTest.divisionOnlineDataTest(dividend, divider, expectedResult);
//            }
//
//
//            if (cl.hasOption("5")) {
//                System.out.println("Local division Test Function missed");
//
//            }
//
//
//            //Max value by ID to test 
//            if (cl.hasOption("6")) {
//                JEVisDataSource ds = new JEVisDataSourceSQL("192.168.2.55", "3306", "jevis", "jevis", "jevistest",null,null);
//                ds.connect("Sys Admin", "jevis");
//                JEVisObject object1 = ds.getObject(Long.parseLong(command[1]));
//                JEVisAttribute toBeTested = object1.getAttribute("Value");
//
//                TestFunction2 MaxTest = new TestFunction2();
//                System.out.println("Next we will test our MAX-Value Function");
//                MaxTest.getMaxValueTestOnline(toBeTested, Double.parseDouble(command[2]));
//            }
//
//            //Max value by path to test 
//            if (cl.hasOption("7")) {
//                TestFunction2 MaxTest = new TestFunction2();
//                System.out.println("Next we will test our MAX-Value Function");
//                MaxTest.getMaxValueTestLocal(command[1], Double.parseDouble(command[2]));
//            }
//
//            //Mean value by ID to test        
//            if (cl.hasOption("8")) {
//                JEVisDataSource ds = new JEVisDataSourceSQL("192.168.2.55", "3306", "jevis", "jevis", "jevistest",null,null);
//                ds.connect("Sys Admin", "jevis");
//                JEVisObject object1 = ds.getObject(Long.parseLong(command[1]));
//                JEVisAttribute toBeTested = object1.getAttribute("Value");
//
//                TestFunction2 MeanTest = new TestFunction2();
//                System.out.println("Next we will test our Mean-Value Function");
//                MeanTest.meanDeviationTestOnline(toBeTested, Double.parseDouble(command[2]));
//            }
//
//            if (cl.hasOption("9")) {
//                System.out.println("Local MeanDeviationTest Function missed");
//
//            }
//
////Average by ID to test
//            if (cl.hasOption("10")) {
//                JEVisDataSource ds = new JEVisDataSourceSQL("192.168.2.55", "3306", "jevis", "jevis", "jevistest",null,null);
//                ds.connect("Sys Admin", "jevis");
//                JEVisObject object1 = ds.getObject(Long.parseLong(command[1]));
//                JEVisAttribute toBeTested = object1.getAttribute("Value");
//                TestFunction2 AverageTest = new TestFunction2();
//                System.out.println("Next we will test our Average-Value Function");
//                AverageTest.getAverageValueTestOnline(toBeTested, Double.parseDouble(command[2]));
//            }
//
////Average by Path to test
//            if (cl.hasOption("11")) {
//                TestFunction2 AverageTest = new TestFunction2();
//                System.out.println("Next we will test our Average-Value Function");
//                AverageTest.getAverageValueTestLocal(command[1], Double.parseDouble(command[2]));
//            }
//
//
//
//
////shift time by ID
//            if (cl.hasOption("12")) {
//                JEVisDataSource ds = new JEVisDataSourceSQL("192.168.2.55", "3306", "jevis", "jevis", "jevistest",null,null);
//                ds.connect("Sys Admin", "jevis");
//                JEVisObject object1 = ds.getObject(Long.parseLong(command[1]));
//                JEVisObject expectedObj = ds.getObject(Long.parseLong(command[3]));
//                JEVisAttribute toBeShifted = object1.getAttribute("Value");
//                JEVisAttribute expectedResult = expectedObj.getAttribute("Value");
//                TestFunction2 ShiftTimeTest = new TestFunction2();
//                System.out.println("Next we will test our ShiftTime Function");
//                ShiftTimeTest.addShiftTimeTestOnline(toBeShifted, Integer.parseInt(command[2]), expectedResult);
//            }
//
////shift time by Path
//            if (cl.hasOption("13")) {
//                TestFunction2 ShiftTimeTest = new TestFunction2();
//                System.out.println("Next we will test our ShiftTime Function");
//                ShiftTimeTest.addShiftTimeTestLocal(command[1], Integer.parseInt(command[2]), command[3]);
//
//
//            }
//
//
////low pass filter by ID test
//            if (cl.hasOption("14")) {
//                JEVisDataSource ds = new JEVisDataSourceSQL("192.168.2.55", "3306", "jevis", "jevis", "jevistest",null,null);
//                ds.connect("Sys Admin", "jevis");
//                JEVisObject object1 = ds.getObject(Long.parseLong(command[1]));
//                JEVisObject expectedObj = ds.getObject(Long.parseLong(command[3]));
//
//                JEVisAttribute toBeTested = object1.getAttribute("Value");
//                JEVisAttribute expectedResult = expectedObj.getAttribute("Value");
//
//                TestFunction2 LowPassFilterTest = new TestFunction2();
//                System.out.println("Next we will test our Low-Pass-Filter");
//                LowPassFilterTest.lowPassFilterTestOnline(toBeTested, Double.parseDouble(command[2]), expectedResult);
//            }
//
//
//            if (cl.hasOption("15")) {
//                System.out.println("Local Low Pass Filter function missed");
//
//            }
//
//
//
////deriviation by ID to Test        
//            if (cl.hasOption("16")) {
//                JEVisDataSource ds = new JEVisDataSourceSQL("192.168.2.55", "3306", "jevis", "jevis", "jevistest",null,null);
//                ds.connect("Sys Admin", "jevis");
//
//                JEVisObject object1 = ds.getObject(Long.parseLong(command[1]));
//
//                JEVisObject expectedObj = ds.getObject(Long.parseLong(command[2]));
//                JEVisAttribute tobeTested = object1.getAttribute("Value");
//                JEVisAttribute expectedResult = expectedObj.getAttribute("Value");
//
//                TestFunction2 DerivationTest = new TestFunction2();
//                System.out.println("Next we will test our Derivation Function");
//                DerivationTest.derivationTestOnline(tobeTested, expectedResult);
//            }
//
////deriviation by path to Test        
//            if (cl.hasOption("17")) {
//                TestFunction2 DerivationTest = new TestFunction2();
//                System.out.println("Next we will test our Derivation Function");
//                DerivationTest.derivationTestLocal(command[1], command[2]);
//            }
//
//
////split value by ID to test
//            if (cl.hasOption("18")) {
//                JEVisDataSource ds = new JEVisDataSourceSQL("192.168.2.55", "3306", "jevis", "jevis", "jevistest",null,null);
//                ds.connect("Sys Admin", "jevis");
//
//                JEVisObject object1 = ds.getObject(Long.parseLong(command[1]));
//                JEVisObject expectedObj = ds.getObject(Long.parseLong(command[3]));
//                JEVisAttribute toBeTested = object1.getAttribute("Value");
//                JEVisAttribute exp = expectedObj.getAttribute("Value");
//                TestFunction2 SplitValuesTest = new TestFunction2();
//                System.out.println("Next we will test our SplitValues Function");
//                SplitValuesTest.splitValuesTestOnline(toBeTested, Integer.parseInt(command[2]), exp);
//            }
//
//            //split value by path to test
//            if (cl.hasOption("19")) {
//                TestFunction2 SplitValuesTest = new TestFunction2();
//                System.out.println("Next we will test our SplitValues Function");
//                SplitValuesTest.splitValuesTestLocal(command[1], Integer.parseInt(command[2]), command[3]);
//            }
//
//
//
////interpolation by ID to test       
//            if (cl.hasOption("20")) {
//                JEVisDataSource ds = new JEVisDataSourceSQL("192.168.2.55", "3306", "jevis", "jevis", "jevistest",null,null);
//                ds.connect("Sys Admin", "jevis");
//
//                JEVisObject object1 = ds.getObject(Long.parseLong(command[1]));
//                JEVisObject expectedObj = ds.getObject(Long.parseLong(command[3]));
//                JEVisAttribute toBeTested = object1.getAttribute("Value");
//                JEVisAttribute exp = expectedObj.getAttribute("Value");
//
//                TestFunction2 InterpolationTest = new TestFunction2();
//                System.out.println("Next we will test our Interpolation Function");
//                InterpolationTest.interpLinearTestOnline(toBeTested, Integer.parseInt(command[2]), exp);
//            }
//
//
//
//            if (cl.hasOption("21")) {
//                TestFunction2 InterpolationTest = new TestFunction2();
//                System.out.println("Next we will test our Interpolation Function");
//                InterpolationTest.interpLinearTestLocal(command[1], Integer.parseInt(command[2]), command[3]);
//            }
        }
    }
    
    public void isNull(Object... obj) {
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] == null) {
                if (i == obj.length - 1) {
                    System.out.println("The expected result can't be found in XML file,please check the attributes of node");
                } else {
                    System.out.println("The parameter " + (i+1) + " can't be found in XML file,please check the attributes of node");
                }
            }
        }
    }
}
