package com.example.geocoder;

import java.util.List;

class GeocoderResponseMetaData
{
    public String request ;
    public String results ;
    public String found ;
}

 class MetaDataProperty
{
    public GeocoderResponseMetaData GeocoderResponseMetaData ;
}

 class Envelope
{
    public String lowerCorner ;
    public String upperCorner ;
}

 class BoundedBy
{
    public Envelope Envelope ;
}

 class Point
{
    public String pos ;
}

 class GeoObject
{
    public MetaDataProperty metaDataProperty;
    public String name ;
    public String description ;
    public BoundedBy boundedBy ;
    public Point Point ;
}

 class FeatureMember
{
    public GeoObject GeoObject ;
}

 class GeoObjectCollection
{
    public MetaDataProperty metaDataProperty ;
    public List<FeatureMember> featureMember ;
}

class Response
{
    public GeoObjectCollection GeoObjectCollection ;
}

public class RespYandex
{
    public Response response ;
}