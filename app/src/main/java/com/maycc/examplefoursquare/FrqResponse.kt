package com.maycc.examplefoursquare

class FrqResponse(var response: Response)

class Response(var venues: ArrayList<Venue>)

class Venue(var id: String, var name: String, var categories: ArrayList<Category>)

class Category(var id: String, var icon: Icon)

class Icon(var prefix: String, var suffix: String)