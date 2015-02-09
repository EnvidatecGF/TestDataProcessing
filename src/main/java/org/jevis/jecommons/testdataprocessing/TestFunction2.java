/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.jecommons.testdataprocessing;

import au.com.bytecode.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.jevis.api.JEVisAttribute;
import org.jevis.api.JEVisException;
import org.jevis.api.JEVisSample;
import org.joda.time.DateTime;

/**
 *
 * @author jx
 */
public class TestFunction2 {
    //fertig
    public List<Double> multiSampleList(List<Double> l1, List<Double> l2) throws ParseException, JEVisException {

        List<Double> testResult = new ArrayList<Double>();
        for (int i = 0; i < l1.size(); i++) {
            testResult.set(i, l1.get(i) * l2.get(i));
        }

        return testResult;
    }

     //fertig
    public double formater(double x) {
        Locale.setDefault(Locale.ENGLISH);
        DecimalFormat df1 = new DecimalFormat("####.000");
        double result = Double.parseDouble(df1.format(x));
        return result;
    }

    //fertig
    public void onlineDataCompare(List<JEVisSample> realResult, List<JEVisSample> expectedResult) throws JEVisException {
    boolean sign =false; 
        if (realResult.size() != expectedResult.size()) {
            System.out.println(realResult.size());
            System.out.println("the result doesn't match");
        } else {
            
             System.out.println("Real Result:                                 Expected Result:");
            for (int i = 0; i < realResult.size(); i++) {
                //small errors is acceptable
                if (realResult.get(i).getTimestamp().equals(expectedResult.get(i).getTimestamp()) &(Math.abs((Double.parseDouble((realResult.get(i).getValue()).toString()))-(Double.parseDouble(expectedResult.get(i).getValue().toString()))) )<0.01)    { 
                    System.out.println();
                    System.out.println(realResult.get(i).getTimestamp() +": "+ realResult.get(i).getValue()+"      "+expectedResult.get(i).getTimestamp()+": "+expectedResult.get(i).getValue() +"---->match!"); 
                } 
                else {
                    System.out.println();
                    System.out.println(realResult.get(i).getTimestamp() +": "+ realResult.get(i).getValue()+"      "+expectedResult.get(i).getTimestamp()+": "+expectedResult.get(i).getValue() +"---->not match!");
                    sign=true;
                }
            }
        }
        if (sign)
           System.out.println("The test failed");
        else
            System.out.println("The test successed");
        System.out.println("");
        System.out.println("Test finished");
        System.out.println("");
    }
    
    //fertig
    public void localDataCompare(List<JEVisSampleTest2> realResult, List<JEVisSampleTest2> expectedResult) throws JEVisException {
    boolean sign =false; 
        if (realResult.size() != expectedResult.size()) {
            System.out.println(realResult.size());
            System.out.println("the result doesn't match");
        } else {

             System.out.println("Real Result:                                 Expected Result:");
            for (int i = 0; i < realResult.size(); i++) {
                //small errors is acceptable
                if (realResult.get(i).getTimestamp().equals(expectedResult.get(i).getTimestamp()) &(Math.abs((Double.parseDouble((realResult.get(i).getValue()).toString()))-(Double.parseDouble(expectedResult.get(i).getValue().toString()))) )<0.01){ 
                    System.out.println();
                    System.out.println(realResult.get(i).getTimestamp() +": "+ realResult.get(i).getValue()+"      "+expectedResult.get(i).getTimestamp()+": "+expectedResult.get(i).getValue() +"---->match!"); 
                }else {
                    System.out.println();
                    System.out.println(realResult.get(i).getTimestamp() +": "+ realResult.get(i).getValue()+"      "+expectedResult.get(i).getTimestamp()+": "+expectedResult.get(i).getValue() +"---->not match!");
                    sign=true;
                }
            }
        }
        if (sign)
           System.out.println("The test failed");
        else
            System.out.println("The test successed");
        System.out.println("");
        System.out.println("Test finished");
        System.out.println("");
    }
    
