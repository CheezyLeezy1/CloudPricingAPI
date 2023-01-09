package com.mycompany.packagepricingsystem;

import javax.xml.bind.annotation.XmlRootElement;

/*
Learned something cool from an error I had for 2+ hours:
kept getting empty array of objects being returned from GET methods...
JSON can only serialise (return) a Package object if there is both getter and setters

ALSO, you need an empty constructor in object class or else you get errors:

[Exception [EclipseLink-50001] (Eclipse Persistence Services - 2.7.6.v20200131-b7c997804f):
org.eclipse.persistence.exceptions.JAXBException Exception Description:
The class com.mycompany.packagepricingsystem.Package requires a zero argument constructor or a specified factory method.
Note that non-static inner classes do not have zero argument constructors and are not supported.]
* */


@XmlRootElement
public class Package {
    private int id;
    private double price;
    private double discountFactor;

    public Package(){
    }

    public Package(int id, double price, double discountPercentage) {
        this.id = id;
        this.price = price;
        this.discountFactor = discountPercentage;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscountFactor() {
        return discountFactor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDiscountFactor(double discountFactor) {
        this.discountFactor = discountFactor;
    }

    @Override
    public String toString() {
    return "ID: " + id + ", PRICE: " + price + ", DISCOUNT: " + discountFactor;
    }

}
