package com.mycompany.packagepricingsystem;
/*

* Package: 1
Cost per gigabyte: 3 Euro
Discount factor: 0%

* Package: 2
Cost per gigabyte: 4 Euro
Discount factor: 5%

* Package: 3
Cost per gigabyte: 5 Euro
Discount factor: 10%

* Package: 4
Cost per gigabyte: 6 Euro
Discount factor: 15%


* Package: 5
Cost per gigabyte: 7 Euro
Discount factor: 20%

*/


import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@SuppressWarnings("ALL")
@Path("/packages")
public class StorageService {
    public static ArrayList<Package> packages = new ArrayList<Package>();


    static {

            Package pk1 = new Package(1, 1.00, 0.0);
            Package pk2 = new Package(2, 2.00, 0.05);
            Package pk3 = new Package(3, 3.25, 0.1);
            Package pk4 = new Package(4, 4.50, 0.15);
            Package pk5 = new Package(5, 6.50, 0.23);

            packages.add(pk1);
            packages.add(pk2);
            packages.add(pk3);
            packages.add(pk4);
            packages.add(pk5);

        }

    /*
    http://localhost:49000/api/packages
    allows both xml and json, however the accept-heading must be specified
    JSON is first option as is first media type when accept-heading is any
    */

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ArrayList<Package> getPackages() {
        System.out.println("Packages returned!");
        return packages;
    }

    /*
    * http://localhost:49000/api/packages/1
    * http://localhost:49000/api/packages/2
    * http://localhost:49000/api/packages/3
    * http://localhost:49000/api/packages/4
    * http://localhost:49000/api/packages/5
    *
    *
    * */

    @GET
    @Path("/{packageID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response returnJsonPackage(@PathParam("packageID") int packageID) {
        int actualIndex = packageID - 1;

        if (actualIndex < 0 || actualIndex >= packages.size()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Not Found - Package ID : " + packageID + "\nChoose Between 1 and " + packages.size() )
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }

        Package found = packages.get(actualIndex);
        System.out.println("Package Found : " + found);
        return Response.ok(found, MediaType.APPLICATION_JSON).build();
    }


    @GET
    @Path("/{packageID}")
    @Produces(MediaType.APPLICATION_XML)
    public Response returnXmlPackage(@PathParam("packageID") int packageID) {
        int actualIndex = packageID - 1;

        if (actualIndex < 0 || actualIndex >= packages.size()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Not Found - Package ID : " + packageID + "\nChoose Between 1 and " + packages.size() )
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }

        Package found = packages.get(actualIndex);
        System.out.println("Package Found : " + found);
        return Response.ok(found, MediaType.APPLICATION_XML).build();
    }

    /*
    http://localhost:49000/api/packages/usage/50
    http://localhost:49000/api/packages/usage/150
    http://localhost:49000/api/packages/usage/251
    http://localhost:49000/api/packages/usage/351
    http://localhost:49000/api/packages/usage/500
    http://localhost:49000/api/packages/usage/800
     */

    @GET
    @Path("/usage/{gigabytes}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStoragePriceJson(@PathParam("gigabytes") int gigabytes){
        Package pk;

        if(gigabytes <= 100){
        pk = packages.get(0);
        }

        else if(gigabytes <= 250){
        pk = packages.get(1);
        }

        else if(gigabytes <= 350){
        pk = packages.get(2);
        }

        else if(gigabytes <= 500){
        pk = packages.get(3);
        }

        else if(gigabytes > 500){
        pk = packages.get(4);
        }

        else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        int id = pk.getId();
        double price = pk.getPrice();
        double discountPercent = pk.getDiscountFactor();
        double result = gigabytes * (price / (1 + discountPercent) );

        String totalCost = String.format("%.2f", result);

        String response = "Package : " +id+ " TotalCost : " + totalCost;
        return Response.ok(response, MediaType.APPLICATION_JSON_TYPE).build();
    }


    @GET
    @Path("/usage/{gigabytes}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getStoragePriceXml(@PathParam("gigabytes") int gigabytes){
        Package pk;

        if(gigabytes <= 100){
            pk = packages.get(0);
        }

        else if(gigabytes <= 250){
            pk = packages.get(1);
        }

        else if(gigabytes <= 350){
            pk = packages.get(2);
        }

        else if(gigabytes <= 500){
            pk = packages.get(3);
        }

        else if(gigabytes > 500){
            pk = packages.get(4);
        }

        else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        int id = pk.getId();
        double price = pk.getPrice();
        double discountPercent = pk.getDiscountFactor();
        double result = gigabytes * (price / (1 + discountPercent) );

        String totalCost = String.format("%.2f", result);

        String response = "Package : " +id+ " TotalCost : " + totalCost;
        return Response.ok(response, MediaType.APPLICATION_XML).build();
    }

}
