/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.jecommons.testdataprocessing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class xmlLesenAcAtt {
    public Object paserXML(String path, String function, String param, String type) throws DocumentException {
        Object myAtt = null;
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(path);
            Element root = doc.getRootElement();
            List<Element> undernode = root.elements();
            for (int j = 0; j < undernode.size(); j++) {
                Element node = (Element) undernode.get(j);
                myAtt = find(node, function.toLowerCase(), param.toLowerCase());
                if (myAtt != null) {
                    return myAtt;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myAtt;
    }
    
    private Object find(Element node, String function, String param) throws JEVisException {

//        if(node.attributeCount()< 3){
//            if (!node.elements().isEmpty()) {
//            for (Object under : node.elements()) {
//                Element undernode = (Element) under;
//                if (find(undernode, function, param, type) != null) {
//                    return find(undernode, function, param, type);
//                }
//            }
//        }
//        }
        Map<String,String> attval = new HashMap<String,String>();
        for (int i = 0; i < node.attributeCount(); i++) {
            attval.put(node.attribute(i).getName().toLowerCase(), node.attribute(i).getValue().toLowerCase());
//            attval.add(node.attribute(i).getValue().toLowerCase());
        }
        if (attval.containsKey("function") && attval.containsKey("param") && attval.containsKey("type") && attval.get("function").equals(function) && attval.get("param").equals(param)) {
            if (attval.get("type").equals("jevis")) {
                return typJEVis(node, "jevis");
            }
            if (attval.get("type").equals("id")) {
                return typJEVis(node, "id");
            }
            if (attval.get("type").equals("value")) {
                return typValue(node);
            }
            if (attval.get("type").equals("time")) {
                return typTime(node);
            }
            if (attval.get("type").equals("trueorfalse")) {
                return typTrueOrFalse(node);
            }
        } else if (!node.elements().isEmpty()) {
            for (Object under : node.elements()) {
                Element undernode = (Element) under;
                if (find(undernode, function, param) != null) {
                    return find(undernode, function, param);
                }
            }
        }
//        }
        return null;
    }
    
    private JEVisAttribute typJEVis(Element node, String type) throws JEVisException {
        JEVisAttributeTest myAtt1 = null;
        JEVisAttribute myAtt2 = null;
        List<JEVisSample> list = new ArrayList<JEVisSample>();

        if (type.equals("jevis")) {
            for (Object e : node.elements()) {
                Element elem = (Element) e;
                double value = Double.parseDouble(elem.attributeValue("value"));
                DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss Z");
                DateTime time = formatter.parseDateTime(elem.attributeValue("ts"));
                JEVisSample sam = new JEVisSampleTest(time, value);
                list.add(sam);
            }
            myAtt1 = new JEVisAttributeTest(list);
            return myAtt1;
        }

        if (type.equals("id")) {
            long id;
            List<String> key_to_database = new ArrayList<String>();
            for (Object e : node.elements()) {
                Element elem = (Element) e;
                key_to_database.add(elem.getText());
                System.out.println(elem.getText());

            }
            try {
                JEVisDataSource ds = new JEVisDataSourceSQL(key_to_database.get(0), key_to_database.get(1), key_to_database.get(2), key_to_database.get(3), key_to_database.get(4));
                ds.connect(key_to_database.get(5), key_to_database.get(6));
                id = Long.parseLong(key_to_database.get(key_to_database.size() - 1));

                JEVisObject obj = ds.getObject(id);
                myAtt2 = obj.getAttribute("Value");

            } catch (Exception je) {
                System.out.println(je.getMessage());
            }
            return myAtt2;
        }
        return null;
    }

    private List<Double> typValue(Element node) {
        List<Double> list = new ArrayList<Double>();

        for (Object e : node.elements()) {
            Element elem = (Element) e;
            double value = Double.parseDouble(elem.getText());
            list.add(value);
        }

        return list;
    }

    private List<DateTime> typTime(Element node) {
        List<DateTime> list = new ArrayList<DateTime>();

        for (Object e : node.elements()) {
            Element elem = (Element) e;
            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss Z");
            DateTime time = formatter.parseDateTime(elem.getText());
            list.add(time);
        }

        return list;
    }
        
    private List<Boolean> typTrueOrFalse(Element node) {
        List<Boolean> list = new ArrayList<Boolean>();

        for (Object e : node.elements()) {
            Element elem = (Element) e;
            Boolean truefalse = Boolean.parseBoolean(elem.getText());
            list.add(truefalse);
        }
        return list;
    }
}
