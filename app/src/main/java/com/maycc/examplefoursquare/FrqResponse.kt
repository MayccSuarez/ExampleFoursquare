package com.maycc.examplefoursquare

class FrqResponse(var response: Response)

class Response(var venues: ArrayList<Venue>)

class Venue(var id: String, var name: String)
