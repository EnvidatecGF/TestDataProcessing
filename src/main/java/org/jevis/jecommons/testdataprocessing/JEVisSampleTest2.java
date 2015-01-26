/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.jecommons.testdataprocessing;

import org.joda.time.DateTime;

/**
 *
 * @author gf
 */
public class JEVisSampleTest2 {
    private DateTime TimeStample;
     private Double value;

    public void setTimeStample(DateTime time) {
        this.TimeStample = time;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public DateTime getTimestamp() {
        return TimeStample;
    }

    public Double getValue() {
        return value;
    }

  

    
     public JEVisSampleTest2(DateTime x, Double y){
         x=this.TimeStample;
         y=this.value;      
     }
   
     public JEVisSampleTest2(){
     this.TimeStample=null;
     this.value=0.0;}
}