    //fertig
    public void compare(double x1, double x2){
        System.out.println("Real Result:       Expected Result:");
        if ((Math.abs(x1-x2)>0.01))
        {
            System.out.println(x1+"               "+x2 +"---->not match");
            System.out.println("");
            System.out.println("the Result doesn't match, test failed");
            System.out.println("");
                  System.exit(0);
        }        
        else{
           System.out.println(x1+"                  "+x2 +"----->match");
           System.out.println("Successful!!");
        }
    }
    

    //fertig
    public List<JEVisSample> multiplicationTestOnline(JEVisAttribute f1, JEVisAttribute f2, JEVisAttribute e) throws ParseException, JEVisException {

        List<JEVisSample> factor1 = new ArrayList();
        List<JEVisSample> factor2 = new ArrayList();
        List<JEVisSample> realResult = new ArrayList();
        List<JEVisSample> expectedResult = new ArrayList();
        factor1 = f1.getAllSamples();
        factor2 = f2.getAllSamples();
        expectedResult = e.getAllSamples();
        boolean find = false;
        DecimalFormat df = new DecimalFormat("####.000");
        for (Object o1 : factor1) {
            loop:
            for (Object o2 : factor2) {
                find = false;
                //compare the timestample,when equals, then multip, when not equals, then find next.
                if (((JEVisSample) o1).getTimestamp().equals(((JEVisSample) o2).getTimestamp())) {
                    JEVisSample sample = f1.buildSample(((JEVisSample) o1).getTimestamp(), df.parse(df.format(((JEVisSample) o1).getValueAsDouble() * ((JEVisSample) o2).getValueAsDouble())), "CSV Import by Sys Admin");
                    realResult.add(sample);
                    find = true;
                    break loop;
                }
            }
            if (find == false) {
                JEVisSample sample = f1.buildSample(((JEVisSample) o1).getTimestamp(), df.parse(df.format(((JEVisSample) o1).getValueAsDouble())), "CSV Import by Sys Admin");
                realResult.add(sample);
            }
        }
        onlineDataCompare(realResult, expectedResult);
        return realResult;
    }

    
   

    public List<JEVisSampleTest2> multiplicationTestLocal(String factorpath1, String factorpath2, String expectedResultPath) throws IOException, ParseException, JEVisException {

        List<JEVisSampleTest2> factor1 = new ArrayList();
        List<JEVisSampleTest2> factor2 = new ArrayList();
        List<JEVisSampleTest2> expectedResult = new ArrayList();
        factor1 =this.getFile(factorpath1);
        factor2 = this.getFile(factorpath2);
        expectedResult = this.getFile(expectedResultPath);
        System.out.println("Factor1");
        resultOutput(factor1);
        System.out.println("Factor2");
        resultOutput(factor2);
       List<JEVisSampleTest2> result = new ArrayList();
        boolean find = false;
        for (Object o1 : factor1) {
            loop:
            for (Object o2 : factor2) {
                find = false;
                if (((JEVisSampleTest2) o1).getTimestamp().equals(((JEVisSampleTest2) o2).getTimestamp())) {
                    DateTime ts = ((JEVisSampleTest2) o1).getTimestamp();
                    Double value = ((JEVisSampleTest2) o1).getValue() * ((JEVisSampleTest2) o2).getValue();
                    JEVisSampleTest2 temp = new JEVisSampleTest2();
                    temp.setTimeStample(ts);
                    temp.setValue(value);
                    result.add(temp);
                    find = true;
                    break loop;
                }
            }
            if (find == false) {
                DateTime ts = ((JEVisSampleTest2) o1).getTimestamp();
                Double value = ((JEVisSample) o1).getValueAsDouble();
                JEVisSampleTest2 temp = new JEVisSampleTest2(ts, formater(value));
                result.add(temp);
            }
        }
        localDataCompare(result, expectedResult);
        return result;
    }

    
//local multiplication in this case: data row * sigle number    
//    public List<JEVisSampleTest2> multiSampleListTest(String factorpath1, double factor2, String expectedResultPath) throws IOException, ParseException, JEVisException {
//
//        List<JEVisSampleTest2> factor1 = new ArrayList();
//        
//        List<JEVisSampleTest2> expectedResult = new ArrayList();
//        factor1 = this.getFile(factorpath1);
//     
//        expectedResult = this.getFile(expectedResultPath);
//        System.out.println("Factor1");
//        resultOutput(factor1);
//        System.out.println("Factor2  :  "+ factor2);
   
//        List<JEVisSampleTest2> result = new ArrayList();
//        boolean find = false;
//
//        for (Object o1 : factor1) {
//                    DateTime ts = ((JEVisSampleTest2) o1).getTimeStample();
//                    Double value = ((JEVisSampleTest2) o1).getValue() *factor2;
//                    JEVisSampleTest2 temp = new JEVisSampleTest2(ts, formater(value));
//                    result.add(temp);
//                    find = true;
//            } 
//        compare(result, expectedResult);
//        return result;
//    }
//  
    
