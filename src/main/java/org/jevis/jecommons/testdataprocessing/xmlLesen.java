/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.jecommons.testdataprocessing;

import java.util.ArrayList;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jevis.api.JEVisAttribute;
import org.jevis.api.JEVisDataSource;
import org.jevis.api.JEVisException;
import org.jevis.api.JEVisObject;
import org.jevis.api.JEVisSample;
import org.jevis.api.sql.JEVisDataSourceSQL;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author gf
 */
public class xmlLesen {

    public Object paserXML(String args,String function, String sample) throws DocumentException {
        Object myAtt = null;
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(args);
            Element root = doc.getRootElement();
            List<org.dom4j.Element> param = root.elements();
            for (int j = 0; j < param.size(); j++) {
                Element node = (Element) param.get(j);
                myAtt=find(node,function, sample);
                if(myAtt!=null){
                    return myAtt;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myAtt;
    }

    private Object find(Element node,String function, String sample) throws JEVisException {
        if (node.getName().equals("INPUT") && node.attributeValue("function").equals(function) && node.attributeValue("name").equals(sample)) {
            List<org.dom4j.Element> param = node.elements();
            for (int i = 0; i < param.size(); i++) {
                if(param.get(i).getName().equals("Samples") && param.get(i).attributeValue("typ").equals("JEVis") || param.get(i).attributeValue("typ").equals("ID")){
                    return typJEVis(param.get(i),param.get(i).attributeValue("typ"));
                }else if(param.get(i).getName().equals("Samples") && param.get(i).attributeValue("typ").equals("Value")){
                    return typValue(param.get(i));
                }else if(param.get(i).getName().equals("Samples") && param.get(i).attributeValue("typ").equals("Time")){
                    return typTime(param.get(i));
                }
            }
        }else if(node.getName().equals("RESULT") && node.attributeValue("function").equals(function) && node.attributeValue("name").equals(sample)){
            List<org.dom4j.Element> param = node.elements();
            for (int j = 0; j < param.size(); j++) {
                if(param.get(j).getName().equals("Samples") && param.get(j).attributeValue("typ").equals("JEVis") || param.get(j).attributeValue("typ").equals("ID")){
                    return typJEVis(param.get(j),param.get(j).attributeValue("typ"));
                }else if(param.get(j).getName().equals("Samples") && param.get(j).attributeValue("typ").equals("Value")){
                    return typValue(param.get(j));
                }else if(param.get(j).getName().equals("Samples") && param.get(j).attributeValue("typ").equals("Time")){
                    return typTime(param.get(j));
                }
            }
        }
        return null;
    }

//    private JEVisAttribute nodeByNodes(Element node) {
//        JEVisAttributeTest myAtt = null;
//        List<JEVisSample> list = new ArrayList<JEVisSample>();
//
//        if (!node.getName().equals("Samples")) {
//            List<org.dom4j.Element> param = node.elements();
//            for (int j = 0; j < param.size(); j++) {
//                nodeByNodes(param.get(j));
//            }
//        } else {
//            for (Object e : node.elements()) {
//                Element elem = (Element) e;
//                double value = Double.parseDouble(elem.attributeValue("value"));
//                DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss Z");
//                DateTime time = formatter.parseDateTime(elem.attributeValue("ts"));
//                System.out.println(time + "-------*" + value);
//                JEVisSample sam = new JEVisSampleTest(time, value);
//                list.add(sam);
//            }
//            myAtt = new JEVisAttributeTest(list);
//            
//        }
//        return myAtt;
//    }
    
    private JEVisAttribute typJEVis(Element node, String typ) throws JEVisException {
        JEVisAttributeTest myAtt1 = null;
        JEVisAttribute myAtt2 = null;
        List<JEVisSample> list = new ArrayList<JEVisSample>();

//        if (!node.getName().equals("Samples")) {
//            List<org.dom4j.Element> param = node.elements();
//            for (int j = 0; j < param.size(); j++) {
//                nodeByNodes(param.get(j));
//            }
//        } else {
        if (typ.equals("JEVis")) {
            for (Object e : node.elements()) {
                Element elem = (Element) e;
                double value = Double.parseDouble(elem.attributeValue("value"));
                DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss Z");
                DateTime time = formatter.parseDateTime(elem.attributeValue("ts"));
//                System.out.println(time + "-------*" + value);
                JEVisSample sam = new JEVisSampleTest(time, value);
                list.add(sam);
            }
            myAtt1 = new JEVisAttributeTest(list);
            return myAtt1;
        }

        if (typ.equals("ID")) {
            long id;
            List<String> key_to_database = new ArrayList<String>();
            for (Object e : node.elements()) {
                Element elem = (Element) e;
                key_to_database.add(elem.getText());
                System.out.println(elem.getText());

            }
            JEVisDataSource ds = new JEVisDataSourceSQL(key_to_database.get(0), key_to_database.get(1), key_to_database.get(2), key_to_database.get(3), key_to_database.get(4),null,null);

            ds.connect(key_to_database.get(5), key_to_database.get(6));
            id = Long.parseLong(key_to_database.get(key_to_database.size() - 1));
            JEVisObject obj = ds.getObject(id);
            myAtt2 = obj.getAttribute("Value");
            return myAtt2;
        }
//        }
        return null;
    }
    
        private List<Double> typValue(Element node) {
        List<Double> list = new ArrayList<Double>();

//        if (!node.getName().equals("Samples")) {
//            List<org.dom4j.Element> param = node.elements();
//            for (int j = 0; j < param.size(); j++) {
//                nodeByNodes(param.get(j));
//            }
//        } else {
            for (Object e : node.elements()) {
                Element elem = (Element) e;
                double value = Double.parseDouble(elem.getText());
                list.add(value);
            }
            
//        }
        return list;
    }
        
        private List<DateTime> typTime(Element node) {
        List<DateTime> list = new ArrayList<DateTime>();

//        if (!node.getName().equals("Samples")) {
//            List<org.dom4j.Element> param = node.elements();
//            for (int j = 0; j < param.size(); j++) {
//                nodeByNodes(param.get(j));
//            }
//        } else {
            for (Object e : node.elements()) {
                Element elem = (Element) e;
                DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss Z");
                DateTime time = formatter.parseDateTime(elem.getText());
                list.add(time);
            }
            
//        }
        return list;
    }
   
}
