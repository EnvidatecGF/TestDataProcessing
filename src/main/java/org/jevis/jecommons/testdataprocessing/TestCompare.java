/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.jecommons.testdataprocessing;

import java.util.List;
import org.jevis.api.JEVisAttribute;
import org.jevis.api.JEVisException;
import org.jevis.api.JEVisSample;
import org.joda.time.DateTime;

/**
 *
 * @author gf
 */
public class TestCompare {
public boolean compareJEVis(JEVisAttribute expRe,List<JEVisSample> re) throws JEVisException{
        List<JEVisSample> result = re;
        List<JEVisSample> expResult = expRe.getAllSamples();
        int size = 0;
//        System.out.println(re.size());
//            System.out.println(expResult.size());
        if(result.size()==expResult.size()){
            size=result.size();
        }else{
            System.out.println("the length of the calculated result and the excepted result is not same,it is absolutely false!!");
            return false;
        }
        for (int i=0;i<size;i++){
            if(!result.get(i).getTimestamp().equals(expResult.get(i).getTimestamp()) || !result.get(i).getValueAsString().equals(expResult.get(i).getValueAsString())){
                System.out.println("*************************************************False");
//                System.out.println(result.get(i).getTimestamp()+"!!!!!"+expResult.get(i).getTimestamp());
//                System.out.println(result.get(i).getValueAsString()+"!!!!!"+expResult.get(i).getValueAsString());
                return false;
            }
        }
        System.out.println("*************************************************true");
        return true;
    }

    public boolean compareValue(double value1,double value2){
        if(value1 != value2){
            System.out.println("*************************************************False");
            return false;
        }
        System.out.println("*************************************************true");
        return true;
    }
    
    public boolean compareTime(List<DateTime> expRe,List<DateTime> re){
        int size = 0;
        if(re.size()==expRe.size()){
            size=re.size();
        }else{
            System.out.println("the length of the calculated result and the excepted result is not same,it is absolutely false!!");
            return false;
        }
        for (int i=0;i<size;i++){
            if(!re.get(i).equals(expRe.get(i))){
                System.out.println("*************************************************False");
                return false;
            }
        }
        System.out.println("*************************************************true");
        return true;
    }
}