    public void resultOutput(List<JEVisSampleTest2> s) throws JEVisException {
        for (int i = 0; i < s.size(); i++) {
            System.out.println("samples ts:" + s.get(i).getTimestamp()+ "---->" + s.get(i).getValue());
        }
    }

    //fertig
    public List<JEVisSampleTest2> getFile(String Filename) throws IOException, ParseException, JEVisException {
        String csvFilename = Filename;
        CSVReader csvReader = new CSVReader(new FileReader(csvFilename), ';');
        List<String[]> content = csvReader.readAll();
        
           
        List<JEVisSampleTest2> samples = new ArrayList();
        for (String[] object : content) {
            try {
                DateTime ts = parseDate(object[0]);
            
                Double value = Double.parseDouble(object[1]);
   
                JEVisSampleTest2 temp = new JEVisSampleTest2();
                temp.setTimeStample(ts);
                temp.setValue(value);
          
                samples.add(temp);
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    
        return samples;
    }
    
    //fertig
    public static DateTime parseDate(String zeit) throws ParseException {

        SimpleDateFormat model = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        DateTime t = new DateTime(model.parse(zeit));
        return t;
    }

    

    //use Path to test
    public List<JEVisSampleTest2> divisionLocalDataTest(String factorpath1, String factorpath2, String expectedResultPath) throws ParseException, JEVisException, IOException {

        List<JEVisSampleTest2> dividend = new ArrayList();
        List<JEVisSampleTest2> divider = new ArrayList();
        List<JEVisSampleTest2> expectedResult = new ArrayList();
        List<JEVisSampleTest2> result = new ArrayList();
        dividend = this.getFile(factorpath1);
        divider = this.getFile(factorpath2);
        expectedResult = this.getFile(expectedResultPath);
        System.out.println("Dividend");
        resultOutput(dividend);
        System.out.println("Divider");
        resultOutput(divider);

        if (dividend.size() != divider.size()) {
            System.out.println("the size of two samples are not same");
            System.exit(0);
        }

        for (JEVisSampleTest2 o1 : dividend) {
            loop:
            for (JEVisSampleTest2 o2 : divider) {
                //compare the timestample,when equals, then divide, when not equals, then find next.
                if ( o1.getTimestamp().equals(o2.getTimestamp())) {
                    if (o2.getValue() != 0) {
                        
                    DateTime ts = o1.getTimestamp();
                    Double value = o1.getValue() / o2.getValue();
                    JEVisSampleTest2 temp = new JEVisSampleTest2(ts, formater(value));
                    result.add(temp);
                    break loop;
                    } else {
                        System.out.println("falsch!!the divider shouldn't be 0!");
                        System.out.println("exit the Test!");
                        System.exit(0);
                    }
                }
            }
        }
        localDataCompare(result, expectedResult);
        return result;
    }
    
    
    //use ID to test
    public List<JEVisSample> divisionOnlineDataTest(JEVisAttribute f1, JEVisAttribute f2, JEVisAttribute e) throws ParseException, JEVisException, IOException {

        List<JEVisSample> dividend = new ArrayList();
        List<JEVisSample> divider = new ArrayList();
          List<JEVisSample> result = new ArrayList();
        List<JEVisSample> expectedResult = new ArrayList();

        dividend = f1.getAllSamples();
        divider = f2.getAllSamples();
        expectedResult = e.getAllSamples();
//        System.out.println("Dividend");
//        resultOutput(dividend);
//        System.out.println("Divider");
//        resultOutput(divider);

        if (dividend.size() != divider.size()) {
            System.out.println("the size of two samples are not same, exit the test");
            System.exit(0);
        }

        for (JEVisSample o1 : dividend) {
            loop:
            for (JEVisSample o2 : divider) {
                //compare the timestample,when equals, then divide, when not equals, then find next.
                if ( o1.getTimestamp().equals(o2.getTimestamp())) {
                    if (o2.getValueAsDouble() != 0) {
                        
                    DateTime ts = o1.getTimestamp();
                    Double value = o1.getValueAsDouble() / o2.getValueAsDouble();
                    JEVisSample temp = f1.buildSample(ts, formater(value));
                    result.add(temp);
                    break loop;
                    } else {
                        System.out.println("falsch!!the divider shouldn't be 0!");
                        System.out.println("exit the Test!");
                        System.exit(0);
                    }
                }
            }
        }
        onlineDataCompare(result, expectedResult);
        return result;
    }

    
   
    public void getAverageValueTestLocal(String path, double expectedAverage) throws JEVisException, IOException, ParseException {
        List<JEVisSampleTest2> toBeTested = new ArrayList();
        toBeTested=getFile(path);
        double averagevalue = 0;
        for (JEVisSampleTest2 o : toBeTested) {
            averagevalue = averagevalue + o.getValue();
        }
        compare((averagevalue / toBeTested.size()),expectedAverage);
    }
    
    
    public void getAverageValueTestOnline(JEVisAttribute f, double expectedAverage) throws JEVisException, IOException, ParseException {
        List<JEVisSample> toBeTested = new ArrayList();
        toBeTested=f.getAllSamples();
        double averagevalue = 0;
        for (JEVisSample o : toBeTested) {
            averagevalue = averagevalue + o.getValueAsDouble();
        }

        compare((averagevalue / toBeTested.size()),expectedAverage);
    }
    
    
    public double getAverageValue(JEVisAttribute f) throws JEVisException, IOException, ParseException {
        List<JEVisSample> toBeTested = new ArrayList();
        toBeTested=f.getAllSamples();
        double averagevalue = 0;
        for (JEVisSample o : toBeTested) {
            averagevalue = averagevalue + o.getValueAsDouble();
        }

        return (averagevalue / toBeTested.size());
    }
    
    public double getAverageValue(String path) throws JEVisException, IOException, ParseException {
        List<JEVisSampleTest2> toBeTested = new ArrayList();
        toBeTested=getFile(path);
        double averagevalue = 0;
        for (JEVisSampleTest2 o : toBeTested) {
            averagevalue = averagevalue + o.getValue();
        }
    return (averagevalue / toBeTested.size());
    }
    
    public double getAverageValueTest(String path) throws JEVisException, IOException, ParseException {
        List<JEVisSampleTest2> toBeTested = new ArrayList();
        toBeTested=getFile(path);
        double averagevalue = 0;
        for (JEVisSampleTest2 o : toBeTested) {
            averagevalue = averagevalue + o.getValue();
        }
       
        return (averagevalue / toBeTested.size());
    }
    

    //fertig
    public double getMaxValueTestLocal(String path, double expectedMax) throws JEVisException, IOException, ParseException {
       List<JEVisSampleTest2> toBeTested = new ArrayList();
       toBeTested=getFile(path);
       double max_value = 0;
        for (JEVisSampleTest2 o : toBeTested) {
            max_value = (max_value > o.getValue())? max_value :  o.getValue();
        }
        compare(max_value,expectedMax);
        return max_value;
    }
    
    
    //fertig
    public double getMaxValueTestOnline(JEVisAttribute myAtt1, double e) throws JEVisException {
      
        double max_value = 0;
        List<JEVisSample> temp = new ArrayList();
        temp.addAll(myAtt1.getAllSamples());
        for (Object o : temp) {
            max_value = (max_value > ((JEVisSample) o).getValueAsDouble()) ? max_value : ((JEVisSample) o).getValueAsDouble();
        }
        compare(max_value,e);
        
        return max_value;
    }
    
    
    //Mean by path to test
    public double meanDeviationTestLocal(String path, double expectedMeanValue) throws JEVisException, IOException, ParseException {
        double temp;
        double tempmean = 0;
        temp = this.getAverageValueTest(path);
       List<JEVisSampleTest2> toBeTested = new ArrayList();
       toBeTested=getFile(path);
 
        for (JEVisSampleTest2 o : toBeTested) {
            tempmean = tempmean + Math.abs(o.getValue() - temp);
        }
  
        compare(expectedMeanValue,(tempmean / toBeTested.size()));
        return tempmean / toBeTested.size();
    }

    
    //Mean by ID to test
    public double meanDeviationTestOnline(JEVisAttribute f1,double expectedMeanValue) throws JEVisException, IOException, ParseException {
        double temp;
        double tempmean = 0;
        temp = this.getAverageValue(f1);
       List<JEVisSample> toBeTested = new ArrayList();
       toBeTested.addAll(f1.getAllSamples());
 
        for (JEVisSample o : toBeTested) {
            tempmean = tempmean + Math.abs(o.getValueAsDouble() - temp);
        }
  
        compare((tempmean / toBeTested.size()),expectedMeanValue);
        return tempmean / toBeTested.size();
    }
    
    //Mean by ID to test
    public List<JEVisSample> addShiftTime(JEVisAttribute myAtt1, int shiftTime) throws ParseException, JEVisException {

        List<JEVisSample> temp = new ArrayList();
        List<JEVisSample> result = new ArrayList();
        temp.addAll(myAtt1.getAllSamples());
        for (Object o : temp) {
            JEVisSample sample = myAtt1.buildSample(((JEVisSample) o).getTimestamp().plusSeconds(shiftTime), ((JEVisSample) o).getValueAsDouble(), "finish");
            result.add(sample);
        }
        return result;
    }
    
//shift-time by path to test 
     public List<JEVisSampleTest2> addShiftTimeTestLocal(String path1, int shiftTime, String path2) throws ParseException, JEVisException, IOException {
         List<JEVisSampleTest2> result = new ArrayList();
        List<JEVisSampleTest2> toBeTested = new ArrayList();
         List<JEVisSampleTest2> expectedResult = new ArrayList();
         
        toBeTested =getFile(path1);
        expectedResult=getFile(path2);
        
        for (JEVisSampleTest2 o : toBeTested) {
                    DateTime ts = o.getTimestamp().plusSeconds(shiftTime);
                    
                    Double value = o.getValue();
            JEVisSampleTest2 temp = new JEVisSampleTest2(ts, formater(value));
             result.add(temp);
        }
        localDataCompare(result,expectedResult );
        return  result;
    }


//shiftTime by ID to Test
     public List<JEVisSample> addShiftTimeTestOnline(JEVisAttribute f, int shiftTime,JEVisAttribute e) throws ParseException, JEVisException, IOException {
         List<JEVisSample> result = new ArrayList();
        List<JEVisSample> toBeTested = new ArrayList();
         List<JEVisSample> expectedResult = new ArrayList();
         
        toBeTested.addAll(f.getAllSamples());
        expectedResult.addAll(e.getAllSamples());
        
        for (JEVisSample o : toBeTested) {
                    DateTime ts = o.getTimestamp().plusSeconds(shiftTime);
                    
                    Double value = o.getValueAsDouble();
            JEVisSample temp =f.buildSample(ts, formater(value));
             result.add(temp);
        }
        onlineDataCompare(result,expectedResult );
        return  result;
    }
    
     
    public List<JEVisSample> lowPassFilter(JEVisAttribute myAtt1, double setNumber) throws ParseException, JEVisException {
        List<JEVisSample> temp = new ArrayList();
        List<JEVisSample> result = new ArrayList();
        temp.addAll(myAtt1.getAllSamples());
        for (Object o : temp) {
            if (((JEVisSample) o).getValueAsDouble() < setNumber) {
                JEVisSample sample = myAtt1.buildSample(((JEVisSample) o).getTimestamp(), setNumber, "finish");
                result.add(sample);
            } else {
                JEVisSample sample = myAtt1.buildSample(((JEVisSample) o).getTimestamp(), ((JEVisSample) o).getValueAsDouble(), "finish");
                result.add(sample);
            }
        }
        return result;
    }

    
    //use path to test
     public List<JEVisSampleTest2> lowPassFilterTestLocal(String path1, double setFilterValue, String path2) throws ParseException, JEVisException, IOException {
        List<JEVisSampleTest2> result = new ArrayList();
        List<JEVisSampleTest2> toBeTested = new ArrayList();
        List<JEVisSampleTest2> expectedResult = new ArrayList();
         toBeTested =getFile(path1);
        expectedResult=getFile(path2);
        for (JEVisSampleTest2 o : toBeTested) {
            if (o.getValue() < setFilterValue) {
                DateTime ts=o.getTimestamp();
                Double value =o.getValue();
                JEVisSampleTest2 temp = new JEVisSampleTest2(ts, formater(value));
                result.add(temp);
                
            } else {
                DateTime ts=o.getTimestamp();
                Double value =setFilterValue;
                JEVisSampleTest2 temp = new JEVisSampleTest2(ts, formater(value));
                result.add(temp);
            }
        }
        localDataCompare(result,expectedResult );
        return result;
    }
//  use path to test   
    
    
    
    //use ID to test
    public List<JEVisSample> lowPassFilterTestOnline(JEVisAttribute f, double setFilterValue, JEVisAttribute e) throws ParseException, JEVisException, IOException {
        List<JEVisSample> toBeTested = new ArrayList();
        List<JEVisSample> result = new ArrayList();
        List<JEVisSample> expectedResult = new ArrayList();
         toBeTested =f.getAllSamples();
        expectedResult=e.getAllSamples();
        for (JEVisSample o : toBeTested) {
            if (o.getValueAsDouble() < setFilterValue) {
                DateTime ts=o.getTimestamp();
                Double value =o.getValueAsDouble();
                JEVisSample temp =f.buildSample(ts, formater(value));
                result.add(temp);
                
            } else {
                DateTime ts=o.getTimestamp();
                Double value =setFilterValue;
                JEVisSample temp = f.buildSample(ts,formater(value));
                result.add(temp);
            }
        }
        onlineDataCompare(result,expectedResult);
        return result;
    }
   
     
        public List<JEVisSample> splitValuesTestOnline(JEVisAttribute f, int setSplitNumber, JEVisAttribute e) throws ParseException, JEVisException, IOException {
        List<JEVisSample> toBeTested = new ArrayList();
        List<JEVisSample> result = new ArrayList();
        List<JEVisSample> expectedResult = new ArrayList();

        toBeTested.addAll(f.getAllSamples());
        expectedResult.addAll(e.getAllSamples());

        int period = 900;
        int j = 1;
        for (JEVisSample o : toBeTested) {
                    DateTime ts2=o.getTimestamp();
                    Double value2 =o.getValueAsDouble()/(setSplitNumber+ 1);
                    JEVisSample temp2 = f.buildSample(ts2, formater(value2));
                    result.add(temp2);
            for (j = 1; j <= setSplitNumber; j++) {
                    
                if (result.size() != ((setSplitNumber + 1) * toBeTested.size() + 1)) {
                    DateTime ts1=o.getTimestamp().plusSeconds((j) * period / (setSplitNumber + 1));
                    Double value1 =o.getValueAsDouble()/(setSplitNumber+ 1);
                    JEVisSample temp1 = f.buildSample(ts1, formater(value1));
                    result.add(temp1);
      }
            }
        }
            System.out.println(result.size());
            System.out.println(expectedResult.size());
         onlineDataCompare(result,expectedResult );
        return result;
    }
     
     public List<JEVisSampleTest2> splitValuesTestLocal(String path1, int setSplitNumber, String path2) throws ParseException, JEVisException, IOException {
        List<JEVisSampleTest2> result = new ArrayList();
        List<JEVisSampleTest2> toBeTested = new ArrayList();
        List<JEVisSampleTest2> expectedResult = new ArrayList();
        toBeTested =getFile(path1);
        expectedResult=getFile(path2);

        int period = 900;
        int j = 1;
        for (JEVisSampleTest2 o : toBeTested) {
                    DateTime ts2=o.getTimestamp();
                    Double value2 =o.getValue()/(setSplitNumber+ 1);
                    JEVisSampleTest2 temp2 = new JEVisSampleTest2(ts2, formater(value2));
                    result.add(temp2);
            for (j = 1; j <= setSplitNumber; j++) {
                    
                if (result.size() != ((setSplitNumber + 1) * toBeTested.size() + 1)) {
                    DateTime ts1=o.getTimestamp().plusSeconds((j) * period / (setSplitNumber + 1));
                    Double value1 =o.getValue()/(setSplitNumber+ 1);
                    JEVisSampleTest2 temp1 = new JEVisSampleTest2(ts1, formater(value1));
                    result.add(temp1);
       
//                    from Floria
//                    myAtt1.getPeriod().getSeconds();
//                    sample.getTimestamp().plus(myAtt1.getPeriod());
          
                }
            }
        }
         localDataCompare(result,expectedResult );
        return result;
    }
//     
     

     
//derivation by ID to test           
         public List<JEVisSample> derivationTestOnline(JEVisAttribute f,JEVisAttribute e) throws ParseException, JEVisException, IOException {
         List<JEVisSample> result = new ArrayList();
         List<JEVisSample> toBeTested = new ArrayList();
         List<JEVisSample> expectedResult = new ArrayList();
         toBeTested.addAll(f.getAllSamples());
         expectedResult.addAll(e.getAllSamples());
             //problem: how to read period automatically
         int period = 900;
        for (JEVisSample o : toBeTested) {
            if (result.size() < (toBeTested.size() - 1)) {
                DateTime ts=o.getTimestamp();
                Double value =((toBeTested.get(toBeTested.indexOf(o) + 1).getValueAsDouble() -  o.getValueAsDouble()) / period);
                JEVisSample temp = f.buildSample(ts, formater(value));
                result.add(temp);
            }
        }
        onlineDataCompare(result,expectedResult );
        return (result);
    }

         
//derivation by path to test        
         public List<JEVisSampleTest2> derivationTestLocal(String path1,String path2) throws ParseException, JEVisException, IOException {
         List<JEVisSampleTest2> result = new ArrayList();
         List<JEVisSampleTest2> toBeTested = new ArrayList();
         List<JEVisSampleTest2> expectedResult = new ArrayList();
         toBeTested =getFile(path1);
         expectedResult=getFile(path2);
             //problem: how to read period automatically
         int period = 900;
        for (JEVisSampleTest2 o : toBeTested) {
            if (result.size() < (toBeTested.size() - 1)) {
                DateTime ts=o.getTimestamp();
                Double value =((toBeTested.get(toBeTested.indexOf(o) + 1).getValue()-o.getValue()) / period);
                JEVisSampleTest2 temp = new JEVisSampleTest2(ts, formater(value));
                result.add(temp);
            }
        }
        localDataCompare(result,expectedResult );
        return (result);
    }


//Interpolation by ID to Test 
 public List<JEVisSample> interpLinearTestOnline(JEVisAttribute f, int numberOfInsertPoint, JEVisAttribute e) throws ParseException, JEVisException, IOException {
        List<JEVisSample> result = new ArrayList();
        List<JEVisSample> toBeTested = new ArrayList();
        List<JEVisSample> expectedResult = new ArrayList();
        toBeTested.addAll(f.getAllSamples());
        expectedResult.addAll(e.getAllSamples());

        int period = 900;
    
        for (JEVisSample o : toBeTested) {
                    DateTime ts2=o.getTimestamp();
                    Double value2 =o.getValueAsDouble();
                   JEVisSample temp2 = f.buildSample(ts2, formater(value2));
                    result.add(temp2);
                    if (toBeTested.indexOf(o)!=(toBeTested.size()-1)){
            for (int j = 1; j <= numberOfInsertPoint; j++) {
                if (result.size() != (numberOfInsertPoint * (toBeTested.size() - 1))) {
                    
                    DateTime ts1=o.getTimestamp().plusSeconds((j) * period / (numberOfInsertPoint + 1));
                    Double value1 = o.getValueAsDouble() + j*((toBeTested.get(toBeTested.indexOf(o) + 1).getValueAsDouble()-o.getValueAsDouble()) / (numberOfInsertPoint + 1));
                    JEVisSample temp1 = f.buildSample(ts1, formater(value1));
                    result.add(temp1);
                }
            }
            }
        }
         onlineDataCompare(result,expectedResult );
        return result;
    }
//Interpolation by ID to Test
// 
 
//Interpolation by path to Test
 public List<JEVisSampleTest2> interpLinearTestLocal(String path1, int setSplitNumber, String path2) throws ParseException, JEVisException, IOException {
        List<JEVisSampleTest2> result = new ArrayList();
        List<JEVisSampleTest2> toBeTested = new ArrayList();
        List<JEVisSampleTest2> expectedResult = new ArrayList();
        toBeTested =getFile(path1);
        expectedResult=getFile(path2);

        int period = 900;
    
        for (JEVisSampleTest2 o : toBeTested) {
                    DateTime ts2=o.getTimestamp();
                    Double value2 =o.getValue();
                    JEVisSampleTest2 temp2 = new JEVisSampleTest2(ts2, formater(value2));
                    result.add(temp2);
                    if (toBeTested.indexOf(o)!=(toBeTested.size()-1)){
            for (int j = 1; j <= setSplitNumber; j++) {
                if (result.size() != (setSplitNumber * (toBeTested.size() - 1))) {
                    
                    DateTime ts1=o.getTimestamp().plusSeconds((j) * period / (setSplitNumber + 1));
                    Double value1 = o.getValue() + j*((toBeTested.get(toBeTested.indexOf(o) + 1).getValue()-o.getValue()) / (setSplitNumber + 1));
                    JEVisSampleTest2 temp1 = new JEVisSampleTest2(ts1, formater(value1));
                    result.add(temp1);

                }
            }
            }
        }
         localDataCompare(result,expectedResult );
        return result;
    }
//Interpolation by path to Test
// 
 
 
 
 
 //    public List<JEVisSample> splitValues(JEVisAttribute myAtt1, int theNumWantedToBe) throws ParseException, JEVisException {
//
//        List<JEVisSample> temp = new ArrayList();
//        List<JEVisSample> result = new ArrayList();
//        temp.addAll(myAtt1.getAllSamples());
//        int period = 900;
//        int j = 1;
//        for (Object o : temp) {
//            for (j = 1; j <= theNumWantedToBe; j++) {
//                if (result.size() != ((theNumWantedToBe + 1) * (myAtt1).getSampleCount() + 1)) {
//                    JEVisSample sample1 = myAtt1.buildSample(((JEVisSample) o).getTimestamp().plusSeconds((j) * period / (theNumWantedToBe + 1)), ((JEVisSample) o).getValueAsDouble() / (theNumWantedToBe + 1), "finish");
//                    JEVisSample sample2 = myAtt1.buildSample(((JEVisSample) o).getTimestamp(), ((JEVisSample) o).getValueAsDouble() / (theNumWantedToBe + 1), "finish");
////                    from Floria
////                    myAtt1.getPeriod().getSeconds();
////                    sample.getTimestamp().plus(myAtt1.getPeriod());
//                    result.add(sample1);
//                    result.add(sample2);
//                }
//            }
//        }
//        return result;
//    }
//
//    public List<JEVisSample> derivation(JEVisAttribute myAtt1) throws ParseException, JEVisException {
//        List<JEVisSample> temp = new ArrayList();
//        List<JEVisSample> result = new ArrayList();
//        temp.addAll(myAtt1.getAllSamples());
//        int period = 900;
//        for (Object o : temp) {
//            if (result.size() < (temp.size() - 1)) {
//                JEVisSample sample = myAtt1.buildSample(((JEVisSample) o).getTimestamp(), ((temp.get(temp.indexOf(o) + 1).getValueAsDouble() - ((JEVisSample) o).getValueAsDouble()) / period), "finish");
//                result.add(sample);
//            }
//        }
//        return (result);
//    }
// 
//    public SamplesBank differentialCumulativeConverter(SamplesBank cumulative, int period, double firstvalue) {
//        Map<DateTime, Double> temp = new LinkedHashMap<DateTime, Double>();
//        Set<Map.Entry<DateTime, Double>> sets = cumulative.sample.entrySet();
//        temp.put(cumulative.first, firstvalue);
//        for (Map.Entry<DateTime, Double> entry : sets) {
//            if (temp.size() < ((sample.size() + 1))) {
//                temp.put(entry.getKey().plusSeconds(period), (sample.get(entry.getKey().plusSeconds(period)) + entry.getValue()));
//            }
////            cumulative.sortByTime(temp);
//        }
//        return cumulative;
//    }
}
