package com.example.firebasefirestore.model

class User {
    var name: String? = null
    var age: Int? = null
    var id: String? = null

    constructor()

    constructor(name: String?, age: Int?, id: String?) {
        this.name = name
        this.age = age
        this.id = id
    }

    override fun toString(): String {
        return "User(name=$name, age=$age, id=$id)"
    }


}