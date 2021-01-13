package com.veselove.xmlparser;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

public class SmartphoneResourceParser {

    private ArrayList<Smartphone> smartphones;

    public SmartphoneResourceParser(){
        smartphones = new ArrayList<>();
    }

    public ArrayList<Smartphone> getSmartphones(){
        return smartphones;
    }

    public boolean parse(XmlPullParser xpp){
        boolean status = true;
        Smartphone currentSmartphone = null;
        boolean inEntry = false;
        String textValue = "";

        try{
            int eventType = xpp.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT){
                String tagName = xpp.getName();
                switch (eventType){
                    case XmlPullParser.START_TAG:
                    if("smartphone".equalsIgnoreCase(tagName)){
                        inEntry = true;
                        currentSmartphone = new Smartphone();
                    }
                    break;
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if(inEntry){
                            if("smartphone".equalsIgnoreCase(tagName)){
                                smartphones.add(currentSmartphone);
                                inEntry = false;
                            } else if("brand".equalsIgnoreCase(tagName)){
                                currentSmartphone.setBrand(textValue);
                            } else if("modelName".equalsIgnoreCase(tagName)){
                                currentSmartphone.setModelName(textValue);
                            } else if("operatingSystem".equalsIgnoreCase(tagName)){
                                currentSmartphone.setOperatingSystem(textValue);
                            } else if("internalMemory".equalsIgnoreCase(tagName)){
                                currentSmartphone.setInternalMemory(textValue);
                            } else if("ramMemory".equalsIgnoreCase(tagName)){
                                currentSmartphone.setRamMemory(textValue);
                            } else if("batteryCapacity".equalsIgnoreCase(tagName)){
                                currentSmartphone.setBatteryCapacity(textValue);
                            }
                        }
                        break;
                    default:
                }
                eventType = xpp.next();
            }
        }
        catch (Exception e){
            status = false;
            e.printStackTrace();
        }
        return status;
    }
}
