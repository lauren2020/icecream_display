package com.example.icecreamdisplay.ui

class Flavor {
    var id: Int = 0
    var name: String = "Test Name"
    var description: String = "Test Description"
    var image: String = "Picture Here"
    var nutrition: String = "Nutrition Here"

    companion object {
        fun newInstance(id: Int, name: String) : Flavor {
            var flavor = Flavor()
            flavor.name = name
            flavor.id = id
            return flavor
        }
    }
}